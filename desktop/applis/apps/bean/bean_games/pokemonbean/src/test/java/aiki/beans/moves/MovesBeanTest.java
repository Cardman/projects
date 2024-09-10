package aiki.beans.moves;

import aiki.facade.enums.SelectedBoolean;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class MovesBeanTest extends InitDbMoves {
    @Test
    public void movesBegin() {
        assertSizeEq(0,callMovesBeanMovesGet(dispAllMoves(feedDb())));
    }
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
        assertEq(AUTRE,first(elt(callMovesBeanCategoriesGet(dispAllMoves(feedDb())),1)));
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
    @Test
    public void typedCat() {
        assertEq("",callMovesBeanCategoryGet(dispAllMoves(feedDb())));
    }
    @Test
    public void maxAcc() {
        assertEq("",callMovesBeanMaxAccuracyGet(dispAllMoves(feedDb())));
    }
    @Test
    public void minAcc() {
        assertEq("",callMovesBeanMinAccuracyGet(dispAllMoves(feedDb())));
    }
    @Test
    public void maxPow() {
        assertEq("",callMovesBeanMaxPowerGet(dispAllMoves(feedDb())));
    }
    @Test
    public void minPow() {
        assertEq("",callMovesBeanMinPowerGet(dispAllMoves(feedDb())));
    }
    @Test
    public void typedName() {
        assertEq("",callMovesBeanTypedNameGet(dispAllMoves(feedDb())));
    }
    @Test
    public void typedType() {
        assertEq("",callMovesBeanTypedTypeGet(dispAllMoves(feedDb())));
    }
    @Test
    public void learnt() {
        assertEq(SelectedBoolean.YES_AND_NO.getBoolName(), callMovesBeanLearntGet(dispAllMoves(feedDb())));
    }
    @Test
    public void bools() {
        assertSizeEq(3, callMovesBeanBooleansGet(dispAllMoves(feedDb())));
    }
    @Test
    public void wholeWord() {
        assertFalse(callMovesBeanWholeWordGet(dispAllMoves(feedDb())));
    }
    @Test
    public void wholeWordSet1() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanWholeWordSet(bean_,false);
        assertFalse(callMovesBeanWholeWordGet(bean_));
    }
    @Test
    public void wholeWordSet2() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanWholeWordSet(bean_,true);
        assertTrue(callMovesBeanWholeWordGet(bean_));
    }
    @Test
    public void typedCatSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanCategorySet(bean_,C_CAT);
        assertEq(C_CAT,callMovesBeanCategoryGet(bean_));
    }
    @Test
    public void maxAccSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxAccuracySet(bean_,"1");
        assertEq("1",callMovesBeanMaxAccuracyGet(bean_));
    }
    @Test
    public void minAccSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMinAccuracySet(bean_,"1");
        assertEq("1",callMovesBeanMinAccuracyGet(bean_));
    }
    @Test
    public void maxPowSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxPowerSet(bean_,"1");
        assertEq("1",callMovesBeanMaxPowerGet(bean_));
    }
    @Test
    public void minPowSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"1");
        assertEq("1",callMovesBeanMinPowerGet(bean_));
    }
    @Test
    public void typedNameSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedNameSet(bean_,M_DAM_TR);
        assertEq(M_DAM_TR,callMovesBeanTypedNameGet(bean_));
    }
    @Test
    public void typedTypeSet() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_, T_TYPE1_TR);
        assertEq(T_TYPE1_TR,callMovesBeanTypedTypeGet(bean_));
    }
    @Test
    public void learntSet1() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callMovesBeanLearntGet(bean_));
    }
    @Test
    public void learntSet2() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callMovesBeanLearntGet(bean_));
    }
    @Test
    public void search1() {
        NaSt bean_ = dispAllMoves(feedDb());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(7,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
        assertTrue(StringUtil.contains(keys_,M_WEA));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search2() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(5,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_WEA));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search3() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void search4() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_,"__");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search5() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_, T_TYPE1_TR);
        callMovesBeanWholeWordSet(bean_,true);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(7,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
        assertTrue(StringUtil.contains(keys_,M_WEA));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search6() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_,"__");
        callMovesBeanWholeWordSet(bean_,true);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search7() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanCategorySet(bean_,C_CAT);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(5,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search8() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMinAccuracySet(bean_,"4/5");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(5,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
        assertTrue(StringUtil.contains(keys_,M_WEA));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
    }
    @Test
    public void search9() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxAccuracySet(bean_,"4/5");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
    }
    @Test
    public void search10() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"0");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(5,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search11() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"1");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search12() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxPowerSet(bean_,"1");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(6,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
        assertTrue(StringUtil.contains(keys_,M_WEA));
        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
    }
    @Test
    public void search13() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"11");
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search14() {
        NaSt bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedNameSet(bean_,M_DAM_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertEq(M_DAM, getValMoveId(bean_));
    }
    @Test
    public void afterSearch() {
        NaSt bean_ = dispAllMovesSearch(feedDb());
        assertSizeEq(7,callMovesBeanMovesGet(bean_));
    }
    @Test
    public void lineName1() {
        assertEq(M_DAM_BAD_TR, callMoveLineBeanDisplayNameGet(0));
    }
    @Test
    public void lineName2() {
        assertEq(M_DAM_POW_TR, callMoveLineBeanDisplayNameGet(1));
    }
    @Test
    public void lineName3() {
        assertEq(M_DAM_TR, callMoveLineBeanDisplayNameGet(2));
    }
    @Test
    public void lineName4() {
        assertEq(M_DAM_VAR_TR, callMoveLineBeanDisplayNameGet(3));
    }
    @Test
    public void lineName5() {
        assertEq(M_DAM_VERY_BAD_TR, callMoveLineBeanDisplayNameGet(4));
    }
    @Test
    public void lineName6() {
        assertEq(M_STA_TR, callMoveLineBeanDisplayNameGet(5));
    }
    @Test
    public void lineName7() {
        assertEq(M_WEA_TR, callMoveLineBeanDisplayNameGet(6));
    }
    @Test
    public void pp() {
        assertEq(1,callMoveLineBeanPpGet(2));
    }
    @Test
    public void category() {
        assertEq(C_CAT1_TR,callMoveLineBeanCategoryGet(2));
    }
    @Test
    public void prio() {
        assertEq(1,callMoveLineBeanPriorityGet(2));
    }
    @Test
    public void acc() {
        assertEq("1",callMoveLineBeanAccuracyGet(2));
    }
    @Test
    public void pow() {
        assertEq("1",callMoveLineBeanPowerGet(2));
    }
    @Test
    public void types1() {
        assertSizeEq(1,callMoveLineBeanTypesGet(2));
    }
    @Test
    public void types2() {
        assertEq(T_TYPE1_TR,elt(callMoveLineBeanTypesGet(2),0));
    }
    @Test
    public void damagingMove1() {
        assertTrue(callMoveLineIsDamageMove(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void damagingMove2() {
        assertFalse(callMoveLineIsDamageMove(callMoveLineBeanMoveLineGet(5)));
    }
    @Test
    public void direct1() {
        assertTrue(callMoveLineIsDirect(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void direct2() {
        assertFalse(callMoveLineIsDirect(callMoveLineBeanMoveLineGet(3)));
    }
    @Test
    public void indPp() {
        assertEq(1,callMoveLinePpGet(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void indCategory() {
        assertEq(C_CAT1_TR,callMoveLineCategoryGet(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void indPrio() {
        assertEq(1,callMoveLinePriorityGet(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void indName() {
        assertEq(M_DAM_TR,callMoveLineDisplayNameGet(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void indTypes1() {
        assertSizeEq(1,callMoveLineGetTypes(callMoveLineBeanMoveLineGet(2)));
    }
    @Test
    public void intTypes2() {
        assertEq(T_TYPE1_TR,elt(callMoveLineGetTypes(callMoveLineBeanMoveLineGet(2)),0));
    }
    @Test
    public void clickRow1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML, goToLine(2));
    }
    @Test
    public void clickRow2() {
        assertEq(M_DAM, goToLineId(2));
    }
}
