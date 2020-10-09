package code.expressionlanguage.inherits;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.util.StringList;
import code.util.core.SortConstants;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;


public final class PrimitiveTypeUtilTest extends ProcessMethodCommon {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String ARR_ARR_CUST_CLASS = "[[pkg.CustClass";
    private static final String ARR_CUST_CLASS = "[pkg.CustClass";




















    @Test
    public void cmpTypes1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        String int_ = context_.getAliasInteger();
        String nb_ = context_.getAliasNumber();
        assertEq(SortConstants.SWAP_SORT, cmpTypes(context_, int_, nb_));
    }

    private static int cmpTypes(AnalyzedTestContext context_, String int_, String nb_) {
        return AnaTypeUtil.cmpTypes(nb_,int_, context_.getAnalyzing());
    }

    @Test
    public void getSubclasses1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getAliasInteger(), context_.getAliasNumber());
        StringList sub_ = getSubclasses(context_, classes_);
        assertEq(1, sub_.size());
        assertEq(context_.getAliasInteger(), sub_.get(0));
    }

    @Test
    public void getSubclasses2Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getAliasString(), context_.getAliasNumber());
        StringList sub_ = getSubclasses(context_, classes_);
        assertEq(2, sub_.size());
        assertEq(context_.getAliasString(), sub_.get(0));
        assertEq(context_.getAliasNumber(), sub_.get(1));
    }

    @Test
    public void getSubclasses3Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getAliasVoid(), context_.getAliasVoid());
        StringList sub_ = getSubclasses(context_, classes_);
        assertTrue(sub_.onlyOneElt());
        assertEq(context_.getAliasVoid(), sub_.get(0));
    }

    private static StringList getSubclasses(AnalyzedTestContext context_, StringList classes_) {
        return AnaTypeUtil.getSubclasses(classes_, context_.getAnalyzing());
    }

    private AnalyzedTestContext simpleContextEl() {
        return ctxAna();
    }
}
