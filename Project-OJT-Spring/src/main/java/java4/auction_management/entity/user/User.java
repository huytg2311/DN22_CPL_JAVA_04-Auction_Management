package java4.auction_management.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bid> bidList;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private  List<Cart> cartList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private  List<Bill> billLlist;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Transient
    public String getImagePath() {
        if (image == null || id == null) return null;
        return "/images/" + id + "/" + image;
    }
}