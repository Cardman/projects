package aiki.beans.moves;

import org.junit.Test;

public final class MovesBeanTest extends InitDbMoves {
    @Test
    public void moves1() {
        assertSizeEq(2,callMovesBeanMovesGet(dispLearntMoves(feedDb())));
    }
    @Test
    public void moves2() {
        assertSizeEq(4, callMovesBeanMovesGet(dispNotLearntMoves(feedDb())));
    }
    @Test
    public void moves4() {
        assertSizeEq(6, callMovesBeanMovesGet(dispAllMoves(feedDb())));
    }
}
