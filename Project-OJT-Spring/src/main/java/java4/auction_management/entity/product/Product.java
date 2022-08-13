package java4.auction_management.entity.product;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.auction.EStatus;
import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.category.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "product name not empty")
    private String productName;

    @NotBlank(message = "description not empty")
    private String description;

    @Column(length = 1000)
    private String listImage;

    private LocalDateTime datePost;

    private boolean isSold;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @OneToOne(mappedBy = "product")
    private CartDetail cartDetail;

    @Override
    public String toString() {
        return "{" +
                "\"productId\":" + productId +
                ", \"productName\": \"" + productName + '\"' +
                '}';
    }
}