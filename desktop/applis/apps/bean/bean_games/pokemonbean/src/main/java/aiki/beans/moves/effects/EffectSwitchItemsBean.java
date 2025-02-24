package aiki.beans.moves.effects;
import aiki.fight.moves.effects.EffectSwitchItems;
import aiki.fight.moves.effects.enums.MoveItemType;
import code.scripts.pages.aiki.MessagesDataEffswitchitems;

public class EffectSwitchItemsBean extends EffectBean {

    private MoveItemType moveObject;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EffectSwitchItems effect_ = (EffectSwitchItems) getEffect();
        moveObject = effect_.getMoveObject();
    }

    @Override
    public void buildSubEff() {
        procExchangeType(getMoveObject(),MoveItemType.DELETE_DEF_TARGET_BERRY, MessagesDataEffswitchitems.M_P_61_DELETE_BERRY);
        procExchangeType(getMoveObject(),MoveItemType.TAKE_OBJET,MessagesDataEffswitchitems.M_P_61_TAKE_ITEM);
        procExchangeType(getMoveObject(),MoveItemType.REMOVE_TARGET_OBJECT,MessagesDataEffswitchitems.M_P_61_REMOVE_ITEM);
        procExchangeType(getMoveObject(),MoveItemType.EXCHANGE_OBJECTS,MessagesDataEffswitchitems.M_P_61_SWITCH_ITEMS);
        procExchangeType(getMoveObject(),MoveItemType.REUSE_LAST_OBJECT,MessagesDataEffswitchitems.M_P_61_REUSE_ITEM);
        procExchangeType(getMoveObject(),MoveItemType.GIVE_OBJECT_TARGET,MessagesDataEffswitchitems.M_P_61_GIVE_TO_TARGET);
        procExchangeType(getMoveObject(),MoveItemType.USE_OBJECT_AS_POSSIBLE,MessagesDataEffswitchitems.M_P_61_USE_ITEM_IF_POSSIBLE);
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

    public MoveItemType getMoveObject() {
        return moveObject;
    }
}