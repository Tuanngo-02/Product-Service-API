package com.service.medicine.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.medicine.model.InvalidatedToken;

@Repository
public interface InvalidatedTokenReponsitory extends JpaRepository<InvalidatedToken, String> {}
