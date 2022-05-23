package java4.auction_management.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String fullname;

    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    private LocalDate dayOfBirth;

    private String address;

    private String idCard;

    @Column(length = 500)
    private String image;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Bid> bidList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private  List<Cart> cartList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private  List<Bill> billLlist;
}
