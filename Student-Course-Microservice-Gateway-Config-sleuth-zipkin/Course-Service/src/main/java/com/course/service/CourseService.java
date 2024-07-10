package com.course.service;

import java.util.List;

import com.course.entity.Course;

public interface CourseService {
	boolean create(Course course);

	Course getById(int courseId);

	List<Course> getAll();

	boolean update(Course course);

	boolean delete(int courseId);
}
