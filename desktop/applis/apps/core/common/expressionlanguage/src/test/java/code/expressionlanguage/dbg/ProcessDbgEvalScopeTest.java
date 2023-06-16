package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalScopeTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exfield=1;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl cont_ = scope(files_,"pkg/Ex",41);
        assertEq(0,cont_.getInfosVars().size());
        assertEq(0,cont_.getCache().getLocalVariables().size());
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int exfield=1;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl cont_ = scope(files_,"pkg/Ex",34);
        assertEq(0,cont_.getInfosVars().size());
        assertEq(0,cont_.getCache().getLocalVariables().size());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(int t, int u){\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl cont_ = scope(files_,"pkg/Ex",72);
        assertEq(2,cont_.getInfosVars().size());
        assertEq("int",cont_.getInfosVars().getVal("t").getClassName());
        assertEq("int",cont_.getInfosVars().getVal("u").getClassName());
        assertEq(0,cont_.getCache().getLocalVariables().size());
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(int t, int u){\n");
        xml_.append("  return ((int t,int u:int)->Math.mod(t+#t,u+#u)).call(5,7);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedPageEl cont_ = scope(files_,"pkg/Ex",92);
        assertEq(2,cont_.getInfosVars().size());
        assertEq("int",cont_.getInfosVars().getVal("t").getClassName());
        assertEq("int",cont_.getInfosVars().getVal("u").getClassName());
        assertEq(2,cont_.getCache().getLocalVariables().size());
        assertEq("int",cont_.getCache().getLocalVar("t",0).getClassName());
        assertEq("int",cont_.getCache().getLocalVar("u",0 ).getClassName());
    }
}
