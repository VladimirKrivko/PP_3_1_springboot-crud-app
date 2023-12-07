package ru.kata.pp.springbootcrudapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "user", schema = "pp_2_3_1_spring_mvc_hibernate")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;
    @Column(name = "second_name", length = 32)
    private String lastName;
}
