package com.hoangsonha.fashion_store.controller;

import com.hoangsonha.fashion_store.entity.*;
import com.hoangsonha.fashion_store.service.BillDetailProductService;
import com.hoangsonha.fashion_store.service.BillService;
import com.hoangsonha.fashion_store.service.ShoppingService;
import com.hoangsonha.fashion_store.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
public class BillController {
    @Autowired private BillService billService;
    @Autowired private ShoppingService shoppingService;
    @Autowired private UserService userService;
    @Autowired private BillDetailProductService billDetailProductService;

    @GetMapping("/thanh_toan")
    public String showFormThanhToan(Model model, HttpSession httpSession) {
        Bill bill = new Bill();
        User user = (User) httpSession.getAttribute("userLogin");
        if(user == null) {
            model.addAttribute("bill", new Bill());
            return "thanh_toan";
        } else {
            bill.setName(user.getFirstName() + " " + user.getLastName());
            bill.setEmail(user.getEmail());
            bill.setPhone(user.getPhoneNumber());
            model.addAttribute("bill", bill);
            return "thanh_toan_user";
        }
    }

    @GetMapping("/hoan_tat_thanh_toan")
    public String thanhToanThanhCong(HttpSession httpSession) {
        if(httpSession.getAttribute("userLogin") != null) {
            return "hoan_tat_thanh_toan_user";
        }
        return "hoan_tat_thanh_toan";
    }

    @PostMapping("/bill/save")
    public String saveBill(@ModelAttribute("bill") Bill bill, HttpSession httpSession, Model model, RedirectAttributes redirectAttributes) {
        int temp = (int) httpSession.getAttribute("totalProduct");
        bill.setQuantity((Integer) httpSession.getAttribute("totalProduct"));
        bill.setTotal((Integer) httpSession.getAttribute("totalPrice"));

        if(temp == 0) {
            model.addAttribute("message5", "Trong giỏ hàng k có sản phẩm nào, vui lòng thêm sản phẩm");
            redirectAttributes.addFlashAttribute("message5","Trong giỏ hàng k có sản phẩm nào, vui lòng thêm sản phẩm" );
            return "redirect:/thanh_toan";
        }
            if (billService.saveBill(bill) == 1) {
                Collection<Shopping> cart = (Collection<Shopping>) httpSession.getAttribute("listCart");
                billDetailProductService.addBillDetail(cart, bill.getEmail());
                httpSession.removeAttribute("listCart");
            }
            return "redirect:/hoan_tat_thanh_toan";
        }

    @GetMapping("/bill/delete/{id}")
    public String deleteBill(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("Hello everyone");
        return "login_ttnu";
    }

}
