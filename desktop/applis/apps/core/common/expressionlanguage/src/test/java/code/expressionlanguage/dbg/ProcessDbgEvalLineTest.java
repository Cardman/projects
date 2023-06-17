package code.expressionlanguage.dbg;

import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgEvalLineTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("t+u","pkg.Ex","exmeth",111,files_);
        assertEq(11,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("{class Loc{static final int i=10;}return Loc.i+t+u;}","pkg.Ex","exmeth",111,files_);
        assertEq(21,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}public static int db(int a){return 2*a;}}");
        Struct cont_ = valueDbg("db(t+u)","pkg.Ex","exmeth",111,files_);
        assertEq(22,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}public static int db(int a){return 2*a;}}");
        ArrayStruct cont_ = valueDbgExc("1/0","pkg.Ex","exmeth",111,files_);
        assertEq(1, cont_.getLength());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return fct.call(23,27);}}");
        Struct cont_ = valueDbg("#t+#u","pkg.Ex","exmeth",144,files_);
        assertEq(11,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return fct.call(23,27);}}");
        Struct cont_ = valueDbg("t+u","pkg.Ex","exmeth",144,files_);
        assertEq(50,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int f;public Ex(int t,int u){f=Math.mod(t,u);}public static int exmeth(){return new Ex(8,3).f;}}");
        Struct cont_ = valueDbg("t+u","pkg.Ex","exmeth",57,files_);
        assertEq(11,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){return switch[int](1){default;int v = Math.mod(t,u);return v;};}}");
        Struct cont_ = valueDbg("t+u","pkg.Ex","exmeth",141,files_);
        assertEq(11,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("t+u+\"_\".length()","pkg.Ex","exmeth",111,files_);
        assertEq(12,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("t+u+class(Ex).getName().length()","pkg.Ex","exmeth",111,files_);
        assertEq(17,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int field=4;public static int exmeth(){return new Ex().exmeth(8,3);}public int exmeth(int t, int u){int v = Math.mod(t,u);return v;}}");
        Struct cont_ = valueDbg("t+u+field","pkg.Ex","exmeth",132,files_);
        assertEq(15,((NumberStruct)cont_).intStruct());
    }
}
