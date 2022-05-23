package java4.auction_management.entity.bid;

import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
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
public class Bid {

    @Id
    private String id;

    private LocalTime bidTime;

    private double bidPrice;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne
    private User user;

    private String addressBid;
}
