package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;
import code.util.StringList;


public final class EffectCopyMove extends Effect {

    private short copyingMoveForUser;
    private boolean copyingMoveForUserDef;
    private StringList movesNotToBeCopied;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getMoves().getKeys(),movesNotToBeCopied,_data);
        DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
        if (copyingMoveForUserDef) {
            DataInfoChecker.checkZero(copyingMoveForUser,_data);
            return;
        }
        DataInfoChecker.checkPositive(copyingMoveForUser,_data);

    }

    public short getCopyingMoveForUser() {
        return copyingMoveForUser;
    }

    public void setCopyingMoveForUser(short _copyingMoveForUser) {
        copyingMoveForUser = _copyingMoveForUser;
    }

    public boolean getCopyingMoveForUserDef() {
        return copyingMoveForUserDef;
    }

    public void setCopyingMoveForUserDef(boolean _copyingMoveForUserDef) {
        copyingMoveForUserDef = _copyingMoveForUserDef;
    }

    public StringList getMovesNotToBeCopied() {
        return movesNotToBeCopied;
    }

    public void setMovesNotToBeCopied(StringList _movesNotToBeCopied) {
        movesNotToBeCopied = _movesNotToBeCopied;
    }
}
