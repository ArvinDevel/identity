package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "name")
    String name;

    @Column
    String passwd;

    @Column
    String status;

    @Column
    Date registrationDate;

}
