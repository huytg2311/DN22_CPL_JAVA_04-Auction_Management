package java4.auction_management.service;

import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.service.IService;

import java.util.Optional;

public interface IEWalletService extends IService<EWallet, Long> {

    EWallet getEWalletByAccount_Username(String username);

    @Override
    EWallet save(EWallet entity);

    @Override
    Optional<EWallet> getById(Long id);
}
