package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Hand;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandDisplayTest {

    @Test
    public void displayFaceUpCard() throws Exception {
        Hand hand = new Hand(List.of(new Card(Suit.HEARTS, Rank.ACE)));

        assertThat(ConsoleHand.displayFaceUpCard(hand))
                .isEqualTo("[31m┌─────────┐[1B[11D│A        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        A│[1B[11D└─────────┘");
    }

    @Test
    public void cardsAsString() throws Exception {
        Hand hand = new Hand(List.of(new Card(Suit.CLUBS, Rank.QUEEN),
                                     new Card(Suit.HEARTS, Rank.KING)));

        assertThat(ConsoleHand.cardsAsString(hand))
                .isEqualTo("[30m┌─────────┐[1B[11D│Q        │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│        Q│[1B[11D└─────────┘[6A[1C[31m┌─────────┐[1B[11D│K        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        K│[1B[11D└─────────┘");
    }

}

