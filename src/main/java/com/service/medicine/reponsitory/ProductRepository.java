package com.service.medicine.reponsitory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.medicine.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    @Query("SELECT p FROM Product p WHERE (:keyword IS NULL OR p.name LIKE %:keyword%) " +
            "AND (:category IS NULL OR p.category.name LIKE %:category%) ")
    Page<Product> findAllByKeywords(Pageable pageable, @Param("keyword") String keyword, @Param("category") String category);

}
