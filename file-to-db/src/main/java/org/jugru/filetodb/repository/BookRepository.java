package org.jugru.filetodb.repository;

import org.jugru.filetodb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
