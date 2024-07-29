package com.pguijaru.cartmanager.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String description;
    private int amount;
}
