package aiki.beans.items;

import code.maths.Rate;
import org.junit.Test;

public final class HealingHpBeanTest extends InitDbHealingHp {
    @Test
    public void hp() {
        assertEq(Rate.one(),callHealingHpBeanHpGet());
    }
}
