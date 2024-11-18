package aiki.fight.effects;
import aiki.db.DataBase;
import aiki.fight.moves.effects.EffectStatistic;


public final class EffectWhileSendingWithStatistic extends EffectWhileSending {

    private EffectStatistic effect;
    private boolean withEffect;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (withEffect) {
            effect.validate(_data);
        }
    }

    public EffectStatistic getEffect() {
        return effect;
    }

    public void setEffect(EffectStatistic _effect) {
        effect = _effect;
    }

    public boolean isWithEffect() {
        return withEffect;
    }

    public void setWithEffect(boolean _w) {
        this.withEffect = _w;
    }
}
