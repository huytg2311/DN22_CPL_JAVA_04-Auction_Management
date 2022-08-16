package java4.auction_management.controller;

import java4.auction_management.entity.bill.Bill;

import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.service.IBillService;
import java4.auction_management.service.impl.BillService;
import java4.auction_management.service.impl.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService iBillService;

    @Autowired
    CartDetailService cartDetailService;

    @GetMapping("/billConfirm/{billId}")
    public String showBillConfirmForm(@PathVariable("billId") Long billId, Model model) {
        Bill bill = iBillService.getById(billId).orElseThrow(() -> {
            throw  new IllegalStateException("No bill was found by id: " + billId);
        });
        model.addAttribute("bill", bill);
        return "/bill/bill-confirm";
    }


    @PostMapping("/billConfirm")
    public String editBillConfirm(@Valid @ModelAttribute  Bill bill, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        String cartDetailID = request.getParameter("cartdetailid");
        CartDetail cartDetail = cartDetailService.getCartDetailByCartDetailID(Long.parseLong(cartDetailID));
        bill.setCartDetail(cartDetail);

        iBillService.save(bill);
        return "redirect:/cart";

    }


}
