package org.example.poker.services.combinations;

import org.example.poker.model.Card;
import org.example.poker.model.Color;
import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

import java.util.List;

public class FlushCombinationEvaluator implements CombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        List<Card> cards = hand.getCards();
        Color color = cards.get(0).getColor();

        return cards.stream().allMatch(
                card -> color == card.getColor()
        );
    }

    @Override
    public Combination getCombination() {
        return Combination.FLUSH;
    }
}
