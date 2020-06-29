package org.example.tictactoe.services;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Cell;
import org.example.tictactoe.model.Player;
import org.example.tictactoe.model.Point;
import org.example.tictactoe.rules.ColumnRule;
import org.example.tictactoe.rules.DiagonalRule;
import org.example.tictactoe.rules.RowRule;
import org.example.tictactoe.rules.Rule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameServiceImpl implements GameService {

    private final List<Rule> rules = Stream.of(
            new RowRule(), new ColumnRule(), new DiagonalRule()
    ).collect(Collectors.toList());

    @Override
    public Player evaluate(final Board board) {

        Player winner = Player.NONE;

        if (board != null) {

            List<Cell> cells = board.getCells();
            if (cells != null && !cells.isEmpty()) {
                winner = checkBoard(cells);
            }
        }
        return winner;
    }

    private Player checkBoard(final List<Cell> cells) {
        Map<Player, List<Point>> pointsByPlayer = getPointsByPlayer(cells);

        for (final Map.Entry<Player, List<Point>> entry : pointsByPlayer.entrySet()) {

            Player player = entry.getKey();
            List<Point> points = entry.getValue();

            if (isPointsInWinningSituation(points)) {
                return player;
            }
        }
        return Player.NONE;
    }

    private boolean isPointsInWinningSituation(final List<Point> points) {
        boolean result = false;

        for (final Rule rule : rules) {

            if (rule.check(points)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private Map<Player, List<Point>> getPointsByPlayer(final List<Cell> cells) {
        return cells.stream()
                .collect(
                        Collectors.groupingBy(Cell::getPlayer, Collectors.mapping(Cell::getPoint, Collectors.toList()))
                );
    }
}
