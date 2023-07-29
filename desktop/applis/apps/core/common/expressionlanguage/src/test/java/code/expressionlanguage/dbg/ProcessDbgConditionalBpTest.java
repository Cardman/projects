package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.AllAccessedTypes;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgConditionalBpTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalSt("Ax.f==1","pkg.Ex","exmeth",files_);
        assertEq(1,cont_.nbPages());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxSt("pkg.Ex", files_);
        StackCall cont_ = conditionalSt("Ax.f==1","pkg.Ex","exmeth",res_);
        assertEq(0, tryInitStaticlyTypes(cont_,new Options(), res_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalInst("f>=0","pkg.Ex","exmeth",files_);
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxInst("pkg.Ex", files_);
        StackCallReturnValue cont_ = conditionalInstView("f>=0","pkg.Ex","exmeth",res_);
        assertEq(0,dbgNormal("pkg.Ex",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalSt("Ax.f==0","pkg.Ex","exmeth",files_);
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalInst("f<0","pkg.Ex","exmeth",files_);
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalStd("t+u==11","pkg.Ex","exmeth",111,files_);
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxStd("pkg.Ex", 111, files_);
        StackCallReturnValue cont_ = conditionalStdView("t+u==11","pkg.Ex","exmeth", 111,files_,res_);
        assertEq(0,dbgNormal("pkg.Ex",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalStd("t+u==10","pkg.Ex","exmeth",111,files_);
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalStd("1/0==1/0","pkg.Ex","exmeth",111,files_);
        assertEq(2,cont_.nbPages());
        assertEq(1, ((ErrorStruct)((CustomFoundExc)cont_.getBreakPointInfo().getBreakPointOutputInfo().getCallingStateSub()).getStruct()).getStack().getLength());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ReportedMessages cont_ = conditionalStdViewBad("s","pkg.Ex",111,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = conditionalSt("","pkg.Ex","exmeth",files_);
        assertEq(1,cont_.nbPages());
    }
    @Test
    public void test13() {
        assertFalse(new AllAccessedTypes().isTypeHidden(null,null));
    }
}
