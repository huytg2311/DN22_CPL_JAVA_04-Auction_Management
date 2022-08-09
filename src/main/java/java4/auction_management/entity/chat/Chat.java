package java4.auction_management.entity.chat;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @JoinColumn(name = "account_username")
    @ManyToOne(fetch =  FetchType.EAGER)
    private Account account;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<ChatDetail> chatDetails;
}
