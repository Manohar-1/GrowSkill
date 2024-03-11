package com.GrowSkill.entity;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Recording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordingId;
    private String url;
    private String password;

    @ManyToOne
    @JoinColumn(name = "classId")
    private Class enrolledClass;
}
