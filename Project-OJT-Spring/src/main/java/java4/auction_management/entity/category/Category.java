package java4.auction_management.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> productList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private  List<Bill> billList;




}
