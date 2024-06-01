package com.example.wantedpreonboardingchallengebackend20.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;

	private Long seller_id;

	private String name;

	private int price;

	private int amount;

	private String status;
}
