package code.expressionlanguage.dbg;

import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalForIterativeTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){v+=i;}return v;}}");
        Struct cont_ = valueDbg("(t+u)*i","pkg.Ex","exmeth",139,files_);
        assertEq(14,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,5);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){if(([i])==1){v+=i;}}return v;}}");
        Struct cont_ = valueDbg("(t+u)*i+([i])","pkg.Ex","exmeth",152,files_);
        assertEq(25,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){v+=((:int)->{int s=0;iter(int i=t+1;u+1;1){s+=i+#i;}return s;}).call();}return v;}}");
        Struct cont_ = valueDbg("i","pkg.Ex","exmeth",182,files_);
        assertEq(4,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){v+=((:int)->{int s=0;iter(int i=t+1;u+1;1){s+=i+#i;}return s;}).call();}return v;}}");
        Struct cont_ = valueDbg("#i","pkg.Ex","exmeth",182,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,5);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){if(([i])==1){v+=((:int)->{int s=0;iter(int i=t+1;u+2;1){if(([i])==2){s+=i+#i;}}return s;}).call();}}return v;}}");
        Struct cont_ = valueDbg("([i])","pkg.Ex","exmeth",208,files_);
        assertEq(2,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,5);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){if(([i])==1){v+=((:int)->{int s=0;iter(int i=t+1;u+2;1){if(([i])==2){s+=i+#i;}}return s;}).call();}}return v;}}");
        Struct cont_ = valueDbg("([#i])","pkg.Ex","exmeth",208,files_);
        assertEq(1,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;iter(int i=t-1;u-1;1){v+=i;}return v;}}");
        ReportedMessages cont_ = valueDbgKo("i","pkg.Ex","exmeth", 136,files_);
        assertTrue(cont_.notAllEmptyErrors());
    }
}
