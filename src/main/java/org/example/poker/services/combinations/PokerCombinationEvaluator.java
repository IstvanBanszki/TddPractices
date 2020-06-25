package org.example.poker.services.combinations;

import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

import java.util.Map;
import java.util.stream.Collectors;

public class PokerCombinationEvaluator implements CombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        Map<Integer, Long> rankValueCounters = hand.getCards()
                .stream()
                .collect(Collectors.groupingBy(
                        card -> card.getRank().getValue(), Collectors.counting()
                ));
        return rankValueCounters.entrySet()
                .stream()
                .anyMatch(entry -> entry.getValue().intValue() == 4);
    }

    @Override
    public Combination getCombination() {
        return Combination.POKER;
    }
}
