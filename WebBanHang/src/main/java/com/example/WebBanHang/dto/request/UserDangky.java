package com.example.WebBanHang.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDangky {
        @NotNull(message = "Tên không được để trống!")
        @Length(min = 3, max = 100, message = "Tên phải từ 3 đến 100 kí tự!")
        private String fullname;
        @NotNull(message = "Mật khẩu không được để trống")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Mật khẩu không hợp lệ!")
        @Length(min = 6, message = "Mật khẩu phải có ít nhất 6 kí tự")
        private String password;
        @NotNull(message = "Email không được để trống!")
        @Email(message = "Email không đúng định dạng")
        private String email;
    
        
}
