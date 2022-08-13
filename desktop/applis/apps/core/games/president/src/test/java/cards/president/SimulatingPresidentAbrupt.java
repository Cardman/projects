package cards.president;

import cards.president.enumerations.Playing;
import code.util.ByteMap;
import code.util.Bytes;
import code.util.CustList;

public final class SimulatingPresidentAbrupt extends AbstractSimulatingPresident {

    public SimulatingPresidentAbrupt(GamePresident _gamePresident) {
        super(_gamePresident);
    }

    @Override
    public void displayUserHand(HandPresident _hand) {
        getDisplaying();
    }

    @Override
    public void displayLooserMessage(HandPresident _h, byte _l, byte _w) {
        getDisplaying();
    }

    @Override
    public void displayWinnerMessage(HandPresident _h, byte _l, byte _w) {
        getDisplaying();
    }

    @Override
    public void displayLineReturn() {
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
    public boolean stopped() {
        return partiePresidentSimulee().getTricks().size() == 1;
    }

    @Override
    public void stopDemo() {
        getDisplaying();
    }

    @Override
    public void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, int _noDeal, CustList<HandPresident> _swtichedCards) {
        getDisplaying();
    }

    @Override
    public void setupDeck() {
        getDisplaying();
    }

    @Override
    public void gearStatusChange(ByteMap<Playing> _status, byte _starter) {
        getDisplaying();
    }

    @Override
    public void displayPlayedHand(HandPresident _hand) {
        getDisplaying();
    }

    @Override
    public void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer) {
        getDisplaying();
    }

    @Override
    public void displayTrickLeader(byte _player) {
        getDisplaying();
    }

    @Override
    public void endDeal() {
        getDisplaying();
    }
}
