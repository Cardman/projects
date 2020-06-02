package cards.tarot;

public final class SimulatingTarotAbrupt extends AbstractSimulatingTarot {
    private GameTarot gameTarot;

    public SimulatingTarotAbrupt(GameTarot _gameTarot) {
        gameTarot = _gameTarot;
    }

    @Override
    public boolean stopped() {
        if (gameTarot.getTricks().isEmpty()) {
            return false;
        }
        if (!gameTarot.getTricks().last().getVuParToutJoueur()) {
            return false;
        }
        return gameTarot.getProgressingTrick().total() == 1;
    }
}
