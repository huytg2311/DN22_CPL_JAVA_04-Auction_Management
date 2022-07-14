package java4.auction_management.entity.user;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
//    @NotBlank(message = "Full Name is required")
    private String fullname;

//    @NotEmpty(message = "Email Not Empty")
//    @Pattern(regexp = "^[a-zA-Z][\\\\w-]+@([\\\\w]+\\\\.[\\\\w]+|[\\\\w]+\\\\.[\\\\w]{2,}\\\\.[\\\\w]{2,})$", message = "Email Invalid !")
    private String email;

//    @NotEmpty(message = "Phone Number Not Empty")
//    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Phone Number Invalid !")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private EGender gender;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @DateBeforeCurrent
    private LocalDate dayOfBirth;

//    @NotEmpty(message = "Address Not Empty")
//    @NotBlank(message = "Address is required")
    private String address;

//    @NotEmpty(message = "ID Card Not Empty")
//    @NotBlank(message = "Id Card required")
    private String idCard;

//    @Column(length = 500)
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

}