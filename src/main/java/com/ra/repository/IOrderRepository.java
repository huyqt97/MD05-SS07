package com.ra.repository;

import com.ra.dto.response.OrdersDTO;
import com.ra.model.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long> {

    @Query(value =
            "select new com.ra.dto.response.OrdersDTO(o.id,o.phone,o.address,o.note,o.created,o.status,u.username,u.email)"
                    +"from Orders o JOIN Users u ON o.users.id = u.id")
    List<OrdersDTO> getOrdersList();

//    @Query(value = "update orders SET status = :status WHERE id = :id",nativeQuery = true)
    @Query(value = "update Orders SET status = :status WHERE id = :id")
    @Modifying
    @Transactional
    void updateStatus(@Param("status") Boolean status,@Param("id") Long id);
    
    Optional<Orders> findByStatusAndUsersId(boolean status,Long idUser);
}
