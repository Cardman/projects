package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalArrLoopTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int res;for(int a:{3,1,2}){res+=a;}return res;}}");
        Struct cont_ = valueDbg("2*a","pkg.Ex","exmeth",75,files_);
        assertEq(6,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int res;for(int a:{3,4,2}){if(([a])==1){res+=a;}}return res;}}");
        Struct cont_ = valueDbg("a+([a])","pkg.Ex","exmeth",88,files_);
        assertEq(5,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int res;for(int a:{3,1,2}){res+=a;}return res;}}");
        ReportedMessages cont_ = valueDbgKo("a","pkg.Ex","exmeth", 66,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int res;for(that int o:{3,1,2}){res+=o;}return res;}}");
        Struct cont_ = valueDbg("2*o","pkg.Ex","exmeth",80,files_);
        assertEq(6,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int res;for(that int o:{3,4,2}){if(([o])==1){res+=o;}}return res;}}");
        Struct cont_ = valueDbg("o+([o])","pkg.Ex","exmeth",93,files_);
        assertEq(5,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int res;for(that int o:{3,1,2}){res+=o;}return res;}}");
        ReportedMessages cont_ = valueDbgKo("o","pkg.Ex","exmeth", 71,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
}
