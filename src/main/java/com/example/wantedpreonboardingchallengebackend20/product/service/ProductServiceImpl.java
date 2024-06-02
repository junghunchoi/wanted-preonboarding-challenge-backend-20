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
		Product product = Product.builder()
		                         .seller_id(productDTO.getSeller_id())
		                         .name(productDTO.getName())
		                         .price(productDTO.getPrice())
		                         .amount(productDTO.getAmount())
		                         .status(productDTO.getStatus())
		                         .build();
		Long id = productRepository.save(product).getId();

		return id;
	}

	@Override
	public List<Object> list() {
		// 리스트 담아서 반환

		QProduct product = QProduct.product;
		List<Tuple> results = queryFactory.select(product.name, product.price, product.amount, product.status)
		                                  .from(product)
		                                  .orderBy(product.id.desc())
		                                  .fetch();

		return Arrays.asList(results.toArray());
	}

	@Override
	public ProductDTO readOne(Long id) {
		return modelMapper.map(productRepository.getProductById(id),
			ProductDTO.class);
	}


}
