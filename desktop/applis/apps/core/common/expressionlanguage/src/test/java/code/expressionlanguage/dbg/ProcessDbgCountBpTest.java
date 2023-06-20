package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgCountBpTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = countSt(1,"pkg.Ex","exmeth",files_);
        assertEq(1,cont_.nbPages());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxSt("pkg.Ex", files_);
        StackCall cont_ = countSt(1,"pkg.Ex","exmeth",res_);
        assertEq(0, tryInitStaticlyTypes(cont_,new Options(), res_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = countInst(1,"pkg.Ex","exmeth",files_);
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxSt("pkg.Ex", files_);
        StackCallReturnValue cont_ = countInstView(1,"pkg.Ex","exmeth",res_);
        assertEq(0,dbgNormal("pkg.Ex",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = countSt(2,"pkg.Ex","exmeth",files_);
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = countInst(2,"pkg.Ex","exmeth",files_);
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = countStd(1,"pkg.Ex","exmeth",111,files_);
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxStd("pkg.Ex", 111, files_);
        StackCallReturnValue cont_ = countStdView(1,"pkg.Ex","exmeth", 111,files_,res_);
        assertEq(0,dbgNormal("pkg.Ex",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = countStd(2,"pkg.Ex","exmeth",111,files_);
        assertEq(0,cont_.nbPages());
    }
}
