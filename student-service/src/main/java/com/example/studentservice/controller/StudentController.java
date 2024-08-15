package com.example.studentservice.controller;

import com.example.studentservice.entity.Student;
import com.example.studentservice.exception.StudentNotFoundException;
import com.example.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/listStudents")
    public List<Student> getStudents() {

        return studentService.getStudents();
    }
    @GetMapping("/getStudent/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.saveStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        try {
            studentService.updateStudent(id, studentDetails);
            return "Student updated";
        } catch (StudentNotFoundException e) {
            return "Student not found";
        }
    }
    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return "Student deleted";
        } catch (StudentNotFoundException e) {
            return "Student not found";
        }
    }
}
