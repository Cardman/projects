package aiki.beans.moves.effects;
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
    public boolean takeItem() {
        return moveObject == MoveItemType.TAKE_OBJET;
    }
    public boolean useItemAsPossible() {
        return moveObject == MoveItemType.USE_OBJECT_AS_POSSIBLE;
    }
    public boolean removeTargetItem() {
        return moveObject == MoveItemType.REMOVE_TARGET_OBJECT;
    }
    public boolean giveTargetItem() {
        return moveObject == MoveItemType.GIVE_OBJECT_TARGET;
    }
    public boolean switchItems() {
        return moveObject == MoveItemType.EXCHANGE_OBJECTS;
    }
    public boolean deleteTargetBerry() {
        return moveObject == MoveItemType.DELETE_DEF_TARGET_BERRY;
    }
    public boolean resuseLastItem() {
        return moveObject == MoveItemType.REUSE_LAST_OBJECT;
    }
}