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

    @Column(columnDefinition = "default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private EStatus auctionStatus = EStatus.WAITING;

    @OneToOne(mappedBy = "auction")
    private Product product;

    private double reservePrice;

    private double stepPrice;

    private boolean isFinish;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bid> bidList;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



}
