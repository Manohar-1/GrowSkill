package com.GrowSkill.entity;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;
    private String name;
    private String specialization;
    private String email;
    private String password;
}
