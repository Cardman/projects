package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.MoveItemType;
import aiki.fight.moves.enums.TargetChoice;


public final class EffectSwitchItems extends Effect {

    private MoveItemType moveObject;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        boolean checkTargetChoice_ = false;
        if (moveObject == MoveItemType.TAKE_OBJET) {
            checkTargetChoice_ = true;
        } else if (moveObject == MoveItemType.USE_OBJECT_AS_POSSIBLE) {
            checkTargetChoice_ = true;
        } else if (moveObject == MoveItemType.REMOVE_TARGET_OBJECT) {
            checkTargetChoice_ = true;
        } else if (moveObject == MoveItemType.GIVE_OBJECT_TARGET) {
            checkTargetChoice_ = true;
        } else if (moveObject == MoveItemType.EXCHANGE_OBJECTS) {
            checkTargetChoice_ = true;
        } else if (moveObject == MoveItemType.DELETE_DEF_TARGET_BERRY) {
            checkTargetChoice_ = true;
        }
        if (checkTargetChoice_) {
            if (getTargetChoice() == TargetChoice.LANCEUR) {
                _data.setError(true);
                return;

            }
            return;
        }
        if (moveObject == MoveItemType.REUSE_LAST_OBJECT) {
            return;
        }
        _data.setError(true);
        return;

    }

    public MoveItemType getMoveObject() {
        return moveObject;
    }

    public void setMoveObject(MoveItemType _moveObject) {
        moveObject = _moveObject;
    }
}
