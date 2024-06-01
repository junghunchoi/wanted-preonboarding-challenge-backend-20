package com.example.wantedpreonboardingchallengebackend20.transaction.service;

import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.entity.QProduct;
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

	@Override
	@Transactional
	public List<Object> purchaseList(Long buyer_id) {

		QTransaction transaction = QTransaction.transaction;
		QProduct product = QProduct.product;



		List<Tuple> results = queryFactory.select(product.name, product.amount)
		                                  .from(transaction)
		                                  .leftJoin(product).on(transaction.product_id.eq(product.id))
		                                  .where(transaction.buyer_id.eq(buyer_id))
		                                  .fetch();
		log.info(results.toString());
		return Arrays.asList(results.toArray());
	}

	@Override
	public List<Object> reserveList(Long id) {
		QTransaction transaction = QTransaction.transaction;
		QProduct product = QProduct.product;

		List<Tuple> results = queryFactory.select(product.name, product.amount)
		                                  .from(transaction)
		                                  .join(product).on(product.id.eq(transaction.product_id))
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

		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

		return transactionRepository.save(transaction).getId();
	}

	@Override
	public Transaction confirm(TransactionDTO transactionDTO) {
		transactionDTO.setStatus("1");

		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

		transactionRepository.save(transaction);
		return transaction;
	}
}
