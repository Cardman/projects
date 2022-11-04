package aiki.beans.moves.effects;

import org.junit.Test;

public final class EffectOrderBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getTargetAttacksLast1() {
        assertFalse(callEffectOrderBeanTargetAttacksLastGet(dispMoveEffOrder(false)));
    }
    @Test
    public void getTargetAttacksLast2() {
        assertTrue(callEffectOrderBeanTargetAttacksLastGet(dispMoveEffOrder(true)));
    }
}
