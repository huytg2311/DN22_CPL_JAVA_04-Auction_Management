package java4.auction_management.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;

    private String name;

    private double reservePrice;

    private LocalTime timeAuction;

    private LocalTime timeFinish;

    private int stepPrice;

    private double currentPrice;

    private String productInfo;

    @Column(length = 500)
    private String productImage;

    private boolean isApprove;

    private String productStatus;

    @ManyToOne
    private Category category;

    @OneToOne(mappedBy = "product")
    @JsonIgnore
    private Bid bid;


}
