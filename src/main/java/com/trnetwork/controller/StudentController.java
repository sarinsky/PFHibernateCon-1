package com.trnetwork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trnetwork.model.Student;

import com.trnetwork.repository.StudentRepository;

@CrossOrigin(origins= "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentRepository repository;
	
	/*public List<Student> getStudents(){
		return 
	}*/
	
/*	@GetMapping("/tutorials/n") //entra por esta api con el metodo get 
	public ResponseEntity<List<Student>> findByEmail(@RequestParam(required = false) String title) {
		return null;
	}*/
	
	@GetMapping("/tutorials") //entra por esta api con el metodo get 
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String title) {
		/*RESPONSE ENTITY ES UN ALISTA DE TUTORIAL GETALLTUTORIAL NO RED*/
		try {
			List<Student> student = new ArrayList<Student>();

			if (title == null) 
				repository.findAll().forEach(student::add);
			//DEVOLVEMOS UNA LSTA DE TUTORIALS
			else
				repository.findByTitleContaining(title).forEach(student::add);
				

			if (student.isEmpty()) 
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				/*204 ESTA BIEN PERO NO HAY CONTENIDO 200,400,500 OK */
			

			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id ){
		Optional<Student> studentData = repository.findById(id);
		if (studentData.isPresent() ) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/student")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		try {
			Student _student= repository
					.save(new Student(student.getName(), student.getEmail(), student.getDob()));
					//.save(new Student(student.getName(), student.getEmail(), student.getDob()),false);
					//.save(new Student(student.getName(), student.getEmail(), student.getDob(), false));
			return new ResponseEntity<>(_student, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student){
		Optional<Student> studentData  = repository.findById(id);
		
		if (studentData.isPresent()) {
			Student _student = studentData.get();
			_student.setName(student.getName());
			_student.setEmail(student.getEmail());
			_student.setDob(student.getDob());
			
			return new ResponseEntity<>(repository.save(_student), HttpStatus.OK);
		}else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/student/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id){
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/student")
	public ResponseEntity<HttpStatus> deleteAllStudents(){
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	@GetMapping("student/id")
	public ResponseEntity<List<Student>> findById(){
		
		try {
			List<Student> student = repository.findByPublished(true);
			if (student.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(student, HttpStatus.OK); 
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}
