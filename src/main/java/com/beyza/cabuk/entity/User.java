package com.beyza.cabuk.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MyUser")
@Data
public class User extends BaseEntity {     // This class will now correspond to a table in the database. By default, the table name is the class name, ie User. Column names are id, firstName and lastName
    @Id
    @SequenceGenerator(name = "user_seq_gen",sequenceName = "user_gen",initialValue = 100,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME",length = 100)
    private String firstName;

    @Column(name = "SURNAME",length = 100)
    private String lastName;

}
