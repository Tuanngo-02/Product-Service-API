package com.service.medicine.reponsitory;

import com.service.medicine.model.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenReponsitory extends JpaRepository<InvalidatedToken, String> {
}
