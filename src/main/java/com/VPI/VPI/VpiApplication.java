package com.VPI.VPI;

import com.VPI.VPI.Entities.AdminEntity;
import com.VPI.VPI.Repositories.IUsuarioRepository;
import com.VPI.VPI.Services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = IUsuarioRepository.class)
public class VpiApplication {

	@Autowired
	IAdminService adminService;


	/*@PostConstruct
	public AdminEntity createAdminAFuego() {
		System.out.println("User: admin Pass: 1234");
		return adminService.createAdminAFuego();

	}*/


	public static void main(String[] args) {
		SpringApplication.run(VpiApplication.class, args);




	}





}


