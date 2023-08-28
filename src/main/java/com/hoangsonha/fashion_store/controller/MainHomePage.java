package com.hoangsonha.fashion_store.controller;

import com.hoangsonha.fashion_store.entity.Product;
import com.hoangsonha.fashion_store.entity.User;
import com.hoangsonha.fashion_store.service.ProductService;
import com.hoangsonha.fashion_store.service.ShoppingService;
import com.hoangsonha.fashion_store.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainHomePage {
    @Autowired private UserService userService;
    @Autowired private ProductService productService;
    @Autowired private ShoppingService shoppingService;


    /*

    @ModelAttribute("userdto")
       public User use(){
           return new User();
    }

    */

    @GetMapping("")
    public String showHomePage(Model model, HttpSession httpSession) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        httpSession.setAttribute("list_ao", productService.getProductByKind_ao());
        httpSession.setAttribute("list_quan", productService.getProductByKind_quan());
        httpSession.setAttribute("list_doboi", productService.getProductByKind_doboi());
        httpSession.setAttribute("list_phukien", productService.getProductByKind_phukien());
        httpSession.setAttribute("list_giay", productService.getProductByKind_giay());
        httpSession.setAttribute("list_dam", productService.getProductByKind_dam());
        return "index";
    }

    @GetMapping("/tim_kiem")
    public String showHomePage(Model model, @RequestParam("keyword") String key, HttpSession httpSession) {
        List<Product> productList = productService.getProductByKey(key);
        model.addAttribute("productList", productList);
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        model.addAttribute("valueKeyword", key);
        model.addAttribute("message_key", "Tìm thấy " + productList.size() + " sản phẩm liên quan đến từ \"" + key + "\"");
        httpSession.setAttribute("valueKeyword", key);
        if(httpSession.getAttribute("userLogin")==null) {
            return "index";
        } else {
            return "index_user";
        }
    }

    @GetMapping("/dang_ki_tai_khoan")
    public String showFormDangKiTaiKhoan(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        return "form_dang_ki_tai_khoan";
    }

    @PostMapping("/save")
    public String saveUSer(User user, RedirectAttributes redirectAttributes) {
        if(userService.checkEmailExist(user.getEmail())) {
            redirectAttributes.addFlashAttribute("message", "Đăng kí thất bại do Email đã tồn tại. Vui lòng nhập lại Email");
            return "redirect:/dang_ki_tai_khoan";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("message", "Tạo tài khoản thành công");
        return "redirect:/dang_ki_tai_khoan";
    }

    @GetMapping("/dang_nhap")
    public String showFormDangNhap(Model model) {
        model.addAttribute("user10", new User());
        model.addAttribute("totalProduct", shoppingService.totalQuantity());
        return "form_dang_nhap";
    }

    @GetMapping("/login/user")
    public String loginOfUser(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("userLogin") !=null) {
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("productList", productList);
            model.addAttribute("totalProduct", shoppingService.totalQuantity());
            httpSession.setAttribute("list_ao", productService.getProductByKind_ao());
            httpSession.setAttribute("list_quan", productService.getProductByKind_quan());
            httpSession.setAttribute("list_doboi", productService.getProductByKind_doboi());
            httpSession.setAttribute("list_phukien", productService.getProductByKind_phukien());
            httpSession.setAttribute("list_giay", productService.getProductByKind_giay());
            httpSession.setAttribute("list_dam", productService.getProductByKind_dam());
            return "index_user";
        }
        return "redirect:/dang_nhap";
    }

    @PostMapping("/login")
    public String showLogin(User user, RedirectAttributes redirectAttributes, Model model, HttpSession httpSession) {

        if(userService.checkUserByEmail(user.getEmail())==false) {
            redirectAttributes.addFlashAttribute("message1", "Thông tin email hoặc mật khẩu không chính xác");
            return "redirect:/dang_nhap";
        }
        if(userService.checkUserByPassword(user.getEmail(),user.getPassword())==false) {
            redirectAttributes.addFlashAttribute("message1", "Thông tin email hoặc mật khẩu không chính xác");
            return "redirect:/dang_nhap";
        }
        if(userService.checkUser(user.getEmail(), user.getPassword())) {
            if(userService.checkRole(user.getEmail())==1) {
                httpSession.setAttribute("user",user.getEmail());
                List<Product> productList = productService.getAllProducts();
                model.addAttribute("productList", productList);
                return "admin_page";
            } else {
                if(userService.checkRole(user.getEmail())==0) {
                    user.setLastName(userService.getLastName(user.getEmail()));
                    user.setFirstName(userService.getFirstName(user.getEmail()));
                    user.setPhoneNumber(userService.getPhone(user.getEmail()));
                    httpSession.setAttribute("userLogin", user);
                    List<Product> productList = productService.getAllProducts();
                    model.addAttribute("productList", productList);
                    return "redirect:/login/user";
                }
            }
        }
        return "redirect:/dang_nhap";
    }

    @GetMapping("/logout")
    public String showLogoutPage(HttpSession httpSession) {
        if(httpSession.getAttribute("user") != null) {
            httpSession.removeAttribute("user");
        }
        if(httpSession.getAttribute("userLogin") !=null) {
            httpSession.removeAttribute("userLogin");
        }
        return "redirect:/dang_nhap";
    }

    @GetMapping("/admin/new")
    public String showAdminAddNewProduct(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("product", new Product());
            model.addAttribute("title", "Tạo sảm phẩm mới");

            return "form_add_new_product";
        }
        return "redirect:/dang_nhap";
    }

    @PostMapping("/admin/save")
    public String saveProduct(Model model, Product product, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("product", productService.save(product));
            redirectAttributes.addFlashAttribute("message3", "Sản phẩm với id " + product.getId() + " đã được thêm, sửa thành công");
            return "redirect:/login";
        }
        return "redirect:/dang_nhap";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("user") !=null) {
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("productList", productList);
            return "admin_page";
        }
        return "redirect:/dang_nhap";
    }

    @GetMapping("/admin/edit/{id}")

    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        if(httpSession.getAttribute("user") !=null) {
            Product product1 = productService.getProductById(id);
            Product product2 = new Product(product1.getId(), product1.getProductName(), product1.getProductYear(), product1.getPrice(), product1.getDetail(), product1.getKind(), product1.getImg());
            productService.save(product2);
            model.addAttribute("product", product2);
            model.addAttribute("title", "Đang sửa sản phẩm với id " + id);
            return "form_add_new_product";
        }
        return "redirect:/dang_nhap";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        if(httpSession.getAttribute("user") !=null) {
            redirectAttributes.addFlashAttribute("message3", "Đã xoá thành công sản phẩm với id " + id);
            productService.deleteProduct(id);
            return "redirect:/login";
        }
        return "redirect:/dang_nhap";
    }


    @GetMapping("/image/{img:.+}")    // .+ : là phần đuôi của ảnh (png, ...)

    public ResponseEntity<byte[]> readDetailFile(@PathVariable("img") String img) {
        try {
            byte[] bytes = productService.readImageContent(img);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }catch(Exception e) {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/chi_tiet_san_pham/{id}")
    public String showDetailProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "chi_tiet_san_pham";
    }

    // Session : lưu vào session khi ng dùng thêm giỏ hàng và k lưu vài database vì sẽ tràn data

    // session : luw trữ data trên server và mất khi đóng trình duyệt

    // cookie : lưu trữ data trên client và có sẵn trong trình duyệt  -> dễ bị đánh cắp data

    // ajax : k load lại trang

}

