package com.example.studentservice.service;

import com.example.studentservice.entity.Student;
import com.example.studentservice.exception.StudentNotFoundException;
import com.example.studentservice.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));
    }
   @Transactional
    public Student saveStudent(Student student) {
        Student existingStudent = studentRepo.findByStudentCode(student.getStudentCode());
        if (existingStudent != null) {
            throw new IllegalArgumentException("Student with code " + student.getStudentCode() + " already exists");
        }
        return studentRepo.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepo.findById(id).orElse(null);

        if (student == null) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }

        if (studentDetails.getName() != null) {
            student.setName(studentDetails.getName());
        }
        if (studentDetails.getEmail() != null) {
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getAge() != null) {
            student.setAge(studentDetails.getAge());
        }

        return studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found");
        }
        studentRepo.deleteById(id);
    }
}
