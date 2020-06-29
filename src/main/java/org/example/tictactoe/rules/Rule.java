package org.example.tictactoe.rules;

import org.example.tictactoe.model.Point;

import java.util.List;

public interface Rule {

    boolean check(final List<Point> points);
}
