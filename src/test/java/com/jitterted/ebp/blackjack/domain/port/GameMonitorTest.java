package com.jitterted.ebp.blackjack.domain.port;

import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class GameMonitorTest {

    @Test
    public void playerStandsThenGameIsOverAndResultsSentToMonitor() throws Exception {
        // create the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(new Deck(), gameMonitorSpy);
        game.initialDeal();

        game.playerStands();

        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndGoesBustThenGameIsOverAndResultsSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerHitsAndGoesBust(), gameMonitorSpy);
        game.initialDeal();

        game.playerHits();

        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndDoesNotBustThenResultsNotSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerHitsDoesNotBust(), gameMonitorSpy);
        game.initialDeal();

        game.playerHits();

        verify(gameMonitorSpy, never()).roundCompleted(any(Game.class));
    }

    @Test
    public void playerDealtBlackjackThenResultsSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerDealtBlackjack(), gameMonitorSpy);

        game.initialDeal();

        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

}