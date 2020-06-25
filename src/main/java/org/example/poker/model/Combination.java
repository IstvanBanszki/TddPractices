package org.example.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Combination {

    ROYAL_FLUSH(10), STRAIGHT_FLUSH(9), POKER(8), FLUSH(7), FULL_HOUSE(6),
    STRAIGHT(5), DRILL(4), TWO_PAIR(3), PAIR(2), HIGH_CARD(1), ERROR(-1);

    private final int value;
}
