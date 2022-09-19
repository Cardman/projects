package aiki.fight.moves.effects;

import aiki.db.DataBase;
import aiki.fight.moves.effects.enums.MoveItemType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.util.DataInfoChecker;


public final class EffectSwitchItems extends Effect {

    private MoveItemType moveObject;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        boolean checkTargetChoice_ = moveObject == MoveItemType.TAKE_OBJET || moveObject == MoveItemType.USE_OBJECT_AS_POSSIBLE || moveObject == MoveItemType.REMOVE_TARGET_OBJECT || moveObject == MoveItemType.GIVE_OBJECT_TARGET || moveObject == MoveItemType.EXCHANGE_OBJECTS || moveObject == MoveItemType.DELETE_DEF_TARGET_BERRY;
        if (checkTargetChoice_) {
            DataInfoChecker.checkTargetNot(TargetChoice.LANCEUR,getTargetChoice(),_data);
        }

    }

    public MoveItemType getMoveObject() {
        return moveObject;
    }

    public void setMoveObject(MoveItemType _moveObject) {
        moveObject = _moveObject;
    }
}
