package code.expressionlanguage.dbg;

import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalReturnTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("(t+u)*v","pkg.Ex","exmeth",136,files_);
        assertEq(22,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);that int w=that(v);w++;return v;}}");
        Struct cont_ = valueDbg("(t+u)*v","pkg.Ex","exmeth",159,files_);
        assertEq(33,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return fct.call(23,27);}}");
        Struct cont_ = valueDbg("#t+#u+v","pkg.Ex","exmeth",171,files_);
        assertEq(12,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return fct.call(23,27);}}");
        Struct cont_ = valueDbg("t+u+v","pkg.Ex","exmeth",171,files_);
        assertEq(51,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){final int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("(t+u)*v","pkg.Ex","exmeth",142,files_);
        assertEq(22,((NumberStruct)cont_).intStruct());
    }
}
