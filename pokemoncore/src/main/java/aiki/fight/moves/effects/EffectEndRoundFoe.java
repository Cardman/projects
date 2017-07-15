package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import code.datacheck.CheckedData;
import code.maths.Rate;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class EffectEndRoundFoe extends EffectEndRound {

    private Rate inflictedRateHpTarget;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!inflictedRateHpTarget.isZeroOrGt()) {
            throw new DataException();
        }
        if (inflictedRateHpTarget.isZero()) {
            throw new DataException();
        }
    }
    @Override
    public RelationType getRelation() {
        return RelationType.ADV;
    }

    public Rate getInflictedRateHpTarget() {
        return inflictedRateHpTarget;
    }

    public void setInflictedRateHpTarget(Rate _inflictedRateHpTarget) {
        inflictedRateHpTarget = _inflictedRateHpTarget;
    }
}
