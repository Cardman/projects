package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodImplicitCastTest extends ProcessMethodCommon {

    @Test
    public void test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(int,$id)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 2+3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(Integer i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(Long i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.intValue();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5I;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(Long i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.intValue();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5I;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }

    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return 2+3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> out = new ExClass<>();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }

    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExCaller<int>).method();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<S> {\n");
        xml_.append(" public staticCall ExClass<S> method(){\n");
        xml_.append("  return (S)(2+3);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExCaller<int>).method();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<S> {\n");
        xml_.append(" public staticCall S method(){\n");
        xml_.append("  ExClass<S> out = new ExClass<S>();\n");
        xml_.append("  out.field = (S)5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExSum a = new ExSum();\n");
        xml_.append("  a.field=2;\n");
        xml_.append("  ExSum b = new ExSum();\n");
        xml_.append("  b.field=3;\n");
        xml_.append("  return a+=b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ pkg.ExSum (pkg.ExSum a, pkg.ExSum b){\n");
        xml_.append("  ExSum e = new ExSum();\n");
        xml_.append("  e.field=a.field+b.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  a[0].field=10;\n");
        xml_.append("  ExSum b = new ExSum();\n");
        xml_.append("  b.field=3;\n");
        xml_.append("  return a[0]+=b;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" ExSum[] e = {new ExSum()};\n");
        xml_.append(" public ExSum this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ pkg.ExSum (pkg.ExSum a, pkg.ExSum b){\n");
        xml_.append("  ExSum e = new ExSum();\n");
        xml_.append("  e.field=a.field+b.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(13, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  a[0]=10;\n");
        xml_.append("  return a[0]+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(13, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  int a=10;\n");
        xml_.append("  return a+=3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(13, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExSum a = new ExSum();\n");
        xml_.append("  a.field=2;\n");
        xml_.append("  return a++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator++ pkg.ExSum (pkg.ExSum a){\n");
        xml_.append("  ExSum e = new ExSum();\n");
        xml_.append("  e.field=a.field+1;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(2, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  a[0].field=10;\n");
        xml_.append("  return a[0]++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" ExSum[] e = {new ExSum()};\n");
        xml_.append(" public ExSum this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator++ pkg.ExSum (pkg.ExSum a){\n");
        xml_.append("  ExSum e = new ExSum();\n");
        xml_.append("  e.field=a.field+1;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(10, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  a[0]=10;\n");
        xml_.append("  return a[0]++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(10, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  int a=10;\n");
        xml_.append("  return a++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(10, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExSum a = new ExSum();\n");
        xml_.append("  a.field=2;\n");
        xml_.append("  return ++a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator++ pkg.ExSum (pkg.ExSum a){\n");
        xml_.append("  ExSum e = new ExSum();\n");
        xml_.append("  e.field=a.field+1;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(3, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test24() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  a[0].field=10;\n");
        xml_.append("  return ++a[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" ExSum[] e = {new ExSum()};\n");
        xml_.append(" public ExSum this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator++ pkg.ExSum (pkg.ExSum a){\n");
        xml_.append("  ExSum e = new ExSum();\n");
        xml_.append("  e.field=a.field+1;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(11, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  a[0]=10;\n");
        xml_.append("  return ++a[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(11, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  int a=10;\n");
        xml_.append("  return ++a;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(11, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  return a[0]=10;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" int[] e = {0};\n");
        xml_.append(" public int this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(10, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExCont a = new ExCont();\n");
        xml_.append("  return a[0].field=10;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCont {\n");
        xml_.append(" ExSum[] e = {new ExSum()};\n");
        xml_.append(" public ExSum this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSum {\n");
        xml_.append(" public int field;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(ExSum i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(10, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExSub out = new ExSub();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:ExClass {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }

    @Test
    public void test30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExSub<int> out = new ExSub<int>();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub<S>:ExClass<S> {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }
    @Test
    public void test31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Integer $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }
    @Test
    public void test32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Integer $(ExClass i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void test33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Byte $(ExClass i){\n");
        xml_.append("  return (byte)i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }
    @Test
    public void test34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Cl1:Int1 {\n");
        xml_.append(" public Cl1(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Cl2:Int1 {\n");
        xml_.append(" public Cl2(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int1 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Int1(int field) {\n");
        xml_.append("  this.field=field;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Int1 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  var out2 = new Cl1(2);\n");
        xml_.append("  var out1 = new Cl2(1);\n");
        xml_.append("  var i = 8;\n");
        xml_.append("  return i>5?out1:out2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Byte $(ExClass i){\n");
        xml_.append("  return (byte)i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(1,getNumber(ret_));
    }
    @Test
    public void test35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Cl1:Int1 {\n");
        xml_.append(" public Cl1(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Cl2:Int1 {\n");
        xml_.append(" public Cl2(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int1 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Int1(int field) {\n");
        xml_.append("  this.field=field;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Int1 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  var out2 = new Cl1(1);\n");
        xml_.append("  var out1 = new Cl2(2);\n");
        xml_.append("  var i = 8;\n");
        xml_.append("  return i>5?out1:out2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Byte $(ExClass i){\n");
        xml_.append("  return (byte)i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(2,getNumber(ret_));
    }
    @Test
    public void test36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Cl1:Int1 {\n");
        xml_.append(" public Cl1(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Cl2:Int1 {\n");
        xml_.append(" public Cl2(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int1 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Int1(int field) {\n");
        xml_.append("  this.field=field;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Int1 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  var out2 = new Cl1(2);\n");
        xml_.append("  var out1 = new Cl2(1);\n");
        xml_.append("  var i = 8;\n");
        xml_.append("  return i<5?out1:out2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Byte $(ExClass i){\n");
        xml_.append("  return (byte)i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(2,getNumber(ret_));
    }
    @Test
    public void test37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Cl1:Int1 {\n");
        xml_.append(" public Cl1(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Cl2:Int1 {\n");
        xml_.append(" public Cl2(int field) {\n");
        xml_.append("  interfaces(Int1)(field);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.Int1 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public Int1(int field) {\n");
        xml_.append("  this.field=field;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Int1 i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  var out2 = new Cl1(1);\n");
        xml_.append("  var out1 = new Cl2(2);\n");
        xml_.append("  var i = 8;\n");
        xml_.append("  return i<5?out1:out2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Byte $(ExClass i){\n");
        xml_.append("  return (byte)i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(1,getNumber(ret_));
    }

    @Test
    public void test38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass o = explicit(int,$id)5;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass o = 2+3;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void test40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass o = 5;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  String p=\"\";\n");
        xml_.append("  ExClass o = null;\n");
        xml_.append("  return p+=o = explicit(int,$id)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return \"\"+field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }

    @Test
    public void test42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  String p=\"\";\n");
        xml_.append("  ExClass o = null;\n");
        xml_.append("  return p+=o = 2+3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return \"\"+field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }
    @Test
    public void test43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  String p=\"\";\n");
        xml_.append("  ExClass o = null;\n");
        xml_.append("  return p+=o = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public String $toString(){\n");
        xml_.append("  return \"\"+field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }

    @Test
    public void test44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = 2+3;\n");
        xml_.append("  return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> out = new ExClass<>();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  int sec = out;\n");
        xml_.append("  return sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }

    @Test
    public void test46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> out = new ExClass<>();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  int[] sec = {out};\n");
        xml_.append("  return sec[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }

    @Test
    public void test47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> out = new ExClass<>();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  int[] sec = new int[]{out};\n");
        xml_.append("  return sec[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }

    @Test
    public void test48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = static().$lambda(ExClass,call,ExClass).call(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * i.field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10,getNumber(ret_));
    }

    @Test
    public void test49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * i.field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10,getNumber(ret_));
    }

    @Test
    public void test50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * i[0].field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10,getNumber(ret_));
    }

    @Test
    public void test51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call(5,6);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * i[0].field + 2 * i[1].field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(22,getNumber(ret_));
    }

    @Test
    public void test52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call($id(ExClass,ExClass...),5,6);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * i[0].field + 2 * i[1].field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(22,getNumber(ret_));
    }

    @Test
    public void test53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call($id(ExClass,ExClass...),5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * i[0].field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10,getNumber(ret_));
    }

    @Test
    public void test54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass b,ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * b.field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10,getNumber(ret_));
    }

    @Test
    public void test55() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call($id(ExClass,ExClass,ExClass...),5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass b,ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * b.field;\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(10,getNumber(ret_));
    }
    @Test
    public void testFail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test2Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test3Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExSub method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:ExClass {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void test4Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return 2+3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.length();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test5Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExSub method(){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" private static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub:ExClass {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate_31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> e = new ExClass<int>();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(int)class(ExClass<int>).getDeclaredImplicits(class(int),class(ExClass<int>))[0].invoke(null,e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5,getNumber(ret_));
    }
    @Test
    public void calculate31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate31_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass<T> $(ExClass<T> i){\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate31____Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(null,class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass<T> $(ExClass<T> i){\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate31_____Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),null)[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass<T> $(ExClass<T> i){\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate31__Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculate31___Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass).getDeclaredImplicits(null,class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(int i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = (T)i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculate32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return class(ExClass).getDeclaredImplicits()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void calculate32_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null,5,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass<T> $(ExClass<T> i){\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculate32__Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass<T> $(ExClass<T> i){\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculate40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits()[0].invokeDirect(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void calculate41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits()[0].invokeDirect(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits()[0].invokeDirect(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass).getDeclaredImplicits()[0].invoke(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass).getDeclaredImplicits()[0].invokeDirect(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void calculateExTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  for (Class c: Class.getAllClasses()){\n");
        xml_.append("   if (c.getName().startsWith(\"pkg.ExClass\")){\n");
        xml_.append("    return $(ExClass<int>)c.getDeclaredImplicits()[0].invoke(null,5);\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculateEx2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredImplicits(class(ExClass<int>),class(int))[0].invoke(null,\"5\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculate26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  Fct<int,ExClass> fct = $lambda(ExClass,$,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate_27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  Fct<int,ExClass> fct = $lambda(ExClass,$,$id,ExClass,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate__27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Fct<ExClass,int> fct = $lambda(ExClass,$,$id,int,ExClass);\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return fct.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  Fct<int,ExClass> fct = $lambda(ExClass,$,$id,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate_28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  Fct<int,ExClass<int>> fct = $lambda(ExClass<int>,$,$id,ExClass<T>,T);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate__28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Fct<ExClass<int>,int> fct = $lambda(ExClass<int>,$,$id,T,ExClass<T>);\n");
        xml_.append("  ExClass<int> e = new ExClass<int>();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return fct.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate___28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply<S> {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(Apply<int>).method();\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int method(){\n");
        xml_.append("  Fct<ExClass<S>,S> fct = $lambda(ExClass<S>,$,$id,T,ExClass<T>);\n");
        xml_.append("  ExClass<S> e = new ExClass<>();\n");
        xml_.append("  e.field = (S)5;\n");
        xml_.append("  return (int)fct.call(e);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculate28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  Fct<int,ExClass<int>> fct = $lambda(ExClass<int>,$,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return new ExClassTwo<int>().method(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo<S> {\n");
        xml_.append(" public ExClass<S> method(S i){\n");
        xml_.append("  Fct<S,ExClass<S>> fct = $lambda(ExClass<S>,$,S);\n");
        xml_.append("  return fct.call(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  Fct<int,ExClass<int>> fct = $lambda(ExClass<int>,$,$id,#T);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  Fct<Object,String> fct = $lambda(String,$,Object);\n");
        xml_.append("  return fct.call(\"5\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }
    @Test
    public void calculate34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  Fct<Object,String> fct = $lambda(String,$,Object);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }

    @Test
    public void calculate35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,$,$id,ExClass<#T>).call(new ExClass<int>());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));

    }

    @Test
    public void calculate36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,$,$id,#T).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));

    }
    @Test
    public void calculate38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,$,$id,int).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));

    }
    @Test
    public void calculate42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,$,$id).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));

    }
    @Test
    public void calculate43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  Fct<CharSequence,String> fct = $lambda(String,$,CharSequence);\n");
        xml_.append("  return fct.call(\"5\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq("5", getString(ret_));
    }
    @Test
    public void calculate44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $lambda(ExClass<int>,$,$id).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateError("pkg.Apply", id_, args_, cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void calculate6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,$,$id,#T...,int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T... i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,$,#T...,int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T... i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return $lambda(String,$,Object,Object);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void test11_Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?> method(){\n");
        xml_.append("  return 2+3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void test12_Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<!int> out = new ExClass<int>();\n");
        xml_.append("  out.field = 5;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static T $(ExClass<T> i){\n");
        xml_.append("  return i.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

}
