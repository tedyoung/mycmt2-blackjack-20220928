package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() throws Exception {
        Deck deck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                 Rank.QUEEN, Rank.JACK,
                                 Rank.THREE);
        Game game = new Game(deck);
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo("You Busted, so you lose.  💸");
    }

}