package org.example.linesplitter.services;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class LineSplitterServiceImpl implements LineSplitterService {

    private final int maxLength;

    @Override
    public String[] split(final String text) {

        if (text == null) {
            return new String[0];
        }
        final String trimmedText = text.trim();

        if (trimmedText.length() == 0 || text.length() <= maxLength) {
            return new String[] {trimmedText};
        }
        return splitIntoPieces(trimmedText);
    }

    private String[] splitIntoPieces(final String text) {
        List<String> lines = new LinkedList<>();
        StringBuilder textSb = new StringBuilder(text);

        while (textSb.length() > maxLength) {
            int end = getEndIndex(textSb.toString());

            lines.add(textSb.substring(0, end));
            textSb = new StringBuilder(textSb.substring(end).trim());
        }
        lines.add(textSb.toString().trim());

        return lines.toArray(String[]::new);
    }

    private int getEndIndex(final String text) {
        if (text.charAt(maxLength) == ' ') {
            return maxLength;
        }
        return text.substring(0, maxLength).trim().lastIndexOf(' ');
    }
}
