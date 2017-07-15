package aiki.fight.moves.effects;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public abstract class EffectEndRoundStatus extends EffectEndRound {

    private Rate inflictedRateHpTarget;

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

    public void setInflictedRateHpTarget(Rate _inflictedRateHpTarget) {
        inflictedRateHpTarget = _inflictedRateHpTarget;
    }
}
