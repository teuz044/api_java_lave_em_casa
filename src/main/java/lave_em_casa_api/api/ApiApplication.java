package lave_em_casa_api.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//AQUI EU DESABILITO A SENHA (exclude= {UserDetailsServiceAutoConfiguration.class})
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}