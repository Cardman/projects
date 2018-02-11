package aiki.beans.moves.effects;
import code.bean.Accessible;
import aiki.fight.moves.effects.EffectSwitchItems;
import aiki.fight.moves.effects.enums.MoveItemType;

public class EffectSwitchItemsBean extends EffectBean {

    private MoveItemType moveObject;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchItems effect_ = (EffectSwitchItems) getEffect();
        moveObject = effect_.getMoveObject();
    }

    @Accessible
    private boolean takeItem() {
        return moveObject == MoveItemType.TAKE_OBJET;
    }

    @Accessible
    private boolean useItemAsPossible() {
        return moveObject == MoveItemType.USE_OBJECT_AS_POSSIBLE;
    }

    @Accessible
    private boolean removeTargetItem() {
        return moveObject == MoveItemType.REMOVE_TARGET_OBJECT;
    }

    @Accessible
    private boolean giveTargetItem() {
        return moveObject == MoveItemType.GIVE_OBJECT_TARGET;
    }

    @Accessible
    private boolean switchItems() {
        return moveObject == MoveItemType.EXCHANGE_OBJECTS;
    }

    @Accessible
    private boolean deleteTargetBerry() {
        return moveObject == MoveItemType.DELETE_DEF_TARGET_BERRY;
    }

    @Accessible
    private boolean resuseLastItem() {
        return moveObject == MoveItemType.REUSE_LAST_OBJECT;
    }
}
