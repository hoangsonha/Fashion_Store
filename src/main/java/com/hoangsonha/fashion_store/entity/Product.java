package com.hoangsonha.fashion_store.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_name", nullable = false, unique = true, length = 300)
    private String productName;
    @Column(name = "product_year", nullable = false, length = 50)
    private String productYear;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "detail", nullable = true, length = 2048)
    private String detail;
    @Column(name = "kind", nullable = false, length = 10)
    private String kind;

    private String img;

}
