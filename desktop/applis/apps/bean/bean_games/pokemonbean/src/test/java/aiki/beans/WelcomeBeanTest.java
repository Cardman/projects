package aiki.beans;

import aiki.beans.moves.AikiBeansMovesStd;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class WelcomeBeanTest extends InitDbWelcome {
    @Test
    public void allMoves1() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertFalse(forms(bean_).contains(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void allMoves2() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateAllMoves(displaying(displaying(beanWelcome(feedDb())))));
    }
    @Test
    public void learntMoves() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateLearntMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertTrue(forms(bean_).contains(CST_LEARNT));
        assertTrue(forms(bean_).getValBool(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
    @Test
    public void notLearntMoves() {
        Struct bean_ = beanWelcome(feedDb());
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_MOVES_HTML,navigateNotLearntMoves(displaying(bean_)));
        assertTrue(forms(bean_).contains(CST_MOVES_SET));
        assertTrue(forms(bean_).contains(CST_LEARNT));
        assertFalse(forms(bean_).getValBool(CST_LEARNT));
        assertTrue(forms(bean_).getValMoveData(CST_MOVES_SET).isEmpty());
        assertTrue(forms(bean_).contains(CST_LEARNT_MOVES));
        CustList<String> keys_ = forms(bean_).getValMoveData(CST_LEARNT_MOVES).getKeys();
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,M_DAM));
        assertTrue(StringUtil.contains(keys_,M_STA));
    }
}
