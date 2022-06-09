package java4.auction_management.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.chat.Chat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String username;

    private String password;

    @Column(columnDefinition = "default 'ROLE_USER'")
    @Enumerated(EnumType.STRING)
    private ERole role = ERole.ROLE_USER;

    @Column(columnDefinition = "default true")
    private boolean enable = true;

    @OneToOne(mappedBy = "account")
    private User user;

    public boolean getEnable() {
        return this.enable;
    }

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Chat> chatList;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}