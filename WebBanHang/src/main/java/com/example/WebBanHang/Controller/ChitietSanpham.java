package com.example.WebBanHang.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.WebBanHang.Dao.ProductDao;
import com.example.WebBanHang.Dao.ReviewDao;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.model.Review;
import com.example.WebBanHang.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ChitietSanpham {
  @Autowired
  ProductDao productDao;

  @Autowired
  ReviewDao reviewDao;

  @GetMapping("/ChitietSanpham")
  public String Chitietsanpham(Model model, @RequestParam("id") Integer id) {
    // Lấy thông tin sản phẩm
    Optional<Product> product = productDao.findById(id);
    if (product.isPresent()) {
      model.addAttribute("pro", product.get());
    } else {
      model.addAttribute("message", "Sản phẩm không tồn tại.");
      return "error/404"; // Chuyển hướng đến trang lỗi
    }
    model.addAttribute("pro", product.get());

    // Lấy danh sách đánh giá cho sản phẩm
    List<Review> reviews = reviewDao.findByProductId(id);
    model.addAttribute("reviews", reviews);

    

    return "form/ChitietSanpham";
  }

  @PostMapping("/ChitietSanpham")
  public String chitetsanphampost(Model model, @RequestParam("id") Integer id) {
    Optional<Product> product = productDao.findById(id);
    if (product.isPresent()) {
      model.addAttribute("pro", product.get());
    }
    return "form/ChitietSanpham";
  }

  @PostMapping("/Sanpham/danhgia")
  public String submitReview(@RequestParam("productId") Integer productId,
      @RequestParam("rating") Integer rating,
      @RequestParam("comment") String comment,
      HttpSession session) {
    // Lấy đối tượng User từ session
    User user = (User) session.getAttribute("ShowUser");
    if (user == null) {
      return "redirect:/form/Dangnhap"; // Chuyển hướng đến trang đăng nhập
    }

    // Tạo mới một đánh giá
    Review review = new Review();
    review.setProductId(productId);
    review.setRating(rating);
    review.setComment(comment);
    review.setUsername(user.getFullname()); // Lấy fullname từ đối tượng User
    review.setCreatedAt(new java.util.Date());

    // Lưu đánh giá vào cơ sở dữ liệu
    reviewDao.save(review);

    // Redirect về trang chi tiết sản phẩm
    return "redirect:/ChitietSanpham?id=" + productId;
  }
}