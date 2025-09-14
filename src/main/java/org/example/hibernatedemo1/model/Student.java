package org.example.hibernatedemo1.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "student")
@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(nullable = false)
    private String name;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "roll_no", unique = true)
    private String rollNo;

}
