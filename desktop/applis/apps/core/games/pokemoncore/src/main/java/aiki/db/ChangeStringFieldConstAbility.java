package aiki.db;

import aiki.fight.moves.effects.*;

public final class ChangeStringFieldConstAbility implements ChangeStringField {
    private final EffectSwitchAbilities effectWhileSendingWithStatistic;

    public ChangeStringFieldConstAbility(EffectSwitchAbilities _l) {
        this.effectWhileSendingWithStatistic = _l;
    }

    @Override
    public String value() {
        return effectWhileSendingWithStatistic.getConstAbility();
    }

    @Override
    public void value(String _v) {
        effectWhileSendingWithStatistic.setConstAbility(_v);
    }
}
