package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.BooleanList;
import code.util.Bytes;
import code.util.CustList;
import code.util.EnumList;
import code.util.core.BoolVal;

public abstract class AbstractSimulatingTarot implements SimulatingTarot {
    private DisplayingTarot displayingTarot = new DisplayingTarot();
    @Override
    public void displayLineReturn() {

    }

    @Override
    public DisplayingTarot getDisplaying() {
        return displayingTarot;
    }

    @Override
    public void actingBid(byte _player) {

    }

    @Override
    public void actedBid(byte _player, BidTarot _bid) {

    }

    @Override
    public void noBid() {

    }

    @Override
    public void constCallPlayer(byte _called) {

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
    public void callCard() {

    }

    @Override
    public void callCard(byte _taker, HandTarot _calledCards) {

    }

    @Override
    public void seeDog(HandTarot _calledCards) {

    }

    @Override
    public void autoCall(Bytes _called, byte _taker) {

    }

    @Override
    public void beforeSeeDog(byte _taker, HandTarot _curHand) {

    }

    @Override
    public void mergeDog(byte _taker, HandTarot _curHandAdd, HandTarot _last) {

    }

    @Override
    public void mergedDog(byte _taker, HandTarot _nextHand) {

    }

    @Override
    public void declareSlam(CustList<BoolVal> _slam, byte _taker, BidTarot _bid) {

    }

    @Override
    public void firstCardPlaying(byte _joueur) {

    }

    @Override
    public void nextCardPlaying(byte _joueur) {

    }

    @Override
    public void declareHandfuls(byte _joueur, EnumList<Handfuls> _annoncesPoignees, HandTarot _poignee) {

    }

    @Override
    public void declareMiseres(byte _joueur, EnumList<Miseres> _annoncesMiseres) {

    }

    @Override
    public void displayCalled(byte _joueur) {

    }

    @Override
    public void played(byte _joueur, CardTarot _playedCard) {

    }

    @Override
    public void displayUserHand(HandTarot _main) {

    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {

    }

    @Override
    public void displaySmallBound(CustList<BoolVal> _smallBound, byte _trickWinner) {

    }

    @Override
    public void clearCarpet(byte _nbPlayers) {

    }

    @Override
    public void beginPlay() {

    }
}
