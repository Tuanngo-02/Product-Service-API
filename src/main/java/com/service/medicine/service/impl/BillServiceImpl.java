package com.service.medicine.service.impl;

import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.mapper.UserMapper;
import com.service.medicine.model.Bill;
import com.service.medicine.model.Cart;
import com.service.medicine.model.Medicine;
import com.service.medicine.model.User;
import com.service.medicine.reponsitory.BillReponsitory;
import com.service.medicine.reponsitory.InvalidatedTokenReponsitory;
import com.service.medicine.reponsitory.MedicineRepository;
import com.service.medicine.reponsitory.UserRepository;
import com.service.medicine.service.BillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillServiceImpl implements BillService {
    BillReponsitory billReponsitory;
    MedicineRepository medicineRepository;
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public Bill getBillDetail(Long id) {
        Optional<Bill> bill = billReponsitory.findById(id);
        return bill.isPresent()? bill.get() : null;
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billReponsitory.save(bill);
    }

    @Override
    public Bill getMyBill() {
        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Optional<Bill> bill = billReponsitory.findByUserId(user.getId());

        return bill.isPresent() ? bill.get() : null;
    }

    public float getCartAmount(List<Cart> carts){
        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for (Cart cart : carts) {
            Long medicineId = cart.getMedicineId();
            Optional<Medicine> medicine = medicineRepository.findById(medicineId);
            if (medicine.isPresent()) {
                Medicine product1 = medicine.get();
                if (product1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = product1.getPrice() * product1.getAvailableQuantity();
                    cart.setQuantity(product1.getAvailableQuantity());
                } else {
                    singleCartAmount = cart.getQuantity() * product1.getPrice();
                    availableQuantity = product1.getAvailableQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setMedicineName(product1.getName());
                cart.setAmount(singleCartAmount);
                medicineRepository.save(product1);
            }
        }
        return totalCartAmount;
    }
}
