package cards.tarot;

public abstract class AbstractSimulatingTarot implements SimulatingTarot {
    private final DisplayingTarot displayingTarot;
    private final GameTarot gameTarot;
    private final IntGameTarot intGameTarot;

    protected AbstractSimulatingTarot(DisplayingTarot _d,GameTarot _g,IntGameTarot _ia) {
        displayingTarot = _d;
        this.gameTarot = _g;
        this.intGameTarot = _ia;
    }

    public IntGameTarot getInt() {
        return intGameTarot;
    }

    @Override
    public DisplayingTarot getDisplaying() {
        return displayingTarot;
    }

    @Override
    public GameTarot partieTarotSimulee(){
        return gameTarot;
    }
}
