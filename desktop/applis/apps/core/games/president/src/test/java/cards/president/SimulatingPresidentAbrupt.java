package cards.president;

public final class SimulatingPresidentAbrupt extends AbstractSimulatingPresident {
    private GamePresident gamePresident;

    public SimulatingPresidentAbrupt(GamePresident _gamePresident) {
        gamePresident = _gamePresident;
    }

    @Override
    public boolean stopped() {
        return gamePresident.getTricks().size() == 1;
    }
}
