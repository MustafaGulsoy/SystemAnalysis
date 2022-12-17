package com.UlimaStella.Doga_Server_Demo.models;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.domain.Writer;
import lombok.Data;

@Data
public class BookToWriter {
    private Long writerId;
    private Long bookId;
}
