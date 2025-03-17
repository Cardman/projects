package cards.president;

import code.util.CustList;
import code.util.Ints;

public interface SimulatingPresident {
    IntGamePresident getInt();
//    GamePresident partiePresidentSimulee();
    HandPresident displayUserHand(HandPresident _hand, GamePresident _g);
//    void displayUserHand(HandPresident _hand);
    DisplayingPresident getDisplaying();
    HandPresident userHand(GamePresident _g);
    HandPresident playedCards(GamePresident _game);
    int  addCardsToCurrentTrickAndLoop(GamePresident _game, HandPresident _hand, HandPresident _userHand);

//    void displayLooserMessage(HandPresident _h, byte _l, byte _w);
//    void displayWinnerMessage(HandPresident _h, byte _l, byte _w);
//    void displayLineReturn();
//    void pause();
//    void prepare();
    int prepareNext(int _no);
//    void beginDemo();
//    void sleepSimu(long _millis);
    int stopped();
//    int stoppedDemo();
//    boolean stopped();
//    void stopDemo();
    int  displaySwitchedUserHand(GamePresident _g,Ints _winners, Ints _loosers, int _noDeal, CustList<HandPresident> _swtichedCards);
//    void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, int _noDeal, CustList<HandPresident> _swtichedCards);
//    void setupDeck();
//    void gearStatusChange(ByteMap<Playing> _status, byte _starter);
//    void displayPlayedHand(HandPresident _hand);
//    void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer);
    Ints getNewRanks(GamePresident _g, int _no);
    CustList<TricksHandsPresident> getHistory();
//    void displayTrickLeader(byte _player);
//    void endDeal();
}
