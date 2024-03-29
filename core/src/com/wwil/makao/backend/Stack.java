package com.wwil.makao.backend;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private final List<Card> cards = new ArrayList<>();

    public void addCardToStack(Card card){
        cards.add(card);
    }
    public boolean isJackOnTop(){
        return peekCard().getRank().equals(Rank.J);
    }

    public boolean isJackBeforeJoker(){
        return peekCard().getRank().equals(Rank.JOKER) && getCards().get(getCards().size()-2).getRank().equals(Rank.J);
    }
    public Card peekCard(){
        return cards.get(getCards().size()-1);
    }
    public boolean isRefreshNeeded(){
        return getCards().size() > 3;
    }

    public List<Card> getCards() {
        return cards;
    }
}
