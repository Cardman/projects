package cards.belote.tsts;

import cards.belote.GameBelote;

public final class TstsBeloteStoppedSimuCond implements TstsBeloteStoppedSimuInt {
    private final GameBelote game;

    public TstsBeloteStoppedSimuCond(GameBelote _g) {
        this.game = _g;
    }

    @Override
    public boolean stopped() {
        return !game.getTricks().isEmpty();
    }
}
