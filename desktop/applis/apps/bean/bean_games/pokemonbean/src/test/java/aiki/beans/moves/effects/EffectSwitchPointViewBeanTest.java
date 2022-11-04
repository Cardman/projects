package aiki.beans.moves.effects;

import aiki.fight.moves.effects.enums.PointViewChangementType;
import org.junit.Test;

public final class EffectSwitchPointViewBeanTest extends InitDbMoveEffectSwitch {
    @Test
    public void thieveBonus1() {
        assertTrue(callEffectSwitchPointViewBeanThieveBonus(dispMoveEffSwitchPointView(PointViewChangementType.THIEF_BONUSES)));
    }
    @Test
    public void thieveBonus2() {
        assertFalse(callEffectSwitchPointViewBeanThieveBonus(dispMoveEffSwitchPointView(PointViewChangementType.NOTHING)));
    }
    @Test
    public void attractDamageMoves1() {
        assertTrue(callEffectSwitchPointViewBeanAttractDamageMoves(dispMoveEffSwitchPointView(PointViewChangementType.ATTRACT_DAMAGES_MOVES)));
    }
    @Test
    public void attractDamageMoves2() {
        assertFalse(callEffectSwitchPointViewBeanAttractDamageMoves(dispMoveEffSwitchPointView(PointViewChangementType.NOTHING)));
    }
    @Test
    public void mirrorAgainstUser1() {
        assertTrue(callEffectSwitchPointViewBeanMirrorAgainstUser(dispMoveEffSwitchPointView(PointViewChangementType.MIRROR_AGAINST_THROWER)));
    }
    @Test
    public void mirrorAgainstUser2() {
        assertFalse(callEffectSwitchPointViewBeanMirrorAgainstUser(dispMoveEffSwitchPointView(PointViewChangementType.NOTHING)));
    }
}
