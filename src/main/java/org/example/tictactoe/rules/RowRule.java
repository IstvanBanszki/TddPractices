package org.example.tictactoe.rules;

import org.example.tictactoe.model.Point;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RowRule implements Rule {

    @Override
    public boolean check(final List<Point> points) {
        Map<Integer, Long> rowCounts = points.stream()
                .collect(
                        Collectors.groupingBy(Point::getX, Collectors.counting())
                );
        return rowCounts.values()
                .stream()
                .anyMatch(value -> value == 3L);
    }
}
