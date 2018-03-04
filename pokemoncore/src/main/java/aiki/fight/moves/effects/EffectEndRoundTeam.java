package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.effects.enums.RelationType;
import code.maths.Rate;
import code.serialize.CheckedData;
import code.util.annot.RwXml;

@CheckedData
@RwXml
public final class EffectEndRoundTeam extends EffectEndRound {

    private Rate deleteAllStatus;
    private Rate deleteAllStatusAlly;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!deleteAllStatusAlly.isZeroOrGt()) {
            throw new DataException();
        }
        if (!deleteAllStatus.isZeroOrGt()) {
            throw new DataException();
        }
    }

    @Override
    public RelationType getRelation() {
        return RelationType.EQUIPE;
    }

    public Rate getDeleteAllStatus() {
        return deleteAllStatus;
    }
    public void setDeleteAllStatus(Rate _deleteAllStatus) {
        deleteAllStatus = _deleteAllStatus;
    }
    public Rate getDeleteAllStatusAlly() {
        return deleteAllStatusAlly;
    }
    public void setDeleteAllStatusAlly(Rate _deleteAllStatusAlly) {
        deleteAllStatusAlly = _deleteAllStatusAlly;
    }
}
