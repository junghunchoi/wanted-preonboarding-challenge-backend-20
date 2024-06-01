package com.example.wantedpreonboardingchallengebackend20.product.repository;


import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product getProductById(Long id);

}
