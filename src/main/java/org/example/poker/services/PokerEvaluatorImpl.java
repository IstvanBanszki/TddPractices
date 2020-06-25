package org.example.poker.services;

import org.example.poker.model.Card;
import org.example.poker.model.Combination;
import org.example.poker.model.Hand;
import org.example.poker.services.combinations.CombinationEvaluator;
import org.example.poker.services.combinations.DrillCombinationEvaluator;
import org.example.poker.services.combinations.FlushCombinationEvaluator;
import org.example.poker.services.combinations.FullHouseCombinationEvaluator;
import org.example.poker.services.combinations.PairCombinationEvaluator;
import org.example.poker.services.combinations.PokerCombinationEvaluator;
import org.example.poker.services.combinations.RoyalFlushCombinationEvaluator;
import org.example.poker.services.combinations.StraightCombinationEvaluator;
import org.example.poker.services.combinations.StraightFlushCombinationEvaluator;
import org.example.poker.services.combinations.TwoPairCombinationEvaluator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerEvaluatorImpl implements PokerEvaluator {

    private final List<CombinationEvaluator> evaluators = Stream.of(
            new RoyalFlushCombinationEvaluator(),
            new StraightFlushCombinationEvaluator(),
            new PokerCombinationEvaluator(),
            new FullHouseCombinationEvaluator(),
            new FlushCombinationEvaluator(),
            new StraightCombinationEvaluator(),
            new DrillCombinationEvaluator(),
            new TwoPairCombinationEvaluator(),
            new PairCombinationEvaluator()
    ).collect(Collectors.toList());

    @Override
    public Combination evaluateHand(final Hand hand) {

        if (hand == null || hand.getCards().size() != 5) {
            return Combination.ERROR;
        }
        return evaluate(hand);
    }

    private Combination evaluate(final Hand hand) {

        sortCardsByRank(hand.getCards());
        for (CombinationEvaluator evaluator : evaluators) {
            if (evaluator.isEvaluate(hand)) {
                return evaluator.getCombination();
            }
        }
        return Combination.HIGH_CARD;
    }

    private void sortCardsByRank(final List<Card> cards) {
        cards.sort(Comparator.comparingInt((Card card) -> card.getRank().getValue()));
    }
}
