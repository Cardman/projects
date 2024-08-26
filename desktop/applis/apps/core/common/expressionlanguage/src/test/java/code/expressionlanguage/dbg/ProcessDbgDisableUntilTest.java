package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgDisableUntilTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){dep(1);int t = 0;dep(2);return t;}public static int dep(int p){return p;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        StackCallReturnValue first_ = disableHitUntilStdView("pkg.Ex", "exmeth", 118,59, cont_);
        assertEq(0,dbgContinueNormal(dbgContinueNormal(first_.getStack(),cont_.getContext()),cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 0;iter(int i=0;4;1){iter(int j=0;4;1){t++;}t++;}return t;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        CustList<String> first_ = disableHitUntilStdViewLog("pkg.Ex", "exmeth", 94,99, "i", "i+\",\"+j", cont_, true);
        assertEq(7, first_.size());
        assertEq("0", first_.get(0));
        assertEq("1,0", first_.get(1));
        assertEq("1", first_.get(2));
        assertEq("2,0", first_.get(3));
        assertEq("2", first_.get(4));
        assertEq("3,0", first_.get(5));
        assertEq("3", first_.get(6));
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 0;iter(int i=0;4;1){iter(int j=0;4;1){t++;}t++;}return t;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        CustList<String> first_ = disableHitUntilStdViewLog("pkg.Ex", "exmeth", 94,99, "i", "i+\",\"+j", cont_, false);
        assertEq(16, first_.size());
        assertEq("0", first_.get(0));
        assertEq("1,0", first_.get(1));
        assertEq("1,1", first_.get(2));
        assertEq("1,2", first_.get(3));
        assertEq("1,3", first_.get(4));
        assertEq("1", first_.get(5));
        assertEq("2,0", first_.get(6));
        assertEq("2,1", first_.get(7));
        assertEq("2,2", first_.get(8));
        assertEq("2,3", first_.get(9));
        assertEq("2", first_.get(10));
        assertEq("3,0", first_.get(11));
        assertEq("3,1", first_.get(12));
        assertEq("3,2", first_.get(13));
        assertEq("3,3", first_.get(14));
        assertEq("3", first_.get(15));
    }
}
