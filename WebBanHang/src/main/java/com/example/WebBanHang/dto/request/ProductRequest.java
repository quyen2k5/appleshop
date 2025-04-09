package com.example.WebBanHang.dto.request;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Tiêu đề không được để trống!")
    @Length(min = 3, max = 300, message = "Tiêu đề phải từ 3 đến 300 kí tự!")
    private String title;
    @NotNull(message = "Ảnh không được để trống!")
    private MultipartFile img;
    @NotNull(message = "Giá tiền không được để trống!")
    @Positive(message = "Giá tiền phải lớn hơn 0!")
    private float price;
    @NotNull(message = "Mô tả không được để trống!")
    @Length(min = 3, max = 300, message = "Mô tả phải từ 3 đến 300 kí tự!")
    private String description;
    @NotNull(message = "Vui lòng chọn loại sản phẩm!")
    private Integer categoryId; 
    private Boolean isdelete;
}
