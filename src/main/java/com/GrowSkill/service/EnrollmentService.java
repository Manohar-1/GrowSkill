package com.GrowSkill.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GrowSkill.entity.Enrollment;
import com.GrowSkill.repository.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository; 
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private CustomerService customerService;

    public Enrollment enrollCustomer(Long customerId, Long courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCustomer(customerService.getCustomerById(customerId));
        enrollment.setCourse(courseService.getCourseById(courseId));
        enrollment.setPaymentStatus("Pending"); // Assuming initial status is pending
        return enrollmentRepository.save(enrollment);
    }

	public Enrollment getEnrollmentById(Long enrollmentId) {
		Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
		if(enrollment.isPresent()) {
			return enrollment.get();
		}
		
		return null;
	}

	public List<Enrollment> getEnrollmentsByCustomer(Long customerId) {
		List<Enrollment> enrollments = enrollmentRepository.findByCustomer_CustomerId(customerId);
		return enrollments;
	}
	
	public List<Enrollment> getEnrollmentsByCourse(Long courseId){
		List<Enrollment> enrollments = enrollmentRepository.findByCourse_CourseId(courseId);
		return enrollments;
	}

	public void cancelEnrollment(Long enrollmentId) {
		Optional<Enrollment> enrollmentOpt = enrollmentRepository.findById(enrollmentId);
		
		
		if(enrollmentOpt.isPresent()) {
			Enrollment enrollment = enrollmentOpt.get();
			enrollmentRepository.delete(enrollment);
		}
			
	}


}
