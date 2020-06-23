package org.example.linesplitter.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("WordSplitterService - Calling 'String[] split(String text)' method")
public class LineSplitterServiceTest {

    @MethodSource("initTestParams")
    @ParameterizedTest
    public void testSplit(final TestParams testParams) {
        // given
        LineSplitterService service = new LineSplitterServiceImpl(testParams.getMaxLength());
        // when
        String[] result = service.split(testParams.getText());
        // then
        Assert.assertArrayEquals(result, testParams.getExpected());
    }

    private static TestParams[] initTestParams() {
        return new TestParams[] {
                new TestParams(
                        null,
                        10,
                        new String[0],
                        "in case of text param is null, should give back an empty array"
                ),
                new TestParams(
                        "",
                        10,
                        new String[] {""},
                        "in case of text param is an empty string, should give back an array contains an empty string"
                ),
                new TestParams(
                        "       ",
                        10,
                        new String[] {""},
                        "in case of text param is full of whitespace, should give back an array contains an empty string"
                ),
                new TestParams(
                        "Simple",
                        10,
                        new String[] {"Simple"},
                        "in case of text param is \"Simple\", should give back an array contains {\"Simple\"}"
                ),
                new TestParams(
                        "Easy Test",
                        10,
                        new String[] {"Easy Test"},
                        "in case of text param is \"Easy Test\", should give back an array contains {\"Easy Test\"}"
                ),
                new TestParams(
                        "Simple Test",
                        10,
                        new String[] {"Simple", "Test"},
                        "in case of text param is \"Simple Test\", should give back an array contains {\"Simple\", \"Test\"}"
                ),
                new TestParams(
                        "Simple Test Never Ending",
                        10,
                        new String[] {"Simple", "Test Never", "Ending"},
                        "in case of text param is \"Simple Test Never Ending\", should give back an array contains {\"Simple\", \"Test Never\", \"Ending\"}"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String text;
        private final int maxLength;
        private final String[] expected;
        private final String testName;

        @Override
        public String toString() {
            return this.testName;
        }
    }
}
