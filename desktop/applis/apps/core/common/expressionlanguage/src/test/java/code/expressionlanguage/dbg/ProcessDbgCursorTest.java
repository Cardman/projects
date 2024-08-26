package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgCursorTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursorInstruction(stack_, cont_, 82);
        assertEq(1,next_.nbPages());
        assertEq(82,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursorInstruction(stack_, cont_, 82);
        assertEq(0, dbgContinueNormalValueNextInstMethod(next_, cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursorExpression(stack_, cont_, 91);
        assertEq(1,next_.nbPages());
        assertEq(91,now(next_));
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursorExpression(stack_, cont_, 91);
        assertEq(0, dbgContinueNormalValueNextInstMethod(next_, cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursorExpression(stack_, cont_, 91);
        assertEq(1,next_.nbPages());
        assertEq(91,now(next_));
        dbgContinueNormalValueCursorExpression(stack_, cont_, 93);
        assertEq(1,next_.nbPages());
        assertEq(93,now(next_));
        dbgContinueNormalValueCursorExpression(stack_, cont_, 87);
        assertEq(1,next_.nbPages());
        assertEq(87,now(next_));
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursorExpression(stack_, cont_, 91);
        dbgContinueNormalValueCursorExpression(stack_, cont_, 93);
        dbgContinueNormalValueCursorExpression(stack_, cont_, 87);
        assertEq(0, dbgContinueNormalValueNextInstMethod(next_, cont_.getContext()).nbPages());
    }
    @Test
    public void noCursor1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        int off_ = ResultExpressionOperationNode.beginPartExp(21, cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"));
        assertEq(-1,off_);
    }
    @Test
    public void noCursor2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static void exmeth(){return;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        int off_ = ResultExpressionOperationNode.beginPartExp(55, cont_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex"));
        assertEq(-1,off_);
    }
}
