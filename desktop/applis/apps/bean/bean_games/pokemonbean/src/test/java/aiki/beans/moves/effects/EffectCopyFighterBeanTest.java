package aiki.beans.moves.effects;

import org.junit.Test;

public final class EffectCopyFighterBeanTest extends InitDbMoveEffectCopy {
    @Test
    public void ppForMoves() {
        assertEq(1,callEffectCopyFighterBeanPpForMovesGet(dispMoveEffCopyFighter()));
    }
}
