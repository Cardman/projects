package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.StepDbgActionEnum;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgMuteTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 69, cont_, StepDbgActionEnum.DEBUG, false);
        assertEq(1,first_.getStack().nbPages());
        assertEq(52,now(first_.getStack()));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 69, cont_, StepDbgActionEnum.DEBUG, false);
        cursor(first_.getStack(),cont_,82);
        StackCallReturnValue second_ = stdViewMuteContinue(first_, cont_, StepDbgActionEnum.CURSOR, true);
        assertEq(1,second_.getStack().nbPages());
        assertEq(82,now(second_.getStack()));
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 69, cont_, StepDbgActionEnum.DEBUG, false);
        cursor(first_.getStack(),cont_,82);
        StackCallReturnValue second_ = stdViewMuteContinue(first_, cont_, StepDbgActionEnum.CURSOR, false);
        assertEq(1,second_.getStack().nbPages());
        assertEq(69,now(second_.getStack()));
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 69, cont_, StepDbgActionEnum.DEBUG, false);
        cursor(first_.getStack(),cont_,82);
        StackCallReturnValue second_ = stdViewMuteContinue(first_, cont_, StepDbgActionEnum.CURSOR, false);
        assertEq(0,dbgContinueNormal(second_.getStack(),cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 69, cont_, StepDbgActionEnum.DEBUG, true);
        assertEq(0,first_.getStack().nbPages());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 131, cont_, StepDbgActionEnum.DEBUG, false);
        cursor(first_.getStack(),cont_,82);
        StackCallReturnValue second_ = stdViewMuteContinue(first_, cont_, StepDbgActionEnum.NEXT_IN_METHOD, true);
        assertEq(1,second_.getStack().nbPages());
        assertEq(69,now(second_.getStack()));
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        StackCallReturnValue first_ = stdViewMute("pkg.Ex", "exmeth", 52, 131, cont_, StepDbgActionEnum.DEBUG, false);
        cursor(first_.getStack(),cont_,82);
        StackCallReturnValue second_ = stdViewMuteContinue(first_, cont_, StepDbgActionEnum.NEXT_IN_METHOD, false);
        assertEq(2,second_.getStack().nbPages());
        assertEq(131,now(second_.getStack()));
    }
}
