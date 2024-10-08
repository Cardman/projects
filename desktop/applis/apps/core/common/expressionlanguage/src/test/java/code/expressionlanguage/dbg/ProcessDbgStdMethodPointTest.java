package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StopDbgEnum;
import code.expressionlanguage.exec.dbg.*;
import code.expressionlanguage.functionid.AbsractIdentifiableCommon;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgStdMethodPointTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        assertFalse(is(cont_, fct(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        assertTrue(is(cont_, toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        assertFalse(is(cont_, fct(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        assertFalse(is(cont_, fct(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()))));
    }

    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        assertFalse(is(cont_, fct(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        assertFalse(is(cont_, fct(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        assertTrue(is(cont_, toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        assertTrue(is(cont_, toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false))));
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggled(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        assertTrue(is(cont_, fct(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()))));
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggled(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        assertTrue(is(cont_, fct(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()))));
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return 1;}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        toggledEnabled(cont_,cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        toggledEnabled(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        assertTrue(is(cont_, fct(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()))));
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
    }

    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test15() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a==2&&b==4",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test16() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a==3",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test17() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("b==5",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test18() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("length()==4",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test19() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
    }

    @Test
    public void test20() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test21() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test22() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a==2&&b==4&&a+b==6",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test23() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a==3",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test24() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("b==5",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test25() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("length()==4",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger(), cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test26() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().append(\"56\").length();}public static StringBuilder exmeth2(){return new StringBuilder(\"1234\");}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringExitingCondition("length()==4","length()==6",cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasAppend(),false, cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertSame(StopDbgEnum.METHOD_STD_ENTRY,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(1, dbgContinueNormalCheck(stack_, cont_.getContext()).nbPages());
        assertEq(65, now(stack_));
        assertSame(StopDbgEnum.METHOD_STD_EXIT,stack_.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test27() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(4).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==1&&a[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(96, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test28() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(3,4).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==2&&a[0]==3&&a[1]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test29() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(4).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==2",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test30() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(3,4).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==3",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test31() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(4).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==1&&a[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(96, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test32() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(3,4).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==2&&a[0]==3&&a[1]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test33() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(4).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==2",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test34() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(3,4).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==3",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test35() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().substring(b:4,a:2).length();}public static String exmeth2(){return \"1234\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSubstring(),false, cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test36() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(a:{4}).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==1&&a[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(96, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test37() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(a:{3,4}).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==2&&a[0]==3&&a[1]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test38() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(a:{4}).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==2",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test39() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(a:{3,4}).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==3",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test40() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(a:{4}).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==1&&a[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(96, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test41() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(a:{3,4}).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==2&&a[0]==3&&a[1]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(100, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test42() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return f.call(a:{4}).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==2",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test43() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int,int);return f.call(a:{3,4}).substring(b:4,a:2).length();}public static String exmeth2(int a,int b){return \"12\"+a+b;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==3",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true, cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test44() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return ((String)((Method)f.metaInfo()).invoke(null,4)).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasMetaInfo(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(114, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test45() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return ((String)((Method)f.metaInfo()).invoke(null,4)).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasMetaInfo(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(114, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test46() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],new,int);return f.call(4).length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==1&&a[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test47() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(int[],new,int);return f.call(4).length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==1&&a[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasFct(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasCall(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test48() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new StringBuilder(\"1\").length();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a==\"1\"",cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(55, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test49() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return new StringBuilder(\"1\").length();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a==\"1\"",cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(55, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test50() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return \"1,2;3,4\".splitChars(',',';').length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a.length==2&&a[0]==','&&a[1]==';'",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSplitChars(),true,cont_.getContext().getStandards().getPrimTypes().getAliasPrimChar()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test51() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return \"1,2;3,4\".splitChars(',',';').length;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a.length==2&&a[0]==','&&a[1]==';'",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasSplitChars(),true,cont_.getContext().getStandards().getPrimTypes().getAliasPrimChar()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test52() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return ((String)((Method)f.metaInfo()).invoke(null,4)).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("a==null&&b.length==1&&b[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasMethod(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasInvoke(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject(),cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(126, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test53() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=static().$lambda(Ex,exmeth2,int);return ((String)((Method)f.metaInfo()).invoke(null,4)).substring(b:4,a:2).length();}public static String exmeth2(int a){return \"123\"+a;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("a==null&&b.length==1&&b[0]==4",cont_,cont_.getContext().getStandards().getReflect().getAliasMethod(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getReflect().getAliasInvoke(),true,cont_.getContext().getStandards().getCoreNames().getAliasObject(),cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(126, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test54() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){Ex e=exmeth2().length();return e.t;}public static String exmeth2(){return \"1\";}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(63, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test55() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){Ex e=exmeth2().length();return e.t;}public static String exmeth2(){return \"1\";}public int t;public(int t){this.t=t;}public static Ex $(int u){return new(u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        entering(cont_,"pkg/Ex",166);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(63, now(stack_));
        assertEq(1, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
        assertEq(53, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test56() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static boolean exmeth(){var s=\"\";return s.isEmpty()||s.charAt(0)==' ';}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasIsEmpty(),false));
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasCharAt(),false,cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(70, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test57() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static String exmeth(){return StringUtil.valueOf(new Ex());}public String $toString(){return \"123\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("Class.getClass(a)==class(pkg.Ex)",cont_,cont_.getContext().getStandards().getCoreNames().getAliasStringUtil(),getMethodId(MethodAccessKind.STATIC,cont_.getContext().getStandards().getCoreNames().getAliasStringUtilValueOf(),false,cont_.getContext().getStandards().getCoreNames().getAliasObject()));
        cont_.toggleBreakPoint("pkg/Ex",121);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(121, now(stack_));
        StackCall next_ = dbgContinueNormalCheck(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(69, now(next_));
        assertEq(0, dbgContinueNormal(next_, cont_.getContext()).nbPages());
    }

    @Test
    public void test58() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(CharSequence).getDeclaredMethods(\"length\",false,false)[0].invoke(exmeth2());}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(124, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test59() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(CharSequence).getDeclaredMethods(\"length\",false,false)[0].invoke(exmeth2());}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(124, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test60() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=exmeth2().$lambda(CharSequence,length);return f.call();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(102, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test61() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=exmeth2().$lambda(CharSequence,length);return f.call();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(102, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test62() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(CharSequence).getDeclaredMethods(\"length\",false,false)[0].invoke(0);}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test63() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return (int)class(CharSequence).getDeclaredMethods(\"length\",false,false)[0].invoke(null);}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test64() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return ((StringBuilder)class(StringBuilder).getDeclaredConstructors(false,class(String))[0].newInstance(exmeth2())).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(140, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test65() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return ((StringBuilder)class(StringBuilder).getDeclaredConstructors(false,class(String))[0].newInstance(exmeth2())).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(140, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test66() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(StringBuilder,new,String);return f.call(exmeth2()).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(97, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test67() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){var f=$lambda(StringBuilder,new,String);return f.call(exmeth2()).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exiting(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(97, stack_.getCall(0).getTraceIndex());
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test68() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return ((StringBuilder)class(StringBuilder).getDeclaredConstructors(false,class(String))[0].newInstance(null,exmeth2())).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test69() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return ((StringBuilder)class(StringBuilder).getDeclaredConstructors(false,class(String))[0].newInstance(0)).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test70() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return ((StringBuilder)class(StringBuilder).getDeclaredConstructors(false,class(String))[0].newInstance((Object[])null)).length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        entering(cont_,cont_.getContext().getStandards().getCharSeq().getAliasStringBuilder(),getConstructorId(false,cont_.getContext().getStandards().getCharSeq().getAliasString()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test71() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}public class pkg.Ex2{public int condition=-1;public int cond(){return condition;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("((pkg.Ex2)new pkg.Ex2(){public int cond(){return 1;}}).cond()==length()",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test72() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}public class pkg.Ex2{public int condition=-1;public int cond(){return condition;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("((pkg.Ex2)new pkg.Ex2(){public int cond(){return 1;}}).cond()==length()",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test73() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return Math.abs(exmeth2());}public static int exmeth2(){return 1;}}public class pkg.Ex2{public int condition=-1;public int cond(){return condition;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("((pkg.Ex2)new pkg.Ex2(){public int cond(){return 1;}}).cond()==1",cont_,cont_.getContext().getStandards().getMathRef().getAliasMath(),getMethodId(MethodAccessKind.STATIC,cont_.getContext().getStandards().getMathRef().getAliasAbs(),false,cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(60, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test74() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return Math.abs(exmeth2());}public static int exmeth2(){return 1;}}public class pkg.Ex2{public int condition=-1;public int cond(){return condition;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("((pkg.Ex2)new pkg.Ex2(){public int cond(){return 1;}}).cond()==1",cont_,cont_.getContext().getStandards().getMathRef().getAliasMath(),getMethodId(MethodAccessKind.STATIC,cont_.getContext().getStandards().getMathRef().getAliasAbs(),false,cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(60, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test75() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("{static class Ex2{public int condition=-1;public int cond(){return 1;}}return ((Ex2)new Ex2(){public int cond(){return 1;}}).cond()==length();}",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test76() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return exmeth2().length();}public static String exmeth2(){return \"1\";}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("{static class Ex2{public int condition=-1;public int cond(){return condition;}}return ((Ex2)new Ex2(){public int cond(){return 1;}}).cond()==length();}",cont_,cont_.getContext().getStandards().getCharSeq().getAliasCharSequence(),getMethodId(MethodAccessKind.INSTANCE,cont_.getContext().getStandards().getCharSeq().getAliasLength(),false));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(65, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test77() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return Math.abs(exmeth2());}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        enteringCondition("{static class Ex2{public int condition=-1;public int cond(){return condition;}}return ((Ex2)new Ex2(){public int cond(){return 1;}}).cond()==1;}",cont_,cont_.getContext().getStandards().getMathRef().getAliasMath(),getMethodId(MethodAccessKind.STATIC,cont_.getContext().getStandards().getMathRef().getAliasAbs(),false,cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(60, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test78() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){return Math.abs(exmeth2());}public static int exmeth2(){return 1;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        exitingCondition("{static class Ex2{public int condition=-1;public int cond(){return condition;}}return ((Ex2)new Ex2(){public int cond(){return 1;}}).cond()==1;}",cont_,cont_.getContext().getStandards().getMathRef().getAliasMath(),getMethodId(MethodAccessKind.STATIC,cont_.getContext().getStandards().getMathRef().getAliasAbs(),false,cont_.getContext().getStandards().getPrimTypes().getAliasPrimInteger()));
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(60, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public int f;public static int exmeth(){return new Ex().exmeth2()+=2;}public that int exmeth2(){return that(f);}{f=f;}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        StandardNamedFunction e_ = exiting(cont_, cont_.getContext().getStandards().getCoreNames().getAliasObject(), getConstructorId(false));
        StdMethodPointBlockPair wp_ = cont_.getPair(e_);
        assertFalse(new StdMethodKeyString().keyString(wp_).isEmpty());
    }
    private void div(ResultContext _cont) {
        _cont.toggleExcPoint(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME);
        ExcPoint val_ = _cont.getPairExc(_cont.getContext().getStandards().getCoreNames().getAliasDivisionZero(), ExcPointBlockKey.SAME).getValue();
        val_.setThrown(true);
        val_.setCaught(false);
        val_.setPropagated(true);
    }
    private boolean is(ResultContext _cont, StandardNamedFunction _off) {
        return _cont.is(_off);
    }

    private ClassField cf(String _cl, String _f) {
        return new ClassField(_cl,_f);
    }
    private void write(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(true);
        pair(_cont, _cf).getValue().setCompoundRead(false);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private void compoundRead(ResultContext _cont, ClassField _cf) {
        pair(_cont, _cf).getValue().setRead(false);
        pair(_cont, _cf).getValue().setWrite(false);
        pair(_cont, _cf).getValue().setCompoundRead(true);
        pair(_cont, _cf).getValue().setCompoundWrite(false);
        pair(_cont, _cf).getValue().setCompoundWriteErr(false);
    }

    private WatchPointBlockPair pair(ResultContext _cont, ClassField _cf) {
        int n_ = _cont.getPageEl().getAnaClassBody(_cf.getClassName()).getNumberAll();
        return _cont.getPairWatch(true,n_,_cf.getFieldName());
    }

    private void enteringCondition(String _newValue,ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = entering(_cont, _clName, _id);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        StdMethodPointBlockPair wp_ = _cont.getPair(s_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, wp_, _cont, type_, new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());
//        wp_.getValue().getResultEntry().result(res_,_newValue);
    }
    private void exitingCondition(String _newValue,ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = exiting(_cont, _clName, _id);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        StdMethodPointBlockPair wp_ = _cont.getPair(s_);
        wp_.getValue().getResultExit().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultExit().getResultStr());
//        ResultContextLambda res_ = ResultContextLambda.dynamicAnalyze(_newValue, wp_, _cont, type_, new DefContextGenerator());
//        assertTrue(res_.getReportedMessages().isAllEmptyErrors());
//        wp_.getValue().getResultExit().result(res_,_newValue);
    }
    private void enteringExitingCondition(String _newValue, String _exit,ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = enteringExiting(_cont, _clName, _id);
//        String type_ = _cont.getPageEl().getAliasPrimBoolean();
        StdMethodPointBlockPair wp_ = _cont.getPair(s_);
        wp_.getValue().getResultEntry().analyze(wp_,_newValue,"", "", _cont,new DefContextGenerator());
        wp_.getValue().getResultExit().analyze(wp_,_exit,"", "", _cont,new DefContextGenerator());
        assertEq(_newValue,wp_.getValue().getResultEntry().getResultStr());
        assertEq(_exit,wp_.getValue().getResultExit().getResultStr());
//        ResultContextLambda resEnter_ = ResultContextLambda.dynamicAnalyze(_newValue, wp_, _cont, type_, new DefContextGenerator());
//        ResultContextLambda resExit_ = ResultContextLambda.dynamicAnalyze(_exit, wp_, _cont, type_, new DefContextGenerator());
//        assertTrue(resEnter_.getReportedMessages().isAllEmptyErrors());
//        assertTrue(resExit_.getReportedMessages().isAllEmptyErrors());
//        wp_.getValue().getResultEntry().result(resEnter_,_newValue);
//        wp_.getValue().getResultExit().result(resExit_,_exit);
    }

    private void entering(ResultContext _cont, String _file, int _offset) {
        AbsPairPoint p_ = _cont.toggleWatchPoint(_file, _offset);
        ((MethodPointBlockPair)p_).getValue().setEntry(true);
        ((MethodPointBlockPair)p_).getValue().setExit(false);
    }
    private StandardNamedFunction entering(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = toggled(_cont, _clName, _id);
        _cont.getPair(s_).getValue().setEntry(true);
        _cont.getPair(s_).getValue().setExit(false);
        return s_;
    }

    private StandardNamedFunction exiting(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = toggled(_cont, _clName, _id);
        _cont.getPair(s_).getValue().setEntry(false);
        _cont.getPair(s_).getValue().setExit(true);
        return s_;
    }
    private StandardNamedFunction enteringExiting(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardNamedFunction s_ = toggled(_cont, _clName, _id);
        _cont.getPair(s_).getValue().setEntry(true);
        _cont.getPair(s_).getValue().setExit(true);
        return s_;
    }
    private StandardNamedFunction toggled(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardType v_ = _cont.getContext().getStandards().getStandards().getVal(_clName);
        StandardNamedFunction s_ = _id.look(v_).first();
        _cont.toggleBreakPoint(v_,s_);
        return s_;
    }

    private StandardNamedFunction toggledEnabled(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardType v_ = _cont.getContext().getStandards().getStandards().getVal(_clName);
        StandardNamedFunction s_ = _id.look(v_).first();
        _cont.toggleBreakPointEnabled(v_,s_);
        return s_;
    }
    private StandardNamedFunction fct(ResultContext _cont, String _clName, AbsractIdentifiableCommon _id) {
        StandardType v_ = _cont.getContext().getStandards().getStandards().getVal(_clName);
        return _id.look(v_).first();
    }
}
