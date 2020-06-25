package org.example.poker.services;

import org.example.poker.model.Combination;
import org.example.poker.model.Hand;

public interface PokerEvaluator {

    Combination evaluateHand(final Hand hand);
}
