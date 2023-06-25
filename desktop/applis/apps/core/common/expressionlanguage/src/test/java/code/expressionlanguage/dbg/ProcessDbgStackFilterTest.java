package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgStackFilterTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = stackSta("pkg.Ex","exmeth",files_,new String[]{"pkg/Ex","pkg/Ex"},new int[]{56,82});
        assertEq(1,cont_.nbPages());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxSt("pkg.Ex", files_);
        StackCall cont_ = stackStaView("pkg.Ex","exmeth",res_, new String[]{"pkg/Ex","pkg/Ex"},new int[]{56,82}).getStack();
        assertEq(0, tryInitStaticlyTypes(cont_,new Options(), res_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = stackIns("pkg.Ex","exmeth",files_,new String[]{"pkg/Ex","pkg/Ex"},new int[]{49,82});
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ResultContext res_ = ctxInst("pkg.Ex", files_);
        StackCallReturnValue cont_ = stackInsView("pkg.Ex","exmeth",res_, new String[]{"pkg/Ex","pkg/Ex"},new int[]{56,82});
        assertEq(0,dbgNormal("pkg.Ex",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public static int f=1;}public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = stackSta("pkg.Ex","exmeth",files_,new String[]{"pkg/Ex","pkg/Ex"},new int[]{56,56});
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ax{public int f=1;}public class pkg.Ex:Ax {public static int exmeth(){return new Ex().f;}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        StackCall cont_ = stackIns("pkg.Ex","exmeth",files_,new String[]{"pkg/Ex","pkg/Ex"},new int[]{56,56});
        assertEq(0,cont_.nbPages());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        StackCall cont_ = stackStdView("pkg.Ex2","exmeth",130,files_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{60,132}).getStack();
        assertEq(4,cont_.nbPages());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 130, files_);
        StackCallReturnValue cont_ = stackStdView("pkg.Ex2","exmeth",130,res_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{60,132});
        assertEq(0,dbgNormal("pkg.Ex2",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 130, files_);
        StackCallReturnValue cont_ = stackStdView("pkg.Ex2","exmeth",130,res_,new String[]{"pkg/Ex1","pkg/Ex0"},new int[]{132,132});
        assertEq(0,cont_.getStack().nbPages());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 130, files_);
        StackCallReturnValue cont_ = stackStdView("pkg.Ex2","exmeth",130,res_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{60,54});
        assertEq(0,cont_.getStack().nbPages());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}public static int sup3(){return 1;}}");
        StackCall cont_ = stackStdView("pkg.Ex2","exmeth",130,files_,new String[]{"pkg/Ex2","pkg/Ex2"},new int[]{60,165}).getStack();
        assertEq(4,cont_.nbPages());
    }
}
