package cards.belote;

public abstract class AbstractSimulatingBelote implements SimulatingBelote {
    private final DisplayingBelote displayingBelote;
    private final GameBelote gameBelote;
    private final IntGameBelote intGameBelote;

    protected AbstractSimulatingBelote(DisplayingBelote _d, GameBelote _g, IntGameBelote _i) {
        displayingBelote = _d;
        this.gameBelote = _g;
        intGameBelote = _i;
    }

    @Override
    public IntGameBelote getInt() {
        return intGameBelote;
    }

    @Override
    public DisplayingBelote getDisplaying() {
        return displayingBelote;
    }
    @Override
    public GameBelote partieBeloteSimulee() {
        return gameBelote;
    }

}
