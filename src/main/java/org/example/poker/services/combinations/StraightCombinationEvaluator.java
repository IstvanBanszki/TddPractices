package org.example.poker.services.combinations;

import org.example.poker.model.Card;
import org.example.poker.model.Combination;
import org.example.poker.model.Hand;
import org.example.poker.model.Rank;

import java.util.List;

public class StraightCombinationEvaluator implements CombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        List<Card> cards = hand.getCards();
        if (isFiveHighStraight(cards)) {
            return true;
        }
        int prevValue = -1;

        for (final Card card : cards) {
            int currentValue = card.getRank().getValue();

            if (prevValue == -1 || (prevValue + 1) == currentValue) {
                prevValue = currentValue;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Combination getCombination() {
        return Combination.STRAIGHT;
    }

    private boolean isFiveHighStraight(final List<Card> cards) {
        return cards.get(0).getRank() == Rank.TWO &&
                cards.get(1).getRank() == Rank.THREE &&
                cards.get(2).getRank() == Rank.FOUR &&
                cards.get(3).getRank() == Rank.FIVE &&
                cards.get(4).getRank() == Rank.ACE;
    }
}
