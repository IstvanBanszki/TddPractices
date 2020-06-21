package org.example.customlist.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@DisplayName("CustomList Test - Calling isEmpty() method")
public class CustomListIsEmptyTest {

    @MethodSource("initParams")
    @ParameterizedTest(name = "#{index} {arguments}")
    public void testIsEmpty(TestParams listTestParams) {
        // given
        List<String> listObject = new CustomList<>(listTestParams.getObjects());
        // when
        boolean size = listObject.isEmpty();
        // then
        Assert.assertEquals(size, listTestParams.isExpected());
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                new TestParams(
                        new String[] {},
                        true,
                        "empty test case, should return true"
                ),
                new TestParams(
                        new String[] {"Example"},
                        false,
                        "one element test case, should return false"
                ),
                new TestParams(
                        new String[] {"Example1", "Example2", "Example3", "Example4", "Example5", "Example6", "Example7", "Example8", "Example9", "Example10"},
                        false,
                        "ten element test case, should return false"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String[] objects;
        private final boolean expected;
        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
