package cards.belote;

import cards.belote.enumerations.CardBelote;
import code.util.Ints;

public interface SimulatingBelote {

    Ints players(GameBelote _g);
    void bid(GameBelote _g);
    CardBelote play(GameBelote _g);
    int ajouterDixDeDerPliEnCours(GameBelote _g);
    boolean keepBidding(GameBelote _g);
    void ecarter(GameBelote _gt);
    IntGameBelote getInt();
//    GameBelote partieBeloteSimulee();
//    void displayLineReturn();
    DisplayingBelote getDisplaying();
//    void actingBid(byte _player);
//    void actedBid(byte _player, BidBeloteSuit _bid);
    int stoppedRound(int _nbBids,int _nbPlayers);
//    void nextRound(int _nbBids,byte _nbPlayers);
//    void secRound(byte _nbPlayers);
    boolean noBid(GameBelote _g);
//    void noBid();
//    void pause();
//    void prepare();
//    void beginDemo();
//    void sleepSimu(long _millis);
    int stopped();
//    int stoppedDemo();
//    void stopDemo();

//    void endDeal();

//    void declareSlam(byte _taker,BidBeloteSuit _bid);

//    void firstCardPlaying(byte _joueur);
//
//    void nextCardPlaying(byte _joueur);
//
//    void belReb(HandBelote _hand, CardBelote _playedCard,byte _joueur);
//
//    void declare(byte _joueur, DeclareHandBelote _annonces);
//
//    void played(byte _joueur, CardBelote _playedCard);
//
//    void displayUserHand(HandBelote _main);

//    void displayTrickWinner(byte _trickWinner);

//    void displayLastTrick(byte _trickWinner);

//    void clearCarpet(byte _nbPlayers);

//    void beginPlay();

//    void dealCards(byte _donneur);
    int dealCardsStep(int _donneur);

    int dealCardStep(int _step, int _gotCards, int _p);
//    void dealCard(int _step, int _gotCards, byte _p);
    int completerDonne(GameBelote _g);
}
