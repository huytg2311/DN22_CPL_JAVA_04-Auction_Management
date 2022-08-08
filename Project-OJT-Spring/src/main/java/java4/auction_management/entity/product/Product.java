package java4.auction_management.entity.product;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.auction.EStatus;
import java4.auction_management.entity.category.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @NotEmpty(message = "Name not empty")
    private String productName;

    private String description;

    @Column(length = 1000)
    private String listImage;

    private LocalDateTime datePost;

    private boolean isSold;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private Auction auction;






    //    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Image> imageList;

}