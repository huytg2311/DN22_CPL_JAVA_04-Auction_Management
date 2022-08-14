package java4.auction_management.entity.bill;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    private LocalDateTime timeBill;

    @NotBlank(message = "Delivery Address is required")
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private EShipping shippingMethod;

    @Column(columnDefinition = "default 'WAITING'")
    @Enumerated(EnumType.STRING)
    private ETransport eTransport = ETransport.WAITING;

    @OneToOne(cascade = CascadeType.ALL)
    private CartDetail cartDetail;


}
