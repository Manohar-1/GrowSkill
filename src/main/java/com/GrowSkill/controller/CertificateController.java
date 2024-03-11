package com.GrowSkill.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GrowSkill.entity.Certificate;
import com.GrowSkill.service.CertificateService;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Certificate> generateCertificate(@RequestParam Long customerId, @RequestParam Long courseId) {
        Certificate certificate = certificateService.generateCertificate(customerId, courseId);
        return new ResponseEntity<>(certificate, HttpStatus.CREATED);
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long certificateId) {
        Certificate certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Certificate>> getCertificatesByCustomer(@PathVariable Long customerId) {
        List<Certificate> certificates = certificateService.getCertificatesByCustomer(customerId);
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Certificate>> getCertificatesByCourse(@PathVariable Long courseId) {
        List<Certificate> certificates = certificateService.getCertificatesByCourse(courseId);
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }
}
