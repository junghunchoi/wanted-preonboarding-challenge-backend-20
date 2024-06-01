package com.example.wantedpreonboardingchallengebackend20.product.service;


import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import java.util.List;

public interface ProductService {

	Long register(ProductDTO productDTO);

	List<Object> list();

	ProductDTO readOne(Long id);


}
