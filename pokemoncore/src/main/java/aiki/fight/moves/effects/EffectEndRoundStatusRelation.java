package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectEndRoundStatusRelation extends EffectEndRoundStatus {

    private Rate thievedHpRateTargetToUser;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!getInflictedRateHpTarget().isZeroOrGt()) {
            throw new DataException();
        }
        if (!thievedHpRateTargetToUser.isZeroOrGt()) {
            throw new DataException();
        }
        if (getInflictedRateHpTarget().isZero()) {
            if (!thievedHpRateTargetToUser.isZero()) {
                return;
            }
            throw new DataException();
        }
        if (!thievedHpRateTargetToUser.isZero()) {
            throw new DataException();
        }
        return;
    }

    @Override
    public RelationType getRelation() {
        return RelationType.STATUT_RELATION;
    }

    public Rate getThievedHpRateTargetToUser() {
        return thievedHpRateTargetToUser;
    }

    public void setThievedHpRateTargetToUser(Rate _thievedHpRateTargetToUser) {
        thievedHpRateTargetToUser = _thievedHpRateTargetToUser;
    }
}
