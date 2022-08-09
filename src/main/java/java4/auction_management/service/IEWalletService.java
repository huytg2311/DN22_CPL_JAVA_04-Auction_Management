package java4.auction_management.service;

import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.service.IService;

public interface IEWalletService extends IService<EWallet, Long> {

    EWallet getEWalletByAccount_Username(String username);

}
