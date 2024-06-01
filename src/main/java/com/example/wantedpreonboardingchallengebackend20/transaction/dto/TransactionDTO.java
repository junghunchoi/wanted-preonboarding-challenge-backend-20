package com.example.wantedpreonboardingchallengebackend20.transaction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
	Long id;

	Long product_id;

	Long buyer_id;

	Long seller;

	int price;

	String status;
}
