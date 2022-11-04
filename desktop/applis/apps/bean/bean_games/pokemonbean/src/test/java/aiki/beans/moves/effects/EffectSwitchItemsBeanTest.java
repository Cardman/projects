package aiki.beans.moves.effects;

import aiki.fight.moves.effects.enums.MoveItemType;
import org.junit.Test;

public final class EffectSwitchItemsBeanTest extends InitDbMoveEffectSwitch {
    @Test
    public void takeItem1() {
        assertTrue(callEffectSwitchItemsBeanTakeItem(dispMoveEffSwitchItems(MoveItemType.TAKE_OBJET)));
    }
    @Test
    public void takeItem2() {
        assertFalse(callEffectSwitchItemsBeanTakeItem(dispMoveEffSwitchItems(MoveItemType.GIVE_OBJECT_TARGET)));
    }
    @Test
    public void useItemAsPossible1() {
        assertTrue(callEffectSwitchItemsBeanUseItemAsPossible(dispMoveEffSwitchItems(MoveItemType.USE_OBJECT_AS_POSSIBLE)));
    }
    @Test
    public void useItemAsPossible2() {
        assertFalse(callEffectSwitchItemsBeanUseItemAsPossible(dispMoveEffSwitchItems(MoveItemType.GIVE_OBJECT_TARGET)));
    }
    @Test
    public void removeTargetItem1() {
        assertTrue(callEffectSwitchItemsBeanRemoveTargetItem(dispMoveEffSwitchItems(MoveItemType.REMOVE_TARGET_OBJECT)));
    }
    @Test
    public void removeTargetItem2() {
        assertFalse(callEffectSwitchItemsBeanRemoveTargetItem(dispMoveEffSwitchItems(MoveItemType.GIVE_OBJECT_TARGET)));
    }
    @Test
    public void giveTargetItem1() {
        assertTrue(callEffectSwitchItemsBeanGiveTargetItem(dispMoveEffSwitchItems(MoveItemType.GIVE_OBJECT_TARGET)));
    }
    @Test
    public void giveTargetItem2() {
        assertFalse(callEffectSwitchItemsBeanGiveTargetItem(dispMoveEffSwitchItems(MoveItemType.REMOVE_TARGET_OBJECT)));
    }
    @Test
    public void switchItems1() {
        assertTrue(callEffectSwitchItemsBeanSwitchItems(dispMoveEffSwitchItems(MoveItemType.EXCHANGE_OBJECTS)));
    }
    @Test
    public void switchItems2() {
        assertFalse(callEffectSwitchItemsBeanSwitchItems(dispMoveEffSwitchItems(MoveItemType.REMOVE_TARGET_OBJECT)));
    }
    @Test
    public void deleteTargetBerry1() {
        assertTrue(callEffectSwitchItemsBeanDeleteTargetBerry(dispMoveEffSwitchItems(MoveItemType.DELETE_DEF_TARGET_BERRY)));
    }
    @Test
    public void deleteTargetBerry2() {
        assertFalse(callEffectSwitchItemsBeanDeleteTargetBerry(dispMoveEffSwitchItems(MoveItemType.REMOVE_TARGET_OBJECT)));
    }
    @Test
    public void resuseLastItem1() {
        assertTrue(callEffectSwitchItemsBeanResuseLastItem(dispMoveEffSwitchItems(MoveItemType.REUSE_LAST_OBJECT)));
    }
    @Test
    public void resuseLastItem2() {
        assertFalse(callEffectSwitchItemsBeanResuseLastItem(dispMoveEffSwitchItems(MoveItemType.REMOVE_TARGET_OBJECT)));
    }
}
