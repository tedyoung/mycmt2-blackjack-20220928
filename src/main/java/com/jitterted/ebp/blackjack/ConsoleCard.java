package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleCard {

    // DOMAIN OBJECT --> SUITABLE FOR OUTSIDE WORLD
    // CARD --> ANSI DISPLAY STRING (meant for Console)
    public static String display(Card card) {
        String[] lines = new String[7];
        lines[0] = "┌─────────┐";
        lines[1] = String.format("│%s%s       │", card.rank().display(), card.rank() == Rank.TEN ? "" : " ");
        lines[2] = "│         │";
        lines[3] = String.format("│    %s    │", card.suit().symbol());
        lines[4] = "│         │";
        lines[5] = String.format("│       %s%s│", card.rank() == Rank.TEN ? "" : " ", card.rank().display());
        lines[6] = "└─────────┘";

        Ansi.Color cardColor = card.suit().isRed() ? Ansi.Color.RED : Ansi.Color.BLACK;
        return ansi()
                .fg(cardColor).toString()
                + String.join(ansi().cursorDown(1)
                                    .cursorLeft(11)
                                    .toString(), lines);
    }
}
