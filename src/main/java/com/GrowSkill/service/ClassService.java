package com.GrowSkill.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.GrowSkill.entity.Class;

import com.GrowSkill.repository.ClassRepository;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public Class createClass(Class classObj) {
        return classRepository.save(classObj);
    }

    public Class getClassById(Long classId) {
        return classRepository.findById(classId).orElse(null);
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public List<Class> getClassesByCourse(Long courseId) {
        return classRepository.findByCourse_CourseId(courseId);
    }

    public Class updateClass(Long classId, Class classObj) {
        Class existingClass = getClassById(classId);
        if (existingClass != null) {
            classObj.setClassId(classId);
            return classRepository.save(classObj);
        }
        return null;
    }

    public void deleteClass(Long classId) {
        classRepository.deleteById(classId);
    }
}
