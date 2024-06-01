package com.example.wantedpreonboardingchallengebackend20.product;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@Log4j2
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
	@Mock
	private JPAQueryFactory queryFactory;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@Spy
	private ModelMapper modelMapper;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void testRegister() {
		// given
		ProductDTO productDTO = ProductDTO.builder()
		                                  .seller_id(1L)
		                                  .name("Test Product")
		                                  .price(1000)
		                                  .amount(10)
		                                  .status("0")
		                                  .build();




		// when
		Long productId = productService.register(productDTO);

		// then
//		assertEquals(1L, productId);
//		verify(productRepository, times(1)).save(any(Product.class));
	}

//	@Test
//	void testList() {
//		// given
//		List<Object[]> results = new ArrayList<>();
//		results.add(new Object[]{"Product 1", 1000, 10});
//		results.add(new Object[]{"Product 2", 2000, 20});
//
//		when(queryFactory.select(any(), any(), any())
//		                 .from(any())
//		                 .orderBy(any())
//		                 .fetch()).thenReturn(results);
//
//		// when
//		List<Object> products = productService.list();
//
//		// then
//		assertEquals(2, products.size());
//	}
//
//	@Test
//	void testReadOne() {
//		// given
//		Long productId = 1L;
//		Product product = new Product();
//		product.setId(productId);
//		product.setName("Test Product");
//		product.setPrice(1000);
//		product.setAmount(10);
//
//		ProductDTO productDTO = new ProductDTO();
//		productDTO.setId(productId);
//		productDTO.setName("Test Product");
//		productDTO.setPrice(1000);
//		productDTO.setAmount(10);
//
//		when(productRepository.getProductById(productId)).thenReturn(product);
//		when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
//
//		// when
//		ProductDTO result = productService.readOne(productId);
//
//		// then
//		assertEquals(productId, result.getId());
//		assertEquals("Test Product", result.getName());
//		assertEquals(1000, result.getPrice());
//		assertEquals(10, result.getAmount());
//	}
}