package com.SpringBootCognizant;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	@Id
	private int department_Id;
	private String department_Name;
	private String location;
	
	@OneToMany(mappedBy="department") //one department should have list of students
	@JsonIgnore
	private List<Student> student;
	
	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public Department(int department_Id, String department_Name, String location) {
		this.department_Id = department_Id;
		this.department_Name = department_Name;
		this.location = location;
	}

	public Department() {

	}

	public int getDepartment_Id() {
		return department_Id;
	}

	public void setDepartment_Id(int department_Id) {
		this.department_Id = department_Id;
	}

	public String getDepartment_Name() {
		return department_Name;
	}

	public void setDepartment_Name(String department_Name) {
		this.department_Name = department_Name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Department [department_Id=" + department_Id + ", department_Name=" + department_Name + ", location="
				+ location + "]";
	}
	
}
