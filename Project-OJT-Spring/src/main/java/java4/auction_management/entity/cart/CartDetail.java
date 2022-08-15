package java4.auction_management.entity.cart;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailID;

    @JoinColumn(name = "cartId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;

    @JoinColumn(name = "productId")
    @OneToOne(cascade = CascadeType.MERGE)
    private Product product;

    @OneToOne
    @NotFound(action= NotFoundAction.IGNORE)
    private Bid bid;

    @OneToOne(mappedBy = "cartDetail")
    private Bill bill;

    @Override
    public String toString() {
        return "{" +
                "\"cartDetailID\":" + cartDetailID +
                ", \"product\":" + product.toString() +
                ", \"bid\":" + bid.toString() +
                '}';
    }
}