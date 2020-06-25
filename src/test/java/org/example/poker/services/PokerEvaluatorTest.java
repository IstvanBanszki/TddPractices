package org.example.poker.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.poker.model.Card;
import org.example.poker.model.Color;
import org.example.poker.model.Combination;
import org.example.poker.model.Hand;
import org.example.poker.model.Rank;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("PokerEvaluator Test - Calling 'Combination evaluateHand(final Hand hand)' method")
public class PokerEvaluatorTest {

    @ParameterizedTest
    @MethodSource("initParams")
    public void testEvaluate(final TestParams testParams) {
        // given
        PokerEvaluator evaluator = new PokerEvaluatorImpl();
        // when
        Combination result = evaluator.evaluateHand(testParams.getHand());
        // then
        Assert.assertEquals(result.getValue(), testParams.getExpectedCombination().getValue());
    }

    private static TestParams[] initParams() {
        return new TestParams[] {
                new TestParams(
                        null,
                        Combination.ERROR,
                        "in case of 'null' hand, should evaluate as 'Error' combinations"
                ),
                new TestParams(
                        new Hand(Collections.emptyList()),
                        Combination.ERROR,
                        "in case of 'empty' hand, should evaluate as 'Error' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.TWO, Color.CLUBS),
                                        new Card(Rank.THREE, Color.DIAMONDS),
                                        new Card(Rank.FOUR, Color.SPADES))
                                      .collect(Collectors.toList())
                        ),
                        Combination.ERROR,
                        "in case of '{Two-Clubs, Three-Diamonds, Four-Spades}' hand, should evaluate as 'Error' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.TWO, Color.CLUBS),
                                        new Card(Rank.FOUR, Color.DIAMONDS),
                                        new Card(Rank.FIVE, Color.SPADES),
                                        new Card(Rank.TEN, Color.HEARTS),
                                        new Card(Rank.QUEEN, Color.CLUBS))
                                      .collect(Collectors.toList())
                        ),
                        Combination.HIGH_CARD,
                        "in case of {Two-Clubs, Three-Diamonds, Four-Spades, Five-Hearts, Queen-Clubs} hand, should evaluate as 'High Card' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.ACE, Color.CLUBS),
                                        new Card(Rank.THREE, Color.DIAMONDS),
                                        new Card(Rank.FOUR, Color.SPADES),
                                        new Card(Rank.FIVE, Color.HEARTS),
                                        new Card(Rank.ACE, Color.CLUBS))
                                        .collect(Collectors.toList())
                        ),
                        Combination.PAIR,
                        "in case of {Ace-Clubs, Three-Diamonds, Four-Spades, Five-Hearts, Ace-Clubs} hand, should evaluate as 'Pair' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.KING, Color.CLUBS),
                                        new Card(Rank.THREE, Color.DIAMONDS),
                                        new Card(Rank.FOUR, Color.SPADES),
                                        new Card(Rank.THREE, Color.HEARTS),
                                        new Card(Rank.KING, Color.DIAMONDS))
                                        .collect(Collectors.toList())
                        ),
                        Combination.TWO_PAIR,
                        "in case of {King-Clubs, Three-Diamonds, Four-Spades, Three-Hearts, King-Diamonds} hand, should evaluate as 'Two Pair' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.JACK, Color.CLUBS),
                                        new Card(Rank.THREE, Color.DIAMONDS),
                                        new Card(Rank.FOUR, Color.SPADES),
                                        new Card(Rank.JACK, Color.HEARTS),
                                        new Card(Rank.JACK, Color.DIAMONDS))
                                        .collect(Collectors.toList())
                        ),
                        Combination.DRILL,
                        "in case of {Jack-Clubs, Three-Diamonds, Four-Spades, Jack-Hearts, Jack-Diamonds} hand, should evaluate as 'Drill' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.SIX, Color.DIAMONDS),
                                        new Card(Rank.TWO, Color.CLUBS),
                                        new Card(Rank.FOUR, Color.DIAMONDS),
                                        new Card(Rank.FIVE, Color.SPADES),
                                        new Card(Rank.THREE, Color.HEARTS))
                                        .collect(Collectors.toList())
                        ),
                        Combination.STRAIGHT,
                        "in case of {Six-Diamonds, Two-Clubs, Four-Diamonds, Five-Spades, Three-Hearts} hand, should evaluate as 'Straight' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.FIVE, Color.HEARTS),
                                        new Card(Rank.TWO, Color.CLUBS),
                                        new Card(Rank.FOUR, Color.SPADES),
                                        new Card(Rank.ACE, Color.CLUBS),
                                        new Card(Rank.THREE, Color.CLUBS))
                                        .collect(Collectors.toList())
                        ),
                        Combination.STRAIGHT,
                        "in case of {Five-Hearts, Two-Clubs, Four-Spades, Ace-Clubs, Three-Clubs} hand, should evaluate as 'Straight' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.KING, Color.HEARTS),
                                        new Card(Rank.TWO, Color.HEARTS),
                                        new Card(Rank.THREE, Color.HEARTS),
                                        new Card(Rank.SIX, Color.HEARTS),
                                        new Card(Rank.TEN, Color.HEARTS))
                                        .collect(Collectors.toList())
                        ),
                        Combination.FLUSH,
                        "in case of {King-Hearts, Two-Hearts, Three-Hearts, Six-Hearts, Ten-Hearts} hand, should evaluate as 'Flush' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.TEN, Color.CLUBS),
                                        new Card(Rank.SIX, Color.HEARTS),
                                        new Card(Rank.SIX, Color.CLUBS),
                                        new Card(Rank.TEN, Color.SPADES),
                                        new Card(Rank.SIX, Color.SPADES))
                                        .collect(Collectors.toList())
                        ),
                        Combination.FULL_HOUSE,
                        "in case of {Ten-Clubs, Six-Hearts, Six-Clubs, Ten-Spades, Six-Spades} hand, should evaluate as 'Full House' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.JACK, Color.DIAMONDS),
                                        new Card(Rank.JACK, Color.HEARTS),
                                        new Card(Rank.JACK, Color.CLUBS),
                                        new Card(Rank.TEN, Color.SPADES),
                                        new Card(Rank.JACK, Color.SPADES))
                                        .collect(Collectors.toList())
                        ),
                        Combination.POKER,
                        "in case of {Jack-Diamonds, Jack-Hearts, Jack-Clubs, Ten-Spades, Jack-Spades} hand, should evaluate as 'Poker' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.JACK, Color.SPADES),
                                        new Card(Rank.TEN, Color.SPADES),
                                        new Card(Rank.QUEEN, Color.SPADES),
                                        new Card(Rank.KING, Color.SPADES),
                                        new Card(Rank.NINE, Color.SPADES))
                                        .collect(Collectors.toList())
                        ),
                        Combination.STRAIGHT_FLUSH,
                        "in case of {Jack-Spades, Ten-Spades, Queen-Spades, King-Spades, Nine-Spades} hand, should evaluate as 'Straight Flush' combinations"
                ),
                new TestParams(
                        new Hand(
                                Stream.of(
                                        new Card(Rank.JACK, Color.SPADES),
                                        new Card(Rank.TEN, Color.SPADES),
                                        new Card(Rank.ACE, Color.SPADES),
                                        new Card(Rank.KING, Color.SPADES),
                                        new Card(Rank.QUEEN, Color.SPADES))
                                        .collect(Collectors.toList())
                        ),
                        Combination.ROYAL_FLUSH,
                        "in case of {Jack-Spades, Ten-Spades, Ace-Spades, King-Spades, Nine-Spades} hand, should evaluate as 'Royal Flush' combinations"
                )
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestParams {
        private final Hand hand;
        private final Combination expectedCombination;
        private final String testCaseTitle;

        @Override
        public String toString() {
            return this.testCaseTitle;
        }
    }
}
