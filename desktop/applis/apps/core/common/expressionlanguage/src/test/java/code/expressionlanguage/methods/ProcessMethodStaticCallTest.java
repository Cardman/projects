package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodStaticCallTest extends ProcessMethodCommon {
    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  return staticCall(ExClassTwo<T>).myfcttwo(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo<U> {\n");
        xml_.append(" public staticCall ExClass<U> myfcttwo(U i){\n");
        xml_.append("  ExClass<U> out = new ExClass<U>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  return myfcttwo(i);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExClass<T> myfcttwo(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int... i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i[0];\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T... i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i[0];\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct($vararg(int),$firstopt(5));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int... i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i[0];\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct($vararg(int),$firstopt(5));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T... i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i[0];\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct($vararg(int),$firstopt(5),9);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int... i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i[0]*i[1];\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(45, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct($vararg(int));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int... i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i.length+2;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(2, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct($id(ExClass,staticCall,int),5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate11_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct($id(ExClass,staticCall,0,int),5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct($id(ExClass,staticCall,T),5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  return staticCall(ExClassTwo<T>).myfcttwo($vararg(T),$firstopt(i));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo<U> {\n");
        xml_.append(" public staticCall ExClass<U> myfcttwo(U... i){\n");
        xml_.append("  ExClass<U> out = new ExClass<U>();\n");
        xml_.append("  out.field = i[0];\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  return staticCall().myfcttwo(i);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExClass<T> myfcttwo(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return new ExClass<int>().myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  ExClass<T> out = new ExClass<T>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return new ExClass<int>().myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public ExClass<T> myfct(T i){\n");
        xml_.append("  return staticCall(ExClassTwo<T>).myfcttwo(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo<U> {\n");
        xml_.append(" public staticCall ExClass<U> myfcttwo(U i){\n");
        xml_.append("  ExClass<U> out = new ExClass<U>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).superaccess(ExClass)myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).classchoice(ExClass)myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).thisaccess(ExClass)myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).that.myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExSubClass).super.myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSubClass:ExClass {\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExSubClass).super.myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSubClass:ExClass {\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i+9;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass<int> method(){\n");
        xml_.append("  return staticCall(ExClass<int>).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public T field;\n");
        xml_.append(" public staticCall ExClass<T> myfct(T i){\n");
        xml_.append("  return new ExClassTwo<T>(i).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo<U> {\n");
        xml_.append(" public ExClass<U> field;\n");
        xml_.append(" public ExClassTwo(U i){\n");
        xml_.append("  this(myfcttwo(i));\n");
        xml_.append(" }\n");
        xml_.append(" public ExClassTwo(ExClass<U> i){\n");
        xml_.append("  field = i;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExClass<U> myfcttwo(U i){\n");
        xml_.append("  ExClass<U> out = new ExClass<U>();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass<int>", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public static int myfct(){\n");
        xml_.append("  return staticCall(ExClassThree).myfcttwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.ExClassTwo {\n");
        xml_.append(" public staticCall int myfcttwo(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.ExClassThree:ExClassTwo {\n");
        xml_.append(" public staticCall int myfcttwo(){\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("myfct");
        Struct struct_ = calculateNormal("pkg.ExClass", id_, cont_);
        assertEq(1, ((IntStruct)struct_).intStruct());

    }

    @Test
    public void calculate25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  Fct<int,int> f = staticCall().$lambda(Ex,exmethtwo,int);\n");
        xml_.append("  return f.call(5);\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmethtwo(int p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return 1+(int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(14, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  Fct<int,int> f = staticCall().$lambda(Ex,exmethtwo,int);\n");
        xml_.append("  return f.call(5);\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall int exmethtwo(int p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return 1+(int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(14, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int,int> f = staticCall().$lambda(ExTwo<int>,exmethtwo,int);\n");
        xml_.append("  return f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(14, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo<int>).getDeclaredMethods()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(14, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate28_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo<int>).getDeclaredMethods()[0].invoke(null,5,8);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(cont_.getStandards().getCoreNames().getAliasBadArgNumber(), calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
    @Test
    public void calculate28__Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo<int>).getDeclaredMethods()[0].invoke(null,\"5,8\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(cont_.getStandards().getCoreNames().getAliasCastType(), calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
    @Test
    public void calculate29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo).getDeclaredMethods()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(cont_.getStandards().getCoreNames().getAliasIllegalType(), calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
    @Test
    public void calculate30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo<?>).getDeclaredMethods()[0].invoke(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(cont_.getStandards().getCoreNames().getAliasIllegalType(), calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
    @Test
    public void calculate31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo).getDeclaredMethods()[0].invokeDirect(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(cont_.getStandards().getCoreNames().getAliasIllegalType(), calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
    @Test
    public void calculate32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return (int)class(ExTwo<int>).getDeclaredMethods()[0].invokeDirect(null,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExTwo<T> {\n");
        xml_.append(" public staticCall T exmethtwo(T p){\n");
        xml_.append("  long t=8;\n");
        xml_.append("  return (T)(1+(int)t+(int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(14, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).that.myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSuperClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuperClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i+9;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSuperClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(long i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = (int)i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuperClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" private staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i+9;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate341Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(long i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = (int)i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public ExClass myfct(long i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = (int)i+2;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).thisaccess(ExClass)myfct(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass:ExSuperClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSuperClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i+9;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static ExClass method(){\n");
        xml_.append("  return staticCall(ExClass).myfct($id(ExClass,staticCall,long),5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public staticCall ExClass myfct(int i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = i+8;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append(" public staticCall ExClass myfct(long i){\n");
        xml_.append("  ExClass out = new ExClass();\n");
        xml_.append("  out.field = (int)i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.ExClass", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.ExClass", "field"))).intStruct());
    }
    @Test
    public void calculate37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static Apply method(){\n");
        xml_.append("  return staticCall().myfct(5);\n");
        xml_.append(" }\n");
        xml_.append(" public static Apply myfct(int i){\n");
        xml_.append("  Apply out = new Apply();\n");
        xml_.append("  out.field = i;\n");
        xml_.append("  return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        MethodId id_ = getMethodId("method");
        Struct struct_ = calculateNormal("pkg.Apply", id_, cont_);
        assertEq("pkg.Apply", struct_.getClassName(cont_));
        assertEq(5, ((IntStruct) getField(struct_, new ClassField("pkg.Apply", "field"))).intStruct());
    }
    @Test
    public void calculate1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExClass<ExClassTwo>).myfct();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T:ExClassTwo> {\n");
        xml_.append(" public staticCall int myfct(){\n");
        xml_.append("  return staticCall(T).myfcttwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClassTwo {\n");
        xml_.append(" public staticCall int myfcttwo(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly(files_));
    }
    @Test
    public void calculate2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExClass<?>).myfct();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public staticCall int myfct(){\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly(files_));
    }
    @Test
    public void calculate3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(ExClass<!Number>).myfct();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass<T> {\n");
        xml_.append(" public staticCall int myfct(){\n");
        xml_.append("  return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly(files_));
    }
    @Test
    public void calculate4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  return staticCall(Number[]).myfct();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly(files_));
    }
    @Test
    public void calculate5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public staticCall int myfct(){\n");
        xml_.append("  return staticCall(ExClassFour).myfcttwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.ExClassFour:ExClassTwo:ExClassThree {\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.ExClassTwo {\n");
        xml_.append(" public staticCall int myfcttwo(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public interface pkg.ExClassThree {\n");
        xml_.append(" public staticCall int myfcttwo(){\n");
        xml_.append("  return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly(files_));
    }
}
