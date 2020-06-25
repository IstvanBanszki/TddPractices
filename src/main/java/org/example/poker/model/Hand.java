package org.example.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Hand {

    private final List<Card> cards;
}
