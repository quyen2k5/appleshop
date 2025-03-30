package com.exe.shojin.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)  // Đảm bảo Cart được lưu trước khi CartItem được lưu
    @JoinColumn(name = "CartID", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Column(name = "Quantity")
    private Integer quantity;
}
