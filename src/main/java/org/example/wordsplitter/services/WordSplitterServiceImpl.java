package org.example.wordsplitter.services;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class WordSplitterServiceImpl implements IWordSplitterService {

    private final char splitChar;

    @Override
    public String[] split(final String text) {
        if (text == null) {
            return new String[]{};
        }
        String trimmedText = text.trim();
        if (trimmedText.indexOf(splitChar) == -1) {
            return new String[]{trimmedText};
        }
        return breakIn(trimmedText);
    }

    private String[] breakIn(final String text) {
        List<String> result = new LinkedList<>();
        int begin = 0;
        for (int index = 0, end = text.length();
             index < end;
             index++) {

            if (text.charAt(index) == splitChar) {
                String singleText = getSingleString(text, begin, index);
                begin = index;
                result.add(singleText);
            }
        }
        result.add(getSingleString(text, begin, text.length()));
        return result.toArray(new String[0]);
    }

    private String getSingleString(final String text, final int begin, final int end) {
        if (text.charAt(begin) == splitChar) {
            return text.substring(begin + 1, end);
        }
        return text.substring(begin, end);
    }
}
