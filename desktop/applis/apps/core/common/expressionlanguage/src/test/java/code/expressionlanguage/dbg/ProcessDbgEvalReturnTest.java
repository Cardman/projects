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
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return fct.call(23,27);}}");
        Struct cont_ = valueDbg("((int t,int u,int v:int)->t+u+v).call(#t,#u,v)","pkg.Ex","exmeth",171,files_);
        assertEq(12,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return fct.call(23,27);}}");
        Struct cont_ = valueDbg("((int t,int u,int v:int)->t+u+v).call(t,u,v)","pkg.Ex","exmeth",171,files_);
        assertEq(51,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return switch(2){case 2;return fct.call(23,27);default;return 1;};}}");
        Struct cont_ = valueDbg("switch[int](2){case 2;return #t+#u+v;default;return-1;}","pkg.Ex","exmeth",171,files_);
        assertEq(12,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(8,3);}public static int exmeth(int t, int u){Fct<int,int,int> fct=(t,u)->{int v = Math.mod(t+#t,u+#u);return v;};return switch(2){case 2;return fct.call(23,27);default;return 1;};}}");
        Struct cont_ = valueDbg("switch[int](2){case 2;return t+u+v;default;return-1;}","pkg.Ex","exmeth",171,files_);
        assertEq(51,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return new MyInt1(){public int field = 5;public int m(int a){return 3*a+--field;}}.m(2);}}public interface pkg.MyInt1{public int m(int a);}public interface pkg.MyInt2{public int m(int a);}");
        Struct cont_ = valueDbg("((MyInt2)new(){public int field2 = 5;public int m(int a){return 3*a+--field2;}}).m(field-3)","pkg.Ex","exmeth",116,files_);
        assertEq(10,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return new MyInt1(){public int field = 5;public int m(int a){return 3*a+--field;}}.m(2);}}public interface pkg.MyInt1{public int m(int a);}public interface pkg.MyInt2{public int m(int a);}");
        Struct cont_ = valueDbg("((MyInt2)new MyInt2(){public int field2 = 5;public int m(int a){return 3*a+--field2;}}).m(field-3)","pkg.Ex","exmeth",116,files_);
        assertEq(10,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}}public enum pkg.MyEnum{ONE{}}");
        Struct cont_ = valueDbg("MyEnum.ONE.ordinal()+1","pkg.Ex","exmeth",55,files_);
        assertEq(1,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}}public enum pkg.MyEnum{ONE{}}");
        Struct cont_ = valueDbg("((MyEnum..ONE)MyEnum.ONE).ordinal()+1","pkg.Ex","exmeth",55,files_);
        assertEq(1,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}}public enum pkg.MyEnum{ONE{public static int method(){return 5;}}}");
        Struct cont_ = valueDbg("((MyEnum..ONE)MyEnum.ONE).method()+1","pkg.Ex","exmeth",55,files_);
        assertEq(6,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public final int field;public Ex(int p){field=p;}public static int exmeth(){return 0;}}operator+ pkg.Ex(pkg.Ex a, pkg.Ex b){return new(a.field+b.field);}");
        Struct cont_ = valueDbg("(new Ex(2)+new Ex(4)).field","pkg.Ex","exmeth",104,files_);
        assertEq(6,((NumberStruct)cont_).intStruct());
    }
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public final int field;public Ex(int p){field=p;}public static int exmeth(){return (new Ex(3)+new Ex(4)).field;}}operator+ pkg.Ex(pkg.Ex a, pkg.Ex b){return new(a.field+b.field);}");
        Struct cont_ = valueDbg("a.field*b.field","pkg.Ex","exmeth",178,files_);
        assertEq(12,((NumberStruct)cont_).intStruct());
    }
}
