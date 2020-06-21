package org.example.customlist.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@DisplayName("CustomList Test - Calling size() method")
public class CustomListSizeTest {

    @MethodSource("initParams")
    @ParameterizedTest(name = "#{index} {arguments}")
    public void testSize(TestParams listTestParams) {
        // given
        List<String> listObject = new CustomList<>(listTestParams.getObjects());
        // when
        int size = listObject.size();
        // then
        Assert.assertEquals(size, listTestParams.getExpected());
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                new TestParams(
                        new String[] {},
                        0,
                        "empty test case, should return zero"
                ),
                new TestParams(
                        new String[] {"Example"},
                        1,
                        "one element test case, should return one"
                ),
                new TestParams(
                        new String[] {"Example1", "Example2", "Example3", "Example4", "Example5", "Example6", "Example7", "Example8", "Example9", "Example10"},
                        10,
                        "ten element test case, should return ten"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String[] objects;
        private final int expected;
        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
