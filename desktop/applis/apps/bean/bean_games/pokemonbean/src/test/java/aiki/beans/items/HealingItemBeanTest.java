package aiki.beans.items;

import org.junit.Test;

public final class HealingItemBeanTest extends InitDbHealing {
    @Test
    public void getHealingTeam1() {
        assertTrue(callHealingItemBeanHealingTeamGet(healSimple(true)));
    }
    @Test
    public void getHealingTeam2() {
        assertFalse(callHealingItemBeanHealingTeamGet(healSimple(false)));
    }
}
