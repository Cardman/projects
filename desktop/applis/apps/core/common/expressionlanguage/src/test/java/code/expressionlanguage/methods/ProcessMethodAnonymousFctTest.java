package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodAnonymousFctTest extends ProcessMethodCommon {

    @Test
    public void calculate1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int a:int)->{return 2 * a;}.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(){\n");
        xml_.append("  return staticCall(ExtOther<T>).m((T a:T)->{return (T)(2 * (int)a);},(T)3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExtOther<S> {\n");
        xml_.append(" staticCall S m(Fct<S,S> fct,S a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>().m();\n");
        xml_.append(" }\n");
        xml_.append(" T m(){\n");
        xml_.append("  return staticCall(ExtOther<T>).m((T a:T)->{return (T)(2 * (int)a);},(T)3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExtOther<S> {\n");
        xml_.append(" staticCall S m(Fct<S,S> fct,S a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T local;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(2).m();\n");
        xml_.append(" }\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  local = p;\n");
        xml_.append(" }\n");
        xml_.append(" T m(){\n");
        xml_.append("  return staticCall(ExtOther<T>).m((T a:T)->{return (T)((int)local * (int)a);},(T)3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExtOther<S> {\n");
        xml_.append(" staticCall S m(Fct<S,S> fct,S a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((:int)->{return 6;});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int> fct){\n");
        xml_.append("  return fct.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m((:String)->{\n");
        xml_.append("  String str=\"\";\n");
        xml_.append("  for (Stack s: Stack.current()){\n");
        xml_.append("   str += s.toString();\n");
        xml_.append("   str += \";\";\n");
        xml_.append("  }\n");
        xml_.append("  return str;});\n");
        xml_.append(" }\n");
        xml_.append(" static String m(Fct<String> fct){\n");
        xml_.append("  return fct.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("pkg/Ex:3,10:45\n" +
                "pkg.Ext.static m();pkg/Ex:12,10:218\n" +
                "pkg.Ext.static m($core.Fct<$core.String>);:0,0:0\n" +
                ".;pkg/Ex:5,23:99\n" +
                "pkg.Ext.static .1();", getString(ret_));
    }

    @Test
    public void calculate8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int {\n");
        xml_.append(" int db(int v);\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((Int)(int a:int)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Int fct,int a){\n");
        xml_.append("  return fct.db(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return m(fct,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;},gct = (int a:int)->{return 2 + a;};\n");
        xml_.append("  return m(fct,3)+m(gct,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Fct<int,int> fct = (int a:int)->{return 2 * a;},gct = (int a:int)->{return 2 + a;};\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(fct,3)+m(gct,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }
    @Test
    public void calculate12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static double exmeth(){\n");
        xml_.append("  Math.seed((DoubleGenerator)(:double)->{return 0.25;});\n");
        xml_.append("  return Math.random();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0.25, getDouble(ret_));
    }
    @Test
    public void calculate13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static long exmeth(){\n");
        xml_.append("  Math.seed((Generator)(long a:long)->{return a*10;});\n");
        xml_.append("  return Math.random(10);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(100, getNumber(ret_));
    }

    @Test
    public void calculate14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Intern{\n");
        xml_.append("   int field = 2;\n");
        xml_.append("  }\n");
        xml_.append("  return m((int a:int)->{return new Intern().field * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   int field = 2;\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   int field = 2;\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern(){}.field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  interface Intern{\n");
        xml_.append("   int field(int p);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern(){\n");
        xml_.append("   Fct<int,int> fct = (int b:int)->{return 1+b;};\n");
        xml_.append("   public int field(int p){\n");
        xml_.append("    return fct.call(p);\n");
        xml_.append("   }\n");
        xml_.append("  }.field(1) * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Content content = new Content();\n");
        xml_.append("  m((Content a:void)->{a.incr();},content);\n");
        xml_.append("  return content.value;\n");
        xml_.append(" }\n");
        xml_.append(" static void m(Fct<Content,void> fct,Content a){\n");
        xml_.append("  fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Content {\n");
        xml_.append(" int value = 5;\n");
        xml_.append(" void incr(){\n");
        xml_.append("  value++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{return a / 0;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateError("pkg.Ext", id_, args_, cont_);
        assertEq("$core.DivideZero", ret_.getStruct().getClassName(cont_));
    }

    @Test
    public void calculate20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   Fct<int,int> fct = (int b:int) -> {return b+1;};\n");
        xml_.append("   int field = fct.call(1);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Int{\n");
        xml_.append("   int field = 1;\n");
        xml_.append("  }\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   Fct<int,int> fct = (int b:int) -> {return b+new Int().field;};\n");
        xml_.append("   int field = fct.call(1);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  enum Intern{\n");
        xml_.append("   PLUS_ONE((int b:int) -> {return b+1;}),\n");
        xml_.append("   MULT_TWO((int b:int) -> {return b*2;});\n");
        xml_.append("   Fct<int,int> fct;\n");
        xml_.append("   Intern(Fct<int,int> p){\n");
        xml_.append("    fct = p;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return (Intern.PLUS_ONE.fct.call(1)+Intern.MULT_TWO.fct.call(1)) * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefaultSingle();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }

    @Test
    public void calculate23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  enum Intern{\n");
        xml_.append("   PLUS_ONE((int b:int) -> {return b+1;}){\n");
        xml_.append("    PLUS_ONE(Fct<int,int> p){\n");
        xml_.append("     super(p);\n");
        xml_.append("    }\n");
        xml_.append("   },\n");
        xml_.append("   MULT_TWO((int b:int) -> {return b*2;});\n");
        xml_.append("   Fct<int,int> fct;\n");
        xml_.append("   Intern(Fct<int,int> p){\n");
        xml_.append("    fct = p;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return (Intern.PLUS_ONE.fct.call(1)+Intern.MULT_TWO.fct.call(1)) * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefaultSingle();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }


    @Test
    public void calculate24() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int...a:int)->{\n");
        xml_.append("   int sum = 0;\n");
        xml_.append("   for (int i: a){\n");
        xml_.append("    sum += i;\n");
        xml_.append("   }\n");
        xml_.append("   return sum;}\n");
        xml_.append("  ,3,2,1);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int[],int> fct,int... a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }


    @Test
    public void calculate25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int z,int...a:int)->{\n");
        xml_.append("   int sum = z;\n");
        xml_.append("   for (int i: a){\n");
        xml_.append("    sum += i;\n");
        xml_.append("   }\n");
        xml_.append("   return sum;}\n");
        xml_.append("  ,3,2,1);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int[],int> fct,int z,int... a){\n");
        xml_.append("  return fct.call(z,a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda(\".1\",true,false,class(int))[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda(\".1\",true,false,class(int))[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" staticCall int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredFields()[0].getDeclaredAnonymousLambda(\".1\",true,false,class(int))[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredFields()[0].getDeclaredAnonymousLambda()[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda(\".1\",false,false,class(int))[0].invoke(new Ext(),3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int m(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].invoke(new Ext(),3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Ext(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int p){\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredConstructors()[0].getDeclaredAnonymousLambda(\".1\",false,false,class(int))[0].invoke(new Ext(0),3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Ext(){\n");
        xml_.append("  Fct<int,int> fct = (int a:int)->{return 2 * a;};\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int p){\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Ext).getDeclaredConstructors()[0].getDeclaredAnonymousLambda()[0].invoke(new Ext(0),3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(String).getDeclaredMethods()[0].getDeclaredAnonymousLambda().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculate37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(Character).getDeclaredFields()[0].getDeclaredAnonymousLambda().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculate38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return (int)class(String).getDeclaredConstructors()[0].getDeclaredAnonymousLambda().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculate39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a:int)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a:)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int> fct,int a){\n");
        xml_.append("  return fct.call()+a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((int a)->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<?,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(fct(),3);\n");
        xml_.append(" }\n");
        xml_.append(" static Fct<int,int> fct(){\n");
        xml_.append("  return (a)->{return 2 * a;};\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(fct(),3);\n");
        xml_.append(" }\n");
        xml_.append(" static Fct<int,int> fct(){\n");
        xml_.append("  Fct<int,int> fct = (a)->{return 2 * a;};\n");
        xml_.append("  return fct;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((Fct<int,int>)fct(),3);\n");
        xml_.append(" }\n");
        xml_.append(" static Fct fct(){\n");
        xml_.append("  return (int a:int)->{return 2 * a;};\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((Fct<int,int>)fct(),3);\n");
        xml_.append(" }\n");
        xml_.append(" static Fct fct(){\n");
        xml_.append("  return (Fct<int,int>)(a)->{return 2 * a;};\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(()->{return 6;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int> fct,int a){\n");
        xml_.append("  return fct.call()+a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculate50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a,int b)->{return 2 * a * b;},3,5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,?,int> fct,int a,int b){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(30, getNumber(ret_));
    }

    @Test
    public void calculate51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m();\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(){\n");
        xml_.append("  return staticCall(ExtOther<T>).m((a)->{return (T)(2 * (int)a);},(T)3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.ExtOther<S> {\n");
        xml_.append(" staticCall S m(Fct<S,S> fct,S a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Fct<int,int> fct;\n");
        xml_.append(" Ext(Fct<int,int> fct){\n");
        xml_.append("  this.fct=fct;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext((a)->{return 2 * a;}).fct.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Fct<int,int> fct;\n");
        xml_.append(" Ext(){\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<int,int> fct){\n");
        xml_.append("  this.fct=fct;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext((a)->{return 2 * a;}).fct.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Fct<int,int> fct;\n");
        xml_.append(" Fct<int> fct2;\n");
        xml_.append(" Ext(Fct<int> fct2){\n");
        xml_.append("  this.fct2=fct2;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<int,int> fct){\n");
        xml_.append("  this.fct=fct;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext((a)->{return 2 * a;}).fct.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate55() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  (int a:int)->{return 2 * a;};\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda(\".1\",true,false,class(int))[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  (int a:int)->{return 2 * a;};\n");
        xml_.append("  return (int)class(Ext).getDeclaredMethods()[0].getDeclaredAnonymousLambda()[0].invoke(null,3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate57() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" Fct<int,int> fct;\n");
        xml_.append(" Fct<int> fct2;\n");
        xml_.append(" Ext(Fct<int> fct2){\n");
        xml_.append("  this.fct2=fct2;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<int,int>... fct){\n");
        xml_.append("  this.fct=fct[0];\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext((a)->{return 2 * a;}).fct.call(3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate58() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a->{return 2 * a;},3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate59() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m((a)-> 2 * a,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> 2 * a,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,(a)-> 2 * a);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate62() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,a -> 2 * a);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate63() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,(a)-> 2 * (a + 5));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }

    @Test
    public void calculate64() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static double m(){\n");
        xml_.append("  return m(3.0,a -> .5 * a);\n");
        xml_.append(" }\n");
        xml_.append(" static double m(double a,Fct<double,double> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1.5, getDouble(ret_));
    }

    @Test
    public void calculate65() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,a -> a % 2 == 0 ? 2 : 1);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate66() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a % 2 == 0 ? 2 : 1);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculate67() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,5,(a,b) -> Math.plus(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,Fct<int,int,int> fct){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculate68() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int v = 5;\n");
        xml_.append("  return m(4,5,v > 4 ? (a,b) -> Math.plus(a,b):(a,b) -> Math.mult(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,Fct<int,int,int> fct){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculate69() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  int v = 3;\n");
        xml_.append("  return m(4,5,v > 4 ? (a,b) -> Math.plus(a,b):(a,b) -> Math.mult(a,b));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,Fct<int,int,int> fct){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }

    @Test
    public void calculate70() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,5,(a,b) -> a??b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Integer a,Integer b,Fct<Integer,Integer,int> fct){\n");
        xml_.append("  return fct.call(a,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate71() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new int[]{4,5},a -> a[0]);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate72() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[new int[]{4,5},a -> a[0]];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" void this(int[] a,Fct<int[],int> fct){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate73() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new int[]{a});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[]> fct){\n");
        xml_.append("  return fct.call(a)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate74() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new Ext(a));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Ext> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate75() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new int[a]);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[]> fct){\n");
        xml_.append("  return fct.call(a).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate76() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new int[a][]);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[][]> fct){\n");
        xml_.append("  return fct.call(a).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate77() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new Ext<int,int>(a));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Ext<int,int>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate78() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a instanceof Ext<int,int>?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate79() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext<int,int>(4),a -> a instanceof Ext<int,int>?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext<int,int> a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate80() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext(4),a -> a.p+6);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext a,Fct<Ext,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculate81() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext(4),a -> a?.p+6);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext a,Fct<Ext,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculate82() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[new int[]{4,5},a -> a?[0]];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" void this(int[] a,Fct<int[],int> fct){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate83() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[new int[]{4,5},a -> m+++a[0]];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" void this(int[] a,Fct<int[],int> fct){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate84() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[new int[]{4,5},a -> m--+a[0]];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" void this(int[] a,Fct<int[],int> fct){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate85() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[new int[]{4,5},a -> m+=a[0]];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" void this(int[] a,Fct<int[],int> fct){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate86() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m=3;\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[new int[]{4,5},a -> m??=a[0]];\n");
        xml_.append(" }\n");
        xml_.append(" int this(int[] a,Fct<int[],int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" void this(int[] a,Fct<int[],int> fct){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }

    @Test
    public void calculate87() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,a -> b -> {return 2*b;});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Fct<int,int>> fct){\n");
        xml_.append("  return fct.call(a).call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate88() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,a -> (int b:int) -> {return 2*b;}.call(a));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate89() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,a -> b -> 2*b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Fct<int,int>> fct){\n");
        xml_.append("  return fct.call(a).call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate90() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(3,(a) -> b -> 2*b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Fct<int,int>> fct){\n");
        xml_.append("  return fct.call(a).call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate91() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(3,a -> \"\\\"\"+a);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(int a,Fct<int,String> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("\"3", getString(ret_));
    }

    @Test
    public void calculate92() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(3,a -> \"\"+'\\''+a);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(int a,Fct<int,String> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("'3", getString(ret_));
    }

    @Test
    public void calculate93() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(3,a -> `hello``world`+a);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(int a,Fct<int,String> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("hello`world3", getString(ret_));
    }

    @Test
    public void calculate94() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new Ext<int,int>(a){Ext(int q){super(q);}});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Ext<int,int>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate95() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new int[a][ ]);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[][]> fct){\n");
        xml_.append("  return fct.call(a).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate96() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,new Fct<int,int[][]>[]{a -> new int[a][ ]});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[][]>[] fct){\n");
        xml_.append("  return fct[0].call(a).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate97() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static double m(){\n");
        xml_.append("  return m(3.0,a -> 0.5 * a);\n");
        xml_.append(" }\n");
        xml_.append(" static double m(double a,Fct<double,double> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1.5, getDouble(ret_));
    }

    @Test
    public void calculate98() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static double m(){\n");
        xml_.append("  return m(4,a -> a % 2 == 0 ?.5  : 1.5);\n");
        xml_.append(" }\n");
        xml_.append(" static double m(int a,Fct<int,double> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(0.5, getDouble(ret_));
    }

    @Test
    public void calculate99() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static double m(){\n");
        xml_.append("  return m(3,a -> a % 2 == 0 ?.5  : 1.5);\n");
        xml_.append(" }\n");
        xml_.append(" static double m(int a,Fct<int,double> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1.5, getDouble(ret_));
    }

    @Test
    public void calculate100() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> 2 - a,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(-1, getNumber(ret_));
    }

    @Test
    public void calculate101() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a instanceof Ext<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate102() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext<int,int>[4],a -> a instanceof Ext<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext<int,int>[] a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate103() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" static class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a instanceof Ext.Inner<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate104() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" static class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext.Inner<int,int>[4],a -> a instanceof Ext.Inner<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext.Inner<int,int>[] a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate105() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a instanceof Ext<int,int>.Inner<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate106() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext<int,int>.Inner<int,int>[4],a -> a instanceof Ext<int,int>.Inner<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext<int,int>.Inner<int,int>[] a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate107() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a instanceof Ext<int,Iterable<int>>.Inner<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate108() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext<int,Iterable<int>>.Inner<int,int>[4],a -> a instanceof Ext<int,Iterable<int>>.Inner<int,int>[]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext<int,Iterable<int>>.Inner<int,int>[] a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate109() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> a instanceof Ext<int,int>. Inner<int,int> [ ]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate110() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(new Ext<int,int>.Inner<int,int>[4],a -> a instanceof Ext<int,int>. Inner<int,int> [ ]?4:5);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Ext<int,int>.Inner<int,int>[] a,Fct<Object,int> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculate111() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<Object,boolean> inst = a -> a instanceof Ext<int,int>. Inner<int,int> [ ];\n");
        xml_.append("  return inst.call(4)?4:5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate112() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner<C,D> {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<Object,boolean> inst = a -> a instanceof Ext<int,int>. Inner<int,int>;\n");
        xml_.append("  return inst.call(4)?4:5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate113() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<Object,boolean> inst = a -> a instanceof Ext<int,int>. Inner;\n");
        xml_.append("  return inst.call(4)?4:5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate114() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(3,a -> \" \"+a);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(int a,Fct<int,String> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(" 3", getString(ret_));
    }

    @Test
    public void calculate115() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(3,a -> \"\"+' '+a);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(int a,Fct<int,String> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(" 3", getString(ret_));
    }

    @Test
    public void calculate116() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<Object,boolean> inst = a -> a instanceof Ext<int,int>. Inner&&a instanceof int;\n");
        xml_.append("  return inst.call(4)?4:5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate117() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  Fct<int,String> fct = a -> a+` `;\n");
        xml_.append("  return m(3,fct);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(int a,Fct<int,String> fct){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("3 ", getString(ret_));
    }

    @Test
    public void calculate118() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" class Inner {}\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Fct<Object,boolean> inst = a -> a instanceof int&&a instanceof Ext<int,int>. Inner;\n");
        xml_.append("  return inst.call(4)?4:5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate119() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new Ext<int,int[]>(a){Ext(int q){super(q);}});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Ext<int,int[]>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate120() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new{} Ext<int,int[]>(a){Ext(int q){super(q);}});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Ext<int,int[]>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate121() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<C,D> {\n");
        xml_.append(" int p=4;\n");
        xml_.append(" static int v;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new{} interfaces(Int) Int<int,int[]>(){Int(){interfaces(Int)();}});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Int<int,int[]>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate122() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new(a){Ext(int q){super(q);}});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Ext<int,int[]>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate123() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new[a]);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[]> fct){\n");
        xml_.append("  return fct.call(a).length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate124() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("interface pkg.Int<C,D> {\n");
        xml_.append(" int p=4;\n");
        xml_.append(" static int v;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext<A,B> {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new{ } interfaces(Int) Int<int,int[]>(){Int(){interfaces(Int)();}});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,Int<int,int[]>> fct){\n");
        xml_.append("  return fct.call(a).p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate125() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> ({a}));\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[]> fct){\n");
        xml_.append("  return fct.call(a)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate126() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> (int[]){a});\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,Fct<int,int[]> fct){\n");
        xml_.append("  return fct.call(a)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate127() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a * n((b) -> b,2),3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate128() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a & 5,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate129() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a | 5,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }

    @Test
    public void calculate130() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a ^ 5,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate131() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a << 1,2);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate132() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a <<< 1,2);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate133() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a <<<< 1,2);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate134() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a / 2,6);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }

    @Test
    public void calculate135() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(a -> a != 0 ? 6 / a : 4,2);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append(" static int n(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Int{\n");
        xml_.append("   int field = 1;\n");
        xml_.append("  }\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   Fct<int,int> fct = (int b:int) ->;\n");
        xml_.append("   int field = fct.call(1);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Int{\n");
        xml_.append("   int field = 1;\n");
        xml_.append("  }\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   Fct<int,int>[] fct = (int b:int) -> {return b;};\n");
        xml_.append("   int field = fct.call(1);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Int{\n");
        xml_.append("   int field = 1;\n");
        xml_.append("  }\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   Fct<int,int> fct = b ->;\n");
        xml_.append("   int field = fct.call(1);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("operator+ int () {\n");
        xml_.append(" return x -> new Ex(){};\n");
        xml_.append(" return x -> x -> {};\n");
        xml_.append(" return x -> x + y;\n");
        xml_.append(" return x -> x;\n");
        xml_.append(" return (x) -> x;\n");
        xml_.append("}\n");
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  class Int{\n");
        xml_.append("   int field = 1;\n");
        xml_.append("  }\n");
        xml_.append("  return m((int a:int)->{\n");
        xml_.append("  class Intern{\n");
        xml_.append("   Fct<int,int> fct = b ->;\n");
        xml_.append("   int field = fct.call(1);\n");
        xml_.append("  }\n");
        xml_.append("  return new Intern().field * a;\n");
        xml_.append("  }\n");
        xml_.append("  ,3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(Fct<int,int> fct,int a){\n");
        xml_.append("  return fct.call(a);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  a=x -> 1&;\n");
        xml_.append("  a=x -> 1?;\n");
        xml_.append("  a=x -> 1+;\n");
        xml_.append("  a=x -> 1-;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  a=x -> b(];\n");
        xml_.append("  a=x -> a[);\n");
        xml_.append("  a=x -> new;\n");
        xml_.append("  a=x -> new int{};\n");
        xml_.append("  a=x -> new interfaces(];\n");
        xml_.append("  a=x -> new interfaces;\n");
        xml_.append("  a=x -> new {];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
}
