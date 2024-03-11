package com.GrowSkill.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.GrowSkill.entity.Recording;
import com.GrowSkill.service.RecordingService;

import java.util.List;

@RestController
@RequestMapping("/recordings")
public class RecordingController {

    @Autowired
    private RecordingService recordingService;

    @PostMapping
    public ResponseEntity<Recording> createRecording(@RequestBody Recording recording) {
        Recording createdRecording = recordingService.createRecording(recording);
        return new ResponseEntity<>(createdRecording, HttpStatus.CREATED);
    }

    @GetMapping("/{recordingId}")
    public ResponseEntity<Recording> getRecordingById(@PathVariable Long recordingId) {
        Recording recording = recordingService.getRecordingById(recordingId);
        if (recording != null) {
            return new ResponseEntity<>(recording, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Recording>> getRecordingsByClass(@PathVariable Long classId) {
        List<Recording> recordings = recordingService.getRecordingsByClass(classId);
        return new ResponseEntity<>(recordings, HttpStatus.OK);
    }

    @DeleteMapping("/{recordingId}")
    public ResponseEntity<Void> deleteRecording(@PathVariable Long recordingId) {
        recordingService.deleteRecording(recordingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
