package org.jugru.filetodb.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jugru.filetodb.mapper.BookMapper;
import org.jugru.filetodb.model.CSVBook;
import org.jugru.filetodb.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @SneakyThrows
    public void saveBooksFromCsv(InputStream inputStream) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

            List<CSVBook> beans = new CsvToBeanBuilder<CSVBook>(inputStreamReader)
                    .withType(CSVBook.class)
                    .build()
                    .parse();

            bookRepository.saveAll(bookMapper.toBook(beans));
        }


    }

    @SneakyThrows
    public String getAllBooksAsCsvString() {

        try (Writer writer = new StringWriter()) {
            HeaderColumnNameMappingStrategy<CSVBook> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(CSVBook.class);
            strategy.setColumnOrderOnWrite(new CSVBookHeadersComparator());

            List<CSVBook> books = bookMapper.toCSVBooks(bookRepository.findAll());
            StatefulBeanToCsv<CSVBook> beanToCsv = new StatefulBeanToCsvBuilder<CSVBook>(writer).withMappingStrategy(strategy).build();
            beanToCsv.write(books);

            String answer = writer.toString();
            if (answer.endsWith("\n\r"))
                answer = answer.substring(0, answer.length() - 2);
            if (answer.endsWith("\n") || answer.endsWith("\r"))
                answer = answer.substring(0, answer.length() - 1);
            return answer;
        }
    }

    private class CSVBookHeadersComparator implements java.util.Comparator<String> {

        private final List<String> order = List.of("AUTHOR", "TITLE", "PUBLISHER", "ISBN");

        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(order.indexOf(o1), order.indexOf(o2));
        }
    }
}
