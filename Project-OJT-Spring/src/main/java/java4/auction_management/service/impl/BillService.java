package java4.auction_management.service.impl;

import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.bill.ETransport;
import java4.auction_management.repository.IBillRepository;

import java4.auction_management.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {

    @Autowired
    public IBillRepository iBillRepository;


    @Override
    public List<Bill> getAll() {

        return iBillRepository.findAll();
    }

    @Override
    public Optional<Bill> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Bill save(Bill bill) {
        return iBillRepository.save(bill);
    }



    @Override
    public void deleteById(Long id) {

    }


    @Override
    public Bill getBillCartDetailId(Long cartDetailId) {
        return iBillRepository.getBillCartDetailId(cartDetailId);
    }
}
