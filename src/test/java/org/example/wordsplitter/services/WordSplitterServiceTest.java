package org.example.wordsplitter.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("WordSplitterService Test - split(String text) method")
public class WordSplitterServiceTest {

    @MethodSource("initTestParams")
    @ParameterizedTest(name = "#{index} -> {arguments}")
    public void testSplit(TestParams testParams) {
        // given
        IWordSplitterService service = new WordSplitterServiceImpl(testParams.getSplitChar());
        // when
        String[] result = service.split(testParams.getText());
        // then
        Assert.assertArrayEquals(result, testParams.getExpectedSplit());
    }

    private static TestParams[] initTestParams() {
        return new TestParams[] {
                new TestParams(
                        null,
                        ' ',
                        new String[]{},
                        "in case of null and ' ' is the split char, should give back an empty array"
                ),
                new TestParams(
                        "",
                        ' ',
                        new String[]{""},
                        "in case of empty string and ' ' is the split char, should give back array with the single element of empty string"
                ),
                new TestParams(
                        "    ",
                        ' ',
                        new String[]{""},
                        "in case of string with containing just space and ' ' is the split char, should give back array with the single element of empty string"
                ),
                new TestParams(
                        "text",
                        ' ',
                        new String[]{"text"},
                        "in case of \"text\" string and ' ' is the split char, should give back array of {\"text\"}"
                ),
                new TestParams(
                        "another text",
                        ' ',
                        new String[]{"another", "text"},
                        "in case of \"another text\" string and ' ' is the split char, should give back array of {\"another\", \"text\"}"
                ),
                new TestParams(
                        "another text, plus a lot more text in here",
                        ' ',
                        new String[]{"another", "text,", "plus", "a", "lot", "more", "text", "in", "here"},
                        "in case of \"another text, plus a lot more text in here\" string and ' ' is the split char, should give back array of {\"another\", \"text,\", \"plus\", \"a\", \"lot\", \"more\", \"text\", \"in\", \"here\"}"
                ),
                new TestParams(
                        "text",
                        ',',
                        new String[]{"text"},
                        "in case of \"text\" string and ',' is the split char, should give back array of {\"text\"}"
                ),
                new TestParams(
                        "another text",
                        ',',
                        new String[]{"another text"},
                        "in case of \"another text\" string and ',' is the split char, should give back array of {\"another text\"}"
                ),
                new TestParams(
                        "another text, plus a lot more text in here",
                        ',',
                        new String[]{"another text", " plus a lot more text in here"},
                        "in case of \"another text, plus a lot more text in here\" string and ' ' is the split char, should give back array of {\"another text\", \" plus a lot more text in here\"}"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final String text;
        private final char splitChar;
        private final String[] expectedSplit;
        private final String name;

        @Override
        public String toString() {
            return this.name;
        }
    }
}
