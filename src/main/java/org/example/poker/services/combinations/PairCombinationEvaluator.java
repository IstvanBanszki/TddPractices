package org.example.poker.services.combinations;

import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

import java.util.stream.IntStream;

public class PairCombinationEvaluator implements CombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        IntStream rankValues = hand.getCards()
                .stream()
                .mapToInt(card -> card.getRank().getValue());

        return rankValues.distinct().count() == 4;
    }

    @Override
    public Combination getCombination() {
        return Combination.PAIR;
    }
}
