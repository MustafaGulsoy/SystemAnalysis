package com.UlimaStella.Doga_Server_Demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false)
    private String nameSurname;

    @Column(unique = true)
    private String phone;

    @Column(unique = false)
    private String password;

    @Column(unique = false)
    private String sex;

    @Column(unique = false)
    private String tcId;

    @Column(unique = false)
    private String securityNumber;

    @Column(unique = false)
    private String salary;

    @Column(unique = false)
    private String department;

    @Column(unique = false)
    private String status;

    @Column(unique = false)
    private Date enterenceDate;

    @Column(unique = false)
    private Date exitDate;



    @Column(unique = false)
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();



}
