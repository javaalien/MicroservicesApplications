package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Student findByName(String name);  // select name from student;
}
