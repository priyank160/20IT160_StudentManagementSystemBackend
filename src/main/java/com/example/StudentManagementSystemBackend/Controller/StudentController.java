package com.example.StudentManagementSystemBackend.Controller;

import com.example.StudentManagementSystemBackend.Model.Student;
import com.example.StudentManagementSystemBackend.Repository.StudentRepository;
import com.example.StudentManagementSystemBackend.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins="http://localhost:3000")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public String doLogin(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Log in Success!!!";
    }

    @GetMapping
    public String displayWelcomeMessage(){
        return "<center><h1>Welcome to the Spring Boot Security!!!!</h1></center>";
    }
    @GetMapping("/listStudents")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    } // Select * from student;

    // Get the student information
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentRepository.findById(id).get();
    } // Select * from student where id=?

    // Delete the student
    @DeleteMapping("/student/{id}") // delete from student where id=?
    public List<Student> deleteStudent(@PathVariable Integer id) {
        studentRepository.delete(studentRepository.findById(id).get());
        return studentRepository.findAll();
    }

    // Add new student
    @PostMapping("/student") // insert into student values(?, ?, ?)
    public List<Student> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return studentRepository.findAll();
    }

    // Update the student information
    @PutMapping("/student/{id}") // update table student set name=? where id=?
    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        Student studentObj = studentRepository.findById(id).get();
        studentObj.setName(student.getName());
        studentObj.setAddress(student.getAddress());
        studentRepository.save(studentObj);
        return studentRepository.findAll();
    }

}