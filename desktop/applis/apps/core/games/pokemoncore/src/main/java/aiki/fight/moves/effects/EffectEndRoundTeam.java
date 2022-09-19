package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.RelationType;
import aiki.util.DataInfoChecker;
import code.maths.Rate;


public final class EffectEndRoundTeam extends EffectEndRound {

    private Rate deleteAllStatus;
    private Rate deleteAllStatusAlly;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkPositiveOrZero(deleteAllStatusAlly,_data);
        DataInfoChecker.checkPositiveOrZero(deleteAllStatus,_data);
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
