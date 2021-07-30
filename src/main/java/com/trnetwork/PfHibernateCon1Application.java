package com.trnetwork;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class PfHibernateCon1Application {

	public static void main(String[] args) {
		SpringApplication.run(PfHibernateCon1Application.class, args);
		//System.out.println("hola mundo ");
		/*@Autowired //inyeccion al respositorio
	TutorialRepository tutorialRepository;*/
	}
	/*@GetMapping
	public List<Student> hello() {
		return List.of()
						
		
	}*/

}
