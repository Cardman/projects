package cards.tarot;

public abstract class AbstractSimulatingTarot implements SimulatingTarot {
    private final DisplayingTarot displayingTarot;
    private final GameTarot gameTarot;

    protected AbstractSimulatingTarot(DisplayingTarot _d,GameTarot _g) {
        displayingTarot = _d;
        this.gameTarot = _g;
    }

    @Override
    public DisplayingTarot getDisplaying() {
        return displayingTarot;
    }

    protected GameTarot partieTarotSimulee(){
        return gameTarot;
    }
}
