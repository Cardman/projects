package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgDisableUntilTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){dep(1);int t = 0;dep(2);return t;}public static int dep(int p){return p;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = disableHitUntilStdView("pkg.Ex", "exmeth", 118,59, cont_);
        assertEq(1,first_.getStack().nbPages());
        assertEq(59,now(first_.getStack()));
        assertEq(2,dbgContinueNormal(first_.getStack(),cont_.getContext()).nbPages());
        assertEq(118,now(first_.getStack()));
        assertEq(2,getNumber(ArgumentListCall.toStr(first_.getStack().getLastPage().getRefParams().getVal("p").getValue())));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){dep(1);int t = 0;dep(2);return t;}public static int dep(int p){return p;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = disableHitUntilStdView("pkg.Ex", "exmeth", 118,59, cont_);
        assertEq(0,dbgContinueNormal(dbgContinueNormal(first_.getStack(),cont_.getContext()),cont_.getContext()).nbPages());
    }
}
