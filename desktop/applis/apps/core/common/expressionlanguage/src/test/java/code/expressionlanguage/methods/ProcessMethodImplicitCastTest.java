package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
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
    public void test56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = ExClass.call($id(ExClass,ExClass,ExClass...),5, new ExClass[1]);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass call(ExClass b,ExClass... i){\n");
        xml_.append("  ExClass d = new ExClass();\n");
        xml_.append("  d.field = 2 * b.field + 2 * i.length;\n");
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
        assertEq(12,getNumber(ret_));
    }

    @Test
    public void test57() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> sec = staticCall().$lambda(ExClass<int>,call,ExClass<int>).call(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> call(ExClass<T> i){\n");
        xml_.append("  ExClass<T> d = new ExClass<>();\n");
        xml_.append("  d.field = (T)(2 * (int)i.field);\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> e = new ExClass<>();\n");
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
    public void test58() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> sec = staticCall(ExCaller<int>).call(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<T> {\n");
        xml_.append(" public staticCall ExClass<T> call(T i){\n");
        xml_.append("  return call2(i);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExClass<T> call2(ExClass<T> i){\n");
        xml_.append("  ExClass<T> d = new ExClass<>();\n");
        xml_.append("  d.field = (T)(2 * (int)i.field);\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<S> {\n");
        xml_.append(" public S field;\n");
        xml_.append(" public static ExClass<S> $(S i){\n");
        xml_.append("  ExClass<S> e = new ExClass<>();\n");
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
    public void test59() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> fir = new ExClass<>();\n");
        xml_.append("  fir.field = 5;\n");
        xml_.append("  ExClass<int> sec = staticCall(ExCaller<int>).call2(fir);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<T> {\n");
        xml_.append(" public staticCall ExClass<T> call(T i){\n");
        xml_.append("  ExClass<T> d = new ExClass<>();\n");
        xml_.append("  d.field = (T)(2 * (int)i);\n");
        xml_.append("  return d;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExClass<T> call2(ExClass<T> i){\n");
        xml_.append("  return call(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<S> {\n");
        xml_.append(" public S field;\n");
        xml_.append(" public static S $(ExClass<S> i){\n");
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
        assertEq(10,getNumber(ret_));
    }

    @Test
    public void test60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass ex = new ExClass();\n");
        xml_.append("  return ex??=5;\n");
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
        assertEq(0, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }

    @Test
    public void test61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  ExClass ex = null;\n");
        xml_.append("  return ex??=5;\n");
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
    public void test62() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = new ExClass(5);\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public ExClass(ExClass i){\n");
        xml_.append("  field = 2 * i.field;\n");
        xml_.append(" }\n");
        xml_.append(" public ExClass(){\n");
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
    public void test63() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = new ExClass();\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  sec+=10;\n");
        xml_.append("  return sec.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ int(ExClass i, int j){\n");
        xml_.append("  return Math.plus(i.field,j);\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
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
        assertEq(15,getNumber(ret_));
    }

    @Test
    public void test64() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExContainer sec = new ExContainer();\n");
        xml_.append("  sec[0].field=5;\n");
        xml_.append("  sec[0]+=10;\n");
        xml_.append("  return sec[0].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExContainer {\n");
        xml_.append(" public ExClass[] e = {new ExClass()};\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  e[i]=value;\n");
        xml_.append(" }\n");
        xml_.append(" public ExClass this(int i){\n");
        xml_.append("  return e[i];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ int(ExClass i, int j){\n");
        xml_.append("  return Math.plus(i.field,j);\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
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
        assertEq(15,getNumber(ret_));
    }

    @Test
    public void test65() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = new ExClass();\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExClass e = sec+=10;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ int(ExClass i, int j){\n");
        xml_.append("  return Math.plus(i.field,j);\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
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
        assertEq(15,getNumber(ret_));
    }

    @Test
    public void test66() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = new ExClass();\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  int e = sec+=10;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ int(ExClass i, int j){\n");
        xml_.append("  return Math.plus(i.field,j);\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
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
        assertEq(15,getNumber(ret_));
    }

    @Test
    public void test67() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = null;\n");
        xml_.append("  int v = sec+=10;\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ int(ExClass i, int j){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return 10;\n");
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
    public void test68() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Apply sec = null;\n");
        xml_.append("  int v = sec+=10;\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Apply i){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  ExClass.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSup {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ int(Apply i, int j){\n");
        xml_.append("  return 5;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSup {\n");
        xml_.append(" public static int $(ExSup i){\n");
        xml_.append("  return 10;\n");
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
    public void test69() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Apply sec = null;\n");
        xml_.append("  ExOut v = sec+=10;\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOut $(Apply i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  ExClass.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOut:ExClass {\n");
        xml_.append(" public static int $(ExOut i){\n");
        xml_.append("  return 10;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(ExOut i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSup {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExOut(Apply i, int j){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSup {\n");
        xml_.append(" public static int $(ExSup i){\n");
        xml_.append("  return 10;\n");
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
    public void test70() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Apply sec = null;\n");
        xml_.append("  ExOut v = sec+=10;\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  ExClass.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOut:ExClass {\n");
        xml_.append(" public static int $(ExOut i){\n");
        xml_.append("  return 10;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(ExOut i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOut $(Apply i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSup {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExOut(Apply i, Apply j){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSup {\n");
        xml_.append(" public static int $(ExSup i){\n");
        xml_.append("  return 10;\n");
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
    public void test71() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Apply sec = null;\n");
        xml_.append("  int v = sec+=10;\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Apply i){\n");
        xml_.append("  return 10;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  ExClass.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOut:ExClass {\n");
        xml_.append(" public static int $(ExOut i){\n");
        xml_.append("  return 10;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(ExOut i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSup {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExOut(Apply i, int j){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSup {\n");
        xml_.append(" public static int $(ExSup i){\n");
        xml_.append("  return 10;\n");
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
    public void test72() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Apply sec = null;\n");
        xml_.append("  int v = sec+=10;\n");
        xml_.append("  return v;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" public static int $(Apply i){\n");
        xml_.append("  return 10;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  ExClass.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOut:ExClass {\n");
        xml_.append(" public static int $(ExOut i){\n");
        xml_.append("  return 10;\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply $(ExOut i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSup {\n");
        xml_.append(" public static int v;\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExOut(Apply i, Apply j){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append(" static{\n");
        xml_.append("  Apply.v++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSup {\n");
        xml_.append(" public static int $(ExSup i){\n");
        xml_.append("  return 10;\n");
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
    public void test73() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass sec = new ExClass();\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = sec+=10;\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExClass i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
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
        assertEq(15,getNumber(ret_));
    }

    @Test
    public void test74() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExContainer sec = new ExContainer();\n");
        xml_.append("  sec[0].field=5;\n");
        xml_.append("  ExSub2 e = sec[0]+=10;\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExContainer {\n");
        xml_.append(" public ExClass[] a = {new ExClass()};\n");
        xml_.append(" public ExClass this(int i){\n");
        xml_.append("  return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  a[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExClass i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
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
        assertEq(15,getNumber(ret_));
    }
    @Test
    public void test75() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExContainer sec = new ExContainer();\n");
        xml_.append("  sec[0].field=5;\n");
        xml_.append("  ExSub2 e = sec[0]+=10;\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExContainer {\n");
        xml_.append(" public ExOhter[] a = {new ExOhter()};\n");
        xml_.append(" public ExOhter this(int i){\n");
        xml_.append("  return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  a[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOhter {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExOhter i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOhter $(ExSub i){\n");
        xml_.append("  ExOhter e = new ExOhter();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExOhter i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
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
        assertEq(15,getNumber(ret_));
    }
    @Test
    public void test76() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExOhter sec = new ExOhter();\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = sec+=10;\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExContainer {\n");
        xml_.append(" public ExOhter[] a = {new ExOhter()};\n");
        xml_.append(" public ExOhter this(int i){\n");
        xml_.append("  return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int i){\n");
        xml_.append("  a[i]=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOhter {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExOhter i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOhter $(ExSub i){\n");
        xml_.append("  ExOhter e = new ExOhter();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExOhter i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
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
        assertEq(15,getNumber(ret_));
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
    public void calculate45_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Fct<ExClass,boolean> fct = $lambda(ExClass,true);\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=1;\n");
        xml_.append("  return fct.call(e)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate46_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Fct<ExClass,boolean> fct = $lambda(ExClass,false);\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=5;\n");
        xml_.append("  return fct.call(e)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static boolean true(ExClass i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return i.field!=1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculate47_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> e = new ExClass<int>();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(boolean)class(ExClass<int>).getDeclaredTrueOperators()[0].invoke(null,e)?5:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static boolean true(ExClass<T> i){\n");
        xml_.append("  return i.field==5;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass<T> i){\n");
        xml_.append("  return i.field!=5;\n");
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
    public void calculate48_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> e = new ExClass<int>();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(boolean)class(ExClass<int>).getDeclaredFalseOperators()[0].invoke(null,e)?5:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static boolean true(ExClass<T> i){\n");
        xml_.append("  return i.field==1;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass<T> i){\n");
        xml_.append("  return i.field!=1;\n");
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

    @Test
    public void calculate__9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(ExClass,int,ExClass)e;\n");
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
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return $(ExClass,int)5;\n");
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
    public void calculate9_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return $(ExClass,String)5;\n");
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
        calculateError("pkg.Apply", id_, args_, cont_);
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return $(ExClass,int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"int:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
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
        assertEq("int:5", ((StringStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).getInstance());
    }
    @Test
    public void calculate_11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=\"5\";\n");
        xml_.append("  return $(ExClass,String,ExClass)e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return Integer.parseInt(i.field);\n");
        xml_.append(" }\n");
        xml_.append(" public static String $(ExClass i){\n");
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
        assertEq("5", getString(ret_));
    }
    @Test
    public void calculate__11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=\"5\";\n");
        xml_.append("  return $(ExClass,int,ExClass)e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static int $(ExClass i){\n");
        xml_.append("  return Integer.parseInt(i.field);\n");
        xml_.append(" }\n");
        xml_.append(" public static String $(ExClass i){\n");
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
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return $(ExClass,String)\"5\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass $(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"int:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass $(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
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
        assertEq("string:5", ((StringStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).getInstance());
    }
    @Test
    public void calculate_18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass<int> e = new ExClass<int>();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(ExClass<int>,T,ExClass<T>)e;\n");
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
    public void calculate__18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExCaller<int>).method();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<S> {\n");
        xml_.append(" public staticCall S method(){\n");
        xml_.append("  ExClass<S> e = new ExClass<S>();\n");
        xml_.append("  e.field = (S)5;\n");
        xml_.append("  return $(ExClass<S>,T,ExClass<T>)e;\n");
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
    public void calculate___18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>,ExClass<T>,T)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> e = new ExClass<T>();\n");
        xml_.append("  e.field = i;\n");
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
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate____18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExCaller<int>).method();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExCaller<S> {\n");
        xml_.append(" public staticCall ExClass<S> method(){\n");
        xml_.append("  return $(ExClass<S>,ExClass<T>,T)(S)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> $(T i){\n");
        xml_.append("  ExClass<T> e = new ExClass<T>();\n");
        xml_.append("  e.field = i;\n");
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
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>,#T)5;\n");
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
    public void calculate19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?> method(){\n");
        xml_.append("  return $(ExClass<?>,#T)5;\n");
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
    public void calculate20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?> method(){\n");
        xml_.append("  return $(ExClass<!Object>,#T)5;\n");
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
    public void calculate22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClassTwo method(){\n");
        xml_.append("  return $(ExClassTwo,ExClass)$(ExClass,int)5;\n");
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
        xml_.append("public class pkg.ExClassTwo {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClassTwo $(ExClass i){\n");
        xml_.append("  ExClassTwo out = new ExClassTwo();\n");
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
        assertEq("pkg.ExClassTwo", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClassTwo", "field"))).intStruct());
    }
    @Test
    public void calculate39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return $(ExClass,$id)$(ExClass,int)5;\n");
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
    public void calculate_10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(int,int,ExClass)e;\n");
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
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate_11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(int,ExClass)e;\n");
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
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate_12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field = 5;\n");
        xml_.append("  return $(ExClass,int,int)e;\n");
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
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculate13Fail_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  Fct<ExClass,boolean> fct = $lambda(ExClass,false);\n");
        xml_.append("  Fct<ExClass,boolean> fct2 = $lambda(ExClass,true);\n");
        xml_.append("  Fct<ExSuperWild<?>,boolean> fct3 = $lambda(ExSuperWild<?>,false);\n");
        xml_.append("  Fct<ExSuperWild<?>,boolean> fct4 = $lambda(ExSuperWild<?>,true);\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=5;\n");
        xml_.append("  ExSuper2 f=null;\n");
        xml_.append("  ExSuper2 g=null;\n");
        xml_.append("  f&&g;\n");
        xml_.append("  return fct.call(e)?1:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSuper {\n");
        xml_.append(" public int field;\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuper {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static boolean false(ExSuper e){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExSuper e){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuper2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static boolean false(ExSuper2 e){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExSuper2 e){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" operator&& boolean(ExSuper2 e,ExSuper2 f){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuperWild<T> {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static boolean false(ExSuperWild<T> e){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExSuperWild<T> e){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass2 {\n");
        xml_.append(" public static boolean false(){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public static int false(int i){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass i){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean false(ExClass2... i){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public boolean true(ExClass2... i){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append(" public static boolean true(ExClass2... i){\n");
        xml_.append("  return false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
}
