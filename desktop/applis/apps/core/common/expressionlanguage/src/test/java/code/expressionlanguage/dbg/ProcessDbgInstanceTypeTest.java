package code.expressionlanguage.dbg;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgInstanceTypeTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v=2;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall sInit_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(0, sInit_.nbPages());
        StackCall stack_ = dbgNormalAfterInit("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(13, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getGlobalStruct();
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.Ex", "v")).getStruct();
        assertEq(0, NumParsers.convertToNumber(v_).intStruct());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v=2;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall sInit_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(0, sInit_.nbPages());
        StackCall stack_ = dbgNormalAfterInit("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v=2;\n");
        xml_.append(" public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall sInit_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(0, sInit_.nbPages());
        StackCall stack_ = dbgNormalAfterInit("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(13, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getGlobalStruct();
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.Ex", "v")).getStruct();
        assertEq(0, NumParsers.convertToNumber(v_).intStruct());
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" int v=2;\n");
        xml_.append(" public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall sInit_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(0, sInit_.nbPages());
        StackCall stack_ = dbgNormalAfterInit("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {\n");
        xml_.append(" int v=2;\n");
        xml_.append(" public Ex(){\n");
        xml_.append("  super(3);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuper {\n");
        xml_.append(" int s=2;\n");
        xml_.append(" public ExSuper(int s){\n");
        xml_.append("  this.s=s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall sInit_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(0, sInit_.nbPages());
        StackCall stack_ = dbgNormalAfterInit("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(13, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getGlobalStruct();
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.Ex", "v")).getStruct();
        assertEq(0, NumParsers.convertToNumber(v_).intStruct());
        Struct sec_ = s_.getEntryStruct(new ClassField("pkg.ExSuper", "s")).getStruct();
        assertEq(0, NumParsers.convertToNumber(sec_).intStruct());
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex:ExSuper {\n");
        xml_.append(" int v=2;\n");
        xml_.append(" public Ex(){\n");
        xml_.append("  super(3);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuper {\n");
        xml_.append(" int s=2;\n");
        xml_.append(" public ExSuper(int s){\n");
        xml_.append("  this.s=s;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",13,cont_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointInstanceType("pkg/Ex",13,cont_, true);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().breakPointStaticType("pkg/Ex",13,cont_, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall sInit_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(0, sInit_.nbPages());
        StackCall stack_ = dbgNormalAfterInit("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int sample()2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",18,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(18, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getGlobalStruct();
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.MyAnnot", "sample")).getStruct();
        assertEq(2, NumParsers.convertToNumber(v_).intStruct());
    }

    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" int sample()2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",18,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public @class pkg.MyRecord {\n");
        xml_.append("int v=2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  new MyRecord(v:3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",14,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(14, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getGlobalStruct();
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.MyRecord", "v")).getStruct();
        assertEq(0, NumParsers.convertToNumber(v_).intStruct());
    }

    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public @class pkg.MyRecord {\n");
        xml_.append("int v=2;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  new MyRecord(v:3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",14,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" MyAnnotContent sample()@MyAnnotContent;\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotContent {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",18,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(3, stack_.nbPages());
        assertEq(18, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getGlobalStruct();
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.MyAnnot", "sample")).getStruct();
        assertSame(NullStruct.NULL_VALUE,v_);
    }

    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public annotation pkg.MyAnnot {\n");
        xml_.append(" MyAnnotContent sample()@MyAnnotContent;\n");
        xml_.append("}\n");
        xml_.append("public annotation pkg.MyAnnotContent {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" @MyAnnot\n");
        xml_.append(" public static void catching(){\n");
        xml_.append("  class(Ex).getDeclaredMethods()[0].getAnnotations();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",18,cont_);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    private ExecFileBlock file(ResultContext _cont) {
        return file(_cont,"pkg/Ex");
    }
    private ExecFileBlock file(ResultContext _cont, String _name) {
        return _cont.getContext().getClasses().getDebugMapping().getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }
}
