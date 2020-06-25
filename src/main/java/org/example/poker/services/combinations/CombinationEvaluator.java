package org.example.poker.services.combinations;

import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

public interface CombinationEvaluator {

    boolean isEvaluate(Hand hand);
    Combination getCombination();
}
