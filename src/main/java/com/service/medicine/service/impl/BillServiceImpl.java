package com.service.medicine.service.impl;

import com.service.medicine.exception.AppException;
import com.service.medicine.exception.ErrorCode;
import com.service.medicine.model.Bill;
import com.service.medicine.model.Cart;
import com.service.medicine.model.Product;
import com.service.medicine.model.User;
import com.service.medicine.reponsitory.BillReponsitory;
import com.service.medicine.reponsitory.CartRepositoy;
import com.service.medicine.reponsitory.ProductRepository;
import com.service.medicine.reponsitory.UserRepository;
import com.service.medicine.service.BillService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillServiceImpl implements BillService {
    BillReponsitory billReponsitory;
    CartRepositoy cartRepositoy;
    ProductRepository productRepository;
    UserRepository userRepository;
    @Override
    public Bill getBillDetail(Long id) {
        Optional<Bill> bill = billReponsitory.findById(id);
        for (Cart cart : bill.get().getCartItems()){
            log.info(String.valueOf(cart.getId()));
        }
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
            Optional<Product> medicine = productRepository.findById(medicineId);
            if (medicine.isPresent()) {
                Product product1 = medicine.get();
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
                productRepository.save(product1);
            }
        }
        return totalCartAmount;
    }
    public float getCartAmountAfterUpdate(List<Cart> newCartItems, String userId){
        int availableQuantity = 0;
        float singleCartAmount = 0f;
        float totalCartAmount = 0f;
        int x = 0;
        Optional<Bill> bill1 = billReponsitory.findByUserId(userId);
        for (Cart cart : newCartItems){
            for (Cart existingCart: bill1.get().getCartItems()){
                Optional<Product> medicine = productRepository.findById(existingCart.getMedicineId());
                if (Objects.equals(cart.getMedicineId(), existingCart.getMedicineId()) && medicine.isPresent()){
                    Product product1 = medicine.get();
                    if (cart.getQuantity() > existingCart.getQuantity()){
                        x = cart.getQuantity() - existingCart.getQuantity();
                        singleCartAmount = cart.getQuantity() * product1.getPrice();
                        availableQuantity = product1.getAvailableQuantity() - x ;
                    }else {
                        x = existingCart.getQuantity() - cart.getQuantity();
                        singleCartAmount = cart.getQuantity() * product1.getPrice();
                        availableQuantity = product1.getAvailableQuantity() + x;
                    }
                    totalCartAmount = totalCartAmount + singleCartAmount;
                    product1.setAvailableQuantity(availableQuantity);
                    existingCart.setQuantity(cart.getQuantity());
                    cartRepositoy.save(existingCart);
                    cart.setAmount(singleCartAmount);
                    productRepository.save(product1);
                }
            }
        }
        return totalCartAmount;
    }
}
