package com.example.wantedpreonboardingchallengebackend20.transaction.service;

import com.example.wantedpreonboardingchallengebackend20.Exception.TransactionException;
import com.example.wantedpreonboardingchallengebackend20.Exception.TransactionException.TRANSACTION_ERROR;
import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import com.example.wantedpreonboardingchallengebackend20.product.entity.QProduct;
import com.example.wantedpreonboardingchallengebackend20.product.repository.ProductRepository;
import com.example.wantedpreonboardingchallengebackend20.transaction.dto.TransactionDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.entity.QTransaction;
import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import com.example.wantedpreonboardingchallengebackend20.transaction.repository.TransactionRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final JPAQueryFactory queryFactory;
	private final ModelMapper modelMapper;
	private final TransactionRepository transactionRepository;
	private final ProductRepository productRepository;

	@Override
	@Transactional
	public List<Object> purchaseList(Long buyer_id) {

		QTransaction transaction = QTransaction.transaction;
		QProduct product = QProduct.product;



		List<Tuple> results = queryFactory.select(product.name, transaction.price)
		                                  .from(transaction)
		                                  .leftJoin(product).on(transaction.product.id.eq(product.id))
		                                  .where(transaction.buyer_id.eq(buyer_id))
		                                  .fetch();
		log.info(results.toString());
		return Arrays.asList(results.toArray());
	}

	@Override
	public List<Object> reserveList(Long id) {
		QTransaction transaction = QTransaction.transaction;
		QProduct product = QProduct.product;

		List<Tuple> results = queryFactory.select(product.name, transaction.price)
		                                  .from(transaction)
		                                  .join(product).on(product.id.eq(transaction.product.id))
		                                  .where(transaction.buyer_id.eq(transaction.buyer_id)
		                                                             .or(transaction.seller.eq(id)))
		                                  .fetch();

		return Arrays.asList(results.toArray());

	}

	@Override
	public Long purchase(ProductDTO productDTO, Long memberId) {
		TransactionDTO transactionDTO = TransactionDTO.builder()
		                                              .product_id(productDTO.getId())
		                                              .price(productDTO.getPrice())
		                                              .seller(productDTO.getSeller_id())
		                                              .buyer_id(memberId)
		                                              .status("0")
		                                              .build();

		Product product = modelMapper.map(productDTO, Product.class);

		int productAmount = productDTO.getAmount();
		int confirmWaitAmount = 0;
		int confirmAmount = 0;
		List<Transaction> transactions = transactionRepository.getTransactionsByProduct(product);

		for (Transaction transaction1 : transactions) {
			if(transaction1.getStatus().equals("1")) confirmWaitAmount++; // 구매확정 증가
			if(transaction1.getStatus().equals("2")) confirmAmount++; // 구매확정 증가
		}

		if(productAmount == confirmAmount){ // 모든 수량에 대해 모든 구매자가 모두 구매확정한 경우 - 완료
			productDTO.setStatus("2");
		} else if (productAmount == confirmWaitAmount + confirmAmount) { // 추가 판매가 불가능하고 현재 구매확정을 대기하고 있는 경우 - 예약중
			productDTO.setStatus("1");
		}

		Product convertProduct = modelMapper.map(productDTO, Product.class);

		// 거래의 갯수에 따라 계산한 후 상품의 상태 변경
		productRepository.save(convertProduct);

		List<Transaction> buyingList = transactionRepository.findByProductAndBuyer_id(
			1L, memberId);

		// 한명은 하나의 상품만 구매할 수 있다.
		if (buyingList.size() >= 2) {
			throw new TransactionException(TRANSACTION_ERROR.INVALID);
		}

		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

		Long id = transactionRepository.save(transaction).getId();




		// 계산된 갯수 구매확정대기 횟수 구매확정 댓수
		return id;
	}

	@Override
	public Transaction confirm(TransactionDTO transactionDTO) {

		if (!transactionDTO.getStatus().equals("1")) {
			throw new TransactionException(TRANSACTION_ERROR.CANTCONFIRM);
		}

		transactionDTO.setStatus("2"); // 구매확정

		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

		transactionRepository.save(transaction);
		return transaction;
	}
}
