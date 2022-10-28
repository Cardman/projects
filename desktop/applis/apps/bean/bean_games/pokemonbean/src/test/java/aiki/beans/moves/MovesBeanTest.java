package aiki.beans.moves;

import aiki.db.DataBase;
import aiki.facade.enums.SelectedBoolean;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanWholeWordSet(bean_,false);
        assertFalse(callMovesBeanWholeWordGet(bean_));
    }
    @Test
    public void wholeWordSet2() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanWholeWordSet(bean_,true);
        assertTrue(callMovesBeanWholeWordGet(bean_));
    }
    @Test
    public void typedCatSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanCategorySet(bean_,C_CAT);
        assertEq(C_CAT,callMovesBeanCategoryGet(bean_));
    }
    @Test
    public void maxAccSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxAccuracySet(bean_,"1");
        assertEq("1",callMovesBeanMaxAccuracyGet(bean_));
    }
    @Test
    public void minAccSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMinAccuracySet(bean_,"1");
        assertEq("1",callMovesBeanMinAccuracyGet(bean_));
    }
    @Test
    public void maxPowSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxPowerSet(bean_,"1");
        assertEq("1",callMovesBeanMaxPowerGet(bean_));
    }
    @Test
    public void minPowSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"1");
        assertEq("1",callMovesBeanMinPowerGet(bean_));
    }
    @Test
    public void typedNameSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedNameSet(bean_,M_DAM_TR);
        assertEq(M_DAM_TR,callMovesBeanTypedNameGet(bean_));
    }
    @Test
    public void typedTypeSet() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_,T_TYPE_TR);
        assertEq(T_TYPE_TR,callMovesBeanTypedTypeGet(bean_));
    }
    @Test
    public void learntSet1() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(SelectedBoolean.YES.getBoolName(), callMovesBeanLearntGet(bean_));
    }
    @Test
    public void learntSet2() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(SelectedBoolean.NO.getBoolName(), callMovesBeanLearntGet(bean_));
    }
    @Test
    public void search1() {
        Struct bean_ = dispAllMoves(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.NO.getBoolName());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanLearntSet(bean_,SelectedBoolean.YES.getBoolName());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void search4() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_,"__");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search5() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_,T_TYPE_TR);
        callMovesBeanWholeWordSet(bean_,true);
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedTypeSet(bean_,"__");
        callMovesBeanWholeWordSet(bean_,true);
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search7() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanCategorySet(bean_,C_CAT);
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMinAccuracySet(bean_,"4/5");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxAccuracySet(bean_,"4/5");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
    }
    @Test
    public void search10() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"0");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"1");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
    }
    @Test
    public void search12() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMaxPowerSet(bean_,"1");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
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
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanMinPowerSet(bean_,"11");
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(0,keys_.size());
    }
    @Test
    public void search14() {
        Struct bean_ = dispAllMoves(feedDb());
        callMovesBeanTypedNameSet(bean_,M_DAM_TR);
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML, navigateMovesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_MOVES_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertEq(M_DAM, getValMoveId(bean_));
    }
    @Test
    public void afterSearch() {
        Struct bean_ = dispAllMovesSearch(feedDb());
        assertSizeEq(7,callMovesBeanMovesGet(bean_));
    }
//        assertTrue(StringUtil.contains(keys_,M_DAM));
//        assertTrue(StringUtil.contains(keys_,M_STA));
//        assertTrue(StringUtil.contains(keys_,M_WEA));
//        assertTrue(StringUtil.contains(keys_,M_DAM_BAD));
//        assertTrue(StringUtil.contains(keys_,M_DAM_VAR));
//        assertTrue(StringUtil.contains(keys_,M_DAM_VERY_BAD));
//        assertTrue(StringUtil.contains(keys_,M_DAM_POW));
}
