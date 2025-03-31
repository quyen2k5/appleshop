package com.example.WebBanHang.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.WebBanHang.Dao.CategoryDao;
import com.example.WebBanHang.Dao.ProductDao;
import com.example.WebBanHang.Dao.UserDao;
import com.example.WebBanHang.model.Category;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.User;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/")
public class Home {
    UserDao userdao;
    @Autowired
    ProductDao productDao;
    @Autowired
    CategoryDao categoryDao;
    @GetMapping("/thienduonganmac")
    public String Thienduonganmac(Model model ,HttpSession session){      
        User user = (User)  session.getAttribute("ShowUser");
        model.addAttribute("ShowUser", user);
        List<Category> cate = categoryDao.findAll();
        model.addAttribute("category", cate);
        List<Product> prod = productDao.findByIsdelete(false);
        model.addAttribute("prod", prod);
        return "form/Home"; 
    }
}
    


