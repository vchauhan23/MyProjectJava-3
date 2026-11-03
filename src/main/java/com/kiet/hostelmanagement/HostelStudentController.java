package com.kiet.hostelmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/students")
public class HostelStudentController {

    @Autowired
    private HostelStudentRepository repository;

    // (b) Add Student
    @PostMapping
    public HostelStudent addStudent(@RequestBody HostelStudent student) {
        return repository.save(student);
    }

    // (c) Get All Students
    @GetMapping
    public List<HostelStudent> getAllStudents() {
        return repository.findAll();
    }

    // (c) Get Student By ID
    @GetMapping("/{id}")
    public ResponseEntity<HostelStudent> getStudentById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // (d) Update Student
    @PutMapping("/{id}")
    public ResponseEntity<HostelStudent> updateStudent(
            @PathVariable int id,
            @RequestBody HostelStudent student) {

        return repository.findById(id).map(existing -> {

            existing.setStudentName(student.getStudentName());
            existing.setRollNumber(student.getRollNumber());
            existing.setHostelName(student.getHostelName());
            existing.setRoomNumber(student.getRoomNumber());
            existing.setFee(student.getFee());

            return ResponseEntity.ok(repository.save(existing));

        }).orElse(ResponseEntity.notFound().build());
    }

    // (e) Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student not found");
    }
}