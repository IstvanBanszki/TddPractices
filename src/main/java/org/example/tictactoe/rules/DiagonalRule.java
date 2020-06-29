package org.example.tictactoe.rules;

import org.example.tictactoe.model.Point;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiagonalRule implements Rule {

    private List<Point> forwardDiagonalPoints = Stream.of(
            new Point(0, 0), new Point(1, 1), new Point(2, 2)
    ).collect(Collectors.toList());
    private List<Point> backwardDiagonalPoints = Stream.of(
            new Point(2, 0), new Point(1, 1), new Point(0, 2)
    ).collect(Collectors.toList());

    @Override
    public boolean check(final List<Point> points) {
        return points.containsAll(forwardDiagonalPoints) ||
                points.containsAll(backwardDiagonalPoints);
    }
}
