package java4.auction_management.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.category.Category;
import java4.auction_management.validate.DateTimeBeforeCurrent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producId;

    @NotEmpty(message = "Name not empty")
    private String name;

    private int reservePrice;

    private int timeAuction;

    private LocalDateTime timeFinish;

    private int stepPrice;

    private int currentPrice;

    private String productInfo;

    @Column(length = 500)
    private String productImage;

    private boolean isApprove;

    private String productStatus;

    @JoinColumn(name = "categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonBackReference
    private Bid bid;

    @Transient
    public String getImagePath() {
        if (productImage == null || producId == null) return null;
        return "/images/products/" + producId + "/" + productImage;
    }

}
