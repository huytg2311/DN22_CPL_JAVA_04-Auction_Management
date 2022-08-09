package java4.auction_management.entity.payment;

import java4.auction_management.entity.user.Account;
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
public class EWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "eWallet", cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

}
