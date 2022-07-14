package java4.auction_management.entity.cart;

import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "cartDetail",
            joinColumns = @JoinColumn(name = "cardId"),
            inverseJoinColumns = @JoinColumn(name = "productId")
    )
    private List<Product> productList;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;








}
