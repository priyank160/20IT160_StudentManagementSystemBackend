package com.example.StudentManagementSystemBackend.Controller;

import com.example.StudentManagementSystemBackend.Model.Student;
import com.example.StudentManagementSystemBackend.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins="https://app-dummy123.herokuapp.com")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/listStudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get the student information
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentRepository.findById(id).get();
    }

    // Delete the student
    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudent(@PathVariable Integer id) {
        studentRepository.delete(studentRepository.findById(id).get());
        return studentRepository.findAll();
    }

    // Add new student
    @PostMapping("/student")
    public List<Student> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return studentRepository.findAll();
    }

    // Update the student information
    @PutMapping("/student/{id}")
    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        Student studentObj = studentRepository.findById(id).get();
        studentObj.setName(student.getName());
        studentObj.setAddress(student.getAddress());
        studentRepository.save(studentObj);
        return studentRepository.findAll();
    }

}

