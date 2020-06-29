package org.example.cashregister.services;

import org.example.cashregister.model.Cart;

import java.math.BigDecimal;

public interface BillingService {

    BigDecimal calculatePrice(Cart cart);
}
