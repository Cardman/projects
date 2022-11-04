package aiki.beans.moves.effects;

import code.maths.Rate;
import org.junit.Test;

public final class EffectAllyBeanTest extends InitDbMoveEffectOther {
    @Test
    public void getMultAllyDamage() {
        assertEq(Rate.one(),callEffectAllyBeanMultAllyDamageGet(dispMoveEffAlly()));
    }
}
