package aiki.beans.items;

import code.util.StringList;
import org.junit.Test;

public final class ItemForBattleBeanTest extends InitDbItemsItemForBattle {
    @Test
    public void bonusEffect1() {
        assertEq(2,ItemForBattleBean.bonusEffect(feedDbMove().getData(),M_DAM));
    }
    @Test
    public void initTypesPkAbilities1() {
        StringList ls_ = ItemForBattleBean.initTypesPkAbilities(feedDbAbility(false).getData());
        assertEq(0,ls_.size());
    }
    @Test
    public void initTypesPkAbilities2() {
        StringList ls_ = ItemForBattleBean.initTypesPkAbilities(feedDbAbility(true).getData());
        assertEq(1,ls_.size());
        assertEq(A_ABILITY,ls_.get(0));
    }
}
