package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAuctionRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionByProductProductId(Long productId);

    Auction findAuctionByUserId(Long id);

    Auction getAuctionByAuctionID(Long id);

//    @Query(value = "select a from Auction a where a.user.id = ?1")
//    List<Product> getAuctionByUserId(Long id);

    @Query(value = "select a from Auction  as a where  a.user.id = ?1")
    List<Auction> getAuctionsByUserId(Long id);

    @Query(value = "select a from Auction a where a.user.account.username = ?1 order by a.product.datePost desc ")
    List<Auction> findAuctionsByUsername(String username);

    @Query("select a from Auction as a where a.auctionStatus = 'ACCEPTED' and current_timestamp < a.finishTime order by a.finishTime asc")
    Page<Auction> getAllAuctionByStatus(Pageable pageable);

//    @Query(value = "select c.category_id, c.name, p.product_name, is_sold, list_image, auction_time, max_price, finish_time from category c left join product p on c.category_id = p.category_id left join auction a on p.auction_id = a.auctionid join (select auction_id, max(bid_price) as max_price from bid group by auction_id) as b on a.auctionid = b.auction_id where (c.name like %?1 and p.product_name like %?2 and current_timestamp < finish_time and b.max_price between ?3 and ?4  ) order by b.max_price asc", nativeQuery = true)
    @Query("select a from Auction a where a.product.category.name IN ?2  and current_timestamp < a.finishTime order by a.finishTime desc")
    Page<Auction> searchAuction(Pageable pageable, String categoryName);

}

