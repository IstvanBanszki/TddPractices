package org.example.customlist.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@DisplayName("CustomList Test - Calling contains(Object o) method")
public class CustomListContainsTest {

    @MethodSource("initParams")
    @ParameterizedTest(name = "#{index} {arguments}")
    public void testContains(TestParams listTestParams) {
        // given
        List<String> listObject = new CustomList<>(listTestParams.getObjects());
        // when
        boolean size = listObject.contains(listTestParams.getObjectToSearch());
        // then
        Assert.assertEquals(size, listTestParams.isExpected());
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                new TestParams(
                        new String[] {},
                        "Example",
                        false,
                        "empty list test case, should return false"
                ),
                new TestParams(
                        new String[] {"Example"},
                        "Example",
                        true,
                        "one element in the list and contains the search object, should return true"
                ),
                new TestParams(
                        new String[] {"Example1", "Example2", "Example3", "Example4", "Example5"},
                        "Example4",
                        true,
                        "five element in the list and contains the search object, should return true"
                ),
                new TestParams(
                        new String[] {"Example1", "Example2", "Example3", "Example4", "Example5"},
                        "Example6",
                        false,
                        "five element in the list and not contains the search object, should return false"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String[] objects;
        private final String objectToSearch;
        private final boolean expected;
        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
