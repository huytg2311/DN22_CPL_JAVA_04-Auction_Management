package java4.auction_management.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.category.Category;

import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotEmpty(message = "Name not empty")
    private String name;

    private int reservePrice;

    private int timeAuction;

    private LocalDateTime timeFinish;

    private int stepPrice;

    private int currentPrice;

    private String productInfo;

    @Column(length = 1000)
    private String listImage;

    private boolean isApprove;

    @Column(columnDefinition = "default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private EStatus productStatus = EStatus.WAITING;

    @JoinColumn(name = "categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Bid> bid;

    @JoinColumn(name = "username")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", reservePrice=" + reservePrice +
                ", timeAuction=" + timeAuction +
                ", timeFinish=" + timeFinish +
                ", stepPrice=" + stepPrice +
                ", currentPrice=" + currentPrice +
                ", productInfo='" + productInfo + '\'' +
                '}';
    }

    //    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Image> imageList;

}
