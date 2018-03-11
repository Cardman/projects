package aiki.fight.moves.effects;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.fight.moves.enums.TargetChoice;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public final class EffectCopyMove extends Effect {

    private short copyingMoveForUser;
    private boolean copyingMoveForUserDef;
    private StringList movesNotToBeCopied;
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getMoves().containsAllAsKeys(movesNotToBeCopied)) {
            throw new DataException();
        }
        if (getTargetChoice() == TargetChoice.LANCEUR) {
            throw new DataException();
        }
        if (copyingMoveForUserDef) {
            if (copyingMoveForUser != 0) {
                throw new DataException();
            }
            return;
        }
        if (copyingMoveForUser > 0) {
            return;
        }
        throw new DataException();
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
