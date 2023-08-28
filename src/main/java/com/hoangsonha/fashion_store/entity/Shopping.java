package com.hoangsonha.fashion_store.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Shopping {
    private int quantity = 1;
    private int totalPrice;
    private Product product;
}
