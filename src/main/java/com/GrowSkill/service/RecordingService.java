package com.GrowSkill.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GrowSkill.entity.Recording;
import com.GrowSkill.repository.RecordingRepository;

import java.util.List;

@Service
public class RecordingService {

    @Autowired
    private RecordingRepository recordingRepository;

    public Recording createRecording(Recording recording) {
        return recordingRepository.save(recording);
    }

    public Recording getRecordingById(Long recordingId) {
        return recordingRepository.findById(recordingId).orElse(null);
    }

    public List<Recording> getRecordingsByClass(Long classId) {
        return recordingRepository.findByEnrolledClassClassId(classId);
    }

    public void deleteRecording(Long recordingId) {
        recordingRepository.deleteById(recordingId);
    }
}
