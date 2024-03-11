package com.GrowSkill.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.GrowSkill.entity.Class;

import com.GrowSkill.service.ClassService;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Class> createClass(@RequestBody Class classObj) {
        Class createdClass = classService.createClass(classObj);
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @GetMapping("/{classId}")
    public ResponseEntity<Class> getClassById(@PathVariable Long classId) {
        Class classObj = classService.getClassById(classId);
        if (classObj != null) {
            return new ResponseEntity<>(classObj, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.getAllClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Class>> getClassesByCourse(@PathVariable Long courseId) {
        List<Class> classes = classService.getClassesByCourse(courseId);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<Class> updateClass(@PathVariable Long classId, @RequestBody Class classObj) {
        Class updatedClass = classService.updateClass(classId, classObj);
        if (updatedClass != null) {
            return new ResponseEntity<>(updatedClass, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long classId) {
        classService.deleteClass(classId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
