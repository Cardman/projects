package code.expressionlanguage.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.util.CustList;
import code.util.StringList;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;


public final class PrimitiveTypeUtilTest extends ProcessMethodCommon {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String ARR_ARR_CUST_CLASS = "[[pkg.CustClass";
    private static final String ARR_CUST_CLASS = "[pkg.CustClass";




















    @Test
    public void cmpTypes1Test() {
        ContextEl context_ = simpleContextEl();
        String int_ = context_.getStandards().getAliasInteger();
        String nb_ = context_.getStandards().getAliasNumber();
        assertEq(CustList.SWAP_SORT, AnaTypeUtil.cmpTypes(nb_,int_,context_));
    }

    @Test
    public void getSubclasses1Test() {
        ContextEl context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getStandards().getAliasInteger(), context_.getStandards().getAliasNumber());
        StringList sub_ = AnaTypeUtil.getSubclasses(classes_, context_);
        assertEq(1, sub_.size());
        assertEq(context_.getStandards().getAliasInteger(), sub_.get(0));
    }

    @Test
    public void getSubclasses2Test() {
        ContextEl context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getStandards().getAliasString(), context_.getStandards().getAliasNumber());
        StringList sub_ = AnaTypeUtil.getSubclasses(classes_, context_);
        assertEq(2, sub_.size());
        assertEq(context_.getStandards().getAliasString(), sub_.get(0));
        assertEq(context_.getStandards().getAliasNumber(), sub_.get(1));
    }

    @Test
    public void getSubclasses3Test() {
        ContextEl context_ = simpleContextEl();
        StringList classes_ = new StringList(context_.getStandards().getAliasVoid(), context_.getStandards().getAliasVoid());
        StringList sub_ = AnaTypeUtil.getSubclasses(classes_, context_);
        assertTrue(sub_.onlyOneElt());
        assertEq(context_.getStandards().getAliasVoid(), sub_.get(0));
    }

    private ContextEl simpleContextEl() {
        return ctx();
    }
}
