package java4.auction_management.entity.cart;

import java4.auction_management.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

}
