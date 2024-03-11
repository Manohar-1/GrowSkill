package com.GrowSkill.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GrowSkill.entity.Certificate;


public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	List<Certificate> findByCustomerCustomerId(Long customerId);
    List<Certificate> findByCourseCourseId(Long courseId);
    
}
