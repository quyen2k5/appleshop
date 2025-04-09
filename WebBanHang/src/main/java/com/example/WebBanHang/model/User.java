package com.example.WebBanHang.model;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AccoutUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Họ và tên không được để trống")
    private String fullname;

    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;

    @Email(message = "Email không hợp lệ")
    private String email;

    private String photo;
    private boolean activated;
    private boolean role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders; 

    // Thêm setter cho id để tránh lỗi khi cập nhật người dùng
    public void setId(Integer id) {
        this.id = id;
    }
}
