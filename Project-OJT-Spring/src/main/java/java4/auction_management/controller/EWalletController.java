package java4.auction_management.controller;

import java4.auction_management.entity.payment.EType;
import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.entity.payment.Transaction;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAuctionService;
import java4.auction_management.service.ITransactionService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.EWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class EWalletController {

    @Autowired
    EWalletService ieWalletService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IAuctionService iAuctionService;

    @Autowired
    ITransactionService transactionService;

    @GetMapping("/EWallet/myEwallet")
    public String getMoneyInEWallet(HttpServletRequest httpServletRequest, Model model) {
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        User user = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        List<Transaction> transactionList = transactionService.findTransactionsByUsername(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("transactions", transactionList);
        model.addAttribute("ewallet", eWallet);
        model.addAttribute("user", user);
        return "/ewallet/e-wallet";
    }

    @GetMapping("/deposit")
    public String formDeposit(HttpServletRequest httpServletRequest, Model model) {
        User user = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("depositGetID", eWallet);
        model.addAttribute("depositEmpty", new EWallet());
        model.addAttribute("user", user);
        return "/ewallet/deposit";
    }

    @GetMapping("/updateDrawMoney")
    public String formdrawMoney(HttpServletRequest httpServletRequest, Model model) {
        User user = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("depositGetID", eWallet);
        model.addAttribute("depositEmpty", new EWallet());
        model.addAttribute("user", user);
        return "/ewallet/draw-money";
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute EWallet eWallet, BindingResult bindingResult, HttpServletRequest httpServletRequest, Model model) {
        User user  = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        eWallet.setAccount(user.getAccount());
        ieWalletService.save(eWallet);
        Transaction transaction = new Transaction();
        transaction.setEWallet(eWallet);
        transaction.setEType(EType.ADD);
        transactionService.save(transaction);
        return "redirect:/myEwallet";
    }

    @PostMapping("/updateDeposit")
    public String updateDeposit(HttpServletRequest httpServletRequest) {
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        Double updateMoney = Double.valueOf(httpServletRequest.getParameter("updateMoney"));
        Double total = updateMoney + eWallet.getBalance();
        ieWalletService.updateDeposit(eWallet, total);
        Transaction transaction = new Transaction();
        LocalDateTime localDateTime = LocalDateTime.now();
        transaction.setDateTransaction(localDateTime);
        transaction.setAmount(updateMoney);
        transaction.setEWallet(eWallet);
        transaction.setEType(EType.ADD);
        transactionService.save(transaction);
        return "redirect:/myEwallet";
    }

    @PostMapping("/updateDrawMoney")
    public String drawMoney(HttpServletRequest httpServletRequest, Model model) {
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        Double updateMoney = Double.valueOf(httpServletRequest.getParameter("updateMoney"));
        Double total = eWallet.getBalance() - updateMoney;
        ieWalletService.updateDeposit(eWallet, total);
        Transaction transaction = new Transaction();
        LocalDateTime localDateTime = LocalDateTime.now();
        transaction.setDateTransaction(localDateTime);
        transaction.setAmount(updateMoney);
        transaction.setEWallet(eWallet);
        transaction.setEType(EType.WITHDRAW);
        transactionService.save(transaction);
        return "redirect:/myEwallet";
    }



}
