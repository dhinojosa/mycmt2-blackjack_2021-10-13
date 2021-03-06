package com.jitterted.ebp.blackjack.adapter.in.web;


import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Specific DTO to the Web, because
 * 1. I don't want to use the domain object directly in the web
 * 2. Domain object graph is (probably) not well suited
 */
public class GameView {
    private List<String> dealerCards;
    private List<String> playerCards;

    // TRANSLATE/TRANSFORM/MAP domain (GAME) -> view (ADAPTER)
    public static GameView of(Game game) {
        GameView gameView = new GameView();
        gameView.dealerCards = new ArrayList<>();
        stringListOf(game.dealerHand(), gameView.dealerCards);
        gameView.playerCards = new ArrayList<>();
        stringListOf(game.playerHand(), gameView.playerCards);
        return gameView;
    }

    private static void stringListOf(Hand hand, List<String> playerCards) {
        for (Card card : hand.cards()) {
            playerCards.add(displayOf(card));
        }
    }

    private static String displayOf(Card card) {
        return card.rank().display() + card.suit().symbol();
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<String> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<String> playerCards) {
        this.playerCards = playerCards;
    }
}
