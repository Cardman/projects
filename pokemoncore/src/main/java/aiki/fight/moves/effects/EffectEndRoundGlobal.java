/**
 */
package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;

/**
 */
public final class EffectEndRoundGlobal extends EffectEndRound {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            _data.setError(true);
        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.GLOBAL;
    }
}
