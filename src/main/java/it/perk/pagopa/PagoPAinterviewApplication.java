package it.perk.pagopa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The Class PagoPAinterviewApplication.
 */
@SpringBootApplication
public class PagoPAinterviewApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PagoPAinterviewApplication.class, args);
	}

	/**
	 * Gets the rest template.
	 *
	 * @return the rest template
	 */
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	
}
