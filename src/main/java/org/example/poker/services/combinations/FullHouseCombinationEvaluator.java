package org.example.poker.services.combinations;

import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class FullHouseCombinationEvaluator implements CombinationEvaluator {

    @Override
    public boolean isEvaluate(final Hand hand) {

        Map<Integer, Long> rankValueCounters = hand.getCards()
                .stream()
                .collect(Collectors.groupingBy(
                        card -> card.getRank().getValue(), Collectors.counting()
                ));
        Collection<Long> counters =rankValueCounters.values();
        return counters.size() == 2 && counters.contains(2L) && counters.contains(3L);
    }

    @Override
    public Combination getCombination() {
        return Combination.FULL_HOUSE;
    }
}
