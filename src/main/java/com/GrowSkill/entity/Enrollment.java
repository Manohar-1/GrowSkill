package com.GrowSkill.entity;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    private String paymentStatus;
}
