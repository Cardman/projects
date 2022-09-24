package cards.tarot.tsts;

import cards.tarot.GameTarot;

public final class TstsTarotStoppedSimuCond implements TstsTarotStoppedSimuInt {
    private final GameTarot game;

    public TstsTarotStoppedSimuCond(GameTarot _g) {
        this.game = _g;
    }

    @Override
    public boolean stopped() {
        if (game.getTricks().isEmpty()) {
            return false;
        }
        return game.getTricks().last().getVuParToutJoueur();
    }
}
