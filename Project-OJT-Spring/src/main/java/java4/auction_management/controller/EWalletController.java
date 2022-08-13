package java4.auction_management.controller;

import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.EWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EWalletController {

    @Autowired
    EWalletService ieWalletService;

    @Autowired
    IUserService iUserService;

    @GetMapping("/myEwallet")
    public String getMoneyInEWallet(HttpServletRequest httpServletRequest, Model model) {
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        User user = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("ewallet", eWallet);
        model.addAttribute("user", user);
        return "/ewallet/get-money";
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

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute EWallet eWallet, BindingResult bindingResult, HttpServletRequest httpServletRequest, Model model) {
        User user  = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        eWallet.setAccount(user.getAccount());
        ieWalletService.save(eWallet);
        return "redirect:/myEwallet";
    }

    @PostMapping("/updateDeposit")
    public String updateDeposit(HttpServletRequest httpServletRequest) {
        EWallet eWallet = ieWalletService.getEWalletByAccount_Username(httpServletRequest.getUserPrincipal().getName());
        Double updateMoney = Double.valueOf(httpServletRequest.getParameter("updateMoney"));
        Double total = updateMoney + eWallet.getBalance();
        ieWalletService.updateDeposit(eWallet, total);
        return "redirect:/myEwallet";
    }



}
