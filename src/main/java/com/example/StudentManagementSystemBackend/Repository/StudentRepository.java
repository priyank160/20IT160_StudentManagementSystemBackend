package com.example.StudentManagementSystemBackend.Repository;

import com.example.StudentManagementSystemBackend.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
