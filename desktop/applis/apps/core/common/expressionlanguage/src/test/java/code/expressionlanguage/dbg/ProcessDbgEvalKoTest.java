package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalKoTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ReportedMessages cont_ = valueDbgKoStType("s","pkg.Ex","exmeth",files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ReportedMessages cont_ = valueDbgKoStType("{class Loc{static int i;static{i++;}}return s;}","pkg.Ex","exmeth",files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        ReportedMessages cont_ = valueDbgKoStType("}","pkg.Ex","exmeth",files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
}
