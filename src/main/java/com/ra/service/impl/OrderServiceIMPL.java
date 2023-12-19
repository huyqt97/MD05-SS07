package com.ra.service.impl;

import com.ra.dto.request.CreateOrder;
import com.ra.dto.response.ListOrder;
import com.ra.dto.response.OrdersDTO;
import com.ra.exception.CustomException;
import com.ra.model.Orders;
import com.ra.model.Users;
import com.ra.repository.IOrderRepository;
import com.ra.repository.IUserRepository;
import com.ra.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceIMPL implements IOrderService {
	
	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public Orders addNewOrderToUserByIdUser(Long idUser, CreateOrder createOrder) throws CustomException {
		
		Optional<Users> optionalUsers = userRepository.findById(idUser);
		
		if (optionalUsers.isPresent()) {
			Users users = optionalUsers.get();
			Orders orders = Orders.builder()
					  .address(createOrder.getAddress())
					  .phone(createOrder.getPhone())
					  .note(createOrder.getNote())
					  .created(createOrder.getDate())
					  .status(false)
					  .users(users)
					  .build();
			return orderRepository.save(orders);
		}
		throw new CustomException("user not found");
	}
	
	@Override
	public List<ListOrder> getAllOrder() {
		List<ListOrder> list = orderRepository.findAll().stream().map(item -> ListOrder.builder()
				  .id(item.getId())
				  .address(item.getAddress())
				  .phone(item.getPhone())
				  .note(item.getNote())
				  .created(item.getCreated())
				  .username(item.getUsers().getUsername())
				  .status(item.getStatus())
				  .build()).toList();
		return list;
	}

	@Override
	public List<OrdersDTO> getOrders() {
		return orderRepository.getOrdersList();
	}

	@Override
	public void updateByStatusAndId(Boolean status, Long id) {
		 orderRepository.updateStatus(status,id);
	}
}
