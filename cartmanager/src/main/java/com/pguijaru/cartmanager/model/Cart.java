package com.pguijaru.cartmanager.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cart {
    private Long id;
    private List<Product> products;
    private LocalDateTime lastUpdate;
}
