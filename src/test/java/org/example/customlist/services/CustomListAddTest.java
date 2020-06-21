package org.example.customlist.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@DisplayName("CustomList Test - Calling add(E e) method")
public class CustomListAddTest {

    @MethodSource("initParams")
    @ParameterizedTest(name = "#{index} {arguments}")
    public void testAdd(TestParams listTestParams) {
        // given
        List<String> listObject = new CustomList<>(listTestParams.getInitElements());
        // when
        listObject.add(listTestParams.getObjectToAdd());
        int newSize = listObject.size();
        // then
        Assert.assertEquals(newSize, listTestParams.getExpectedNewSize());
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                new TestParams(
                        new String[] {},
                        "Example",
                        1,
                        "empty test case, should return one as the new list size"
                ),
                new TestParams(
                        new String[] {"Example"},
                        "Example",
                        2,
                        "one element test case, should return two as the new list size"
                ),
                new TestParams(
                        new String[] {"Example1", "Example2", "Example3", "Example4", "Example5", "Example6", "Example7", "Example8", "Example9", "Example10"},
                        "Example",
                        11,
                        "one element test case, should return two as the new list size"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String[] initElements;
        private final String objectToAdd;
        private final int expectedNewSize;
        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
