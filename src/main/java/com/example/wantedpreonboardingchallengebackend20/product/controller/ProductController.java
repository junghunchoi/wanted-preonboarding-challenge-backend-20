package com.example.wantedpreonboardingchallengebackend20.product.controller;


import com.example.wantedpreonboardingchallengebackend20.common.dto.ResultDTO;
import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("")
	public ResponseEntity<ResultDTO<Object>> list() {
		log.info("GET : " + "/api/products");
		ProductDTO productDTO = ProductDTO.builder()
		                                  .seller_id(1L)
		                                  .name("Test Product")
		                                  .price(1000)
		                                  .amount(10)
		                                  .status("0")
		                                  .build();

		productService.register(productDTO);

		List<Object> productList = productService.list();

		return ResponseEntity.ok(ResultDTO.res(HttpStatus.OK, "상품 리스트 반환 성공", productList));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResultDTO<Object>> read(@PathVariable("id") Long id) {
		ProductDTO productDTO = productService.readOne(id);

		return ResponseEntity.ok(ResultDTO.res(HttpStatus.OK, HttpStatus.OK.toString(), productDTO));
	}


}
