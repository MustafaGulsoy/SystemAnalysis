package com.UlimaStella.Doga_Server_Demo.domain;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = EAGER)
    private Writer writer;
    private String name;
    private String detail;
    private Date publishDate;


}
