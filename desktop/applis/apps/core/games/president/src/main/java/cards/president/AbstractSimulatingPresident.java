package cards.president;

public abstract class AbstractSimulatingPresident implements SimulatingPresident {
    private final DisplayingPresident displayingPresident;
    private final GamePresident gamePresident;
    private final IntGamePresident intGamePresident;
    protected AbstractSimulatingPresident(GamePresident _game) {
        this(new DisplayingPresident(),_game,new DefGamePresident());
    }

    protected AbstractSimulatingPresident(DisplayingPresident _d, GamePresident _game, IntGamePresident _ia) {
        displayingPresident = _d;
        gamePresident = _game;
        intGamePresident = _ia;
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
    public GamePresident partiePresidentSimulee() {
        return gamePresident;
    }

}
