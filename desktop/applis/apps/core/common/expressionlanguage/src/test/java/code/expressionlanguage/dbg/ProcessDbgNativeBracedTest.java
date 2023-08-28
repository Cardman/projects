package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.stds.LgNames;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgNativeBracedTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        Forwards fwd_ = cont_.getForwards();
        String enums_ = ((LgNames) fwd_.getGenerator()).getCoreNames().getAliasEnums();
        String ordEn_ = ((LgNames) fwd_.getGenerator()).getPredefTypes().getAliasEnumOrdinal();
        String name_ = ((LgNames) fwd_.getGenerator()).getPredefTypes().getAliasEnumType();
        int metInd_ = cont_.getPageEl().getPreviousFilesBodies().getVal(name_).getContent().indexOf(ordEn_);
        int ret_ = cont_.getPageEl().getPreviousFilesBodies().getVal(name_).getContent().indexOf(enums_, metInd_);
        cont_.toggleBreakPoint(name_,ret_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public enum pkg.Ex {\n");
        xml_.append(" ONE;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return ONE.ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        Forwards fwd_ = cont_.getForwards();
        String enums_ = ((LgNames) fwd_.getGenerator()).getCoreNames().getAliasEnums();
        String ordEn_ = ((LgNames) fwd_.getGenerator()).getPredefTypes().getAliasEnumOrdinal();
        String name_ = ((LgNames) fwd_.getGenerator()).getPredefTypes().getAliasEnumType();
        int metInd_ = cont_.getPageEl().getPreviousFilesBodies().getVal(name_).getContent().indexOf(ordEn_);
        int ret_ = cont_.getPageEl().getPreviousFilesBodies().getVal(name_).getContent().indexOf(enums_, metInd_);
        cont_.toggleBreakPoint(name_,ret_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
}
