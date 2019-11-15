package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodExplicitTest extends ProcessMethodCommon {
    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return explicit(int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return 8-(int)explicit(int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(3, ret_.getNumber());
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return explicit(int)(int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  String str = \"\";\n");
        xml_.append("  return explicit(int)str;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  return explicit(String)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  return explicit(String)null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNull(cont_.getException());
        assertTrue(ret_.isNull());
    }
    @Test
    public void calculate7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  String str = \"\";\n");
        xml_.append("  return explicit(String)str;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("", ret_.getString());
    }
    @Test
    public void calculate8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass,int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"int:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("int:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass,int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"int:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("int:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass,String)\"5\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"int:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("string:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass)\"5\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"int:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("string:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass)\"5\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(Object i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"obj:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("string:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(Object i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"obj:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("obj:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass)\"5\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public String field;\n");
        xml_.append(" public static ExClass explicit(Object i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"obj:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" private static ExClass explicit(String i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = \"string:\"+i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq("obj:5", ((StringStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).getInstance());
    }
    @Test
    public void calculate17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return explicit(ExClass<int>)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return explicit(ExClass<int>,#T)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?> method(){\n");
        xml_.append("  return explicit(ExClass<?>,#T)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?> method(){\n");
        xml_.append("  return explicit(ExClass<!Object>,#T)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?> method(){\n");
        xml_.append("  return explicit(ExClass<?>)new ExClass<int>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(0, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClassTwo method(){\n");
        xml_.append("  return explicit(ExClassTwo,ExClass)explicit(ExClass,int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClassTwo explicit(ExClass i){\n");
        xml_.append("  ExClassTwo out = new ExClassTwo();\n");
        xml_.append("  out.field = i.field;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClassTwo", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClassTwo","field"))).intStruct());
    }
    @Test
    public void calculate23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Apply..ExClass method(){\n");
        xml_.append("  return explicit(Apply..ExClass)5;\n");
        xml_.append(" }\n");
        xml_.append(" public class ExClass {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public static Apply..ExClass explicit(int i){\n");
        xml_.append("   Apply..ExClass out = new Apply().new ExClass();\n");
        xml_.append("   out.field = i;\n");
        xml_.append("   return out;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.Apply..ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.Apply..ExClass","field"))).intStruct());
    }
    @Test
    public void calculate24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Apply.ExClass method(){\n");
        xml_.append("  return explicit(Apply.ExClass)5;\n");
        xml_.append(" }\n");
        xml_.append(" public class ExClass {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public static Apply.ExClass explicit(int i){\n");
        xml_.append("   Apply.ExClass out = new Apply().new ExClass();\n");
        xml_.append("   out.field = i;\n");
        xml_.append("   return out;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefaultSingle();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.Apply..ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.Apply..ExClass","field"))).intStruct());
    }
    @Test
    public void calculate25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int[] method(){\n");
        xml_.append("  return explicit(int[])null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNull(cont_.getException());
        assertTrue(ret_.isNull());
    }
    @Test
    public void calculate26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  Fct<int,ExClass> fct = $lambda(ExClass,explicit,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  Fct<int,ExClass> fct = $lambda(ExClass,explicit,$id,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  Fct<int,ExClass<int>> fct = $lambda(ExClass<int>,explicit,int);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
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
        xml_.append("  Fct<S,ExClass<S>> fct = $lambda(ExClass<S>,explicit,S);\n");
        xml_.append("  return fct.call(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  Fct<int,ExClass<int>> fct = $lambda(ExClass<int>,explicit,$id,#T);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredMethods(\"explicit\",true,false,class(int))[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return class(ExClass).getDeclaredMethods()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  Fct<Object,String> fct = $lambda(String,explicit,Object);\n");
        xml_.append("  return fct.call(\"5\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("5", ret_.getString());
    }
    @Test
    public void calculate34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  Fct<Object,String> fct = $lambda(String,explicit,Object);\n");
        xml_.append("  return fct.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }

    @Test
    public void calculate35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,explicit,$id,ExClass<#T>).call(new ExClass<int>());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));

    }

    @Test
    public void calculate36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,explicit,$id,#T).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());

    }
    @Test
    public void calculate37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return new ExClass<int>().cast(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T cast(T i){\n");
        xml_.append("  return explicit(T)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }
    @Test
    public void calculate38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,explicit,$id,int).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());

    }
    @Test
    public void calculate39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return explicit(ExClass,$id)explicit(ExClass,int)5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredMethods()[0].invokeDirect(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass<int>).getDeclaredMethods()[0].invokeDirect(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<?int> method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,explicit,$id).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());

    }
    @Test
    public void calculate43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static String method(){\n");
        xml_.append("  Fct<CharSequence,String> fct = $lambda(String,explicit,CharSequence);\n");
        xml_.append("  return fct.call(\"5\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq("5", ret_.getString());
    }
    @Test
    public void calculate44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return $lambda(ExClass<int>,explicit,$id).call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculate45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass).getDeclaredMethods()[0].invokeDirect(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  ExClass<int> o = new ExClass<int>();\n");
        xml_.append("  o.field = 5;\n");
        xml_.append("  return $(ExClass<int>)class(ExClass).getDeclaredMethods()[0].invoke(null,o);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        calculateArgument("pkg.Apply", id_, args_, cont_);
        assertNotNull(cont_.getException());
    }
    @Test
    public void calculateCastTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass explicit(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(new IntStruct(5)));
        MethodId id_ = getMethodId("explicit","int");
        Argument ret_ = ProcessMethod.castArgument("pkg.ExClass",id_,args_,cont_);
        Struct struct_ = ret_.getStruct();
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct)((FieldableStruct)struct_).getStruct(new ClassField("pkg.ExClass","field"))).intStruct());
    }
    @Test
    public void calculate1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return explicit(int)null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int explicit(int i){\n");
        xml_.append("  return i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public Apply explicit(int i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Apply explicit(int... i){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Apply explicit(){\n");
        xml_.append("  return null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,explicit,$id,#T...,int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T... i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return $lambda(ExClass<?int>,explicit,#T...,int);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public static ExClass<T> explicit(T... i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return explicit(int,int,int)4;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
    @Test
    public void calculate9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static Object method(){\n");
        xml_.append("  return $lambda(String,explicit,Object,Object);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
}
