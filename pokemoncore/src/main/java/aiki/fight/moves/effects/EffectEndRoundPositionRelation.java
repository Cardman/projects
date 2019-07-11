package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;


public final class EffectEndRoundPositionRelation extends EffectEndRound {

    private Rate healHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (!healHp.isZeroOrGt()) {
            _data.setError(true);
        }
        if (healHp.isZero()) {
            _data.setError(true);
        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.RELATION_POSITION;
    }

    public Rate getHealHp() {
        return healHp;
    }

    public void setHealHp(Rate _healHp) {
        healHp = _healHp;
    }
}
