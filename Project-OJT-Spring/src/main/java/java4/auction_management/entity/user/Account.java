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

    @Enumerated(EnumType.STRING)
    private ERole role;

    private boolean enable;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    public boolean getEnable() {
        return this.enable;
    }

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Chat> chatList;
}
