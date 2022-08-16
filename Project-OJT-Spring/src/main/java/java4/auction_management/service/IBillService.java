package java4.auction_management.service;

import java4.auction_management.entity.bill.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBillService extends IService<Bill, Long> {
    Bill getBillCartDetailId(Long cartDetailId);

    List<Bill> getAllBill();

}
