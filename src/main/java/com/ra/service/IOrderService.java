package com.ra.service;

import com.ra.dto.request.CreateOrder;
import com.ra.dto.response.ListOrder;
import com.ra.dto.response.OrdersDTO;
import com.ra.exception.CustomException;
import com.ra.model.Orders;

import java.util.List;

public interface IOrderService {
	
	Orders addNewOrderToUserByIdUser(Long idUser, CreateOrder createOrder) throws CustomException;
	
	List<ListOrder> getAllOrder();
	List<OrdersDTO> getOrders();

	void updateByStatusAndId(Boolean status,Long id);
}