/*


-----------------------------------------------------(+) quantity (-)----------------------------------------------------------------------
|
|    <td style="display: inline-block">
|              <button type="submit" th:href="@{/edit/cart}" class="page-link minusButton" th:pid="${cart.product.id}">-</button>
|              <input type="text" th:value="${cart.quantity}" name="qty" th:id="quantity" style="width: 70px;" onkeydown="return false">
|              <button type="submit" th:href="@{/edit/cart}" class="page-link plusButton" th:pid="${cart.product.id}">+</button>
|    </td>
|
|
| <script type="text/javascript">
|
|  $(document).ready(function () {
|    $(".minusButton").on("click", function (evt) {
|      evt.preventDefault();
|      decreaseQuantity($(this));
|    });
|    $(".plusButton").on("click", function (evt) {
|      evt.preventDefault();
|      increaseQuantity($(this));
|    });
|  });
|
|
|  function decreaseQuantity(link) {
|    let productId = link.attr("pid");
|    let quantity = $("#quantity");
|
|    let newQuantity = parseInt(quantity.val()) - 1;
|
|    if (newQuantity > 0) {
|      quantity.val(newQuantity)
|    }
|  }
|
|    function increaseQuantity(link) {
|      let productId = link.attr("pid");
|      let quantity = $("#quantity");
|
|      let newQuantity = parseInt(quantity.val()) + 1;
|
|
|      if (newQuantity < 11) {
|        quantity.val(newQuantity)
|      }
|    }
|
| </script>
|
-------------------------------------------------------------------------------------------------------------------------------------------



------------------------------------------------------- Lấy giá trị ----------------------------------------------------------------------
<script>
  function login() {
          const email = document.getElementById("email").value();
          const password = document.getElementById("password").value();

          }

          function login1() {
          var email = document.getElementById("email").value();
          var password = document.getElementById("password").value();

          }

</script>


--------------------------------------------------- End lấy giá trị -----------------------------------------------------------------------



 */