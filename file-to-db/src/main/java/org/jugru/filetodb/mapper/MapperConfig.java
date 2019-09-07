package org.jugru.filetodb.mapper;


import org.jugru.filetodb.model.Book;
import org.jugru.filetodb.model.CSVBook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        addBookToCSVBookMapping(mapper);
        addCSVBookToBookMapping(mapper);


        return mapper;
    }

    private void addCSVBookToBookMapping(ModelMapper modelMapper) {
        TypeMap<CSVBook, Book> typeMap = modelMapper.createTypeMap(CSVBook.class, Book.class);
        typeMap.addMappings(mapper -> mapper.skip(Book::setId));
    }

    private void addBookToCSVBookMapping(ModelMapper modelMapper) {
        TypeMap<Book, CSVBook> typeMap = modelMapper.createTypeMap(Book.class, CSVBook.class);

    }
}
