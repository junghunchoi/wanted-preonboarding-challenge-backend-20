package com.example.wantedpreonboardingchallengebackend20.product.entity;


import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@ToString
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long seller_id;

	private String name;

	private int price;

	private int amount;

	private String status;

	private LocalDateTime regDate;
}
