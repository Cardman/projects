package cards.belote;

import cards.belote.enumerations.CardBelote;

public interface SimulatingBelote {
    void displayLineReturn();
    DisplayingBelote getDisplaying();
    void actingBid(byte _player);
    void actedBid(byte _player, BidBeloteSuit _bid);
    void nextRound(int _nbBids,byte _nbPlayers);
    void secRound(byte _nbPlayers);
    void noBid();
    void pause();
    void prepare();
    void beginDemo();
    void sleepSimu(long _millis);
    boolean stopped();
    void stopDemo();

    void endDeal();

    void declareSlam(byte _taker,BidBeloteSuit _bid);

    void firstCardPlaying(byte _joueur);

    void nextCardPlaying(byte _joueur);

    void belReb(HandBelote _hand, CardBelote _playedCard,byte _joueur);

    void declare(byte _joueur, DeclareHandBelote _annonces);

    void played(byte _joueur, CardBelote _playedCard);

    void displayUserHand(HandBelote _main);

    void displayTrickWinner(byte _trickWinner);

    void displayLastTrick(byte _trickWinner);

    void clearCarpet(byte _nbPlayers);

    void beginPlay();

    void dealCards(byte _donneur);

    void dealCard(int _step, int _gotCards, byte _p);
}
