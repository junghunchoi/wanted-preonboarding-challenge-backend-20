package com.example.wantedpreonboardingchallengebackend20.integrationTest;

import static org.junit.jupiter.api.Assertions.*;

import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import com.example.wantedpreonboardingchallengebackend20.product.repository.ProductRepository;
import com.example.wantedpreonboardingchallengebackend20.transaction.dto.TransactionDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import com.example.wantedpreonboardingchallengebackend20.transaction.repository.TransactionRepository;
import com.example.wantedpreonboardingchallengebackend20.transaction.service.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class TransactionControllerTest {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ProductRepository productRepository;


	@Test
	void purchase() {
		ProductDTO productDTO = ProductDTO.builder()
		                                  .id(1L)
		                                  .seller_id(1L)
		                                  .name("Test Product")
		                                  .price(1000)
		                                  .amount(10)
		                                  .status("0")
		                                  .build();

		transactionService.purchase(productDTO, 1L);
	}

	@Test
	void purchaseList() {
		Long member_id = 1L;

		transactionService.purchaseList(member_id);
	}

	@Test
	void reserveList() {
		Long member_id = 1L;

		transactionService.reserveList(member_id);
	}

	@Test
	void confirm() {

		TransactionDTO transactionDTO = TransactionDTO.builder()
		                                              .id(1L)
		                                              .buyer_id(1L)
		                                              .price(1000)
		                                              .product_id(1L)
		                                              .seller(1L)
		                                              .build();

		transactionService.confirm(transactionDTO);
	}
}