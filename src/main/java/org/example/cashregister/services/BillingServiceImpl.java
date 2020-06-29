package org.example.cashregister.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.cashregister.model.Cart;
import org.example.cashregister.model.Product;
import org.example.cashregister.model.Discount;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@AllArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final Map<Product, Discount> discounts;

    @Override
    public BigDecimal calculatePrice(final Cart cart) {

        if (cart != null) {

            Map<Product, Long> products = cart.getProducts();

            if (products != null && !products.isEmpty()) {
                return getFinalPrice(cart.getProducts());
            }
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal getFinalPrice(final Map<Product, Long> products) {
        BigDecimal result = BigDecimal.ZERO;

        for (final Map.Entry<Product, Long> entry : products.entrySet()) {
            Product product = entry.getKey();
            Long productNumber = entry.getValue();

            result = result.add(checkAndCalculatePrice(product, productNumber));
        }
        return result;
    }

    private BigDecimal checkAndCalculatePrice(final Product product, final Long productNumber) {

        if (discounts.containsKey(product)) {

            Discount discount = discounts.get(product);
            return getDiscountPrice(discount, product.getPrice(), productNumber);
        } else {
            return multiply(product.getPrice(), productNumber, 1L);
        }
    }

    private BigDecimal getDiscountPrice(final Discount discount, final BigDecimal price, final Long productNumber) {

        BigDecimal discountPrice;
        long outcome = productNumber / discount.getNumberOfProduct();
        long remainder = productNumber % discount.getNumberOfProduct();

        if (outcome > 0) {

            discountPrice = multiply(discount.getDiscountPrice(), outcome);

            if (remainder > 0) {
                discountPrice = discountPrice.add(
                        multiply(price, remainder, 1L)
                );
            }
        } else {
            discountPrice = multiply(price, productNumber, 1L);
        }
        return discountPrice;
    }

    private BigDecimal multiply(final BigDecimal price, final Long productNumber) {
        return price.multiply(BigDecimal.valueOf(productNumber));
    }

    private BigDecimal multiply(final BigDecimal price, final Long productNumber, final Long multiplier) {
        return price.multiply(BigDecimal.valueOf(productNumber * multiplier));
    }
}
