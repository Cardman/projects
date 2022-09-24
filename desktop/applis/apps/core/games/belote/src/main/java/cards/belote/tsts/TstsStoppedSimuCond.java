package cards.belote.tsts;

import cards.belote.GameBelote;

public final class TstsStoppedSimuCond implements TstsStoppedSimuInt {
    private final GameBelote game;

    public TstsStoppedSimuCond(GameBelote _g) {
        this.game = _g;
    }

    @Override
    public boolean stopped() {
        return !game.getTricks().isEmpty();
    }
}
