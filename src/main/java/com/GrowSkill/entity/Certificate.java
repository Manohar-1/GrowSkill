package com.GrowSkill.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificateId;
    private Date dateOfIssuance;
    
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
