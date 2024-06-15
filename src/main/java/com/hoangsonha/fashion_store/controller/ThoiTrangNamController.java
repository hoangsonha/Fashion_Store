package com.hoangsonha.fashion_store.controller;

import com.hoangsonha.fashion_store.entity.Product;
import com.hoangsonha.fashion_store.service.ProductService;
import com.hoangsonha.fashion_store.service.ShoppingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ThoiTrangNamController {
    @Autowired private ProductService productService;
    @Autowired private ShoppingService shoppingService;
    @GetMapping("/thoi_trang_nam")
    public String showThoiTrangNamPage(Model model) {
        List<Product> productList = productService.getThoiTrangNam();
        model.addAttribute("productList",productList);
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        return "thoi_trang_nam";
    }
    @GetMapping("/login/thoi_trang_nam")
    public String loginOfThoiTrangNam(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user_Login") != null){
            List<Product> productList = productService.getThoiTrangNam();
        model.addAttribute("productList", productList);
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        return "login_ttn";
        }
        return "redirect:/thoi_trang_nam";
    }

    @GetMapping("/tim_kiem_nam")
    public String searchKeyInNam(Model model,@RequestParam("keyword") String key)  {
        List<Product> productList = productService.getProductByKeyInNam(key);
        model.addAttribute("productList", productList);
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        model.addAttribute("valueKeyword", key);
        model.addAttribute("message_key_2", "Tìm thấy " + productList.size() + " sản phẩm liên quan đến từ \"" + key + "\"");
        return "thoi_trang_nam";
    }
}
