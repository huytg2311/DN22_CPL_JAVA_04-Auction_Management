package java4.auction_management.entity.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java4.auction_management.entity.user.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    private String id;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "chat")
    @JsonIgnore
    private List<ChatDetail> chatDetails;
}
