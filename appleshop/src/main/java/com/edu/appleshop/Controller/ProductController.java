package com.exe.shojin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exe.shojin.model.Product;
import com.exe.shojin.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/{productID}")
    public String proDuctDetails(@PathVariable("productID") String productID, Model model){
        Product product = productService.findByID(productID);
        // if (product == null) {
        //     return "redirect:/"; 
        // }
        model.addAttribute("product", product);
        return "productdetails";
    }


    @PostMapping("/search")
    public String search(RedirectAttributes redirectAttributes,
        @RequestParam(name = "searchNameProduct", required = false) String searchNameProduct,
        @PageableDefault(direction = Direction.ASC, page = 0, sort = "productName") Pageable pageable) {
        
        System.out.println("searchNameProduct: " + searchNameProduct);
        var productPage = productService.searchName("%" + searchNameProduct + "%", pageable);
        redirectAttributes.addFlashAttribute("productPage", productPage);
        return "redirect:/index/home2";
    }

   
}
