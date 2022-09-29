package com.jitterted.ebp.blackjack.domain;

public class Game {

    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    private boolean playerDone = false;

    public Game(Deck deck) {
        this.deck = deck;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    // String here is suspect: may be violating separation of concerns (and in fact, it does)
    public String determineOutcome() {
        if (playerHand.isBusted()) {
            return "You Busted, so you lose.  💸";
        } else if (dealerHand.isBusted()) {
            return "Dealer went BUST, Player wins! Yay for you!! 💵";
        } else if (playerHand.beats(dealerHand)) {
            return "You beat the Dealer! 💵";
        } else if (playerHand.pushes(dealerHand)) {
            return "Push: Nobody wins, we'll call it even.";
        } else {
            return "You lost to the Dealer. 💸";
        }
    }

    public void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    public Hand playerHand() {
        return playerHand;
    }

    // QUERY METHOD
    // -> Snapshot
    // -> Integrity/Encapsulation
    // -> Immutable
    //
    // OPTIONS?
    // =no=  1. copy (clone?) - a copy (new instance) of Hand: mutable, misleading
    // =no=  2. Stream<Card> - doesn't have value() nor faceUpCard() methods
    // =yes= 3a. ImmutableHand (Value Object - DOMAIN) - provide QUERY methods, but just have data
    //           or name it HandView - can have behavior
    // =no=  3b. Data Transfer Object (live in Adapters) - provide Getter/Setter for data - no behavior
    // =no=  4. Wrap with an interface - ReadOnlyHand - has just QUERY methods, changes underneath (not a snapshot)
    public Hand dealerHand() {
        return dealerHand;
    }

    public boolean isPlayerDone() {
        return playerDone;
    }

    public void playerHits() {
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
    }

    public void playerStands() {
        playerDone = true;
    }

}

