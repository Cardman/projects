package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.fight.moves.enums.TargetChoice;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public class EffectEndRoundPositionRelation extends EffectEndRound {

    private Rate healHp;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (getTargetChoice() != TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (!healHp.isZeroOrGt()) {
            throw new DataException();
        }
        if (healHp.isZero()) {
            throw new DataException();
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
