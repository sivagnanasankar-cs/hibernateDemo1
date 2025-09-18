package org.example.hibernatedemo1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "degree")
@Entity
@Data
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Integer degreeId;

    @Column(name = "degree_code", nullable = false, unique = true)
    private String degreeCode;

    @Column(name = "degree_name", nullable = false, unique = true)
    private String degreeName;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments;
}
