package com.GrowSkill.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String description;
    private double price;
    private int duration;
    
    @OneToMany(mappedBy = "course")
    private List<Certificate> certificates;
    
    @ManyToMany(mappedBy = "courses") 
    private List<Customer> customers;
}
