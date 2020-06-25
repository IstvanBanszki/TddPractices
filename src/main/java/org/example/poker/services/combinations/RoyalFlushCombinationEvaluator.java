package org.example.poker.services.combinations;

import org.example.poker.model.Card;
import org.example.poker.model.Combination;
import org.example.poker.model.Hand;
import org.example.poker.model.Rank;

import java.util.List;

public class RoyalFlushCombinationEvaluator extends FlushCombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        List<Card> cards = hand.getCards();
        if (cards.get(0).getRank() == Rank.TEN &&
                cards.get(1).getRank() == Rank.JACK &&
                cards.get(2).getRank() == Rank.QUEEN &&
                cards.get(3).getRank() == Rank.KING &&
                cards.get(4).getRank() == Rank.ACE) {

            return super.isEvaluate(hand);
        }
        return false;
    }

    @Override
    public Combination getCombination() {
        return Combination.ROYAL_FLUSH;
    }
}
