package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgDisableWhenHitTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 0;iter(int i=0;2;1){t+=i+1;}return t;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        StackCallReturnValue first_ = disableHitStdView("pkg.Ex", "exmeth", 76, cont_);
        assertEq(1,first_.getStack().nbPages());
        assertEq(76,now(first_.getStack()));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 0;iter(int i=0;2;1){t+=i+1;}return t;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        StackCallReturnValue first_ = disableHitStdView("pkg.Ex", "exmeth", 76, cont_);
        assertEq(0,dbgContinueNormal(first_.getStack(),cont_.getContext()).nbPages());
    }
}
