package com.example.wantedpreonboardingchallengebackend20.integrationTest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import com.example.wantedpreonboardingchallengebackend20.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ProductControllerTest {

	@Autowired
	private ProductService productService;

	@Test
	public void register() {
		// given
		ProductDTO productDTO = ProductDTO.builder()
		                                  .seller_id(1L)
		                                  .name("Test Product")
		                                  .price(1000)
		                                  .amount(10)
		                                  .status("0")
		                                  .build();

		// when
		Long id = productService.register(productDTO);

		// then
		assertNotNull(id);

	}

	@Test
	void read() {

		Long productId = 1L;



		// when
		ProductDTO result = productService.readOne(productId);
		log.info(result);
		// then
		assertNotNull(result);
	}
}
