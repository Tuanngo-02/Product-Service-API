package com.service.medicine.reponsitory;

import com.service.medicine.model.Cart;
import com.service.medicine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepositoy extends JpaRepository<Cart, Long> {
//    Optional<Cart> findByBillId(Long id);
}
