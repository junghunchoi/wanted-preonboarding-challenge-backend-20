package com.example.wantedpreonboardingchallengebackend20.product.service;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final JPAQueryFactory queryFactory;
	private final ProductRepository productRepository;
	private final TransactionRepository transactionRepository;
	private final ModelMapper modelMapper;

	@Override
	public Long register(ProductDTO productDTO) {
		log.info("ProductDTO: {}", productDTO);
		Product product = Product.builder()
		                         .seller_id(productDTO.getSeller_id())
		                         .name(productDTO.getName())
		                         .price(productDTO.getPrice())
		                         .amount(productDTO.getAmount())
		                         .status(productDTO.getStatus())
		                         .build();
		log.info(product);
		log.info(productRepository.save(product));
		Long id = productRepository.save(product).getId();

		log.info(id);
		return id;
	}

	@Override
	public List<Object> list() {
		// 리스트 담아서 반환

		QProduct product = QProduct.product;
		List<Tuple> results = queryFactory.select(product.name, product.price, product.amount)
		                                  .from(product)
		                                  .orderBy(product.id.desc())
		                                  .fetch();

		return Arrays.asList(results.toArray());
	}

	@Override
	public List<Object> purchaseList(Long buyer_id) {
		QProduct product = QProduct.product;
		QTransaction transaction = QTransaction.transaction;

		List<Tuple> results = queryFactory.select(product.name, product.price, product.amount)
		                                  .from(product)
		                                  .orderBy(product.id.desc())
		                                  .fetch();
		//상태값을 바꿔야함
		return null;
	}

	@Override
	public List<Object> reserveList(Long id) {
		return null;
	}

	@Override
	public ProductDTO readOne(Long id) {
		ProductDTO productDTO = modelMapper.map(productRepository.getProductById(id),
			ProductDTO.class);

		return productDTO;
	}

	@Override
	public Long purchase(ProductDTO productDTO) {
		TransactionDTO transactionDTO = TransactionDTO.builder()
		                                              .product_id(productDTO.getId())
		                                              .price(productDTO.getPrice())
		                                              .seller(productDTO.getSeller_id())
		                                              .build();

		Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);

		return transactionRepository.save(transaction).getId();
	}
}
