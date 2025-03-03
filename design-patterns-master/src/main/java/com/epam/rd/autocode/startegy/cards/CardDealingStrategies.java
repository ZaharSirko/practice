package com.epam.rd.autocode.startegy.cards;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new LinkedHashMap<>();

            for (int i = 1; i <= players; i++) {
                dealtCards.put("Player " + i, new Stack<>());
            }

            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= players; j++) {
                    dealtCards.get("Player " + j).add(deck.dealCard());
                }
            }


            Stack<Card> communityCards = new Stack<>();
            for (int i = 0; i < 5; i++) {
                communityCards.add(deck.dealCard());
            }
            dealtCards.put("Community", communityCards);

            List<Card> remainingCards = deck.restCards();
            dealtCards.put("Remaining", remainingCards);

            return dealtCards;
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new LinkedHashMap<>();

            for (int i = 1; i <= players; i++) {
                dealtCards.put("Player " + i, new Stack<>());
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 1; j <= players; j++) {
                    dealtCards.get("Player " + j).add(deck.dealCard());
                }
            }
            List<Card> remainingCards = deck.restCards();
            dealtCards.put("Remaining", remainingCards);

            return dealtCards;
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new LinkedHashMap<>();

            for (int i = 1; i <= players; i++) {
                dealtCards.put("Player " + i, new Stack<>());
            }

            for (int i = 0; i < 13; i++) {
                for (int j = 1; j <= players; j++) {
                    dealtCards.get("Player " + j).add(deck.dealCard());
                }
            }

            return dealtCards;
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> dealtCards = new LinkedHashMap<>();

            for (int i = 1; i <= players; i++) {
                dealtCards.put("Player " + i, new Stack<>());
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 1; j <= players; j++) {
                    dealtCards.get("Player " + j).add(deck.dealCard());
                }
            }

            Stack<Card> trumpCard = new Stack<>();
            trumpCard.add(deck.dealCard());

            List<Card> remainingCards = deck.restCards();
            dealtCards.put("Remaining", remainingCards);


            dealtCards.put("Trump card", trumpCard);

            return dealtCards;

        };
    }

}
