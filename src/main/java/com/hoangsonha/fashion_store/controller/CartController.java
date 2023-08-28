package com.hoangsonha.fashion_store.controller;

import com.hoangsonha.fashion_store.entity.Product;
import com.hoangsonha.fashion_store.service.ProductService;
import com.hoangsonha.fashion_store.service.ShoppingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired private ProductService productService;
    @Autowired private ShoppingService shoppingService;

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession httpSession) {
        model.addAttribute("listShopping", shoppingService.getAll());
        model.addAttribute("totalPrice", shoppingService.totalPrice());
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        httpSession.setAttribute("listCart", shoppingService.getAll());
        httpSession.setAttribute("totalProduct", shoppingService.totalQuantity());
        httpSession.setAttribute("totalPrice", shoppingService.totalPrice());
        if(httpSession.getAttribute("userLogin") == null) {
            return "shopping_cart";
        } else {
            return "shopping_cart_user";
        }
    }

    @GetMapping("/addcart/{id}")
    public String addCart(HttpServletRequest httpServletRequest,@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if(product!=null) {
            shoppingService.addProduct(id);
        }
        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    @GetMapping("/delete/{id}")
    public String deleteCart(HttpServletRequest httpServletRequest, @PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if(product!=null) {
            shoppingService.deleteProduct(product.getId());
        }
        return "redirect:/cart";
    }

    @PostMapping("/edit/cart")
    public String editCart(@RequestParam("id") Integer id, @RequestParam("quantity") int qty) {
        Product product = productService.getProductById(id);
        shoppingService.editProduct(id, qty);
        return "redirect:/cart";
    }



/*    @GetMapping("/addcart/{id}")
    public String addCart(HttpServletRequest httpServletRequest, HttpSession httpSession, @PathVariable Integer id) { // httpservlerequest : trẩ về trang trước đó

            HashMap<Integer, Shopping> cart = (HashMap<Integer, Shopping>) httpSession.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<Integer, Shopping>();
            }
            cart = shoppingService.addProduct(id, cart);

            httpSession.setAttribute("cart", cart);
            httpSession.setAttribute("totalQuantityCart", shoppingService.totalQuantity(cart));
            httpSession.setAttribute("totalPrice", shoppingService.totalPrice(cart));


 //       return "redirect:/chi_tiet_san_pham/" + id;

        return "redirect:" +httpServletRequest.getHeader("Referer");
    }


    @GetMapping("/delete/cart/{id}")
    public String deleteCart(HttpServletRequest httpServletRequest, HttpSession httpSession, @PathVariable Integer id) { // httpservlerequest : trẩ về trang trước đó
        HashMap<Integer, Shopping> cart = (HashMap<Integer, Shopping>) httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Integer, Shopping>();
        }
        cart = shoppingService.deleteProduct(id);
        httpSession.setAttribute("cart", cart);
        httpSession.setAttribute("totalQuantityCart", shoppingService.totalQuantity(cart));
        httpSession.setAttribute("totalPrice", shoppingService.totalPrice(cart));
        return "redirect:" +httpServletRequest.getHeader("Referer");
    }

    @GetMapping("/edit/cart")
    public String editCart(HttpServletRequest httpServletRequest, HttpSession httpSession, @RequestParam("id") Integer id, @RequestParam("quantity") int quantity) { // httpservlerequest : trẩ về trang trước đó
        HashMap<Integer, Shopping> cart = (HashMap<Integer, Shopping>) httpSession.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<Integer, Shopping>();
        }
        cart = shoppingService.editProduct(id,quantity, cart);
        httpSession.setAttribute("cart", cart);
        httpSession.setAttribute("totalQuantityCart", shoppingService.totalQuantity(cart));
        httpSession.setAttribute("totalPrice", shoppingService.totalPrice(cart));
        return "redirect:" +httpServletRequest.getHeader("Referer");
    }
    @GetMapping("/thanh_toan")
    public String showThanhToan() {
        return "thanh_toan";
    }
 */
}
