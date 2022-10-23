package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_1021")
public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "name")
    String name;

    @Column
    String email;

    @Column
    String phone;

    @Column
    String address;

    @Column
    int age;

    @Column
    String nation;

    @Column
    String passwd;
}
