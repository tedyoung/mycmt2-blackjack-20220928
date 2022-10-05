package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() throws Exception {
        Game game = new Game(StubDeck.playerHitsAndGoesBust());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() throws Exception {
        Game game = new Game(StubDeck.playerStandsAndBeatsDealer());
        game.initialDeal();

        game.playerStands();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void playerDealtBlackjackUponInitialDealThenWinsBlackjack() throws Exception {
        Game game = new Game(new StubDeck(Rank.TEN, Rank.EIGHT,
                                          Rank.ACE, Rank.JACK));

        game.initialDeal();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);

//        assertThat(game.isPlayerDone())
//                .isTrue();
    }

    @Test
    public void newGameNotBlackjackPlayerIsNotDone() throws Exception {
        Game game = new Game(new StubDeck(Rank.TEN, Rank.EIGHT,
                                          Rank.NINE, Rank.JACK));

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isFalse();
    }

}