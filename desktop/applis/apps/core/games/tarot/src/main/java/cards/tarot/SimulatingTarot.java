package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.BooleanList;
import code.util.Bytes;
import code.util.CustList;
import code.util.EnumList;

public interface SimulatingTarot {
    void displayLineReturn();
    DisplayingTarot getDisplaying();
    void actingBid(byte _player);
    void actedBid(byte _player, BidTarot _bid);
    void noBid();
    void constCallPlayer(byte _called);
//    void pause();
    void prepare();
    void beginDemo();
    void sleepSimu(long _millis);
    boolean stopped();
    void stopDemo();

    void endDeal();

    void callCard();
    void callCard(byte _taker,HandTarot _calledCards);
    void seeDog(HandTarot _calledCards);
    void autoCall(Bytes _called,byte _taker);

    void beforeSeeDog(byte _taker, HandTarot _curHand);

    void mergeDog(byte _taker, HandTarot _curHandAdd, HandTarot _last);

    void mergedDog(byte _taker, HandTarot _nextHand);

    void declareSlam(CustList<Boolean> _slam, byte _taker, BidTarot _bid);

    void firstCardPlaying(byte _joueur);

    void nextCardPlaying(byte _joueur);

    void declareHandfuls(byte _joueur, EnumList<Handfuls> _annoncesPoignees, HandTarot _poignee);

    void declareMiseres(byte _joueur, EnumList<Miseres> _annoncesMiseres);

    void displayCalled(byte _joueur);

    void played(byte _joueur, CardTarot _playedCard);

    void displayUserHand(HandTarot _main);

    void displayTrickWinner(byte _trickWinner);

    void displaySmallBound(CustList<Boolean> _smallBound, byte _trickWinner);

    void clearCarpet(byte _nbPlayers);

    void beginPlay();
}
