package com.ra.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailRequest {
	
	private Long productId;
	
	private Integer quantity;
	
}
