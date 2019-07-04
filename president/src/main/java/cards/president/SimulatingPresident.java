package cards.president;

import cards.president.enumerations.Playing;
import code.util.ByteMap;
import code.util.Bytes;

public interface SimulatingPresident {
    void displayUserHand(HandPresident _hand);
    DisplayingPresident getDisplaying();

    void displayLooserMessage(HandPresident _h, byte _l, byte _w);
    void displayWinnerMessage(HandPresident _h, byte _l, byte _w);
    void displayLineReturn();
    void pause();
    void prepare();
    void beginDemo();
    void sleepSimu(long _millis);
    boolean stopped();
    void stopDemo();
    void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, int _noDeal, ByteMap<HandPresident> _swtichedCards);
    void setupDeck();
    void gearStatusChange(ByteMap<Playing> _status, byte _starter);
    void displayPlayedHand(HandPresident _hand);
    void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer);

    void displayTrickLeader(byte _player);
    void endDeal();
}
