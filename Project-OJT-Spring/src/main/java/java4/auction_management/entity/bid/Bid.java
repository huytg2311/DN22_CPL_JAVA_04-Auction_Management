package java4.auction_management.entity.bid;

import com.fasterxml.jackson.annotation.JsonFormat;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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

    @JoinColumn(name = "productId")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
