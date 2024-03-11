package com.GrowSkill.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GrowSkill.entity.Certificate;
import com.GrowSkill.repository.CertificateRepository;

import java.util.Date;
import java.util.List;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;
    
    @Autowired
    private CustomerService customerService; 
    
    @Autowired
    private CourseService courseService;

    public Certificate generateCertificate(Long customerId, Long courseId) {
        Certificate certificate = new Certificate();
        certificate.setCustomer(customerService.getCustomerById(customerId));
        certificate.setCourse(courseService.getCourseById(courseId));
        certificate.setDateOfIssuance(new Date()); // Assuming current date
        return certificateRepository.save(certificate);
    }
    
    public Certificate getCertificateById(Long certificateId) {
        return certificateRepository.findById(certificateId).orElse(null);
    }

    public List<Certificate> getCertificatesByCustomer(Long customerId) {
        return certificateRepository.findByCustomerCustomerId(customerId);
    }

    public List<Certificate> getCertificatesByCourse(Long courseId) {
        return certificateRepository.findByCourseCourseId(courseId);
    }
}
