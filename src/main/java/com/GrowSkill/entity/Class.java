package com.GrowSkill.entity;
import lombok.Data;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@Setter
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private Date date;
    private String time;
    private String topic;
    private String zoomLink;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor;
}
