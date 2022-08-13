package java4.auction_management.entity.bid;

import com.fasterxml.jackson.annotation.JsonFormat;
import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss', 'dd/MM/yyyy")
    private LocalDateTime bidTime;

    private double bidPrice;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @OneToOne(mappedBy = "bid")
    @NotFound(action= NotFoundAction.IGNORE)
    private CartDetail cartDetail;

    @Override
    public String toString() {
        return '{' +
                "\"bidId\":" + bidId +
                ", \"bidPrice\":" + bidPrice +
                ", \"user\":" + user.toString() +
                '}';
    }
}
