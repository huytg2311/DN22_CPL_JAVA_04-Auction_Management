package java4.auction_management.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.chat.Chat;
import java4.auction_management.entity.product.Product;
import java4.auction_management.validate.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
//    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username is required")
//    @UniqueUsername
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Column(columnDefinition = "default 'ROLE_USER'")
    @Enumerated(EnumType.STRING)
    private ERole role = ERole.ROLE_USER;

    @Column(columnDefinition = "default true")
    private boolean enable = true;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Chat> chatList;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Product> productList;

    public boolean getEnable() {
        return this.enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}