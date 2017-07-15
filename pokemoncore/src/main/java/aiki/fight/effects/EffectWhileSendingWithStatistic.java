package aiki.fight.effects;
import code.util.annot.RwXml;
import aiki.DataBase;
import aiki.fight.moves.effects.EffectStatistic;

@RwXml
public class EffectWhileSendingWithStatistic extends EffectWhileSending {

    private EffectStatistic effect;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        effect.validate(_data);
    }

    public EffectStatistic getEffect() {
        return effect;
    }

    public void setEffect(EffectStatistic _effect) {
        effect = _effect;
    }
}
