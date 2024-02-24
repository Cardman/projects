package cards.tarot;

import cards.tarot.enumerations.CardTarot;

public interface SimulatingTarot {
    IntGameTarot getInt();
//    GameTarot partieTarotSimulee();
//    void displayLineReturn();
    DisplayingTarot getDisplaying();
    byte dealer(GameTarot _gt);
    void bid(GameTarot _gt);
//    void actingBid(byte _player);
//    void actedBid(byte _player, BidTarot _bid);
//    void noBid();
    boolean noBid(GameTarot _g);
//    void constCallPlayer(byte _called);
    byte constCallPlayerCall(byte _called);
    void intelligenceArtificielleAppel(GameTarot _gt);
    void ecarter(GameTarot _gt);
    void appelApresEcart(GameTarot _gt);
    void gererChienInconnu(GameTarot _gt);
//    void pause();
//    void prepare();
//    void beginDemo();
//    void sleepSimu(long _millis);

    int stopped();
    int stoppedDemo();

//    boolean stopped();
//    void stopDemo();

//    void endDeal();
    void firstLead(GameTarot _gt);
    CardTarot play(GameTarot _g);
    byte ajouterPetitAuBoutPliEnCours(GameTarot _gt);

//    void callCard();
//    void callCard(byte _taker,HandTarot _calledCards);
//    void seeDog(HandTarot _calledCards);
//    void autoCall(Bytes _called,byte _taker);

//    void beforeSeeDog(byte _taker, HandTarot _curHand);

//    void mergeDog(byte _taker, HandTarot _curHandAdd, HandTarot _last);

//    void mergedDog(byte _taker, HandTarot _nextHand);

//    void declareSlam(byte _taker, BidTarot _bid);

//    void firstCardPlaying(byte _joueur);
//
//    void nextCardPlaying(byte _joueur);
//
//    void declareHandfuls(byte _joueur, IdList<Handfuls> _annoncesPoignees, HandTarot _poignee);
//
//    void declareMiseres(byte _joueur, IdList<Miseres> _annoncesMiseres);

//    void displayCalled(byte _joueur);

//    void played(byte _joueur, CardTarot _playedCard);
//
//    void displayUserHand(HandTarot _main);

//    void displayTrickWinner(byte _trickWinner);

//    void displaySmallBound(CustList<BoolVal> _smallBound, byte _trickWinner);

//    void clearCarpet(byte _nbPlayers);

//    void beginPlay();
}
