package org.example.cashregister.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Cart {

    private final Map<Product, Long> products;
}
