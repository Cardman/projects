package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.syntax.ResultExpressionOperationNode;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.exec.dbg.ExecFileBlockFct;
import code.expressionlanguage.exec.dbg.ExecFileBlockTraceIndex;
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
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",130,files_,new String[]{"pkg/Ex0","pkg/Ex0"},new int[]{54,132}).getStack();
        assertEq(4,cont_.nbPages());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 130, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",130,res_,new String[]{"pkg/Ex0","pkg/Ex0"},new int[]{54,132});
        assertEq(0,dbgNormal("pkg.Ex2",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 130, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",130,res_,new String[]{"pkg/Ex1","pkg/Ex0"},new int[]{132,132});
        assertEq(0,cont_.getStack().nbPages());
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 130, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",130,res_,new String[]{"pkg/Ex0","pkg/Ex0"},new int[]{54,54});
        assertEq(0,cont_.getStack().nbPages());
    }
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}public static int sup3(){return 1;}}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",130,files_,new String[]{"pkg/Ex2","pkg/Ex2"},new int[]{60,165}).getStack();
        assertEq(4,cont_.nbPages());
    }
    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",144,files_,new String[]{"pkg/Ex0","pkg/Ex0"},new int[]{54,132}).getStack();
        assertEq(7,cont_.nbPages());
    }
    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){((int a:int)->a).call(1);return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",63,files_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{63,132}).getStack();
        assertEq(3,cont_.nbPages());
    }
    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){((int a:int)->a).call(1);return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 63, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",63,res_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{63,132});
        assertEq(0,dbgNormal("pkg.Ex2",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test20() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){(switch(1){default;return 1;});return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",75,files_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{75,132}).getStack();
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test21() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){(switch(1){default;return 1;});return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 75, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",75,res_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{75,132});
        assertEq(0,dbgNormal("pkg.Ex2",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test22() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "operator+ pkg.Ex3(pkg.Ex3 a){return a;}public class pkg.Ex2 {public static int exmeth(){+new Ex3();return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        files_.put("pkg/Ex3", "public class pkg.Ex3 {}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",36,files_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{36,132}).getStack();
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test23() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "operator+ pkg.Ex3(pkg.Ex3 a){return a;}public class pkg.Ex2 {public static int exmeth(){+new Ex3();return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        files_.put("pkg/Ex3", "public class pkg.Ex3 {}");
        ResultContext res_ = ctxStd("pkg.Ex2", 36, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",36,res_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{36,132});
        assertEq(0,dbgNormal("pkg.Ex2",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void test24() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex3 {int i;public Ex3(){i=i;}}public class pkg.Ex2 {public static int exmeth(){new Ex3();return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        StackCall cont_ = stackStdViewCall("pkg.Ex2","exmeth",41,files_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{41,132}).getStack();
        assertEq(2,cont_.nbPages());
    }
    @Test
    public void test25() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex3 {int i;public Ex3(){i=i;}}public class pkg.Ex2 {public static int exmeth(){new Ex3();return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){return 1;}}");
        ResultContext res_ = ctxStd("pkg.Ex2", 41, files_);
        StackCallReturnValue cont_ = stackStdViewCall("pkg.Ex2","exmeth",41,res_,new String[]{"pkg/Ex2","pkg/Ex0"},new int[]{41,132});
        assertEq(0,dbgNormal("pkg.Ex2",getMethodId("exmeth"),res_,cont_.getStack()).nbPages());
    }
    @Test
    public void f1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(54,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex0"),res_.getPageEl().getDisplayedStrings()));
        AbsCallContraints two_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(93,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex0"),res_.getPageEl().getDisplayedStrings()));
        assertFalse(one_.match(two_));
    }
    @Test
    public void f2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(54,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex0"),res_.getPageEl().getDisplayedStrings()));
        AbsCallContraints two_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(54,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1"),res_.getPageEl().getDisplayedStrings()));
        assertFalse(one_.match(two_));
    }
    @Test
    public void f3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),54);
        AbsCallContraints two_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(54,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1"),res_.getPageEl().getDisplayedStrings()));
        assertFalse(one_.match(two_));
    }
    @Test
    public void f4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),54);
        AbsCallContraints two_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(54,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1"),res_.getPageEl().getDisplayedStrings()));
        assertFalse(two_.match(one_));
    }
    @Test
    public void f5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),54);
        AbsCallContraints two_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),60);
        assertFalse(two_.match(one_));
    }
    @Test
    public void f6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),54);
        AbsCallContraints two_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex0")),54);
        assertFalse(two_.match(one_));
    }
    @Test
    public void f7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(54,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex0"),res_.getPageEl().getDisplayedStrings()));
        AbsCallContraints two_ = new ExecFileBlockFct(ResultExpressionOperationNode.beginPartFct(55,res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex0"),res_.getPageEl().getDisplayedStrings()));
        assertTrue(one_.match(two_));
    }
    @Test
    public void f8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex0", "public class pkg.Ex0 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex1", "public class pkg.Ex1 {public static int sup1(){return sub();}public static int sup2(){return sub();}public static int sup3(){return sub();}public static int sub(){return Ex2._();}}");
        files_.put("pkg/Ex2", "public class pkg.Ex2 {public static int exmeth(){return Ex0.sup1()+Ex0.sup2()+Ex1.sup1()+Ex1.sup2();}public static int _(){new Ex2();return 1;}{i=i;}int i;}");
        ResultContext res_ = ctxStd("pkg.Ex2", 144, files_);
        AbsCallContraints one_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),60);
        AbsCallContraints two_ = new ExecFileBlockTraceIndex(res_.getForwards().dbg().getFiles().getVal(res_.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex1")),60);
        assertTrue(one_.match(two_));
    }
}
