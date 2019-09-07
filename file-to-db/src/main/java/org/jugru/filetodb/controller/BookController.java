package org.jugru.filetodb.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jugru.filetodb.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/upload")
    @SneakyThrows
    public ResponseEntity upload(@RequestParam("file") MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if(!contentType.contains("text/csv")) {
            return ResponseEntity.badRequest().build();
        }

        InputStream inputStream = multipartFile.getInputStream();
        bookService.saveBooksFromCsv(inputStream);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/download")
    @SneakyThrows
    public void download(HttpServletResponse response) {
        String content = bookService.getAllBooksAsCsvString();
        response.addHeader("Content-disposition", "attachment; filename=books.csv");
        response.setContentType("text/csv; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(content);
        response.getWriter().flush();
    }
}
