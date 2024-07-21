package com.service.medicine.reponsitory;

import com.service.medicine.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, String> {
    Optional<Category> findByCode(String code);
}
