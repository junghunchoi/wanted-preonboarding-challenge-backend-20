package com.example.wantedpreonboardingchallengebackend20.product.entity;


import com.example.wantedpreonboardingchallengebackend20.transaction.entity.Transaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@Column(name = "seller_id")
	private Long seller_id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private int price;

	@Column(name = "amount")
	private int amount;

	@Column(name = "status")
	private String status;


}
