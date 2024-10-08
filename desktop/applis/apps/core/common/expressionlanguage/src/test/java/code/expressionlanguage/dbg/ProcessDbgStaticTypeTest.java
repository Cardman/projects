package code.expressionlanguage.dbg;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgStaticTypeTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" int w=2;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",13);
        updateStaType(cont_, "pkg/Ex",13, true);
        updateInstType(cont_, "pkg/Ex",13, false);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(13, now(stack_));
        Struct v_ = cont_.getContext().getClasses().getCommon().getStaticFields().getVal("pkg.Ex").getVal("v");
        assertEq(0, NumParsers.convertToNumber(v_).intStruct());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int v=2;\n");
        xml_.append(" int w=2;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",13);
        updateStaType(cont_, "pkg/Ex",13, true);
        updateInstType(cont_, "pkg/Ex",13, false);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalInit("pkg.Ex", id_,cont_).getStack();
        StackCall next_ = dbgNormal("pkg.Ex", id_,cont_, stack_);
        assertEq(0, next_.nbPages());
        StackCall stackLan_ = dbgNormal("pkg.Ex", id_, cont_,next_);
        assertEq(0, stackLan_.nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE(2);\n");
        xml_.append(" int v;\n");
        xml_.append(" (int i){\n");
        xml_.append("  v=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return Ex.ONE.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",26);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(22, now(stack_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE(2);\n");
        xml_.append(" int v;\n");
        xml_.append(" (int i){\n");
        xml_.append("  v=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return Ex.ONE.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",26);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        MethodId id_ = getMethodId("exmeth");
        StackCall next_ = dbgNormal("pkg.Ex", id_,cont_, stack_);
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex<T> {\n");
        xml_.append(" ONE<int>(2);\n");
        xml_.append(" int v;\n");
        xml_.append(" (int i){\n");
        xml_.append("  v=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return Ex.ONE.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",29);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        assertEq(1, stack_.nbPages());
        assertEq(25, now(stack_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE(2);\n");
        xml_.append(" int v;\n");
        xml_.append(" (int i){\n");
        xml_.append("  v=i;\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return Ex.ONE.v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",26);
        StackCall stack_ = tryInitStaticlyTypes(cont_.getContext(), cont_.getForwards().getOptions());
        MethodId id_ = getMethodId("exmeth");
        StackCall next_ = dbgNormal("pkg.Ex", id_,cont_, stack_);
        assertEq(0, next_.nbPages());
    }
    private ExecFileBlock file(ResultContext _cont) {
        return file(_cont,"pkg/Ex");
    }
    private ExecFileBlock file(ResultContext _cont, String _name) {
        return _cont.getFiles().getVal(_cont.getPageEl().getPreviousFilesBodies().getVal(_name));
    }
}
