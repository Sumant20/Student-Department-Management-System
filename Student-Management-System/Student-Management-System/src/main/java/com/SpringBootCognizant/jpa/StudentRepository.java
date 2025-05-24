package com.SpringBootCognizant.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootCognizant.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
}
