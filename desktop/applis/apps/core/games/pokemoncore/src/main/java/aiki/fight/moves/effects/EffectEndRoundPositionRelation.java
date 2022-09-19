package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectEndRoundPositionRelation extends EffectEndRound {

    private Rate healHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
        DataInfoChecker.checkPositive(healHp,_data);
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
