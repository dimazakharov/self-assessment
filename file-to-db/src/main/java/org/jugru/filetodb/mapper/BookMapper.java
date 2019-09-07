package org.jugru.filetodb.mapper;


import org.jugru.filetodb.model.Book;
import org.jugru.filetodb.model.CSVBook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Book toBook(CSVBook csvBook) {
        return modelMapper.map(csvBook, Book.class);
    }

    public List<Book> toBook(Collection<CSVBook> books){
        return modelMapper.map(books, new TypeToken<List<Book>>() {}.getType());
    }

    public CSVBook toCSVBook(Book book) {
        return modelMapper.map(book, CSVBook.class);
    }

    public List<CSVBook> toCSVBooks(Collection<Book> books) {
        return modelMapper.map(books, new TypeToken<List<CSVBook>>() {}.getType());
    }
}
