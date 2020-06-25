package org.example.poker.services.combinations;

import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

import java.util.Map;
import java.util.stream.Collectors;

public class TwoPairCombinationEvaluator implements CombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {
        int pairCount = 0;

        Map<Integer, Long> rankValueCounters = hand.getCards()
                .stream()
                .collect(Collectors.groupingBy(
                        card -> card.getRank().getValue(), Collectors.counting()
                ));
        for (Map.Entry<Integer, Long> entry : rankValueCounters.entrySet()) {
            if (entry.getValue() == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    @Override
    public Combination getCombination() {
        return Combination.TWO_PAIR;
    }
}
