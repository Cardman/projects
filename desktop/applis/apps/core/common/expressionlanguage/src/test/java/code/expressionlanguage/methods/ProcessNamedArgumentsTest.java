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

public final class ProcessNamedArgumentsTest extends ProcessMethodCommon {

    @Test
    public void calculate1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2,c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(c:5,b:3,a:2);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2,c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c,int... d){\n");
        xml_.append("  return a*b+c+d.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2,d:new int[]{4,6},c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c,int... d){\n");
        xml_.append("  return a*b+c+d[0]*d[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(35, getNumber(ret_));
    }

    @Test
    public void calculate5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(c:\"5\",b:3,a:true);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(boolean a,int b,String c){\n");
        xml_.append("  return a?\"\"+b:c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("3", getString(ret_));
    }

    @Test
    public void calculate6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m(c:\"5\",b:3,a:false);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(boolean a,int b,String c){\n");
        xml_.append("  return a?\"\"+b:c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }

    @Test
    public void calculate7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Sub();\n");
        xml_.append("  return e.m(d:10,b:13,c:4,a:7);\n");
        xml_.append(" }\n");
        xml_.append(" int m(int a,int b,int c,int d){\n");
        xml_.append("  return a*b+c-d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Sub:Ext {\n");
        xml_.append(" int m(int b,int a,int d,int c){\n");
        xml_.append("  return a*b-c+d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(85, getNumber(ret_));
    }

    @Test
    public void calculate8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Sub e = new Sub();\n");
        xml_.append("  return e.m(d:10,b:13,c:4,a:7);\n");
        xml_.append(" }\n");
        xml_.append(" int m(int a,int b,int c,int d){\n");
        xml_.append("  return a*b+c-d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Sub:Ext {\n");
        xml_.append(" int m(int b,int a,int d,int c){\n");
        xml_.append("  return a*b-c+d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(97, getNumber(ret_));
    }

    @Test
    public void calculate9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int field;\n");
        xml_.append(" Ext(int a,int b,int c,int d){\n");
        xml_.append("  field = a*b+c-d;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext(d:10,b:13,c:4,a:7);\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(85, getNumber(ret_));
    }

    @Test
    public void calculate10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int field;\n");
        xml_.append(" Ext(int a,int b,int c,int d){\n");
        xml_.append("  field = a*b+c-d;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext(d:10,b:13,c:4,a:7){\n");
        xml_.append("   Ext(int a,int b,int c,int d){\n");
        xml_.append("    super(a,b,c,d);\n");
        xml_.append("   }\n");
        xml_.append("  };\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(85, getNumber(ret_));
    }

    @Test
    public void calculate11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int field;\n");
        xml_.append(" Ext(int a,int b,int c,int d){\n");
        xml_.append("  field = a*b+c-d;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Sub();\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("class pkg.Sub:Ext {\n");
        xml_.append(" Sub(){\n");
        xml_.append("  super(d:10,b:13,c:4,a:7);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(85, getNumber(ret_));
    }

    @Test
    public void calculate12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={0,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  e[i:0]=15;\n");
        xml_.append("  return e[i:0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }

    @Test
    public void calculate13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int[] field={3,1};\n");
        xml_.append(" int this(int i){\n");
        xml_.append("  return field[i];\n");
        xml_.append(" }\n");
        xml_.append(" void this(int i){\n");
        xml_.append("  field[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  Ext e = new Ext();\n");
        xml_.append("  return e[i:0]+=15;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }

    @Test
    public void calculate14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" int p;\n");
        xml_.append(" Ext(int p){\n");
        xml_.append("  this.p=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(4,a -> new Ext(p:a));\n");
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
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculate15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2,c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int b){\n");
        xml_.append("  return 5*b+7;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return m(2,c:5,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int a,int b,int c){\n");
        xml_.append("  return a*b+c;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(int b,int c){\n");
        xml_.append("  return 5*b+c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m($id(Ext,boolean,int,String),c:\"5\",b:3,a:true);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(boolean a,int b,String c){\n");
        xml_.append("  return a?\"\"+b:c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("3", getString(ret_));
    }

    @Test
    public void calculate18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  return m($id(Ext,boolean,int,String),c:\"5\",b:3,a:false);\n");
        xml_.append(" }\n");
        xml_.append(" static String m(boolean a,int b,String c){\n");
        xml_.append("  return a?\"\"+b:c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }

    @Test
    public void calculate19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  StringBuilder str = new StringBuilder();\n");
        xml_.append("  str.append(b:1,c:4,a:\"part\");\n");
        xml_.append("  return str.toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("art", getString(ret_));
    }

    @Test
    public void calculate20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext {\n");
        xml_.append(" static String m(){\n");
        xml_.append("  StringBuilder str = new StringBuilder(a:\"art\");\n");
        xml_.append("  return str.toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq("art", getString(ret_));
    }

    @Test
    public void calculate21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:new Ext<>(11));\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,a:new Ext<>(11));\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T> a){\n");
        xml_.append("  return (T)((int)a.field+b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }

    @Test
    public void calculate23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,c:new Ext<>(11),a:new Ext<>(18));\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T> a,Ext<T> c){\n");
        xml_.append("  return (T)((int)a.field-(int)c.field+b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate24() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,c:new Ext<>(11),a:new Ext<>(18));\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T> a){\n");
        xml_.append("  return a.field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T> a,Ext<T> c){\n");
        xml_.append("  return (T)((int)a.field-(int)c.field+b);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,int a,Ext<T> c){\n");
        xml_.append("  return (T)(a-(int)c.field+b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T> a){\n");
        xml_.append("  field=(T)((int)a.field+b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,a:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }

    @Test
    public void calculate27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T> a,Ext<T> c){\n");
        xml_.append("  field=(T)((int)a.field-(int)c.field+b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,c:new Ext<>(11),a:new Ext<>(18)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T> a){\n");
        xml_.append("  field=a.field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T> a,Ext<T> c){\n");
        xml_.append("  field=(T)((int)a.field-(int)c.field+b);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,int a,Ext<T> c){\n");
        xml_.append("  field=(T)(a-(int)c.field+b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,c:new Ext<>(11),a:new Ext<>(18)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:new Ext<>[]{new Ext<>(11)});\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,a:new Ext<>[]{new Ext<>(11)});\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T>[] a){\n");
        xml_.append("  return (T)((int)a[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }

    @Test
    public void calculate31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,c:new Ext<>[]{new Ext<>(11)},a:new Ext<>[]{new Ext<>(18)});\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T>[] a,Ext<T>[] c){\n");
        xml_.append("  return (T)((int)a[0].field-(int)c[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,c:new Ext<>[]{new Ext<>(11)},a:new Ext<>[]{new Ext<>(18)});\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T>[] a){\n");
        xml_.append("  return a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Ext<T>[] a,Ext<T>[] c){\n");
        xml_.append("  return (T)((int)a[0].field-(int)c[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,int a,Ext<T>[] c){\n");
        xml_.append("  return (T)(a-(int)c[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:new Ext<>[]{new Ext<>(11)}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T>[] a){\n");
        xml_.append("  field=(T)((int)a[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,a:new Ext<>[]{new Ext<>(11)}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }

    @Test
    public void calculate35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T>[] a,Ext<T>[] c){\n");
        xml_.append("  field=(T)((int)a[0].field-(int)c[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,c:new Ext<>[]{new Ext<>(11)},a:new Ext<>[]{new Ext<>(18)}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T>[] a){\n");
        xml_.append("  field=a[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Ext<T>[] a,Ext<T>[] c){\n");
        xml_.append("  field=(T)((int)a[0].field-(int)c[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,int a,Ext<T>[] c){\n");
        xml_.append("  field=(T)(a-(int)c[0].field+b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,c:new Ext<>[]{new Ext<>(11)},a:new Ext<>[]{new Ext<>(18)}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(22, getNumber(ret_));
    }

    @Test
    public void calculate37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:a -> 2 * a,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Fct<T,T> a,T b){\n");
        xml_.append("  return a.call(b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(15,c:3,a:a -> 2 * a,d:10);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Fct<T,T> a,T b){\n");
        xml_.append("  return a.call(b);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(int b,Fct<T,T> a,T c, int d){\n");
        xml_.append("  return (T)((int)a.call(c)+b+d);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(31, getNumber(ret_));
    }

    @Test
    public void calculate39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:a -> 2 * a,b:3);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Fct<T,T> a,T b){\n");
        xml_.append("  return a.call(b);\n");
        xml_.append(" }\n");
        xml_.append(" staticCall T m(Fct<T,T,T> a,T b){\n");
        xml_.append("  return a.call(b,b);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<T,T> a,T b){\n");
        xml_.append("  field=a.call(b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:a -> 2 * a,b:3).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<T,T> a,T b){\n");
        xml_.append("  field=a.call(b);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(int b,Fct<T,T> a,T c, int d){\n");
        xml_.append("  field=(T)((int)a.call(c)+b+d);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(15,c:3,a:a -> 2 * a,d:10).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(31, getNumber(ret_));
    }

    @Test
    public void calculate42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<T,T> a,T b){\n");
        xml_.append("  field=a.call(b);\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Fct<T,T,T> a,T b){\n");
        xml_.append("  field=a.call(b,b);\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:a -> 2 * a,b:3).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculate43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(new Ext<>[1][1],0,0,new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(new Ext<>[1][],0,new Ext<>[]{new Ext<>(11)}).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, Ext<T>[] d){\n");
        xml_.append("  return (a[b]=d)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(new Ext<>[1][1],0,0,new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[0][b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(new Ext<>[1][1],0,0,new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, Ext<T>[] d){\n");
        xml_.append("  field=(a[b]=d)[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(new Ext<>[1][],0,new Ext<>[]{new Ext<>(11)}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[0][b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(new Ext<>[1][1],0,0,new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:new Ext<>[1][1],b:0,c:0,d:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:new Ext<>[1][],b:0,d:new Ext<>[]{new Ext<>(11)}).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, Ext<T>[] d){\n");
        xml_.append("  return (a[b]=d)[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:new Ext<>[1][1],b:0,c:0,d:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[0][b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[b]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:new Ext<>[1][1],b:0,c:0,d:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, Ext<T>[] d){\n");
        xml_.append("  field=(a[b]=d)[0].field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:new Ext<>[1][],b:0,d:new Ext<>[]{new Ext<>(11)}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[][][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[0][b][c]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" Ext(Ext<T>[] a,int b, int c, Ext<T> d){\n");
        xml_.append("  field=(a[b]=d).field;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return new Ext<int>(a:new Ext<>[1][1],b:0,c:0,d:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate55() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(new Ext<>[][][]{new Ext<>[1][1]},0,0,new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[0][b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculate56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("class pkg.Ext<T> {\n");
        xml_.append(" T field;\n");
        xml_.append(" Ext(T p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" static int m(){\n");
        xml_.append("  return staticCall(Ext<int>).m(a:new Ext<>[][][]{new Ext<>[1][1]},b:0,c:0,d:new Ext<>(11)).field;\n");
        xml_.append(" }\n");
        xml_.append(" staticCall Ext<T> m(Ext<T>[][][] a,int b, int c, Ext<T> d){\n");
        xml_.append("  return a[0][b][c]=d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkg.Ext", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

}
