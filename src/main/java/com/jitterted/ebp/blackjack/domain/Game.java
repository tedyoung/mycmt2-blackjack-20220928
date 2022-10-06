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
        if (playerHand.isBlackjack()) {
            playerDone = true;
        }
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public GameOutcome determineOutcome() {
        // enforce: playerDone must be true for this to be allowed to be called
        // also: dealer must have taken their turn (if appropriate)
        if (playerHand.isBusted()) {
            return GameOutcome.PLAYER_BUSTED;
        } else if (dealerHand.isBusted()) {
            return GameOutcome.DEALER_BUSTED;
        } else if (playerHand.isBlackjack()) {
            return GameOutcome.PLAYER_WINS_BLACKJACK;
        } else if (playerHand.beats(dealerHand)) {
            return GameOutcome.PLAYER_BEATS_DEALER;
        } else if (playerHand.pushes(dealerHand)) {
            return GameOutcome.PLAYER_PUSHES;
        } else {
            return GameOutcome.PLAYER_LOSES;
        }
    }

    private void dealerTurn() {
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
        // enforce protocol: can't call this method if playerDone == true
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
    }

    public void playerStands() {
        playerDone = true;
        dealerTurn();
    }

}

