package com.trnetwork.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.trnetwork.model.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {
	//List<Student> findByEmail(String email);
	//List<Student> getAllStudents(boolean student); //is findpublished
	//List <Student>
	//List<Student> findByTitleContaining(String title);
	//List<Student> findById(boolean id);
	List<Student> findByPublished(boolean published);
	List<Student> findByTitleContaining(String title);

}
