package cards.president;

import cards.president.enumerations.Playing;
import code.util.ByteMap;
import code.util.Bytes;
import code.util.CustList;

public abstract class AbstractSimulatingPresident implements SimulatingPresident {
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    @Override
    public void displayUserHand(HandPresident _hand) {

    }

    @Override
    public DisplayingPresident getDisplaying() {
        return displayingPresident;
    }

    @Override
    public void displayLooserMessage(HandPresident _h, byte _l, byte _w) {

    }

    @Override
    public void displayWinnerMessage(HandPresident _h, byte _l, byte _w) {

    }

    @Override
    public void displayLineReturn() {

    }

    @Override
    public void pause() {

    }

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
    public void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, int _noDeal, CustList<HandPresident> _swtichedCards) {

    }

    @Override
    public void setupDeck() {

    }

    @Override
    public void gearStatusChange(ByteMap<Playing> _status, byte _starter) {

    }

    @Override
    public void displayPlayedHand(HandPresident _hand) {

    }

    @Override
    public void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer) {

    }

    @Override
    public void displayTrickLeader(byte _player) {

    }

    @Override
    public void endDeal() {

    }
}
