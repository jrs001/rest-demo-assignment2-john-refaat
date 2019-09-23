package com.itworx.restdemo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author john.refaat
 * @date 7/22/2019
 **/
@Entity
@Table(name = "employees")
@Getter @Setter @NoArgsConstructor @ToString
public class Employee implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email_address", nullable = false)
  private String emailId;

  public Employee(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }
}

