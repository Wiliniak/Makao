package com.wwil.makao.backend;

import java.util.Collections;
import java.util.List;

public class PlayerHand {
    private final List<Card> cards;

    public PlayerHand(List<Card> cards) {
        this.cards = cards;
    }

    public void addCardToHand(Card card) {
        cards.add(card);
    }

    public void addCardsToHand(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public void removeCardFromHand(Card card) {
        cards.remove(card);
    }

    public boolean checkIfPlayerHaveNoCards() {
        return cards.isEmpty();
    }

    public Card findCardToDemand() {
        List<Card> playerCards = cards;
        Collections.shuffle(playerCards);
        for (Card card : playerCards) {
            if (card.getRank().getAbility() == Ability.NONE) {
                return card;
            }
        }
        return null;
    }

    public Card findDemandedCard(Card demanded, boolean lookForJ) {
        List<Card> playerCards = cards;
        Collections.shuffle(playerCards);
        Card cardToPlay = null;
        for (Card card : playerCards) {
            if (lookForJ && card.getRank().equals(Rank.J)) {
                return card;
            }

            if (card.getRank() == demanded.getRank()) {
                cardToPlay = card;
            }
        }
        return cardToPlay;
    }

    public Suit giveMostDominantSuit() {
        int[] counts = new int[4]; // Tablica przechowująca liczbę wystąpień dla każdego koloru

        for (Card card : cards) {
            Suit cardSuit = card.getSuit();
            if (cardSuit != Suit.BLACK && cardSuit != Suit.RED) { // Pomijanie BLACK i RED
                counts[cardSuit.ordinal()]++; // Inkrementacja odpowiedniego licznika
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }

        return Suit.values()[maxIndex]; // Zwracanie koloru z największą liczbą wystąpień
    }

    public List<Card> getCards() {
        return cards;
    }
}
