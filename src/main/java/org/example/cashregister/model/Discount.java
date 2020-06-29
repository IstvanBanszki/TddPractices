package org.example.cashregister.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Discount {

    private final long numberOfProduct;
    private final BigDecimal discountPrice;
}
