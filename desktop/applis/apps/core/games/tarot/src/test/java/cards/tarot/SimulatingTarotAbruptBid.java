package cards.tarot;

public final class SimulatingTarotAbruptBid extends AbstractSimulatingTarot {
    private GameTarot gameTarot;

    public SimulatingTarotAbruptBid(GameTarot _gameTarot) {
        gameTarot = _gameTarot;
    }

    @Override
    public boolean stopped() {
        return gameTarot.getBids().size() == 1;
    }
}
