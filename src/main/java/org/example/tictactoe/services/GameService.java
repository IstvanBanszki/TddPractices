package org.example.tictactoe.services;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Player;

public interface GameService {

    Player evaluate(Board board);
}
