package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import code.serialize.CheckedData;

@CheckedData
public class EffectEndRoundPositionTargetRelation extends EffectEndRound {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            throw new DataException();
        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.RELATION_POSITION_CIBLE;
    }
}
