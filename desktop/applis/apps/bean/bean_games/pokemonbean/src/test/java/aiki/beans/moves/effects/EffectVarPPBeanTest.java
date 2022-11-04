package aiki.beans.moves.effects;

import org.junit.Test;

public final class EffectVarPPBeanTest extends InitDbMoveEffectOther {
    @Test
    public void pp() {
        assertEq(1,callEffectVarPPBeanDeletePpGet(dispMoveEffVarPp()));
    }
}
