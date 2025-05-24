package com.SpringBootCognizant.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SpringBootCognizant.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
}
