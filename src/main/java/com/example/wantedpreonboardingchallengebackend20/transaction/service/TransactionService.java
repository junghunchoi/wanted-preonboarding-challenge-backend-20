package com.example.wantedpreonboardingchallengebackend20.transaction.service;

import com.example.wantedpreonboardingchallengebackend20.product.dto.ProductDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.dto.TransactionDTO;
import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import java.util.List;

public interface TransactionService {

	List<Object> purchaseList(Long buyer_id);

	List<Object> reserveList(Long id);

	Long purchase(ProductDTO productDTO, Long memberId);

	Transaction confirm(TransactionDTO transactionDTO);

}
