package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ErroneousStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodReferenceTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,get);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return $($int) f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call(instance).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number,java.lang.Number> f = $lambda(Ex<java.lang.Number>,sumWith,java.lang.Number);\n");
        xml_.append("  $return f.call(instance,6i).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T sumWith(T other){\n");
        xml_.append("  $return $(T)(($(java.lang.Number)field).intValue()+($(java.lang.Number)other).intValue());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<java.lang.Number> f = instance.$lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call().intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  Ex<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $return $(S)($($int)field+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $return $(T)(2i*$($int)field+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  Ex<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $return $(S)($($int)field+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $return $(T)(2i*$($int)field+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<S,S> f = sub.$lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call($(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<ExTwo<S>,S,S> f = $lambda(ExTwo<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric($class(java.lang.Number),$class($int)).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$Fct<java.lang.Number,$int>", getString(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<$int,$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int,$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<Ex,$int,$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<Ex,$int,$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<Ex,$int,$int> g = $lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new Ex(),5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,$that,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,$classchoice,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(ExTwo<S>,$super,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<ExTwo<S>,S,S> f = $lambda(ExTwo<S>,$super,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }

    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,$that,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,$classchoice,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i;\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return superfield;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,$super,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }

    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i;\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return superfield;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(ExTwo,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i;\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(ExTwo,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $lambda(Ex,$new);\n");
        xml_.append("  Ex instance = c.call();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex,Ex.Inner> c = $lambda(Ex.Inner,$new);\n");
        xml_.append("  Ex.Inner instance = c.call($new Ex());\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex.Inner> c = $new Ex().$lambda(Inner,$new);\n");
        xml_.append("  Ex.Inner instance = c.call();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex.Inner> c = $new Ex().$lambda(Inner,$new,$int);\n");
        xml_.append("  Ex.Inner instance = c.call(14i);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner<T> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex.Inner<java.lang.String>> c = $new Ex().$lambda(Inner<java.lang.String>,$new,$int);\n");
        xml_.append("  Ex.Inner<java.lang.String> instance = c.call(14i);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,java.lang.String[]> c = $lambda(java.lang.String[],$new,$int);\n");
        xml_.append("  java.lang.String[] instance = c.call(1i);\n");
        xml_.append("  $return instance.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,Ex<java.lang.String>.Inner> c = $new Ex<java.lang.String>().$lambda(Inner,$new,$int);\n");
        xml_.append("  Ex<java.lang.String>.Inner instance = c.call(14i);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  $Fct<$int,Ex<T>.Inner<T>> c = $this.$lambda(Inner<T>,$new,$int);\n");
        xml_.append("  Ex<T>.Inner<T> instance = c.call(14i);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call2(T p){\n");
        xml_.append("  $return call(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call2(\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  $Fct<$int,Ex<T>.Inner<T>> c = $this.$lambda(Inner<T>,$new,$int);\n");
        xml_.append("  Ex<T>.Inner<T> instance = c.call(14i);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call2(T p){\n");
        xml_.append("  $return (call(p));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call2(\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  $Fct<$int,Ex<T>.Inner<T>> c = $this.$lambda(Inner<T>,$new,$int);\n");
        xml_.append("  Ex<T>.Inner<T> instance = c.call(14i);\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  $Fct<$int,T[]> c = $lambda(T[],$new,$int);\n");
        xml_.append("  T[] instance = c.call(1i);\n");
        xml_.append("  instance[0i]=p;\n");
        xml_.append("  $return instance[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method f = $class($Fct).makeGeneric($class($int),$class($int)).getDeclaredMethods()[0i];\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $($int) f.invoke(g,$(java.lang.Object)$new java.lang.Object[]{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Method,java.lang.Object,java.lang.Object[],java.lang.Object> f = $lambda($Method,invoke,java.lang.Object,java.lang.Object...);\n");
        xml_.append("  $Method g = $class(Ex).getDeclaredMethods(\"exmethtwo\",$true,$false,$class($int))[0i];\n");
        xml_.append("  $return $($int) f.call(g,$null,$new java.lang.Object[]{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],T[]> c = $lambda(T[],clone);\n");
        xml_.append("  T[] instance = c.call(a);\n");
        xml_.append("  instance[0i]=p;\n");
        xml_.append("  $return instance[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  $Fct<T[]> c = $new T[1i].$lambda(T[],clone);\n");
        xml_.append("  T[] instance = c.call();\n");
        xml_.append("  instance[0i]=p;\n");
        xml_.append("  $return instance[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = (ExTwo<S>)$new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int[],$int> f = $static().$lambda(Ex,exmethtwo,$int[]);\n");
        xml_.append("  $return f.call($new $int[]{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int[] p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[] exmeththree($int p){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[][] exmeththree($int[][] p){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int[][] exmeththree($int[][]... p){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int[]>[] exmeththree(ExTwo<$int[]>[] p){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static ExTwo<$int[]> exmeththree(ExTwo<$int[]> p){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  java.lang.$Fct<$void> f = $static().$lambda(Ex,exmethtwo);\n");
        xml_.append("  $if (f.call() == $null){\n");
        xml_.append("   $return 14i;\n");
        xml_.append("  }\n");
        xml_.append("  $return -14i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmethtwo(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<java.lang.Object> f = $static().$lambda(Ex,exmethtwo);\n");
        xml_.append("  $if (f.call() == $null){\n");
        xml_.append("   $return 14i;\n");
        xml_.append("  }\n");
        xml_.append("  $return -14i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmethtwo(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,,field);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int,$int> f = $lambda(Ex,,field,$int);\n");
        xml_.append("  $int res = f.call(instance,18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = instance.$lambda(Ex,,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $normal T get(){\n");
        xml_.append("  ExTwo<T> inner = $new ExTwo<T>();\n");
        xml_.append("  inner.field = (T)14i;\n");
        xml_.append("  $Fct<ExTwo<T>,T> f = $lambda(ExTwo<T>,,field);\n");
        xml_.append("  $return f.call(inner);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Integer> instance = $new ExTwo<java.lang.Integer>();\n");
        xml_.append("  $return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number,S:T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $normal T get(S p){\n");
        xml_.append("  ExTwo<T,S> inner = $new ExTwo<T,S>();\n");
        xml_.append("  inner.field = p;\n");
        xml_.append("  $Fct<ExTwo<T,S>,S,T> f = $lambda(ExTwo<T,S>,,field,S);\n");
        xml_.append("  T res = f.call(inner,p);\n");
        xml_.append("  $return (T) (res.intValue()+inner.field.intValue());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Integer,java.lang.Integer> instance = $new ExTwo<java.lang.Integer,java.lang.Integer>();\n");
        xml_.append("  $return instance.get(14i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,,$classchoice,field);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int,$int> f = $lambda(Ex,,$classchoice,field,$int);\n");
        xml_.append("  $int res = f.call(instance,18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,,$classchoice,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = instance.$lambda(Ex,,$classchoice,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,,$superaccess,field);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int,$int> f = $lambda(Ex,,$superaccess,field,$int);\n");
        xml_.append("  $int res = f.call(instance,18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,,$superaccess,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = instance.$lambda(Ex,,$superaccess,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $static $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static.field=14i;\n");
        xml_.append("  $Fct<$int> f = $static().$lambda(Static,,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $static $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Static,,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + Static.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $static $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static.field=14i;\n");
        xml_.append("  $Fct<$int> f = $lambda(Static,,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $static $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = $lambda(Static,,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + Static.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,+,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,+,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument70_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,+,$id,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument70__Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$operator+ pkg.Static(pkg.Static a) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,+,$id,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument71_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,+,$id,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument73_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static... b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b[0].field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = $lambda($operator,+,Static,Static...);\n");
        xml_.append("  $return f.call(s,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument74_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static... b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b[0].field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static[],Static> f = s.$lambda($operator,+,Static...);\n");
        xml_.append("  $return f.call($new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument76_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static c,pkg.Static... b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + c.field +b[0].field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  Static u = $new Static();\n");
        xml_.append("  u.field=4i;\n");
        xml_.append("  $Fct<Static,Static[],Static> f = s.$lambda($operator,+,Static,Static...);\n");
        xml_.append("  $return f.call(u,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument__FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static c,pkg.Static... b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + c.field +b[0].field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.I1:I3:I4 {}\n");
        xml_.append("$public $interface pkg.I2:I3:I4 {}\n");
        xml_.append("$public $interface pkg.I3 {}\n");
        xml_.append("$public $interface pkg.I4 {}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  Static u = $new Static();\n");
        xml_.append("  u.field=4i;\n");
        xml_.append("  ($true?$(I1)$null:$(I2)$null).$lambda($operator,+);\n");
        xml_.append("  $Fct<Static,Static[],Static> f = s.$lambda($operator,+);\n");
        xml_.append("  $return f.call(u,$new Static[]{t}).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,$classchoice,transform,$id,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument72_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,$classchoice,transform,$id,0,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex<java.lang.Number> instance = $new Ex<java.lang.Number>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,java.lang.Number,java.lang.Number> f = $lambda(Ex<java.lang.Number>,sumWith,$id,T);\n");
        xml_.append("  $return f.call(instance,6i).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T sumWith(T other){\n");
        xml_.append("  $return $(T)(($(java.lang.Number)field).intValue()+($(java.lang.Number)other).intValue());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<S,S> f = sub.$lambda(Ex<S>,transform,$id,S);\n");
        xml_.append("  $return f.call($(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $Fct<java.lang.Number,java.lang.Number> f = sub.$lambda(Ex<java.lang.Number>,transform,$id,S);\n");
        xml_.append("  $return f.call(5i).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument75_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $Fct<java.lang.Number,java.lang.Number> f = sub.$lambda(Ex<java.lang.Number>,transform,$id,0,S);\n");
        xml_.append("  $return f.call(5i).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(S)($($int)t+$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(26, getNumber(ret_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public normal int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<Ex,Ex.Inner> c = $lambda(Ex.Inner,new);\n");
        xml_.append("  Ex.Inner instance = c.call(new Ex());\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public normal int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<Ex.Inner> c = new Ex().$lambda(Inner,new);\n");
        xml_.append("  Ex.Inner instance = c.call();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public(int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  public normal int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int,Ex.Inner> c = new Ex().$lambda(Inner,new,int);\n");
        xml_.append("  Ex.Inner instance = c.call(14i);\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner<T> {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public(int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  public normal int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int,Ex.Inner<String>> c = new Ex().$lambda(Inner<String>,new,int);\n");
        xml_.append("  Ex.Inner<String> instance = c.call(14i);\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $ObjectsUtil.getParent(f);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());

    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $ObjectsUtil.getParent(f);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());

    }

    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $lambda(Ex,$new);\n");
        xml_.append("  $return $ObjectsUtil.getParent(c);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());

    }

    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $lambda(Ex,$new);\n");
        xml_.append("  $return $ObjectsUtil.getParent(c);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());

    }

    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> c = $lambda(Ex,,field);\n");
        xml_.append("  $return $ObjectsUtil.getParent(c);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());

    }

    @Test
    public void calculateArgument85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $Fct<$int,$int> g = $static().$lambda(Ex,exmeththree,$int);\n");
        xml_.append("  $return f == g;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }

    @Test
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f == f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }

    @Test
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Ex(){\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $lambda(Ex,$new);\n");
        xml_.append("  $Fct<$int,Ex> d = $lambda(Ex,$new,$int);\n");
        xml_.append("  $return c == d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }

    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Fct<Ex> c = $lambda(Ex,$new);\n");
        xml_.append("  $return c == c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }

    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $int fieldTwo;\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> c = $lambda(Ex,,field);\n");
        xml_.append("  $Fct<Ex,$int> d = $lambda(Ex,,fieldTwo);\n");
        xml_.append("  $return c == d;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }

    @Test
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $Fct<Ex,$int> c = $lambda(Ex,,field);\n");
        xml_.append("  $return c == c;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }

    @Test
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $null;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.Number> instance = $null;\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $null;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,,field);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $null;\n");
        xml_.append("  $Fct<Ex,$int,$int> f = $lambda(Ex,,field,$int);\n");
        xml_.append("  $int res = f.call(instance,18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }

    @Test
    public void calculateArgument95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,java.lang.String[]> c = $lambda(java.lang.String[],$new,$int);\n");
        xml_.append("  java.lang.String[] instance = c.call(-1i);\n");
        xml_.append("  $return instance.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        Struct exc_ = arg_.getStruct();
        assertEq("code.expressionlanguage.exceptions.NegativeSizeException", exc_.getClassName(cont_));
        assertEq("-1<0", ((StringStruct)((ErroneousStruct)exc_).getMessage()).getInstance());
    }
    @Test
    public void calculateArgument106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $lambda(Ex,get,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $lambda(Ex,get,$id,$static,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument108_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $lambda(Ex,get,$id,$static,0,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $new Ex().$lambda(Ex,get,$id,$static,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument109_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $new Ex().$lambda(Ex,get,$id,$static,0,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,get,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,get,$id,Ex,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument111_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,get,$id,Ex,0,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument112_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static class Inner {\n");
        xml_.append("  public static int exmeth(){\n");
        xml_.append("   Fct<Integer,Integer> f = static().$lambda(Ex.Inner,get,$id,Ex,1,Integer);\n");
        xml_.append("   return f.call(14);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static Integer get(Integer p){\n");
        xml_.append("  return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex..Inner", id_, args_, cont_);
        assertEq(28, getNumber(ret_));
    }
    @Test
    public void calculateArgument112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,get,$id);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S:java.lang.Number> {\n");
        xml_.append(" $public S field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExTwo<java.lang.Number> sub = $new ExTwo<java.lang.Number>();\n");
        xml_.append("  $return sub.exmethtwo($null).intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S exmethtwo(S p){\n");
        xml_.append("  ExTwo<S> sub = $new ExTwo<S>();\n");
        xml_.append("  sub.field=$(S)2i;\n");
        xml_.append("  $Fct<Ex<S>,S,S> f = $lambda(Ex<S>,$superaccess,transform,S);\n");
        xml_.append("  $return f.call(sub,$(S)5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal S transform(S p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<S,$int> f = $lambda(S,intValue);\n");
        xml_.append("  $return $(S)($($int)t+f.call(p));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number>:Ex<T> {\n");
        xml_.append(" $public $normal T transform(T p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return $(T)(2i*$($int)t+2i*$($int)p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(13, getNumber(ret_));
    }

    @Test
    public void calculateArgument114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,$superaccess,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,get,$id);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex<Integer>().inner();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  Ex<T> instance = $new Ex<T>();\n");
        xml_.append("  instance.field=(T)14i;\n");
        xml_.append("  $Fct<Ex<T>,T> f = $lambda(Ex<T>,,field);\n");
        xml_.append("  $return ($int)f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex<Integer>().inner();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  Ex<T> instance = $new Ex<T>();\n");
        xml_.append("  instance.field=(T)14i;\n");
        xml_.append("  $Fct<T> f = instance.$lambda(Ex<T>,,field);\n");
        xml_.append("  $return ($int)f.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,,$that,field);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int,$int> f = $lambda(Ex,,$that,field,$int);\n");
        xml_.append("  $int res = f.call(instance,18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,,$that,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = instance.$lambda(Ex,,$that,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(36, getNumber(ret_));
    }
    @Test
    public void calculateArgument122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return ($int)f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<?,?> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return ($int)f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<?,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,?> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return ($int)f.call(5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument126Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return ($int)f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadArgNumber(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument127Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return ($int)f.call(5i,6i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadArgNumber(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument128Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],$int,T> c = $lambda(T[],[],$int);\n");
        xml_.append("  a[0i]=p;\n");
        xml_.append("  T instance = c.call(a,0);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],$int,T,T> c = $lambda(T[],[]=,$int);\n");
        xml_.append("  T instance = c.call(a,0,p);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<$int,T> c = a.$lambda(T[],[],$int);\n");
        xml_.append("  a[0i]=p;\n");
        xml_.append("  T instance = c.call(0);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<$int,T,T> c = a.$lambda(T[],[]=,$int);\n");
        xml_.append("  T instance = c.call(0,p);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument132Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],$int,T,T> c = $lambda(T[],[]=,$int);\n");
        xml_.append("  c.call(a,0,p);\n");
        xml_.append("  $return a[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument133Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<$int,T,T> c = a.$lambda(T[],[]=,$int);\n");
        xml_.append("  c.call(0,p);\n");
        xml_.append("  $return a[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<T[][],$int,$int,T> c = $lambda(T[][],[],$int,$int);\n");
        xml_.append("  a[0i][0i]=p;\n");
        xml_.append("  T instance = c.call(a,0,0);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<T[][],$int,$int,T,T> c = $lambda(T[][],[]=,$int,$int);\n");
        xml_.append("  T instance = c.call(a,0,0,p);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument136Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<$int,$int,T> c = a.$lambda(T[][],[],$int,$int);\n");
        xml_.append("  a[0i][0i]=p;\n");
        xml_.append("  T instance = c.call(0,0);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<$int,$int,T,T> c = a.$lambda(T[][],[]=,$int,$int);\n");
        xml_.append("  T instance = c.call(0,0,p);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<T[][],$int,$int,T,T> c = $lambda(T[][],[]=,$int,$int);\n");
        xml_.append("  c.call(a,0,0,p);\n");
        xml_.append("  $return a[0][0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<$int,$int,T,T> c = a.$lambda(T[][],[]=,$int,$int);\n");
        xml_.append("  c.call(0,0,p);\n");
        xml_.append("  $return a[0][0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sample", getString(ret_));
    }
    @Test
    public void calculateArgument140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<$int,$int,T> c = a.$lambda(T[][],[],$int,$int);\n");
        xml_.append("  a[0i][0i]=p;\n");
        xml_.append("  T instance = c.call(0,-1);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $Fct<$int,$int,T> c = a.$lambda(T[][],[],$int,$int);\n");
        xml_.append("  a[0i][0i]=p;\n");
        xml_.append("  T instance = c.call(-1,0);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument142Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],$int> c = $lambda(T[],[]);\n");
        xml_.append("  a[0i]=p;\n");
        xml_.append("  $int instance = c.call(a);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument143Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<$int> c = a.$lambda(T[],[]);\n");
        xml_.append("  a[0i]=p;\n");
        xml_.append("  $int instance = c.call();\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument144Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  T[] a = $null;\n");
        xml_.append("  $Fct<T[],$int> c = $lambda(T[],[]);\n");
        xml_.append("  $int instance = c.call(a);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument145Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int call(T p){\n");
        xml_.append("  T[] a = $null;\n");
        xml_.append("  $Fct<$int> c = a.$lambda(T[],[]);\n");
        xml_.append("  $int instance = c.call();\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }

    @Test
    public void calculateArgument146Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int[],$int> f = $static().$lambda(Ex,exmethtwo,$int,$int...);\n");
        xml_.append("  $return f.call(5i,$new $int[]{9});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int q,$int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+q+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }

    @Test
    public void calculateArgument147Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int[],$int> f = $static().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return f.call($new $int[]{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument148Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int[],$int> f = $static().$lambda(Ex,exmethtwo,$int,$int);\n");
        xml_.append("  $return f.call($new $int[]{5,9});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p[0]+p[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(String... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p[0].length()+p[1].length();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }

    @Test
    public void calculateArgument149Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int[],$int> f = $static().$lambda(Ex,exmethtwo,$int[]);\n");
        xml_.append("  $return f.call($new $int[]{5,9});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p[0]+p[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(23, getNumber(ret_));
    }

    @Test
    public void calculateArgument150Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<Object[],$int> f = $static().$lambda(Ex,exmethtwo,$id,Ex,Object...);\n");
        xml_.append("  $return f.call($new $int[]{5,9});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Object... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(11, getNumber(ret_));
    }

    @Test
    public void calculateArgument151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<Object[][],$int> f = $static().$lambda(Ex,exmethtwo,$id,Ex,Object[]...);\n");
        xml_.append("  $return f.call($new Object[][]{$new $int[]{5,9}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(Object[]... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p.length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument152Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $long t;\n");
        xml_.append(" $public Ex($int i){\n");
        xml_.append("  t=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($long i){\n");
        xml_.append("  t=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $Fct<$int,Ex> f = $lambda(Ex,$new,$id,$int);\n");
        xml_.append("  $return f.call(5).t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument153Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $long t;\n");
        xml_.append(" $public Ex($int... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($long... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex> f = $lambda(Ex,$new,$id,$int...);\n");
        xml_.append("  $return f.call($new $int[]{5}).t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument153FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $long t;\n");
        xml_.append(" $public Ex($int... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($long... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex> f = $lambda(Ex,$new,$id,$int...,$int);\n");
        xml_.append("  $return f.call($new $int[]{5}).t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument154FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $long t;\n");
        xml_.append(" $public Ex($int... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($long... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex> f = $new Ex().$lambda(Ex,$new,$int...,$int);\n");
        xml_.append("  $return f.call($new $int[]{5}).t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument154Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $long t;\n");
        xml_.append(" $public Ex($int i){\n");
        xml_.append("  t=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($long i){\n");
        xml_.append("  t=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $Fct<$int,Ex> f = $lambda(Ex,$new,$int);\n");
        xml_.append("  $return f.call(5).t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument155Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $long t;\n");
        xml_.append(" $public Ex($int... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($long... i){\n");
        xml_.append("  t=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $Fct<$int[],Ex> f = $lambda(Ex,$new,$int...);\n");
        xml_.append("  $return f.call($new $int[]{5}).t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument156Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $Fct<String,ExEnum> f = $static().$lambda(ExEnum,valueOf,String);\n");
        xml_.append("  $return f.call(\"ONE\").$name();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("ONE", getString(ret_));
    }

    @Test
    public void calculateArgument157Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $Fct<ExEnum[]> f = $static().$lambda(ExEnum,values);\n");
        xml_.append("  $return f.call()[0].$name();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("ONE", getString(ret_));
    }

    @Test
    public void calculateArgument158Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExEnum exmeth(){\n");
        xml_.append("  $Fct<String,ExEnum> f = $static().$lambda(ExEnum,valueOf,String);\n");
        xml_.append("  $return f.call(\"INEXIST\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE, ret_.getStruct());
    }
    @Test
    public void calculateArgument159Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],$int,T,T> c = $lambda(T[],[]=,$int);\n");
        xml_.append("  T instance = c.call(a,1,p);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(),ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument160Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[1i][1i];\n");
        xml_.append("  $Fct<T[][],$int,$int,T,T> c = $lambda(T[][],[]=,$int,$int);\n");
        xml_.append("  T instance = c.call(a,1,0,p);\n");
        xml_.append("  $return instance;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasBadIndex(),ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument161Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call($new Ex<>(14));\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument162Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,Ex<java.lang.Number>[],$int> f = $lambda(Ex<java.lang.Number>,get,Ex<java.lang.Number>...);\n");
        xml_.append("  $return f.call($new Ex<>(14),$new Ex<>[]{$new Ex<>(15),$new Ex<>(5)});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Ex<T>... args){\n");
        xml_.append("  $return field+args[0].field+args[1].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }

    @Test
    public void calculateArgument163Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(a:{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument164Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call(a:{$new Ex<>(14)});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument165Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,Ex<java.lang.Number>[],$int> f = $lambda(Ex<java.lang.Number>,get,Ex<java.lang.Number>...);\n");
        xml_.append("  $return f.call(a:{$new Ex<>(14),$new Ex<>[]{$new Ex<>(15),$new Ex<>(5)}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Ex<T>... args){\n");
        xml_.append("  $return field+args[0].field+args[1].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
    @Test
    public void calculateArgument166Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,Ex<java.lang.Number>[],$int> f = $lambda(Ex<java.lang.Number>,get,Ex<java.lang.Number>...);\n");
        xml_.append("  $return f.call(a:{$new Ex<>(14),$new Ex<>[2]});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Ex<T>... args){\n");
        xml_.append("  $return field+args.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument167Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Object,$int> f = $static().$lambda(Ex,get,Object);\n");
        xml_.append("  $return f.call(a:{($int a:$int)-> a * 2});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Object args){\n");
        xml_.append("  $return args == $null ? 0 : 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument168Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call($id($Fct,Object...),a:{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }

    @Test
    public void calculateArgument169Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call($id($Fct,Object...),5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument170Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,$int> f = $lambda(Ex<java.lang.Number>,get);\n");
        xml_.append("  $return f.call($id($Fct,Object...),a:{$new Ex<>(14)});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(14, getNumber(ret_));
    }
    @Test
    public void calculateArgument171Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,Ex<java.lang.Number>[],$int> f = $lambda(Ex<java.lang.Number>,get,Ex<java.lang.Number>...);\n");
        xml_.append("  $return f.call($id($Fct,Object...),a:{$new Ex<>(14),$new Ex<>[]{$new Ex<>(15),$new Ex<>(5)}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Ex<T>... args){\n");
        xml_.append("  $return field+args[0].field+args[1].field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
    @Test
    public void calculateArgument172Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Ex<java.lang.Number>,Ex<java.lang.Number>[],$int> f = $lambda(Ex<java.lang.Number>,get,Ex<java.lang.Number>...);\n");
        xml_.append("  $return f.call($id($Fct,Object...),a:{$new Ex<>(14),$new Ex<>[2]});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Ex<T>... args){\n");
        xml_.append("  $return field+args.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument173Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Object,$int> f = $static().$lambda(Ex,get,Object);\n");
        xml_.append("  $return f.call($id($Fct,Object...),a:{($int a:$int)-> a * 2});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Object args){\n");
        xml_.append("  $return args == $null ? 0 : 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument174Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont e = $new($new(5));\n");
        xml_.append("  $var f = $lambda(Ex,=);\n");
        xml_.append("  f.call($that(e[0]),$new(6));\n");
        xml_.append("  $return e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] a;\n");
        xml_.append(" $public ExCont(Ex... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }


    @Test
    public void calculateArgument175Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public Ex(S f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont<Ex<$int>> e = $new($new(5));\n");
        xml_.append("  $var f = $lambda(Ex<$int>,=);\n");
        xml_.append("  f.call($that(e[0]),$new(6));\n");
        xml_.append("  $return e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" $public T[] a;\n");
        xml_.append(" $public ExCont(T... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument176Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public Ex(S f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  ExCont<Ex<S>> e = $new($new((S)5));\n");
        xml_.append("  $var f = $lambda(Ex<S>,=);\n");
        xml_.append("  f.call($that(e[0]),$new((S)6));\n");
        xml_.append("  $return ($int)e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" $public T[] a;\n");
        xml_.append(" $public ExCont(T... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument177Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public Ex(S f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  ExCont<Ex<S>> e = $new($new((S)5));\n");
        xml_.append("  $var f = $lambda(Ex<S>,=);\n");
        xml_.append("  $return ($int)f.call($that(e[0]),$new((S)6)).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" $public T[] a;\n");
        xml_.append(" $public ExCont(T... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument174_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont e = $new($new(5));\n");
        xml_.append("  $var f = $lambda(Ex,:);\n");
        xml_.append("  f.call($that(e[0]));\n");
        xml_.append("  $return e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] a;\n");
        xml_.append(" $public ExCont(Ex... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument175_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public Ex(S f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont<Ex<$int>> e = $new($new(5));\n");
        xml_.append("  $var f = $lambda(Ex<$int>,:);\n");
        xml_.append("  f.call($that(e[0]));\n");
        xml_.append("  $return e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" $public T[] a;\n");
        xml_.append(" $public ExCont(T... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument176_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public Ex(S f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  ExCont<Ex<S>> e = $new($new((S)5));\n");
        xml_.append("  $var f = $lambda(Ex<S>,:);\n");
        xml_.append("  f.call($that(e[0]));\n");
        xml_.append("  $return ($int)e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" $public T[] a;\n");
        xml_.append(" $public ExCont(T... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument177_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<S> {\n");
        xml_.append(" $public S f;\n");
        xml_.append(" $public Ex(S f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  ExCont<Ex<S>> e = $new($new((S)5));\n");
        xml_.append("  $var f = $lambda(Ex<S>,:);\n");
        xml_.append("  $return ($int)f.call($that(e[0])).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont<T> {\n");
        xml_.append(" $public T[] a;\n");
        xml_.append(" $public ExCont(T... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public T $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculateArgument178Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont e = $new($new(5));\n");
        xml_.append("  $var f = $lambda(Ex,=,ExTwo);\n");
        xml_.append("  f.call($that(e[0]),$new(6));\n");
        xml_.append("  $return e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] a;\n");
        xml_.append(" $public ExCont(Ex... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex {\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }

    @Test
    public void calculateArgument_1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Ex($int f){\n");
        xml_.append("  $this.f=f;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ExCont e = $new($new(5));\n");
        xml_.append("  $var f = $lambda(Ex,=,ExTwo);\n");
        xml_.append("  f.call($that(e[0]),$new(6));\n");
        xml_.append("  $return e[0].f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] a;\n");
        xml_.append(" $public ExCont(Ex... a){\n");
        xml_.append("  $this.a=a;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex $this($int i){\n");
        xml_.append("  $return a[i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int i){\n");
        xml_.append("  a[i]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument0FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void exmeth(){\n");
        xml_.append("  $new ExTwo<$int>().$lambda(ExTwo<?>,exmethtwo,$int...);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T>{\n");
        xml_.append(" $public $void exmethtwo(T... p){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int superfield=15i;\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,$super,get);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$Fct<$int,$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int,$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$null});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int> f = $lambda(Ex,,$super,field);\n");
        xml_.append("  $return f.call(instance);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<Ex,$int,$int> f = $lambda(Ex,,$super,field,$int);\n");
        xml_.append("  $int res = f.call(instance,18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,,$super,field);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int,$int> f = instance.$lambda(Ex,,$super,field,$int);\n");
        xml_.append("  $int res = f.call(18i);\n");
        xml_.append("  $return res + instance.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method f = $class($Fct).makeGeneric($class($int),$class($int)).getDeclaredMethods()[0i];\n");
        xml_.append("  $Fct<$int,$int> g = $new Ex().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return $($int) f.invoke(g,$new Object[][]{$null});\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(), arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument24FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1i];\n");
        xml_.append("  $Fct<T[],T[]> c = $lambda(T[],clon);\n");
        xml_.append("  T[] instance = c.call(a);\n");
        xml_.append("  instance[0i]=p;\n");
        xml_.append("  $return instance[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument25FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  $Fct<T[]> c = $new T[1i].$lambda(T[],clon);\n");
        xml_.append("  T[] instance = c.call();\n");
        xml_.append("  instance[0i]=p;\n");
        xml_.append("  $return instance[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument26FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $lambda(Ex,getter,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument27FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $new ExTwo().$lambda(Ex,getter,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument28FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $new ExTwo().$lambda(Inex,getter,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument29FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,get,$id,Inexist,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument30FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,getter,$id,Ex,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument31FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,getter,$id,Ex,Integer...,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p,Integer q...){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument32FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $static().$lambda(Ex,getter,Integer...,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Integer get(Integer p,Integer q...){\n");
        xml_.append("  $return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument33FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = get().$lambda(Ex,getter,Integer...,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void get(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument34FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = get().$lambda(Ex,getter,Integer,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void get(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument35FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex,get,$id,Integer...,Integer);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument36FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Inexist,get,$id,Integer...,Integer);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument37FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex<Integer> instance = $new Ex<Integer>();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = instance.$lambda(Ex<?>,get,$id,T);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(T p){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument38FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $lambda(Ex,getter,Integer,Integer);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void get(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument39FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = $lambda(Ex,get,$id,Integer...,Integer);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument40FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Ex instance = $new Ex();\n");
        xml_.append("  instance.field=14i;\n");
        xml_.append("  $Fct<$int> f = $lambda(Ex,get,Integer...,Integer);\n");
        xml_.append("  $return f.call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument41FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<Integer,Integer> f = $lambda(Ex<?>,get,$id,T);\n");
        xml_.append("  $return f.call(14);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int get(T t){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument42FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex<Integer>().inner();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  Ex<T> instance = $new Ex<T>();\n");
        xml_.append("  instance.field=(T)14i;\n");
        xml_.append("  $Fct<Ex<T>,T,$int> f = $lambda(Ex<T>,,field,$int);\n");
        xml_.append("  $return ($int)f.call(instance,5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument43FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex<Integer>().inner();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  Ex<T> instance = $new Ex<T>();\n");
        xml_.append("  instance.field=(T)14i;\n");
        xml_.append("  $Fct<T,$int> f = instance.$lambda(Ex<T>,,field,$int);\n");
        xml_.append("  $return ($int)f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument44FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $new Ex<Integer>().inner();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  Ex<T> instance = $new Ex<T>();\n");
        xml_.append("  instance.field=(T)14i;\n");
        xml_.append("  $Fct<T,$int> f = instance.$lambda(Number,,field);\n");
        xml_.append("  $return ($int)f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument45FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Number field;\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  $Fct<String,$int> f = $static().$lambda(Ex,,field,String);\n");
        xml_.append("  $return ($int)f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument46FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Number field;\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  $Fct<String,$int> f = $static().$lambda(Ex,,filled,String);\n");
        xml_.append("  $return ($int)f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument47FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Number field;\n");
        xml_.append(" $public $normal $void m(){}\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  $Fct<String,$int> f = m().$lambda(Ex,,filled,String);\n");
        xml_.append("  $return ($int)f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument48FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Number field;\n");
        xml_.append(" $public $normal $void m(){}\n");
        xml_.append(" $public $normal $int inner(){\n");
        xml_.append("  $Fct<String,$int> f = m().$lambda(Ex,);\n");
        xml_.append("  $return ($int)f.call(5);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument49FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,-,Static,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument50FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,-,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument51FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static,Static> f = $lambda($operator,-,Static...,Static);\n");
        xml_.append("  $return f.call(s,t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument52FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a, pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<Static,Static> f = s.$lambda($operator,-,Static...,Static);\n");
        xml_.append("  $return f.call(t).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument53FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,java.lang.String[]> c = $lambda(java.lang.String[],$new,String);\n");
        xml_.append("  java.lang.String[] instance = c.call(1i);\n");
        xml_.append("  $return instance.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument54FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,java.lang.String[]> c = $lambda(java.lang.String[],$new);\n");
        xml_.append("  java.lang.String[] instance = c.call(1i);\n");
        xml_.append("  $return instance.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument55FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,java.lang.String[]> c = $lambda(java.lang.String[]);\n");
        xml_.append("  java.lang.String[] instance = c.call(1i);\n");
        xml_.append("  $return instance.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument56FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,$int,$int> c = $null;\n");
        xml_.append("  $return c.call(m(),m());\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void m(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument57FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?,java.lang.String[]> c = $lambda(java.lang.String[],$int);\n");
        xml_.append("  $Fct<$int,java.lang.String[]> d = c;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument58FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<$int,?> c = $lambda(java.lang.String[]);\n");
        xml_.append("  $Fct<$int,java.lang.String[]> d = c;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument59FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int FIELD = 15;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $static().$lambda(Ex,,FIELD,$int);\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument60FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[] a = $new T[1];\n");
        xml_.append("  a.$lambda(T[],[],$int,$int);\n");
        xml_.append("  $return a[0i][0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument61FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  a.$lambda(T[],[],java.lang.String);\n");
        xml_.append("  $return a[0i][0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument62FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $lambda(T[],[],$int,$int);\n");
        xml_.append("  $return a[0i][0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument63FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $class Inner<U> {\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public($int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  $public $normal $int get(){\n");
        xml_.append("   $return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T call(T p){\n");
        xml_.append("  T[][] a = $new T[][]{$new T[1]};\n");
        xml_.append("  $lambda(T[],[],java.lang.String);\n");
        xml_.append("  $return a[0i][0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  Ex<java.lang.String> instance = $new Ex<java.lang.String>();\n");
        xml_.append("  $return instance.call(\"sample\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument64FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static class Inner {\n");
        xml_.append("  public static int exmeth(){\n");
        xml_.append("   Fct<Integer,Integer> f = static().$lambda(Inner,get,$id,Ex,12345678912,Integer);\n");
        xml_.append("   return f.call(14);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static Integer get(Integer p){\n");
        xml_.append("  return p*2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("en",files_));
    }

    @Test
    public void calculateArgument65FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(b:{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument66FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(b:5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument67FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(2,b:5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument68FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call(b:5i,2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument69FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call($firstopt($null));\n");
        xml_.append("  $return f.call(b:5i,2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument70FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$int,$int> f = $static().$lambda(Ex,exmethtwo,$int);\n");
        xml_.append("  $return f.call($id(Object,Object...),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct,Object),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct,~Object...),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct,~,Object...),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct,1,Object...),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct,$static,Object...),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct),a:{5i});\n");
        xml_.append("  $return f.call($id($Fct,$int...),a:{5i});\n");
        xml_.append("  $return f.metaInfo($id($Fct,Object),a:{5i});\n");
        xml_.append("  $return f.instance($id($Fct,Object),a:{5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeththree($int p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument78FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public class Inner {\n");
        xml_.append("  public int field;\n");
        xml_.append("  public(int p){\n");
        xml_.append("   field=p;\n");
        xml_.append("  }\n");
        xml_.append("  public normal int get(){\n");
        xml_.append("   return field;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  Fct<int,Ex.Inner> c = new Ex().$lambda(Inner,new,int...,int);\n");
        xml_.append("  Ex.Inner instance = c.call(14i);\n");
        xml_.append("  return instance.get();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrLgReadOnly("en",files_));
    }
}
