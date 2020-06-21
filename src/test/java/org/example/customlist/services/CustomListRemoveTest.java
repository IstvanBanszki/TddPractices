package org.example.customlist.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

@DisplayName("CustomList Test - Calling remove(E e) method")
public class CustomListRemoveTest {

    @MethodSource("initParams")
    @ParameterizedTest(name = "#{index} {arguments}")
    public void testRemove(TestParams listTestParams) {
        // given
        List<String> listObject = new CustomList<>(listTestParams.getInitElements());
        // when
        boolean operationResult = listObject.remove(listTestParams.getObjectToRemove());
        int newSize = listObject.size();
        // then
        Assert.assertEquals(operationResult, listTestParams.isExpectedOperationResult());
        Assert.assertEquals(newSize, listTestParams.getExpectedNewSize());
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                new TestParams(
                        new String[] {},
                        "Example",
                        false,
                        0,
                        "empty test case, should return one as the new list size"
                ),
                new TestParams(
                        new String[] {"Example"},
                        "Example",
                        true,
                        0,
                        "one element test case, should return two as the new list size"
                ),
                new TestParams(
                        new String[] {"Example"},
                        "Example1",
                        false,
                        1,
                        "one element test case, should return two as the new list size"
                ),
                new TestParams(
                        new String[] {"Example1", "Example2", "Example3", "Example4", "Example5", "Example6", "Example7", "Example8", "Example9", "Example10"},
                        "Example6",
                        true,
                        9,
                        "one element test case, should return two as the new list size"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String[] initElements;
        private final String objectToRemove;
        private final boolean expectedOperationResult;
        private final int expectedNewSize;
        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
