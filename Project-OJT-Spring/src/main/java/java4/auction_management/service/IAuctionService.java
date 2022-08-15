package java4.auction_management.service;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.repository.IAuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.List;
import java.util.Optional;

@Service
public interface IAuctionService extends IService<Auction, Long>{

    Auction findAuctionByProductProductId(Long productId);

    Page<Auction> findAllAuction(Pageable pageable);

    List<Auction> findAuctionsByUsername(String username);

    Page<Auction> getAllAuctionByStatus(Pageable pageable);


    Auction getAuctionByAuctionID(Long id);

}
