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

}
