package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.belote.tsts.TstsBeloteSimulating;
import cards.belote.tsts.TstsBeloteStoppedSimuFalse;

public final class SimulatingBeloteNormal extends TstsBeloteSimulating {
    public SimulatingBeloteNormal() {
        super(new DisplayingBelote(),null, new TstsBeloteStoppedSimuFalse());
    }

    @Override
    public void displayLineReturn() {
        getDisplaying();
    }

    @Override
    public void actingBid(byte _player) {
        getDisplaying();
    }

    @Override
    public void actedBid(byte _player, BidBeloteSuit _bid) {
        getDisplaying();
    }

    @Override
    public void nextRound(int _nbBids, byte _nbPlayers) {
        getDisplaying();
    }

    @Override
    public void secRound(byte _nbPlayers) {
        getDisplaying();
    }

    @Override
    public void noBid() {
        getDisplaying();
    }

    @Override
    public void prepare() {
        getDisplaying();
    }

    @Override
    public void beginDemo() {
        getDisplaying();
    }

    @Override
    public void sleepSimu(long _millis) {
        getDisplaying();
    }

    @Override
    public void stopDemo() {
        getDisplaying();
    }

    @Override
    public void endDeal() {
        getDisplaying();
    }

    @Override
    public void declareSlam(byte _taker, BidBeloteSuit _bid) {
        getDisplaying();
    }

    @Override
    public void firstCardPlaying(byte _joueur) {
        getDisplaying();
    }

    @Override
    public void nextCardPlaying(byte _joueur) {
        getDisplaying();
    }

    @Override
    public void belReb(HandBelote _hand, CardBelote _playedCard, byte _joueur) {
        getDisplaying();
    }

    @Override
    public void declare(byte _joueur, DeclareHandBelote _annonces) {
        getDisplaying();
    }

    @Override
    public void played(byte _joueur, CardBelote _playedCard) {
        getDisplaying();
    }

    @Override
    public void displayUserHand(HandBelote _main) {
        getDisplaying();
    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {
        getDisplaying();
    }

    @Override
    public void displayLastTrick(byte _trickWinner) {
        getDisplaying();
    }

    @Override
    public void clearCarpet(byte _nbPlayers) {
        getDisplaying();
    }

    @Override
    public void beginPlay() {
        partieBeloteSimulee();
        getDisplaying();
    }

    @Override
    public void dealCards(byte _donneur) {
        getDisplaying();
    }

    @Override
    public void dealCard(int _step, int _gotCards, byte _p) {
        getDisplaying();
    }
}
