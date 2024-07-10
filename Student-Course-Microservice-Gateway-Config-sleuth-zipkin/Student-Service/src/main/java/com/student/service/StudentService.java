package com.student.service;

import com.student.model.Student;
import com.student.model.StudentDetail;

public interface StudentService {
	StudentDetail getById(int id);
	Student getByName(String name);
	boolean save(Student student);
	boolean delete(int id);
	boolean update(Student student);
	StudentDetail getByIdUsingWebClient(int id);
	
}
