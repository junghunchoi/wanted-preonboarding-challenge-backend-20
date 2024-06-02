package com.example.wantedpreonboardingchallengebackend20.unitTest;

import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import com.example.wantedpreonboardingchallengebackend20.product.repository.ProductRepository;
import com.example.wantedpreonboardingchallengebackend20.product.service.ProductService;
import com.example.wantedpreonboardingchallengebackend20.product.service.ProductServiceImpl;
import com.example.wantedpreonboardingchallengebackend20.transaction.repository.TransactionRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@Log4j2
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private JPAQueryFactory queryFactory;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private ProductRepository productRepository;

	@Spy
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	@Rollback(value = false)
	void testRegister() {
		// given
		ProductDTO productDTO = ProductDTO.builder()
		                                  .seller_id(2L)
		                                  .name("Test Product")
		                                  .price(1000)
		                                  .amount(10)
		                                  .status("0")
		                                  .build();

		Product savedProduct = Product.builder()
		                              .id(1L)
		                              .seller_id(productDTO.getSeller_id())
		                              .name(productDTO.getName())
		                              .price(productDTO.getPrice())
		                              .amount(productDTO.getAmount())
		                              .status(productDTO.getStatus())
		                              .build();

		when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

		// when
		Long id = productService.register(productDTO);

		// then
		assertNotNull(id);
		assertEquals(savedProduct.getId(), id);
	}


@Test
void testReadOne() {
	// given
	Long productId = 1L;
	Product product = Product.builder()
	                         .id(productId)
	                         .seller_id(1L)
	                         .name("Test Product")
	                         .price(1000)
	                         .amount(10)
	                         .status("0")
	                         .build();

	when(productRepository.getProductById(productId)).thenReturn(product);

	ProductDTO expectedProductDTO = ProductDTO.builder()
	                                          .id(productId)
	                                          .seller_id(product.getSeller_id())
	                                          .name(product.getName())
	                                          .price(product.getPrice())
	                                          .amount(product.getAmount())
	                                          .status(product.getStatus())
	                                          .build();

	when(modelMapper.map(product, ProductDTO.class)).thenReturn(expectedProductDTO);

	// when
	ProductDTO result = productService.readOne(productId);
	log.info(result);

	// then
	assertNotNull(result);
	assertEquals(expectedProductDTO.getId(), result.getId());
	assertEquals(expectedProductDTO.getSeller_id(), result.getSeller_id());
	assertEquals(expectedProductDTO.getName(), result.getName());
	assertEquals(expectedProductDTO.getPrice(), result.getPrice());
	assertEquals(expectedProductDTO.getAmount(), result.getAmount());
	assertEquals(expectedProductDTO.getStatus(), result.getStatus());
}
}