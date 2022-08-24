package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.Bytes;
import code.util.CustList;
import code.util.EnumList;
import code.util.core.BoolVal;

public final class SimulatingTarotNormal extends AbstractSimulatingTarot {
    public SimulatingTarotNormal() {
        super(new DisplayingTarot(), null);
    }

    @Override
    public boolean stopped() {
        return false;
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
    public void actedBid(byte _player, BidTarot _bid) {
        getDisplaying();
    }

    @Override
    public void noBid() {
        getDisplaying();
    }

    @Override
    public void constCallPlayer(byte _called) {
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
    public void callCard() {
        getDisplaying();
    }

    @Override
    public void callCard(byte _taker, HandTarot _calledCards) {
        getDisplaying();
    }

    @Override
    public void seeDog(HandTarot _calledCards) {
        getDisplaying();
    }

    @Override
    public void autoCall(Bytes _called, byte _taker) {
        getDisplaying();
    }

    @Override
    public void beforeSeeDog(byte _taker, HandTarot _curHand) {
        getDisplaying();
    }

    @Override
    public void mergeDog(byte _taker, HandTarot _curHandAdd, HandTarot _last) {
        getDisplaying();
    }

    @Override
    public void mergedDog(byte _taker, HandTarot _nextHand) {
        getDisplaying();
    }

    @Override
    public void declareSlam(CustList<BoolVal> _slam, byte _taker, BidTarot _bid) {
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
    public void declareHandfuls(byte _joueur, EnumList<Handfuls> _annoncesPoignees, HandTarot _poignee) {
        getDisplaying();
    }

    @Override
    public void declareMiseres(byte _joueur, EnumList<Miseres> _annoncesMiseres) {
        getDisplaying();
    }

    @Override
    public void displayCalled(byte _joueur) {
        getDisplaying();
    }

    @Override
    public void played(byte _joueur, CardTarot _playedCard) {
        getDisplaying();
    }

    @Override
    public void displayUserHand(HandTarot _main) {
        getDisplaying();
    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {
        getDisplaying();
    }

    @Override
    public void displaySmallBound(CustList<BoolVal> _smallBound, byte _trickWinner) {
        getDisplaying();
    }

    @Override
    public void clearCarpet(byte _nbPlayers) {
        getDisplaying();
    }

    @Override
    public void beginPlay() {
        getDisplaying();
    }
}
