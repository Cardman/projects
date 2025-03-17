package cards.tarot;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import code.threads.AbstractAtomicInteger;
import code.threads.ConcreteInteger;

public abstract class AbstractSimulatingTarot implements SimulatingTarot {
    public static final int STATE_ALIVE = 0;
    public static final int STATE_STOPPED = 1;
    private final DisplayingTarot displayingTarot;
    private final IntGameTarot intGameTarot;
    private final AbstractAtomicInteger state;

    protected AbstractSimulatingTarot() {
        this(new DisplayingTarot(), new DefGameTarot(), new ConcreteInteger());
    }
    protected AbstractSimulatingTarot(DisplayingTarot _d, IntGameTarot _ia, AbstractAtomicInteger _state) {
        displayingTarot = _d;
        this.intGameTarot = _ia;
        this.state = _state;
    }

    @Override
    public int dealer(GameTarot _gt) {
        return _gt.getDistribution().getDealer();
    }

    @Override
    public void bid(GameTarot _gt) {
        BidTarot contratTmp_ = getInt().strategieContrat(_gt);
        _gt.ajouterContrat(contratTmp_);
    }

    @Override
    public boolean noBid(GameTarot _g) {
        return !_g.getContrat().isJouerDonne() && _g.pasJeuApresPasse();
    }

    @Override
    public int constCallPlayerCall(int _called) {
        return _called;
    }

    @Override
    public void intelligenceArtificielleAppel(GameTarot _gt) {
        _gt.intelligenceArtificielleAppel(getInt());
    }

    @Override
    public void ecarter(GameTarot _gt) {
        _gt.ecarter(getInt());
    }

    @Override
    public void appelApresEcart(GameTarot _gt) {
        _gt.appelApresEcart(getInt());
    }

    @Override
    public void gererChienInconnu(GameTarot _gt) {
//        _gt.gererChienInconnu();
//        _gt.slam(getInt());
        _gt.ecart(getInt());
    }

    @Override
    public void firstLead(GameTarot _gt) {
        _gt.firstLead();
    }

    @Override
    public CardTarot play(GameTarot _g) {
        return _g.currentPlayerHasPlayed(getInt());
    }

    @Override
    public int ajouterPetitAuBoutPliEnCours(GameTarot _gt) {
        return _gt.ajouterPetitAuBoutPliEnCours();
    }

//    @Override
//    public int stoppedDemo() {
//        return stopped();
//    }

    public IntGameTarot getInt() {
        return intGameTarot;
    }

    @Override
    public DisplayingTarot getDisplaying() {
        return displayingTarot;
    }

//    @Override
//    public GameTarot partieTarotSimulee(){
//        return gameTarot;
//    }

    public AbstractAtomicInteger getState() {
        return state;
    }
}
