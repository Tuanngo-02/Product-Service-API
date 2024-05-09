package com.service.medicine.reponsitory;

import com.service.medicine.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByName(String name);//kiểm tra xem file username có giống username nhập vào ko
}
