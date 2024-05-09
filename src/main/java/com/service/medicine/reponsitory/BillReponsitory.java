package com.service.medicine.reponsitory;

import com.service.medicine.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillReponsitory extends JpaRepository<Bill, Long> {
    Optional<Bill> findByUserId(String id);
}
