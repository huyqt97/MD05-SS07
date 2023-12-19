package com.ra.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantity;
	
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
}
