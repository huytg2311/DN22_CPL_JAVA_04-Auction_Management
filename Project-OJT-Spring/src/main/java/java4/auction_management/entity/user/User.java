package java4.auction_management.entity.user;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.product.Product;
import java4.auction_management.validate.DateTimeBeforeCurrent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotEmpty(message = "Full Name Not Empty")
    @NotBlank(message = "Full Name is required")
    private String fullname;

    //    @NotEmpty(message = "Email Not Empty")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
             message = "Email Invalid !")
    @NotBlank(message = "Email is required")
//    @UniqueEmail
    private String email;

    //    @NotEmpty(message = "Phone Number Not Empty")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Phone Number Invalid !")
    @NotBlank(message = "Phone Number is required")
//    @UniquePhone
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private EGender gender;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @DateTimeBeforeCurrent
    private LocalDate dayOfBirth;

    //    @NotEmpty(message = "Address Not Empty")
//    @NotBlank(message = "Address is required")
    private String address;

    //    @NotEmpty(message = "ID Card Not Empty")
//    @Pattern(regexp = "^[0-9]{9}$", message = "ID Card Invalid!")
//    @NotBlank(message = "Id Card required")
    private String idCard;

    @Column(length = 500)
//    @NotBlank(message = "Image is required")
    private String image;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bid> bidList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> cartList;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Auction> auctionList;


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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "{" +
                "\"account\":" + account.toString() +
                '}';
    }
}