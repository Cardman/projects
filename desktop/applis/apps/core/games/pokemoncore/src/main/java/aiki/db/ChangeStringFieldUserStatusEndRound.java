package aiki.db;

import aiki.fight.moves.effects.*;

public final class ChangeStringFieldUserStatusEndRound implements ChangeStringField {
    private final EffectEndRoundIndividual effectEndRoundIndividual;

    public ChangeStringFieldUserStatusEndRound(EffectEndRoundIndividual _l) {
        this.effectEndRoundIndividual = _l;
    }

    @Override
    public String value() {
        return effectEndRoundIndividual.getUserStatusEndRound();
    }

    @Override
    public void value(String _v) {
        effectEndRoundIndividual.setUserStatusEndRound(_v);
    }
}
