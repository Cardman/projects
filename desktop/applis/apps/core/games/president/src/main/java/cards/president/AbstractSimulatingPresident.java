package cards.president;

import code.threads.AbstractAtomicInteger;
import code.threads.ConcreteInteger;
import code.util.CustList;
import code.util.Ints;

public abstract class AbstractSimulatingPresident implements SimulatingPresident {
    public static final int STATE_ALIVE = 0;
    public static final int STATE_STOPPED = 1;
    private final DisplayingPresident displayingPresident;
    private final IntGamePresident intGamePresident;
    private final AbstractAtomicInteger state;
    private final CustList<TricksHandsPresident> history = new CustList<TricksHandsPresident>();
    protected AbstractSimulatingPresident() {
        this(new DisplayingPresident(), new DefGamePresident(), new ConcreteInteger());
    }

    protected AbstractSimulatingPresident(DisplayingPresident _d, IntGamePresident _ia, AbstractAtomicInteger _s) {
        displayingPresident = _d;
//        gamePresident = _game;
        intGamePresident = _ia;
        state = _s;
    }

//    @Override
//    public int stoppedDemo() {
//        return stopped();
//    }
    @Override
    public HandPresident userHand(GamePresident _g) {
        return new HandPresident();
    }

    @Override
    public HandPresident displayUserHand(HandPresident _hand, GamePresident _g) {
        return _g.mainUtilisateurTriee(_hand, getDisplaying());
    }

    @Override
    public int  displaySwitchedUserHand(GamePresident _g, Ints _winners, Ints _loosers, int _noDeal, CustList<HandPresident> _swtichedCards) {
        return _g.getFirstLeader();
    }

    @Override
    public HandPresident playedCards(GamePresident _game) {
        return getInt().playedCards(_game);
    }

    @Override
    public int  addCardsToCurrentTrickAndLoop(GamePresident _game, HandPresident _hand, HandPresident _userHand) {
        return _game.addCardsToCurrentTrickAndLoop(_hand);
    }

    @Override
    public Ints getNewRanks(GamePresident _g, int _no) {
        return _g.getNewRanks();
    }

    @Override
    public int prepareNext(int _no) {
        return _no+1;
    }

    @Override
    public IntGamePresident getInt() {
        return intGamePresident;
    }
    @Override
    public DisplayingPresident getDisplaying() {
        return displayingPresident;
    }

    @Override
    public CustList<TricksHandsPresident> getHistory() {
        return history;
    }

//    @Override
//    public GamePresident partiePresidentSimulee() {
//        return gamePresident;
//    }

    public AbstractAtomicInteger getState() {
        return state;
    }
}
