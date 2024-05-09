package com.service.medicine.service;

import com.service.medicine.model.Bill;

public interface BillService {
    Bill getBillDetail(Long id);

    Bill saveBill(Bill bill);

    Bill getMyBill();
}
