package java4.auction_management.service.impl;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.repository.IAuctionRepository;
import java4.auction_management.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private IAuctionRepository iAuctionRepository;

    @Override
    public Auction findAuctionByProductProductId(Long productId) {
        return iAuctionRepository.findAuctionByProductProductId(productId);
    }

    @Override
    public Page<Auction> findAllAuction(Pageable pageable) {
        return iAuctionRepository.findAll(pageable);
    }



    @Override
    public Page<Auction> getAllAuctionByStatus(Pageable pageable) {
        return iAuctionRepository.getAllAuctionByStatus(pageable);
    }

    @Override
    public List<Auction> getAll() {
        return iAuctionRepository.findAll();
    }

    @Override
    public Optional<Auction> getById(Long id) {
        return iAuctionRepository.findById(id);
    }

    @Override
    public List<Auction> findAuctionsByUsername(String username){
        return iAuctionRepository.findAuctionsByUsername(username);
    }

    @Override
    public Auction save(Auction entity) {
        return iAuctionRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Auction getAuctionByAuctionID(Long id) {
        return iAuctionRepository.getAuctionByAuctionID(id);
    }



//    @Override
//    public List<Auction> getAuctionsByUserId(Long id) {
//        return iAuctionRepository.getAuctionsByUserId(id);
//
//    }

}
