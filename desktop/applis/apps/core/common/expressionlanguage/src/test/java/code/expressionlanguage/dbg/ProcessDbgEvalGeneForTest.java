package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalGeneForTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int àçéèñùâäêëÿûüîïôö=t-1;àçéèñùâäêëÿûüîïôö<u-1;àçéèñùâäêëÿûüîïôö++){v+=àçéèñùâäêëÿûüîïôö;}return v;}}");
        Struct cont_ = valueDbg("7*àçéèñùâäêëÿûüîïôö","pkg.Ex","exmeth",190,files_);
        assertEq(14,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(2,3);}public static int exmeth(int t, int u){int v = 1;int s=t;for(that int àçéèñùâäêëÿûüîïôö=that(s);àçéèñùâäêëÿûüîïôö<u;àçéèñùâäêëÿûüîïôö++){v+=àçéèñùâäêëÿûüîïôö;}return v;}}");
        Struct cont_ = valueDbg("7*àçéèñùâäêëÿûüîïôö","pkg.Ex","exmeth",205,files_);
        assertEq(14,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(2,3);}public static int exmeth(int t, int u){int v = 1;int s=t;for(final int àçéèñùâäêëÿûüîïôö=s;s<u;s++){v+=àçéèñùâäêëÿûüîïôö;}return v;}}");
        Struct cont_ = valueDbg("7*àçéèñùâäêëÿûüîïôö","pkg.Ex","exmeth",168,files_);
        assertEq(14,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,5);}public static int exmeth(int t, int u){int v = 1;for(int àçéèñùâäêëÿûüîïôö=t-1;àçéèñùâäêëÿûüîïôö<u-1;àçéèñùâäêëÿûüîïôö++){if(([àçéèñùâäêëÿûüîïôö])==1){v+=àçéèñùâäêëÿûüîïôö;}}return v;}}");
        Struct cont_ = valueDbg("7+([àçéèñùâäêëÿûüîïôö])","pkg.Ex","exmeth",219,files_);
        assertEq(8,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int àçéèñùâäêëÿûüîïôö=t-1;àçéèñùâäêëÿûüîïôö<u-1;àçéèñùâäêëÿûüîïôö++){v+=àçéèñùâäêëÿûüîïôö;}return v;}}");
        Struct cont_ = valueDbg("àçéèñùâäêëÿûüîïôö","pkg.Ex","exmeth",125,files_);
        assertEq(0,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int àçéèñùâäêëÿûüîïôö=t-1;àçéèñùâäêëÿûüîïôö<u-1;àçéèñùâäêëÿûüîïôö++){v+=àçéèñùâäêëÿûüîïôö;}return v;}}");
        ReportedMessages cont_ = valueDbgKo("àçéèñùâäêëÿûüîïôö","pkg.Ex","exmeth",219,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
}
