package org.example.tictactoe.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Cell;
import org.example.tictactoe.model.Player;
import org.example.tictactoe.model.Point;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("GameServiceTest")
public class GameServiceTest {

    @ParameterizedTest
    @MethodSource("initParams")
    public void testEvaluate(TestParams testParams) {
        // given
        GameService service = new GameServiceImpl();
        // when
        Player winner = service.evaluate(testParams.getBoard());
        // then
        Assert.assertEquals(testParams.getExpectedWinner(), winner);
    }

    public static TestParams[] initParams() {
        return new TestParams[] {
                TestParams.builder()
                        .testName("In case of 'null' board, should be no winner.")
                        .board(null)
                        .expectedWinner(Player.NONE)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'empty' board, should be no winner.")
                        .board(new Board(Collections.emptyList()))
                        .expectedWinner(Player.NONE)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'X->{0-1} Y->{1-0}' board, should be no winner.")
                        .board(generateBoard(
                                new Cell(Player.X, new Point(0, 1)),
                                new Cell(Player.O, new Point(1, 0))
                                )
                        )
                        .expectedWinner(Player.NONE)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'X->{0-0,0-1} Y->{1-1}' board, should be no winner.")
                        .board(generateBoard(
                                new Cell(Player.X, new Point(0, 1)),
                                new Cell(Player.O, new Point(1, 1)),
                                new Cell(Player.X, new Point(0, 0))
                                )
                        )
                        .expectedWinner(Player.NONE)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'X->{0-0,1-2,0-2} Y->{1-1,2-2}' board, should be X player the winner.")
                        .board(generateBoard(
                                new Cell(Player.X, new Point(0, 1)),
                                new Cell(Player.O, new Point(1, 1)),
                                new Cell(Player.X, new Point(0, 0)),
                                new Cell(Player.O, new Point(2, 2)),
                                new Cell(Player.X, new Point(0, 2))
                                )
                        )
                        .expectedWinner(Player.X)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'X->{0-2,1-2,2-2} Y->{0-0,1-1}' board, should be X player the winner.")
                        .board(generateBoard(
                                new Cell(Player.X, new Point(2, 2)),
                                new Cell(Player.X, new Point(0, 2)),
                                new Cell(Player.X, new Point(1, 2)),
                                new Cell(Player.O, new Point(1, 1)),
                                new Cell(Player.O, new Point(0, 0))
                                )
                        )
                        .expectedWinner(Player.X)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'X->{0-2,1-1,2-0} Y->{0-0,0-1}' board, should be X player the winner.")
                        .board(generateBoard(
                                new Cell(Player.X, new Point(0, 2)),
                                new Cell(Player.X, new Point(1, 1)),
                                new Cell(Player.X, new Point(2, 0)),
                                new Cell(Player.O, new Point(0, 0)),
                                new Cell(Player.O, new Point(0, 1))
                                )
                        )
                        .expectedWinner(Player.X)
                        .build(),
                TestParams.builder()
                        .testName("In case of 'X->{0-0,0-1} Y->{0-2,1-1,2-0}' board, should be O player the winner.")
                        .board(generateBoard(
                                new Cell(Player.X, new Point(0, 0)),
                                new Cell(Player.X, new Point(0, 1)),
                                new Cell(Player.O, new Point(0, 2)),
                                new Cell(Player.O, new Point(1, 1)),
                                new Cell(Player.O, new Point(2, 0))
                                )
                        )
                        .expectedWinner(Player.O)
                        .build()
        };
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class TestParams {

        private final Board board;
        private final Player expectedWinner;
        private final String testName;

        @Override
        public String toString() {
            return this.testName;
        }
    }

    private static Board generateBoard(Cell... cells) {
        return new Board(Stream.of(cells).collect(Collectors.toList()));
    }
}
