package com.example.wantedpreonboardingchallengebackend20.unitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.wantedpreonboardingchallengebackend20.Exception.TransactionException;
import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import com.example.wantedpreonboardingchallengebackend20.product.repository.ProductRepository;
import com.example.wantedpreonboardingchallengebackend20.transaction.dto.TransactionDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import com.example.wantedpreonboardingchallengebackend20.transaction.repository.TransactionRepository;
import com.example.wantedpreonboardingchallengebackend20.transaction.service.TransactionService;
import com.example.wantedpreonboardingchallengebackend20.transaction.service.TransactionServiceImpl;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.Collections;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(MockitoExtension.class)
@Log4j2
class TransactionServiceImplTest {

	@Mock
	private ModelMapper modelMapper;

	private TransactionService transactionService = Mockito.mock(TransactionService.class);
	private TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);
	private ProductRepository productRepository = Mockito.mock(ProductRepository.class);



	private ProductDTO productDTO;
	private TransactionDTO transactionDTO;

	@BeforeEach
	void setUp() {
		productDTO = ProductDTO.builder()
		                       .id(1L)
		                       .name("Product 1")
		                       .price(1000)
		                       .amount(10)
		                       .seller_id(1L)
		                       .status("0")
		                       .build();

		transactionDTO = TransactionDTO.builder()
		                               .product_id(1L)
		                               .price(1000)
		                               .seller(1L)
		                               .buyer_id(1L)
		                               .status("0")
		                               .build();
	}

	@Test
	void purchaseList_shouldReturnPurchaseList() {
		// given
		Long buyerId = 1L;

		List<Transaction> transactions = new ArrayList<>();

		// when
		when(transactionService.purchaseList(any(Long.class))).thenReturn(
			Collections.singletonList(transactions));
		List<Object> result = transactionService.purchaseList(buyerId);

		log.info(result.toString());
		// then
		assertNotNull(result.size());

	}

	@Test
	void reserveList_shouldReturnReserveList() {
		// given
		Long sellerId = 1L;


		// when
		List<Object> result = transactionService.reserveList(sellerId);

		// then
		assertNotNull(result.size());

	}

	@Test
	void purchase_shouldPurchaseProduct() {
		// given
		Long memberId = 1L;
		Product product = mock(Product.class);
		Transaction transaction = mock(Transaction.class);
		when(modelMapper.map(productDTO, Product.class)).thenReturn(product);
		when(transactionRepository.getTransactionsByProduct(product)).thenReturn(Arrays.asList(transaction));
		when(transactionRepository.findByProductAndBuyer_id(1L, memberId)).thenReturn(Arrays.asList());
		when(modelMapper.map(any(TransactionDTO.class), eq(Transaction.class))).thenReturn(transaction);
		when(transactionRepository.save(transaction)).thenReturn(transaction);
		when(transaction.getId()).thenReturn(1L);

		// when
		Long result = transactionService.purchase(productDTO, memberId);

		// then
		assertEquals(1L, result);
		verify(modelMapper).map(productDTO, Product.class);
		verify(transactionRepository).getTransactionsByProduct(product);
		verify(transactionRepository).findByProductAndBuyer_id(1L, memberId);
		verify(modelMapper).map(any(TransactionDTO.class), eq(Transaction.class));
		verify(transactionRepository).save(transaction);
		verify(productRepository).save(any(Product.class));
	}

	@Test
	void purchase_shouldThrowException_whenBuyingListSizeIsGreaterThanOne() {
		// given
		Long memberId = 1L;
		Product product = mock(Product.class);
		Transaction transaction1 = mock(Transaction.class);
		Transaction transaction2 = mock(Transaction.class);
		when(modelMapper.map(productDTO, Product.class)).thenReturn(product);
		when(transactionRepository.findByProductAndBuyer_id(1L, memberId))
			.thenReturn(Arrays.asList(transaction1, transaction2));

		// when & then
		assertThrows(TransactionException.class, () -> transactionService.purchase(productDTO, memberId));
		verify(modelMapper).map(productDTO, Product.class);
		verify(transactionRepository).findByProductAndBuyer_id(1L, memberId);
		verifyNoMoreInteractions(transactionRepository, productRepository);
	}

	@Test
	void confirm_shouldConfirmTransaction() {
		// given
		transactionDTO.setStatus("1");
		Transaction transaction = mock(Transaction.class);
		when(modelMapper.map(transactionDTO, Transaction.class)).thenReturn(transaction);
		when(transactionRepository.save(transaction)).thenReturn(transaction);

		// when
		Transaction result = transactionService.confirm(transactionDTO);

		// then
		assertEquals("2", transactionDTO.getStatus());
		assertEquals(transaction, result);
		verify(modelMapper).map(transactionDTO, Transaction.class);
		verify(transactionRepository).save(transaction);
	}

	@Test
	void confirm_shouldThrowException_whenStatusIsNotOne() {
		// given
		transactionDTO.setStatus("0");

		// when & then
		assertThrows(TransactionException.class, () -> transactionService.confirm(transactionDTO));
		verifyNoInteractions(modelMapper, transactionRepository);
	}
}