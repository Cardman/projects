package cards.belote;

public abstract class AbstractSimulatingBelote implements SimulatingBelote {
    private final DisplayingBelote displayingBelote;
    private final GameBelote gameBelote;

    protected AbstractSimulatingBelote(DisplayingBelote _d,GameBelote _g) {
        displayingBelote = _d;
        this.gameBelote = _g;
    }

    @Override
    public DisplayingBelote getDisplaying() {
        return displayingBelote;
    }
    protected GameBelote partieBeloteSimulee() {
        return gameBelote;
    }

}
