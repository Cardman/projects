package aiki.beans.moves.effects;

import org.junit.Test;

public final class EffectLightTest extends InitDbMoveEffectLightDisplay {
    @Test
    public void acc() {
        assertEq(M_DAM,dispMoveEffAccuracy());
    }
}
