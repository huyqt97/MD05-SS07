package com.ra.repository;

import com.ra.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	
	List<OrderDetail> findAllByOrdersId(Long idOrder);
	
	Optional<OrderDetail> findByOrdersIdAndProductId(Long idOrder, Long productId);
	
}
