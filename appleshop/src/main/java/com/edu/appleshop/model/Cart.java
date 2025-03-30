package com.exe.shojin.model;


import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Carts")
public class Cart {
    @Id
    @Column(name = "UserName")
    private String username; // Khóa chính (liên kết với User)

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;
}

