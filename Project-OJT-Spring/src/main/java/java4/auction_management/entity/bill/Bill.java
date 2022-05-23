package java4.auction_management.entity.bill;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    private String id;

    private LocalDateTime timeBill;

    private String deliveryAddress;

    private String paymentMethod;

    private String shippingMethod;

    @ManyToOne
    private User user;

    @ManyToOne
    private Cart cart;
}
