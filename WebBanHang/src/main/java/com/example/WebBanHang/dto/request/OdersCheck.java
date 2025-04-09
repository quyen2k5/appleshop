package com.example.WebBanHang.dto.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;

import com.example.WebBanHang.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Controller
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OdersCheck {
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Họ tên không được để trống!")
    @Length(min = 3, max = 50, message = "Họ tên phải từ 3 đến 50 kí tự!")
    private String fullname;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @NotNull(message = "Địa chỉ không được để trống!")
    @Length(min = 5, max = 100, message = "Địa chỉ phải từ 5 đến 100 kí tự!")
    private String address;
    private Boolean activated; 
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
