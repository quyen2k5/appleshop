package com.example.WebBanHang.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatetoryCheck {
    @NotNull(message = "Tên loại không được để trống")
    @Length(min = 2, max = 100, message = "Tên loại phải từ 2 đến 100 kí tự")
    private String name;
}
 