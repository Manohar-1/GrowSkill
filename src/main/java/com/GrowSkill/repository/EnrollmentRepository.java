package com.GrowSkill.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GrowSkill.entity.Enrollment;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCustomer_CustomerId(Long customerId);
    List<Enrollment> findByCourse_CourseId(Long courseId);
}
