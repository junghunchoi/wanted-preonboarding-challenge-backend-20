package com.example.wantedpreonboardingchallengebackend20.transaction.repository;

import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("select t from Transaction t where t.product.id = :product_id and t.buyer_id = :buyer_id")
	List<Transaction> findByProductAndBuyer_id(@Param("product_id") Long product_id, @Param("buyer_id")  Long buyer_id);

	List<Transaction> getTransactionsByProduct(Product product);
}
