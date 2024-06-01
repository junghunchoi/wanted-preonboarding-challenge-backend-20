package com.example.wantedpreonboardingchallengebackend20.transaction.controller;


import com.example.wantedpreonboardingchallengebackend20.common.dto.ResultDTO;
import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.dto.TransactionDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.service.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping("/purchase")
	public ResponseEntity<ResultDTO<Object>> purchase(ProductDTO productDTO, Long memberId) {
		transactionService.purchase(productDTO, memberId);

		return ResponseEntity.ok(ResultDTO.res(HttpStatus.OK, "상품 구매 성공", productDTO));
	}

	@GetMapping("/purchaselist")
	public ResponseEntity<ResultDTO<Object>> purchaseList(Long member_id) {
		List<Object> productList = transactionService.purchaseList(member_id);

		return ResponseEntity.ok(ResultDTO.res(HttpStatus.OK, "구매 리스트 반환 성공", productList));
	}

	@GetMapping("/reservelist")
	public ResponseEntity<ResultDTO<Object>> reserveList(Long member_id) {
		List<Object> productList = transactionService.reserveList(member_id);

		return ResponseEntity.ok(ResultDTO.res(HttpStatus.OK, "예약 리스트 반환 성공", productList));
	}

	@PostMapping("/confrim")
	public ResponseEntity<ResultDTO<Object>> confirm(TransactionDTO transactionDTO) {

		transactionService.confirm(transactionDTO);

		return ResponseEntity.ok(ResultDTO.res(HttpStatus.OK, "상품 확정 성공"));
	}
}
