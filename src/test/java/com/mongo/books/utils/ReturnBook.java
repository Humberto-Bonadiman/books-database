package com.mongo.books.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ReturnBook {

    private String title;
    private List<String> authors;
    private Integer numberPages;
    private String bookPublisher;
    private String publicationDate;

}
