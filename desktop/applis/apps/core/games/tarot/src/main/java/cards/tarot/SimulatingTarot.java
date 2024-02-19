package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.Bytes;
import code.util.CustList;
import code.util.IdList;
import code.util.core.BoolVal;

public interface SimulatingTarot {
    IntGameTarot getInt();
    GameTarot partieTarotSimulee();
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

    void declareSlam(byte _taker, BidTarot _bid);

    void firstCardPlaying(byte _joueur);

    void nextCardPlaying(byte _joueur);

    void declareHandfuls(byte _joueur, IdList<Handfuls> _annoncesPoignees, HandTarot _poignee);

    void declareMiseres(byte _joueur, IdList<Miseres> _annoncesMiseres);

    void displayCalled(byte _joueur);

    void played(byte _joueur, CardTarot _playedCard);

    void displayUserHand(HandTarot _main);

    void displayTrickWinner(byte _trickWinner);

    void displaySmallBound(CustList<BoolVal> _smallBound, byte _trickWinner);

    void clearCarpet(byte _nbPlayers);

    void beginPlay();
}
