/**
 */
package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;

/**
 */
public final class EffectEndRoundGlobal extends EffectEndRound {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkTarget(TargetChoice.LANCEUR,getTargetChoice(),_data);
    }

    @Override
    public RelationType getRelation() {
        return RelationType.GLOBAL;
    }
}
