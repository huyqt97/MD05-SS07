package com.ra.service.impl;

import com.ra.dto.request.OrderDetailRequest;
import com.ra.dto.response.OrderDetailResponse;
import com.ra.dto.response.OrderResponse;
import com.ra.exception.CustomException;
import com.ra.model.OrderDetail;
import com.ra.model.Orders;
import com.ra.model.Product;
import com.ra.model.Users;
import com.ra.repository.IOrderDetailRepository;
import com.ra.repository.IOrderRepository;
import com.ra.repository.IProductRepository;
import com.ra.repository.IUserRepository;
import com.ra.service.IOrderDetailService;
import com.ra.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceIMPL implements IOrderDetailService {
	@Autowired
	private IOrderDetailRepository orderDetailRepository;
	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public OrderResponse addNewOrderDetail(OrderDetailRequest orderDetailRequest, Long idUser) throws CustomException {
		Users users = userRepository.findById(idUser).orElseThrow(() -> new CustomException("user not found"));
		
		Optional<Orders> optionalOrders = orderRepository.findByStatusAndUsersId(false, idUser);
		
		if (optionalOrders.isPresent()) {
			Product product = productRepository.findById(orderDetailRequest.getProductId()).orElseThrow(() -> new CustomException("product not found"));
//			khởi tạo dối tượng orderDetail
			
			Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findByOrdersIdAndProductId(optionalOrders.get().getId(), product.getId());
			if (optionalOrderDetail.isPresent()) {
				OrderDetail oldOrderDetail = optionalOrderDetail.get();
				oldOrderDetail.setQuantity(oldOrderDetail.getQuantity() + orderDetailRequest.getQuantity());
//				lưu đối tượng khi nó đã tồn tại
				orderDetailRepository.save(oldOrderDetail);
			} else {
				OrderDetail orderDetail = OrderDetail.builder()
						  .orders(optionalOrders.get())
						  .price(product.getPrice())
						  .product(product)
						  .quantity(orderDetailRequest.getQuantity())
						  .build();
//				lưu khi nó ko tồn tại
				orderDetailRepository.save(orderDetail);
			}

//			lưu đối tượng orderDetail vào db

//			trả về cái OrderResponse có những cái thì bấm vào phần response dto
			return OrderResponse.builder()
					  .idOrder(optionalOrders.get().getId())
					  .username(optionalOrders.get().getUsers().getUsername())
					  .orderDetailResponses(orderDetailRepository.findAllByOrdersId(optionalOrders.get().getId()).stream().map(item -> OrderDetailResponse.builder()
								 .id(item.getId())
								 .productName(item.getProduct().getProductName())
								 .price(item.getPrice())
								 .quantity(item.getQuantity())
								 .build()).toList())
					  .build();
		}
		throw new CustomException("order not found");
	}
	
	@Override
	public List<OrderDetailResponse> getAllOrderDetailByIdOrder(Long idOrder) throws CustomException {
		
		Orders orders = orderRepository.findById(idOrder).orElseThrow(()->new CustomException("order not found"));
		
		List<OrderDetailResponse> list = orderDetailRepository.findAllByOrdersId(idOrder).stream().map(item -> OrderDetailResponse.builder()
				  .id(item.getId())
				  .productName(item.getProduct().getProductName())
				  .price(item.getPrice())
				  .quantity(item.getQuantity())
				  .build()).toList();
		
		return list;
	}
}



