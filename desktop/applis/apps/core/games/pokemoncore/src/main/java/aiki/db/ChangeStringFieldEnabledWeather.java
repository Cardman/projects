package aiki.db;

import aiki.fight.effects.*;

public final class ChangeStringFieldEnabledWeather implements ChangeStringField {
    private final EffectWhileSendingWithStatistic effectWhileSendingWithStatistic;

    public ChangeStringFieldEnabledWeather(EffectWhileSendingWithStatistic _l) {
        this.effectWhileSendingWithStatistic = _l;
    }

    @Override
    public String value() {
        return effectWhileSendingWithStatistic.getEnabledWeather();
    }

    @Override
    public void value(String _v) {
        effectWhileSendingWithStatistic.setEnabledWeather(_v);
    }
}
