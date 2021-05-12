package it.perk.pagopa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Perk
 *
 */
@SpringBootTest()
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DisplayName("Application")
class PagoPAinterviewApplicationTests {

	@Autowired
	private WebApplicationContext webAppContext;
	
	@Test
	@DisplayName("Web context load")
	void contextLoads() {
		Assertions.assertNotNull(webAppContext);
	}

}
