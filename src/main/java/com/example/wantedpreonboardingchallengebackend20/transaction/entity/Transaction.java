package com.example.wantedpreonboardingchallengebackend20.transaction.entity;


import com.example.wantedpreonboardingchallengebackend20.product.entity.Product;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "buyer_id")
	private Long buyer_id;

	@Column(name = "seller")
	private Long seller;

	@Column(name = "price")
	private int price;

	@Column(name = "status", nullable = false)
	private String status;

}
