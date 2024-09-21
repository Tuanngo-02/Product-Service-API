package com.service.medicine.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.medicine.model.Cart;

@Repository
public interface CartRepositoy extends JpaRepository<Cart, Long> {
    //    Optional<Cart> findByBillId(Long id);
}
