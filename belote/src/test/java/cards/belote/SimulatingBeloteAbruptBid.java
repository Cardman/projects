package cards.belote;

public final class SimulatingBeloteAbruptBid extends AbstractSimulatingBelote {
    private GameBelote gameBelote;

    public SimulatingBeloteAbruptBid(GameBelote _gameBelote) {
        gameBelote = _gameBelote;
    }

    @Override
    public boolean stopped() {
        return gameBelote.getBids().size() == 1;
    }
}
