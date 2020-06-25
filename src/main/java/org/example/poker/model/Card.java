package org.example.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {

    private final Rank rank;
    private final Color color;
}
