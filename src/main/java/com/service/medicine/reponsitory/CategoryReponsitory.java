package com.service.medicine.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.medicine.model.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, String> {
    Optional<Category> findByCode(String code);
}
