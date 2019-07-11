package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.enums.TargetChoice;
import code.util.StringList;


public final class EffectCopyMove extends Effect {

    private short copyingMoveForUser;
    private boolean copyingMoveForUserDef;
    private StringList movesNotToBeCopied;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getMoves().containsAllAsKeys(movesNotToBeCopied)) {
            _data.setError(true);
        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            _data.setError(true);
        }
        if (copyingMoveForUserDef) {
            if (copyingMoveForUser != 0) {
                _data.setError(true);
            }
            return;
        }
        if (copyingMoveForUser > 0) {
            return;
        }
        _data.setError(true);

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
