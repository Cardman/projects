package aiki.beans.moves;

import aiki.db.DataBase;
import org.junit.Test;

public final class MovesBeanTest extends InitDbMoves {
    @Test
    public void movesBegin() {
        assertSizeEq(0,callMovesBeanMovesGet(dispAllMoves(feedDb())));
    }
//    @Test
//    public void moves1() {
//        assertSizeEq(2,callMovesBeanMovesGet(dispLearntMoves(feedDb())));
//    }
//    @Test
//    public void moves2() {
//        assertSizeEq(4, callMovesBeanMovesGet(dispNotLearntMoves(feedDb())));
//    }
//    @Test
//    public void moves4() {
//        assertSizeEq(6, callMovesBeanMovesGet(dispAllMoves(feedDb())));
//    }
    @Test
    public void cat1() {
        assertSizeEq(3, callMovesBeanCategoriesGet(dispAllMoves(feedDb())));
    }
    @Test
    public void cat2() {
        assertEq(C_CAT,first(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),0)));
    }
    @Test
    public void cat3() {
        assertEq(C_CAT1_TR,second(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),0)));
    }
    @Test
    public void cat4() {
        assertEq(DataBase.AUTRE,first(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),1)));
    }
    @Test
    public void cat5() {
        assertEq(C_CAT2_TR,second(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),1)));
    }
    @Test
    public void cat6() {
        assertEq("",first(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),2)));
    }
    @Test
    public void cat7() {
        assertEq("",second(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),2)));
    }
}
