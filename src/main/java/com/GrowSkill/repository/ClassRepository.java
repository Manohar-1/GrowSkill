package com.GrowSkill.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; 
import com.GrowSkill.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByCourse_CourseId(Long courseId);
    
}
