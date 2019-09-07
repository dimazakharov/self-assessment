package org.jugru.filetodb.org.jugru.controller;

import org.jugru.filetodb.model.Book;
import org.jugru.filetodb.repository.BookRepository;
import org.jugru.filetodb.util.FileToDbIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@FileToDbIntegrationTest
public class BookControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql("/schema.sql")
    public void uploadTest() {
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = createRequestBodyAndHeadersForUploadTest();

        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/api/upload", HttpMethod.POST, httpEntity, Void.class, "");

        Book firstExpected = Book.builder().id(1L).author("Utterly Evil Wizard").title("How to tame a unicorn").publisher("TCK Publishing").ISBN("1-84356-028-3").build();
        Book secondExpected = Book.builder().id(2L).author("Red Queen").title("Victoria Aveyard").publisher("Orion").ISBN("0-85131-041-9").build();

        List<Book> actual = bookRepository.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, actual.size());
        assertTrue(actual.containsAll(Set.of(firstExpected, secondExpected)));

    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void downloadTest() {

        String expectedContent = "\"AUTHOR\",\"TITLE\",\"PUBLISHER\",\"ISBN\"\n" +
                "\"Utterly Evil Wizard\",\"How to tame a unicorn\",\"TCK Publishing\",\"1-84356-028-3\"";

        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/download", byte[].class);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedContent, new String(responseEntity.getBody()));
        assertTrue(responseEntity.getHeaders().get("Content-Type").contains("text/csv;charset=UTF-8"));
    }


    private HttpEntity<LinkedMultiValueMap<String, Object>> createRequestBodyAndHeadersForUploadTest() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("forUpload.csv"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        return new HttpEntity<>(parameters, headers);
    }
}
