package cards.belote;

import cards.belote.enumerations.CardBelote;
import code.threads.AbstractAtomicInteger;
import code.threads.ConcreteInteger;
import code.util.Ints;

public abstract class AbstractSimulatingBelote implements SimulatingBelote {
    public static final int STATE_ALIVE = 0;
    public static final int STATE_STOPPED = 1;
    private final DisplayingBelote displayingBelote;
    private final IntGameBelote intGameBelote;
    private final AbstractAtomicInteger state;

    protected AbstractSimulatingBelote() {
        this(new DisplayingBelote(), new DefGameBelote(), new ConcreteInteger());
    }
    protected AbstractSimulatingBelote(DisplayingBelote _d, IntGameBelote _i, AbstractAtomicInteger _s) {
        displayingBelote = _d;
        intGameBelote = _i;
        state = _s;
    }

    public AbstractAtomicInteger getState() {
        return state;
    }

    @Override
    public Ints players(GameBelote _g) {
        return _g.getDeal().orderedPlayersBegin(_g.getRegles());
    }

    @Override
    public void bid(GameBelote _g) {
        _g.ajouterContrat(getInt().strategieContrat(_g));
    }

    @Override
    public boolean keepBidding(GameBelote _g) {
        return _g.keepBidding();
    }

    @Override
    public int stoppedRound(int _nbBids, int _nbPlayers) {
        return stopped();
    }

    @Override
    public boolean noBid(GameBelote _g) {
        return !_g.getBid().jouerDonne();
    }
//
//    @Override
//    public int stoppedDemo() {
//        return stopped();
//    }

    @Override
    public int dealCardsStep(int _donneur) {
        return 1;
    }

    @Override
    public int dealCardStep(int _step, int _gotCards, int _p) {
        return _step + 1;
    }

    @Override
    public void ecarter(GameBelote _gt) {
        _gt.ecarter(getInt());
    }

    @Override
    public int completerDonne(GameBelote _g) {
        return _g.completerDonne();
    }

    @Override
    public CardBelote play(GameBelote _g) {
        return _g.playCard(getInt());
    }

    @Override
    public int ajouterDixDeDerPliEnCours(GameBelote _g) {
        return _g.ajouterDixDeDerPliEnCours();
    }

    @Override
    public IntGameBelote getInt() {
        return intGameBelote;
    }

    @Override
    public DisplayingBelote getDisplaying() {
        return displayingBelote;
    }
//    @Override
//    public GameBelote partieBeloteSimulee() {
//        return gameBelote;
//    }

}
