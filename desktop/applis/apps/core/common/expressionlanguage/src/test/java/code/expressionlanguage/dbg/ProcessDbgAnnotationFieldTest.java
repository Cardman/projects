package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.WatchPoint;
import code.expressionlanguage.exec.dbg.WatchPointBlockPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgAnnotationFieldTest extends ProcessDbgCommon {

    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",90);
        assertTrue(isWatch(cont_, cf("pkg.ExAnnot","method")));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPointEnabled("pkg/Ex",90);
        assertTrue(isWatch(cont_, cf("pkg.ExAnnot","method")));
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){return ((ExAnnot)class(Ex).getAnnotations()[0]).method();}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",149);
        read(cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(107, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method=1,method2=2)public class pkg.Ex {public static int exmeth(){return ((ExAnnot)class(Ex).getAnnotations()[0]).method();}}public annotation pkg.ExAnnot {int method();int method2();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",166);
        readCondition("method()==1",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(124, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method=1,method2=2)public class pkg.Ex {public static int exmeth(){return ((ExAnnot)class(Ex).getAnnotations()[0]).method();}}public annotation pkg.ExAnnot {int method();int method2();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",166);
        readCondition("method()==3",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){return ((ExAnnot)class(Ex).getAnnotations()[0]).method();}}public annotation pkg.ExAnnot {int method();int method2;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",149);
        cont_.toggleWatchPoint("pkg/Ex",162);
        readCondition("method()==1",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(107, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){return ((ExAnnot)class(Ex).getAnnotations()[0]).method();}}public annotation pkg.ExAnnot {int method();int method2;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",149);
        cont_.toggleWatchPoint("pkg/Ex",162);
        readCondition("method()==3",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){return (int)class(ExAnnot).getDeclaredMethods()[0].invoke(((ExAnnot)class(Ex).getAnnotations()[0]));}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",192);
        read(cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(110, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){var f=((ExAnnot)class(Ex).getAnnotations()[0]).$lambda(ExAnnot,method); return f.call();}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",180);
        read(cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(140, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){return (int)class(ExAnnot).getDeclaredMethods()[0].invoke(null);}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",156);
        read(cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int method();int method2;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",236);
        readCondition("method()==1",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(105, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int method()3;int method2;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",236);
        writeCondition("value==3",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(4, stack_.nbPages());
        assertEq(248, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(1)public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int method()3;int method2;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",236);
        writeCondition("value==4",cont_,cf("pkg.ExAnnot","method"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method1();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int method1()3;int method2()4;int method2;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",235);
        cont_.toggleWatchPoint("pkg/Ex",250);
        writeCondition("value==3",cont_,cf("pkg.ExAnnot","method1"));
        writeCondition("value==4",cont_,cf("pkg.ExAnnot","method2"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(4, stack_.nbPages());
        assertEq(248, now(stack_));
        assertEq(4, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(263, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method1=1,method2=2)public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method3();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int method1()3;int method2()4;int method3()5;int method6;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",255);
        cont_.toggleWatchPoint("pkg/Ex",270);
        writeCondition("value==1",cont_,cf("pkg.ExAnnot","method1"));
        writeCondition("value==2",cont_,cf("pkg.ExAnnot","method2"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(242, now(stack_));
        assertSame(StopDbgEnum.FIELD, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(3, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(242, now(stack_));
        assertSame(StopDbgEnum.FIELD, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method1=1,method2=2)public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method3();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int[] method1(){3};int[] method2(){4};int method3()5;int method6;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",255);
        cont_.toggleWatchPoint("pkg/Ex",274);
        writeCondition("value[0]==1",cont_,cf("pkg.ExAnnot","method1"));
        writeCondition("value[0]==2",cont_,cf("pkg.ExAnnot","method2"));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(242, now(stack_));
        assertSame(StopDbgEnum.FIELD, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(3, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(242, now(stack_));
        assertSame(StopDbgEnum.FIELD, stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method1=1,method2=2)public class pkg.Ex {public static int exmeth(){Ex e=((ExAnnot)class(Ex).getAnnotations()[0]).method3();return e.t;}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}public annotation pkg.ExAnnot {int[] method1(){3};int[] method2(){4};int method3()5;int method6;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method1=1,method2=2)public class pkg.Ex {public static int exmeth(){return 0;}}public annotation pkg.ExAnnot {int[] method1(){3};int[] method2(){4};int method3()5;int method6;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_,"pkg.Ex","pkg.Ex2");
        cont_.toggleWatchPoint("pkg/Ex",9);
        cont_.toggleWatchPoint("pkg/Ex",16);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@ExAnnot(method1=1,method2=2)public class pkg.Ex {public static int exmeth(){return 0;}}public annotation pkg.ExAnnot {int[] method1(){3};int[] method2(){4};int method3()5;int method6;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_,"pkg.Ex","pkg.Ex2");
        cont_.toggleWatchPointEnabled("pkg/Ex",9);
        cont_.toggleWatchPointEnabled("pkg/Ex",16);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",94);
        assertTrue(isWatch(cont_, cf("pkg.ExAnnot","method")));
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}public annotation pkg.ExAnnot {int method();}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPointEnabled("pkg/Ex",94);
        assertTrue(isWatch(cont_, cf("pkg.ExAnnot","method")));
    }

    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}public annotation pkg.ExAnnot {int st;int method()st;}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleWatchPoint("pkg/Ex",109);
        assertTrue(isWatchField(cont_, cf("pkg.ExAnnot","st")));
    }
    private boolean isWatch(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.isWatch(false,n_,_cf.getFieldName());
    }

    private boolean isWatchField(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.isWatch(true,n_,_cf.getFieldName());
    }
    private boolean is(ResultContext _cont, int _off) {
        return _cont.is(_cont.getPageEl().getPreviousFilesBodies().getVal("pkg/Ex").getNumberFile()+"/"+_off);
    }

    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }

    private void readCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        read(_cont, _cf);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPointBlockPair p_ = pair(_cont, _cf);
        WatchPoint wp_ = p_.getValue();
        wp_.getResultRead().analyze(p_,_newValue,"", "", _cont, new DefContextGenerator());
        assertEq(_newValue,wp_.getResultRead().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, p_, _cont, type_, new DefContextGenerator(), false);
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultRead().result(res_,_newValue);
    }
    private void read(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(true);
        pair(_cont, _cf).getValue().setWrite(false);
        pair(_cont, _cf).getValue().setCompoundRead(false);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private void writeCondition(String _newValue,ResultContext _cont, ClassField _cf) {
        write(_cont, _cf);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        WatchPointBlockPair p_ = pair(_cont, _cf);
        WatchPoint wp_ = p_.getValue();
        wp_.getResultWrite().analyze(p_,_newValue,"", "", _cont, new DefContextGenerator());
        assertEq(_newValue,wp_.getResultWrite().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyzeField(_newValue, p_, _cont, type_, new DefContextGenerator(), false);
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getResultRead().result(res_,_newValue);
    }
    private void write(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(true);
        pair(_cont, _cf).getValue().setCompoundRead(false);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private WatchPointBlockPair pair(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.getPairWatch(false,n_,_cf.getFieldName());
    }
}
