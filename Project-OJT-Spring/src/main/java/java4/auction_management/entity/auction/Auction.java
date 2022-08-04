package java4.auction_management.entity.auction;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionID;

    private int auctionTime;

    private LocalDateTime finishTime;

    @OneToOne(mappedBy = "auction")
    private Product product;

    private double reservePrice;

    private double stepPrice;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bid> bidList;

    private String status;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



}
