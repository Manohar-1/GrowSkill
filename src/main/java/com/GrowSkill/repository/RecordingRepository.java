package com.GrowSkill.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.GrowSkill.entity.Recording;

public interface RecordingRepository extends JpaRepository<Recording, Long> {

	List<Recording> findByEnrolledClassClassId(Long classId);

}
