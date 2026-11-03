package com.kiet.hostelmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelStudentRepository
        extends JpaRepository<HostelStudent, Integer> {
}