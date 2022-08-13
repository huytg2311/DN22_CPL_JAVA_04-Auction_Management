package java4.auction_management.entity.auction;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionID;

    @Min(value = 1, message = "time must over 0 hour")
    private int auctionTime;

    private LocalDateTime finishTime;

    @Column(columnDefinition = "default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private EStatus auctionStatus = EStatus.WAITING;

    @OneToOne(mappedBy = "auction")
    private Product product;

    @Min(value = 0, message = "Price must over 0$")
    private double reservePrice;

    @Min(value = 1, message = "Step price must over 1")
    private double stepPrice;

    private boolean isFinish;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Bid> bidList;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;



}
