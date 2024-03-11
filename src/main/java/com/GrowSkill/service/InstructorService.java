package com.GrowSkill.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GrowSkill.entity.Instructor;
import com.GrowSkill.repository.InstructorRepository;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor getInstructorById(Long instructorId) {
        return instructorRepository.findById(instructorId).orElse(null);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor updateInstructor(Long instructorId, Instructor instructor) {
        Instructor existingInstructor = getInstructorById(instructorId);
        if (existingInstructor != null) {
            instructor.setInstructorId(instructorId);
            return instructorRepository.save(instructor);
        }
        return null;
    }

    public void deleteInstructor(Long instructorId) {
        instructorRepository.deleteById(instructorId);
    }
}
