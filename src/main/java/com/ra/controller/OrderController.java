package com.ra.controller;

import com.ra.dto.request.CreateOrder;
import com.ra.dto.response.ListOrder;
import com.ra.dto.response.OrdersDTO;
import com.ra.exception.CustomException;
import com.ra.model.Orders;
import com.ra.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/{idUser}")
	public ResponseEntity<Orders> addNewOrderToUserByIdUser(@PathVariable Long idUser, @RequestBody CreateOrder createOrder) throws CustomException {
		return new ResponseEntity<>(orderService.addNewOrderToUserByIdUser(idUser, createOrder), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<OrdersDTO>> getAllOrders() {
		return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
	}
	@PatchMapping("/{idOrder}")
	public ResponseEntity<?> updateStatus(@PathVariable Long idOrder,@RequestParam("status") Boolean status){
		orderService.updateByStatusAndId(status,idOrder);
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
}