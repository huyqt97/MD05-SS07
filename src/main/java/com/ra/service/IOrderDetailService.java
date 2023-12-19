package com.ra.service;

import com.ra.dto.request.OrderDetailRequest;
import com.ra.dto.response.OrderDetailResponse;
import com.ra.dto.response.OrderResponse;
import com.ra.exception.CustomException;

import java.util.List;

public interface IOrderDetailService {
	
	OrderResponse addNewOrderDetail (OrderDetailRequest orderDetailRequest,Long idUser) throws CustomException;

	List<OrderDetailResponse> getAllOrderDetailByIdOrder(Long idOrder) throws CustomException;
	
}

