package cards.president;

public abstract class AbstractSimulatingPresident implements SimulatingPresident {
    private final DisplayingPresident displayingPresident;
    private final GamePresident gamePresident;
    protected AbstractSimulatingPresident(GamePresident _game) {
        this(new DisplayingPresident(),_game);
    }

    protected AbstractSimulatingPresident(DisplayingPresident _d, GamePresident _game) {
        displayingPresident = _d;
        gamePresident = _game;
    }

    @Override
    public DisplayingPresident getDisplaying() {
        return displayingPresident;
    }


    protected GamePresident partiePresidentSimulee() {
        return gamePresident;
    }

}
