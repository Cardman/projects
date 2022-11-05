package aiki.beans.items;

import org.junit.Test;

public final class HealingItemBeanTest extends InitDbItemOther {
    @Test
    public void getHealingTeam1() {
        assertTrue(callHealingItemBeanHealingTeamGet(ppSimple(true)));
    }
    @Test
    public void getHealingTeam2() {
        assertFalse(callHealingItemBeanHealingTeamGet(ppSimple(false)));
    }
}
