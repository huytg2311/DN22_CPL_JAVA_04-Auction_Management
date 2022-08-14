package java4.auction_management.service.impl;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.repository.IBidRepository;
import java4.auction_management.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService implements IBidService {

    @Autowired
    IBidRepository iBidRepository;

    @Override
    public List<Bid> getAll() {
        return null;
    }

    @Override
    public Optional<Bid> getById(Long id) {
        return iBidRepository.getBidByBidId(id);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Bid> listBidSort(Long auctionID) {
        return iBidRepository.listBidSort(auctionID);
    }

    @Override
    public Bid save(Bid entity) {
        return iBidRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Bid> findAllByOrderByBidPriceDesc() {
        return iBidRepository.findAllByOrderByBidPriceDesc();
    }

    @Override
    public List<Bid> findBiddingByUserId(Long userId) {
        return iBidRepository.findBiddingByUserId(userId);
    }

    public List<Bid> getAuctionsHadBeenBidByUsername(String username){
        return iBidRepository.getBidsHadBeenBidByUsername(username);
    }

}
