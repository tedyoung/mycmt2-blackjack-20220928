package com.jitterted.ebp.blackjack;

import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleHand {

    static String displayFaceUpCard(Hand hand) {
        return ConsoleCard.display(hand.faceUpCard());
    }

    public static String cardsAsString(Hand hand) {
        return hand.cards().stream()
                   .map(ConsoleCard::display)
                   .collect(Collectors.joining(
                           ansi().cursorUp(6).cursorRight(1).toString()));
    }
}
