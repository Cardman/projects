package cards.belote;

public final class SimulatingBeloteAbrupt extends AbstractSimulatingBelote {
    private GameBelote gameBelote;

    public SimulatingBeloteAbrupt(GameBelote _gameBelote) {
        gameBelote = _gameBelote;
    }

    @Override
    public boolean stopped() {
        if (gameBelote.getTricks().isEmpty()) {
            return false;
        }
        return gameBelote.getPliEnCours().total() == 1;
    }
}
