package com.service.medicine.reponsitory;

import com.service.medicine.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    boolean existsByName (String name);
}
