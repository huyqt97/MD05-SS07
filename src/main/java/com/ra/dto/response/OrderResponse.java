package com.ra.dto.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
	
	private Long idOrder;
	
	private String username;
	
	private List<OrderDetailResponse> orderDetailResponses;
}
