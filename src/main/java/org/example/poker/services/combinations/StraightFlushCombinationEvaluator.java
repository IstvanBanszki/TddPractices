package org.example.poker.services.combinations;

import org.example.poker.model.Card;
import org.example.poker.model.Color;
import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

import java.util.List;

public class StraightFlushCombinationEvaluator extends StraightCombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        List<Card> cards = hand.getCards();
        Color color = cards.get(0).getColor();
        return super.isEvaluate(hand) && isFlush(cards, color);
    }

    @Override
    public Combination getCombination() {
        return Combination.STRAIGHT_FLUSH;
    }

    private boolean isFlush(final List<Card> cards, final Color color) {
        return cards.stream().allMatch(
                card -> color == card.getColor()
        );
    }
}
