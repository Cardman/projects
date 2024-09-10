package aiki.beans.moves.effects;

import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import org.junit.Test;

public final class EffectCopyMoveBeanTest extends InitDbMoveEffectCopy {
    @Test
    public void movesTransforming() {
        StringList ls_ = EffectCopyMoveBean.movesTransforming(feedDbMoveEffDataDam(true, 1).getData());
        assertEq(1,ls_.size());
        assertEq(M_STA,ls_.get(0));
    }
    @Test
    public void getCopyingMoveForUserDef1() {
        assertTrue(callEffectCopyMoveBeanCopyingMoveForUserDefGet(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void getCopyingMoveForUserDef2() {
        assertFalse(callEffectCopyMoveBeanCopyingMoveForUserDefGet(dispMoveEffCopyMove(false, 1)));
    }
    @Test
    public void getCopyingMoveForUser1() {
        assertTrue(callEffectCopyMoveBeanCopyMoveForUser(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void getCopyingMoveForUser2() {
        assertFalse(callEffectCopyMoveBeanCopyMoveForUser(dispMoveEffCopyMove(true, 0)));
    }
    @Test
    public void getCopyingMoveForUser3() {
        assertEq(1,callEffectCopyMoveBeanCopyingMoveForUserGet(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void getMovesTransforming1() {
        assertSizeEq(1,callEffectCopyMoveBeanMovesTransformingGet(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void getMovesTransforming2() {
        assertEq(M_STA,elt(callEffectCopyMoveBeanMovesTransformingGet(dispMoveEffCopyMove(true, 1)),0));
    }
    @Test
    public void getMovesNotToBeCopied1() {
        assertSizeEq(1,callEffectCopyMoveBeanMovesNotToBeCopiedGet(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void getMovesNotToBeCopied2() {
        assertEq(M_WEA,elt(callEffectCopyMoveBeanMovesNotToBeCopiedGet(dispMoveEffCopyMove(true, 1)),0));
    }
    @Test
    public void getDisplayName() {
        assertEq(M_DAM_TR,callEffectCopyMoveBeanDisplayNameGet(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void getTrDefaultMove() {
        assertEq(M_DAM_VERY_BAD_TR,callEffectCopyMoveBeanGetTrDefaultMove(dispMoveEffCopyMove(true, 1)));
    }
    @Test
    public void clickDefaultMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectCopyMoveBeanClickDefaultMove(dispMoveEffCopyMove(true,1)));
    }
    @Test
    public void clickDefaultMove2() {
        assertEq(M_DAM_VERY_BAD,callEffectCopyMoveBeanClickDefaultMoveId(dispMoveEffCopyMove(true,1)));
    }
    @Test
    public void getTrMoveTrans() {
        assertEq(M_STA_TR,callEffectCopyMoveBeanGetTrMoveTrans(dispMoveEffCopyMove(true, 1),0));
    }
    @Test
    public void clickMoveTrans1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectCopyMoveBeanClickMoveTrans(dispMoveEffCopyMove(true,1),0));
    }
    @Test
    public void clickMoveTrans2() {
        assertEq(M_STA,callEffectCopyMoveBeanClickMoveTransId(dispMoveEffCopyMove(true,1),0));
    }
    @Test
    public void getTrMove() {
        assertEq(M_WEA_TR,callEffectCopyMoveBeanGetTrMove(dispMoveEffCopyMove(true, 1),0));
    }
    @Test
    public void clickMove1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,callEffectCopyMoveBeanClickMove(dispMoveEffCopyMove(true,1),0,0));
    }
    @Test
    public void clickMove2() {
        assertEq(M_WEA,callEffectCopyMoveBeanClickMoveId(dispMoveEffCopyMove(true,1),0,0));
    }
}
