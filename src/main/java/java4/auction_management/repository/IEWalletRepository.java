package java4.auction_management.repository;

import java4.auction_management.entity.payment.EWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEWalletRepository extends JpaRepository<EWallet, Long> {

    EWallet getEWalletByAccount_Username(String username);
}
