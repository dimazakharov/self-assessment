package org.jugru.filetodb.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CSVBook {
    @CsvBindByName(column = "AUTHOR")
    private String author;
    @CsvBindByName(column = "TITLE")
    private String title;
    @CsvBindByName(column = "PUBLISHER")
    private String publisher;
    @CsvBindByName(column = "ISBN")
    private String ISBN;
}
