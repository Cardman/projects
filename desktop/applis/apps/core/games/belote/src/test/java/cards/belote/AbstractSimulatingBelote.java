package cards.belote;

import cards.belote.enumerations.CardBelote;

public abstract class AbstractSimulatingBelote implements SimulatingBelote {
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    @Override
    public void displayLineReturn() {

    }

    @Override
    public DisplayingBelote getDisplaying() {
        return displayingBelote;
    }

    @Override
    public void actingBid(byte _player) {

    }

    @Override
    public void actedBid(byte _player, BidBeloteSuit _bid) {

    }

    @Override
    public void nextRound(int _nbBids, byte _nbPlayers) {

    }

    @Override
    public void secRound(byte _nbPlayers) {

    }

    @Override
    public void noBid() {

    }

//    @Override
//    public void pause() {
//
//    }

    @Override
    public void prepare() {

    }

    @Override
    public void beginDemo() {

    }

    @Override
    public void sleepSimu(long _millis) {

    }

    @Override
    public void stopDemo() {

    }

    @Override
    public void endDeal() {

    }

    @Override
    public void declareSlam(byte _taker, BidBeloteSuit _bid) {

    }

    @Override
    public void firstCardPlaying(byte _joueur) {

    }

    @Override
    public void nextCardPlaying(byte _joueur) {

    }

    @Override
    public void belReb(HandBelote _hand, CardBelote _playedCard, byte _joueur) {

    }

    @Override
    public void declare(byte _joueur, DeclareHandBelote _annonces) {

    }

    @Override
    public void played(byte _joueur, CardBelote _playedCard) {

    }

    @Override
    public void displayUserHand(HandBelote _main) {

    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {

    }

    @Override
    public void displayLastTrick(byte _trickWinner) {

    }

    @Override
    public void clearCarpet(byte _nbPlayers) {

    }

    @Override
    public void beginPlay() {

    }

    @Override
    public void dealCards(byte _donneur) {

    }

    @Override
    public void dealCard(int _step, int _gotCards, byte _p) {

    }
}
