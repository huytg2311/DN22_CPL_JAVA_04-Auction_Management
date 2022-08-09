package java4.auction_management.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDetail {

    @Id
    private LocalDateTime timeChat;

    private String content;

    @JoinColumn(name = "chatId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Chat chat;
}
