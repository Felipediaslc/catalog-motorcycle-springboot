package br.com.catalog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CatalogApplication.class)
@TestPropertySource(locations = "/application.properties")
@AutoConfigureMockMvc
public class CatalogApplicationTests {

	@Test
	void contextLoads() {
	}

}
