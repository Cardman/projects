package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodRecordTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(one:10,two:12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one;\n");
        xml_.append(" $int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(two:10,one:12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one;\n");
        xml_.append(" $int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,Rec> l = $lambda(Rec,$new,field);\n");
        xml_.append("  Rec r = l.call(10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public @$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct l = $lambda(Rec,$new,field);\n");
        xml_.append("  l.call(\"10\");\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,$int,Rec> l = $lambda(Rec,$new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one;\n");
        xml_.append(" $int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,$int,Rec> l = $lambda(Rec,$new,two,one);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one;\n");
        xml_.append(" $int two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(one:10,two:12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(two:10,one:12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(one:10,two:12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(two:10,one:12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,$int,Rec> l = $lambda(Rec,$new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,$int,Rec> l = $lambda(Rec,$new,two,one);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int one,two;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,$int,Rec> l = $lambda(Rec,$new,one,two);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-2, getNumber(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,$int,Rec> l = $lambda(Rec,$new,two,one);\n");
        xml_.append("  Rec r = l.call(10,12);\n");
        xml_.append("  $return r.one-r.two;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int two,one;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec<$int> r = $new Rec<>(field:10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(ExTwo<$int>).exmeth(10);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S> {\n");
        xml_.append(" $public $staticCall S exmeth (S p){\n");
        xml_.append("  Rec<S> r = $new Rec<>(field:p);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,Rec<$int>> l = $lambda(Rec<$int>,$new,field);\n");
        xml_.append("  Rec<$int> r = l.call(10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(ExTwo<$int>).exmeth(10);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S> {\n");
        xml_.append(" $public $staticCall S exmeth (S p){\n");
        xml_.append("  $Fct<S,Rec<S>> l = $lambda(Rec<S>,$new,field);\n");
        xml_.append("  Rec<S> r = l.call(p);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append(" @$class Rec {\n");
        xml_.append("  $int field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  @$class Rec {\n");
        xml_.append("   $int field;\n");
        xml_.append("  }\n");
        xml_.append("  Rec r = $new Rec(field:10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:10);\n");
        xml_.append("  $return r.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec:Int {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $public $int field(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.Int {\n");
        xml_.append(" $int field();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Int r = $new Rec(field:10);\n");
        xml_.append("  $return r.field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec:Int {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $public $int field(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.Int {\n");
        xml_.append(" $int field();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int stField = out();\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return stField;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int out(){\n");
        xml_.append("  $Fct<$int,Rec> l = $lambda(Rec,$new,field);\n");
        xml_.append("  $return l.call(10).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $static $int stField=Ex.stField++;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:(Integer)$null);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.util.exceptions.NullObjectException",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  field = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:$new Ex(10));\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int $(Ex p){\n");
        xml_.append("  $return p.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = (Rec)$class(Rec).getDeclaredConstructors()[0].newInstance();\n");
        xml_.append("  $class(Rec).getDeclaredFields()[0].set(r,10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = (Rec)$class(Rec).defaultInstance();\n");
        xml_.append("  $class(Rec).getDeclaredFields()[0].set(r,10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $Fct<$int,Rec> l = $lambda(Rec,$new,field);\n");
        xml_.append("  l.metaInfo();\n");
        xml_.append("  l.instance();\n");
        xml_.append("  Rec r = l.call(10);\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return ((Annot)$class(Rec).getAnnotations()[0]).field();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@Annot(2)\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot {\n");
        xml_.append(" $int field();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth (){\n");
        xml_.append("  $return $class(Rec).isSpeClass();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isTrue(ret_.getStruct()));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth (){\n");
        xml_.append("  $return $class(Ex).isSpeClass();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isFalse(ret_.getStruct()));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth (){\n");
        xml_.append("  $return $class(Ex).isSpeClass();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$interface pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isFalse(ret_.getStruct()));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec(field:10,RecSuper.field2:12);\n");
        xml_.append("  $return r.field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec:RecSuper {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper {\n");
        xml_.append(" $int field2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec<$int> r = $new Rec<$int>(field:10,RecSuper.field2:12);\n");
        xml_.append("  $return r.field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  $Fct<$int,U,Rec<U>> r = $lambda(Rec<U>,$new,field,RecSuper.field2);\n");
        xml_.append("  $return r.call(10,(U)12).field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>(field:10,RecSuper.field2:(U)12);\n");
        xml_.append("  $return r.field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec();\n");
        xml_.append("  $return r.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec:RecSuper {\n");
        xml_.append(" $int field;\n");
        xml_.append(" {field=10;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper {\n");
        xml_.append(" $int field2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  Rec r = $new Rec();\n");
        xml_.append("  $return r.field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec:RecSuper {\n");
        xml_.append(" $int field;\n");
        xml_.append(" {field=10;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper {\n");
        xml_.append(" $int field2;\n");
        xml_.append(" {field2=12;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return r.field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)12;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  $Fct<$int,Rec<U>> r = $lambda(Rec<U>,$new,field);\n");
        xml_.append("  $return r.call(10).field2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)12;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return r.field3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(RecSuper2,RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S>:RecSuper2<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)11;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper2<R> {\n");
        xml_.append(" R field3;\n");
        xml_.append(" {field3=(R)12;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return r.field3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$interface $interfaces(RecSuper2,RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S>:RecSuper2<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)11;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper2<R> {\n");
        xml_.append(" R field3;\n");
        xml_.append(" {field3=(R)12;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall U exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return r.field3;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$interface $interfaces(;RecSuper2,RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S>:RecSuper2<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)11;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper2<R> {\n");
        xml_.append(" R field3;\n");
        xml_.append(" {field3=(R)12;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(12, getNumber(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return Rec.field25;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$interface $interfaces(RecStSuper1,RecStSuper2;RecSuper2,RecSuper) pkg.Rec<T>:RecSuper<T>:RecStSuper2 {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S>:RecSuper2<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)11;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper2<R> {\n");
        xml_.append(" R field3;\n");
        xml_.append(" {field3=(R)12;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecStSuper1 {\n");
        xml_.append(" $static $int field25;\n");
        xml_.append(" $static {field25=42;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecStSuper2:RecStSuper1 {\n");
        xml_.append(" $static $int field26;\n");
        xml_.append(" $static {field26=11;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(42, getNumber(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return Rec.field26;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$interface $interfaces(RecStSuper1,RecStSuper2;RecSuper2,RecSuper) pkg.Rec<T>:RecSuper<T>:RecStSuper2 {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S>:RecSuper2<S> {\n");
        xml_.append(" S field2;\n");
        xml_.append(" {field2=(S)11;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper2<R> {\n");
        xml_.append(" R field3;\n");
        xml_.append(" {field3=(R)12;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecStSuper1 {\n");
        xml_.append(" $static $int field25;\n");
        xml_.append(" $static {field25=42;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecStSuper2:RecStSuper1 {\n");
        xml_.append(" $static $int field26;\n");
        xml_.append(" $static {field26=56;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(56, getNumber(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<U> {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return $staticCall(Ex<$int>).exmeth();\n");
        xml_.append(" }\n");
        xml_.append(" $public $staticCall $int exmeth(){\n");
        xml_.append("  Rec<U> r = $new Rec<U>();\n");
        xml_.append("  $return Rec.field26;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$interface $interfaces(RecStSuper1,RecStSuper2;) pkg.Rec<T>:RecStSuper2 {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecStSuper1 {\n");
        xml_.append(" $static $int field25;\n");
        xml_.append(" $static {field25=42;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecStSuper2:RecStSuper1 {\n");
        xml_.append(" $static $int field26;\n");
        xml_.append(" $static {field26=56;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(56, getNumber(ret_));
    }
    @Test
    public void fail() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@$class $interfaces(RecSuper,RecSuper;RecSuper,RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" $static $int field2;\n");
        xml_.append(" $static {field2=11;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@$class $interfaces(RecSuper2,RecSuper) pkg.Rec<T>:RecSuper<T> {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper<S> {\n");
        xml_.append(" $int field2;\n");
        xml_.append(" {field2=11;}\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.RecSuper2 {\n");
        xml_.append(" $int field2;\n");
        xml_.append(" {field2=11;}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void recordTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("@$class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(new IntStruct(10)));
        StringMap<String> id_ = new StringMap<String>();
        id_.addEntry("field","$int");
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Rec");
        CustList<ExecNamedFieldContent> named_ = new CustList<ExecNamedFieldContent>();
        named_.add(new ExecNamedFieldContent(new AnaNamedFieldContent("field","$int","pkg.Rec",null),classBody_));
        ArgumentWrapper argumentWrapper_ = ProcessMethod.instanceRecordArgument(cont_, stackCall_, new CustomFoundRecordConstructor(new ExecFormattedRootBlock(classBody_, "pkg.Rec"), new ExecTypeFunction(classBody_, null), named_, args_));
        Argument out_ = argumentWrapper_.getValue();
        assertNull(stackCall_.getCallingState());
        assertNull(argumentWrapper_.getWrapper());
        assertEq("pkg.Rec", out_.getStruct().getClassName(cont_));
        assertEq(10, ((NumberStruct)getField(out_.getStruct(),new ClassField("pkg.Rec","field"))).intStruct());
    }
    @Test
    public void calculateArgumentFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:Rec {\n");
        xml_.append(" $static {\n");
        xml_.append("  $new Rec(0);\n");
        xml_.append("  $new Rec(f:$that(0));\n");
        xml_.append("  $static().$lambda(Rec2,$new,$id);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("@$class $interfaces(Int) pkg.Rec:Int {\n");
        xml_.append(" $int field;\n");
        xml_.append(" Rec(){}\n");
        xml_.append("}\n");
        xml_.append("@$class pkg.Rec2 {\n");
        xml_.append("}\n");
        xml_.append("$interface pkg.Int {\n");
        xml_.append(" $int sup;\n");
        xml_.append(" Int(){}\n");
        xml_.append(" {}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
}
