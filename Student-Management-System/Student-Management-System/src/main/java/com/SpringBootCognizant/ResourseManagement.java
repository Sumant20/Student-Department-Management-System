package com.SpringBootCognizant;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.SpringBootCognizant.exception.DepartmentIdNotFoundException;
import com.SpringBootCognizant.exception.NullTableException;
import com.SpringBootCognizant.exception.SpecialCharacterFoundException;
import com.SpringBootCognizant.jpa.DepartmentRepository;
import com.SpringBootCognizant.jpa.StudentRepository;

@RestController
public class ResourseManagement {
	
	@Autowired
	DepartmentRepository deptRepository;
	StudentRepository studRepository;
	
	public ResourseManagement(DepartmentRepository deptRepository, StudentRepository studRepository) {
		this.deptRepository = deptRepository;
		this.studRepository = studRepository;
	}
	
	@PostMapping("/department")
	public ResponseEntity<Department> postdepartmentData(@RequestBody Department department) {
		if(!department.getDepartment_Name().matches("[a-zA-Z ]+")) {
			throw new SpecialCharacterFoundException(department.getDepartment_Name()+" is Invalid name type");
		}
		Department savedDepartment=deptRepository.save(department);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedDepartment.getDepartment_Id())
					.toUri();
		return ResponseEntity.created(location).build();
		}
	@GetMapping("/department")
	public List<Department> retrieveAllDepartment(){
		List<Department> department= deptRepository.findAll();
		if(department.isEmpty()) {
			throw new NullTableException("Department table is empty");
		}
		return department;
	}
	@GetMapping("/department/{id}")
	public Optional<Department> retrieveDepartment(@PathVariable int id) {
		Optional<Department> department= deptRepository.findById(id);
		if(department.isEmpty()) {
			throw new DepartmentIdNotFoundException(id+" is Invalid Department Id");
		}
		return department;
	}
	@PostMapping("/department/{id}/student-value")
	public ResponseEntity<Student> postStudentData(@PathVariable int id,@RequestBody Student student){
		if(!student.getStudent_Name().matches("[a-zA-Z]+")) {
			throw new SpecialCharacterFoundException(student.getStudent_Name()+" is Invalid name type");
		}
		 Optional<Department> department=deptRepository.findById(id);
		 if(department.isEmpty()) {
			 throw new DepartmentIdNotFoundException(id+" is Invalid Department Id");
		 }
		 student.setDepartment(department.get());
		 Student savedStudent=studRepository.save(student);
		 URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedStudent.getStudent_id())
					.toUri();
		return ResponseEntity.created(location).build();
	}
	@GetMapping("/department/{id}/student-value")
	public List<Student> retrieveStudentPost(@PathVariable int id){
		Optional<Department> department=deptRepository.findById(id);
		if(department.isEmpty()) {
			throw new DepartmentIdNotFoundException(id+" is Invalid Department Id");
		}
		return department.get().getStudent();
	}
	@GetMapping("/students")
	public List<Student> retrieveAllStudent(){
		List<Student> student=studRepository.findAll();
		if(student.isEmpty()) {
			throw new NullTableException("Student table is empty");
		}
		return student;
	}
}
