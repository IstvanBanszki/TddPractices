package org.example.cashregister.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    private final String name;
    private final BigDecimal price;
}
