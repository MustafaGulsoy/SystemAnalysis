package com.UlimaStella.Doga_Server_Demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Table(name = "Book")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = EAGER)
    private Writer writer;
    private String name;
    private String detail;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishDate;


}
