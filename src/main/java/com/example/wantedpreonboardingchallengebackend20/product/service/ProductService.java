package com.example.wantedpreonboardingchallengebackend20.product.service;


import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import java.util.List;

public interface ProductService {

	Long register(ProductDTO productDTO);

	List<Object> list();

	List<Object> purchaseList(Long buyer_id);

	List<Object> reserveList(Long id);

	ProductDTO readOne(Long id);

	Long purchase(ProductDTO productDTO);

}
