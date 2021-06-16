package com.ifat.bdd.final_exam.model.loading;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @Column(nullable = false, name="ID")
    private int id;
    @Column(nullable = false, name="NAME")
    private String name;
    @Column(nullable = false, name = "LASTNAME")
    private String lastName;
    @Column(nullable = false ,name = "COUNTRYOFORIGIN")
    private String countryOfOrigin;
    @Column(nullable = false, name="EMAIL")
    private String email;

}
