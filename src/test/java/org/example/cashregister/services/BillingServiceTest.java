package org.example.cashregister.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.cashregister.model.Cart;
import org.example.cashregister.model.Product;
import org.example.cashregister.services.BillingService;
import org.example.cashregister.services.BillingServiceImpl;
import org.example.cashregister.model.Discount;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@DisplayName("BillingService Test - Calling 'BigDecimal calculatePrice(Cart cart)' method")
public class BillingServiceTest {

    private static final Product MILK = new Product("milk", BigDecimal.valueOf(3.5));
    private static final Product HONEY = new Product("honey", BigDecimal.valueOf(12.25));
    private static final Product BREAD = new Product("bread", BigDecimal.valueOf(2.8));
    private static final Product CHEESE = new Product("cheese", BigDecimal.valueOf(14.6));
    private static final Product BUTTER = new Product("butter", BigDecimal.valueOf(6.3));
    private static final Product HAM = new Product("ham", BigDecimal.valueOf(19.6));
    private static final Product EGG = new Product("egg", BigDecimal.valueOf(0.4));

    private final Map<Product, Discount> discounts = Map.of(
            MILK, new Discount(2, BigDecimal.valueOf(6.5)),
            EGG, new Discount(14, BigDecimal.valueOf(5.2)),
            HONEY, new Discount(3, BigDecimal.valueOf(34.5)),
            HAM, new Discount(1, BigDecimal.valueOf(18))
    );

    @ParameterizedTest
    @MethodSource("initParams")
    public void calculatePriceTest(final TestParams testParams) {
        // given
        BillingService service = new BillingServiceImpl(discounts);
        // when
        BigDecimal result = service.calculatePrice(testParams.getCart());
        // then
        Assert.assertEquals(testParams.getExpected(), result);
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                TestParams.builder()
                        .cart(null)
                        .expected(BigDecimal.ZERO)
                        .testCaseName("in case of 'null' cart, should calculate '0' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(null))
                        .expected(BigDecimal.ZERO)
                        .testCaseName("in case of not precisely initialized cart, should calculate '0' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Collections.emptyMap()))
                        .expected(BigDecimal.ZERO)
                        .testCaseName("in case of 'empty' cart, should calculate '0' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Map.of(BREAD, 1L, MILK,1L)))
                        .expected(BigDecimal.valueOf(6.3))
                        .testCaseName("in case of '1p bread, 1p milk' in the cart, should calculate '6.3' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Map.of(BREAD, 1L, MILK,1L, EGG, 10L)))
                        .expected(BigDecimal.valueOf(10.3))
                        .testCaseName("in case of '1p bread, 1p milk, 10p egg' in the cart, should calculate '10.3' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Map.of(BREAD, 1L, MILK,1L, EGG, 14L)))
                        .expected(BigDecimal.valueOf(11.5))
                        .testCaseName("in case of '1p bread, 1p milk, 14p egg' in the cart, should calculate '11.5' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Map.of(BREAD, 1L, MILK,1L, EGG, 16L)))
                        .expected(BigDecimal.valueOf(12.3))
                        .testCaseName("in case of '1p bread, 1p milk, 16p egg' in the cart, should calculate '12.3' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Map.of(HONEY, 4L, HAM, 2L)))
                        .expected(BigDecimal.valueOf(82.75))
                        .testCaseName("in case of '4p honey, 2p ham' in the cart, should calculate '82.75' price")
                        .build(),
                TestParams.builder()
                        .cart(new Cart(Map.of(CHEESE, 2L, BUTTER,2L, MILK, 2L, BREAD, 1L)))
                        .expected(BigDecimal.valueOf(51.1))
                        .testCaseName("in case of '2p cheese, 2p butter, 2p milk, 1p bread' in the cart, should calculate '51.1' price")
                        .build()
        };
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class TestParams {

        private final Cart cart;
        private final BigDecimal expected;
        private final String testCaseName;

        @Override
        public String toString() {
            return this.testCaseName;
        }
    }
}
