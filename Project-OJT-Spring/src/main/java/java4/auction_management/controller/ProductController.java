package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.category.Category;
import java4.auction_management.entity.product.Product;
import java4.auction_management.service.IBidService;
import java4.auction_management.service.ICategoryService;
import java4.auction_management.service.impl.ProductService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IBidService iBidService;

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return iCategoryService.getAll();
    }

    @GetMapping("/create")
    public String createFormProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/products/create-product";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                @RequestParam("file") MultipartFile[] files) throws IOException {

//        if (file.isEmpty()) {
//
//            return "redirect:/create";
//        }
        try {
            StringBuilder listImage = new StringBuilder();
            for (MultipartFile file : files) {

                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                System.out.println("2" + (uploadResult.get("url").toString()));
                listImage.append(uploadResult.get("url").toString()).append(" ");
            }
            product.setListImage(listImage.toString());
            productService.save(product);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/create";
        }

        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/index2";
    }

    @GetMapping("/load/{id}")
    public String load(@PathVariable("id") Product product, Model model) {
        List<Bid> bidList = iBidService.listBidSort(product.getProductId());
        model.addAttribute("bids", bidList);
        model.addAttribute("product", product);
        String[] listImages = product.getListImage().split(" ");
        model.addAttribute("listImages", listImages);
        return "products/post";

    }

    // ajax controller history bid
    @RequestMapping(value = "/loadBid/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = {"application/json"})

    public @ResponseBody ResponseEntity<Object> sortListBid(@PathVariable("id") Product product) {
        List<Bid> bidList = iBidService.listBidSort(product.getProductId());
        List<JSONObject> entities = new ArrayList<>();

        for (Bid b : bidList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", b.getProduct().getProductId());
            entities.add(jsonObject);
        }
        System.out.println(product.getProductId());
        System.out.println(iBidService.listBidSort(product.getProductId()));
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
}
