package com.example.studentservice.repository;

import com.example.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Procedure("FindByStudentCode")
    Student findByStudentCode(String studentCode);
}