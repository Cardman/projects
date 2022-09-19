package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;

public final class EffectEndRoundPositionTargetRelation extends EffectEndRound {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
    }

    @Override
    public RelationType getRelation() {
        return RelationType.RELATION_POSITION_CIBLE;
    }
}
