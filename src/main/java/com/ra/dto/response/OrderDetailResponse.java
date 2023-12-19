package com.ra.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse {
	
	private Long id;
	
	private String productName;
	
	private Double price;
	
	private Integer quantity;
	
}
