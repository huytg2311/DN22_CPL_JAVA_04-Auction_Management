package java4.auction_management.controller;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.service.impl.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/bid")
public class BidController {

    @Autowired
    BidService bidService;

    @RequestMapping(value = "/createBid", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
    @ResponseBody
    public Bid createBid(@RequestBody Bid bid) {
        return bidService.save(bid);
    }
}
