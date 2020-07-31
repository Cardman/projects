package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.NumberStruct;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ClassesBisTest extends ProcessMethodCommon {
    @Test
    public void calculate0FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$operator+ $int(){$interfaces(pkg.MyInt)();}\n");
        xml_.append("$public $interface pkg.MyInt{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculate1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$annotation pkg.MyAnnot{$public $int v=r{}y}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculate2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("outer {\n");
        xml_.append("inner {\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField183__FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces(ExInt) pkg.Ex:Ex.ExInt {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $interface ExInt {\n");
        xml_.append("  $public $static $int v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField183FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=(5!=4?0:1);\n");
        xml_.append(" $static{a1=5;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField183_FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=(5!=4?0:1);\n");
        xml_.append(" {a1=5;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField183Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=(5!=4?0:1);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefault();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField184_FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int a1=(5!=4?0:1);\n");
        xml_.append(" $void method(){a1=5;}\n");
        xml_.append(" Ex(){a1=5;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField185_FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int a1=(5!=4?0:1);\n");
        xml_.append(" $static{ExTwo e=$new ExTwo(); e.a1=5;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField186_FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int a1=(5!=4?0:1);\n");
        xml_.append(" {ExTwo e=$new ExTwo(); e.a1=5;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField187_FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int a1=(5!=4?0:1);\n");
        xml_.append(" {a1=5;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefaultReadOnly();
        Classes.validateAll(files_,ctx_);
        assertTrue(!ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField184Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Object[] a1=(Object[ ])$null;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefault();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField185Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=( .5);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefault();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField186Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean a1=$null $instanceof Object[ ] && $true;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefault();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField189Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean a1=1 $instanceof Integer || $false;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefault();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField190Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=1e+0;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = contextElSingleDotDefault();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
    }
    @Test
    public void calculateStaticField191Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=1exp1;\n");
        xml_.append(" $public $static $final $double a2=1.0exp1;\n");
        xml_.append(" $public $static $final $double a3=1.exp1;\n");
        xml_.append(" $public $static $final $double a4=0x1power1;\n");
        xml_.append(" $public $static $final $double a5=0x1.0power1;\n");
        xml_.append(" $public $static $final $double a6=0x1.power1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(10.0,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).doubleStruct());
        assertEq(10.0,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a2"))).doubleStruct());
        assertEq(10.0,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a3"))).doubleStruct());
        assertEq(2.0,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a4"))).doubleStruct());
        assertEq(2.0,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a5"))).doubleStruct());
        assertEq(2.0,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a6"))).doubleStruct());
    }
    @Test
    public void calculateStaticField192Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double a1=$true?.5:.2;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(0.5,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).doubleStruct());
     }
    @Test
    public void calculateStaticField193Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=$true?b1:2;\n");
        xml_.append(" $public $static $final $int b1=5;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(5,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField194Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=$false?5:b1;\n");
        xml_.append(" $public $static $final $int b1=2;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField195Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=$true?b1:2/0;\n");
        xml_.append(" $public $static $final $int b1=5;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(5,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField196Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int a1=$false?5/0:b1;\n");
        xml_.append(" $public $static $final $int b1=2;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField197Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean c1=$false||b1;\n");
        xml_.append(" $public $static $final $boolean b1=$true;\n");
        xml_.append(" $public $static $final $int a1=c1?2:5;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField198Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean c1=$null??b1;\n");
        xml_.append(" $public $static $final $boolean b1=$true;\n");
        xml_.append(" $public $static $final $int a1=c1?2:5;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(2,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void calculateStaticField199Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $boolean c1=$true&&b1;\n");
        xml_.append(" $public $static $final $boolean b1=$false;\n");
        xml_.append(" $public $static $final $int a1=c1?2:5;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl ctx_ = ProcessMethodCommon.contextElExp();
        Classes.validateAll(files_,ctx_);
        assertTrue(ctx_.isEmptyErrors());
        assertEq(5,((NumberStruct)ctx_.getClasses().getStaticField(new ClassField("pkg.ExTwo","a1"))).intStruct());
    }
    @Test
    public void validateEl17FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T,S> {\n");
        xml_.append(" $static{$new ExTwo<Number,Number>($null);}\n");
        xml_.append(" $public (T n){}\n");
        xml_.append(" $public (S s){}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl18FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{outer();}\n");
        xml_.append(" $public $static $void outer(){}\n");
        xml_.append(" $public $static $void outer(){}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl19FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$int $v = 0;$int $v = 0;e;p;$return 0;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl20FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $double de = 1..0;\n");
        xml_.append(" $public $double df = 1. .0;\n");
        xml_.append(" $public $double dg = 1. 0;\n");
        xml_.append(" $public $double dh = 1d.;\n");
        xml_.append(" $public $double di = 1df.;\n");
        xml_.append(" $public $double dj = 4g;\n");
        xml_.append(" $public $double dk = .4g;\n");
        xml_.append(" $public $double dl = .4ff;\n");
        xml_.append(" $public $double dm = 1f2;\n");
        xml_.append(" $public $double dn = 4f1;\n");
        xml_.append(" $public $double dp = 1df;\n");
        xml_.append(" $public $double dq = .1.0;\n");
        xml_.append(" $public $double dr = 0x2. ;\n");
        xml_.append(" $public $double ds = 0x2.;\n");
        xml_.append(" $public $double dt = 1.0df;\n");
        xml_.append(" $public $double du = 1.df;\n");
        xml_.append(" $public $double dv = 1ll;\n");
        xml_.append(" $public $double dw = 1.0dgf;\n");
        xml_.append(" $public $double dx = 1.0g;\n");
        xml_.append(" $public $double dy = 4.g;\n");
        xml_.append(" $public $double dz = 4.ff;\n");
        xml_.append(" $public $double da = 4.ff+5;\n");
        xml_.append(" $public $double db = 0b;\n");
        xml_.append(" $public $double dc = 2e ;\n");
        xml_.append(" $public $double dd = 1e .0;\n");
        xml_.append(" $public $double ea = 2e 0;\n");
        xml_.append(" $public $double eb = (java.lang.Object[ )]$null;\n");
        xml_.append(" $public $double ec = -(java.lang.Object[ )]$null;\n");
        xml_.append(" $public $double ed = 1 2;\n");
        xml_.append(" $public $double ee = . 1;\n");
        xml_.append(" $public $double ef = 1  .0;\n");
        xml_.append(" $public $double eg = 1 .0;\n");
        xml_.append(" $public $double eh = 6 1*(\"te\"+[8]);\n");
        xml_.append(" $public $double ei = 1.0.2;\n");
        xml_.append(" $public $double ej = 1.d.f;\n");
        xml_.append(" $public $double ek = 1.0e4d.5;\n");
        xml_.append(" $public $double el = 1.0e5df;\n");
        xml_.append(" $public $double em = 1ee2;\n");
        xml_.append(" $public $double en = 1.0e1g;\n");
        xml_.append(" $public $double ep = .0e1g;\n");
        xml_.append(" $public $double eq = .0e1ff;\n");
        xml_.append(" $public $double er = 1.0e4d.5;\n");
        xml_.append(" $public $double es = 1e;\n");
        xml_.append(" $public $double et = 1.0e.2;\n");
        xml_.append(" $public $double eu = 1e.0;\n");
        xml_.append(" $public $double ev = 1.0e5.2;\n");
        xml_.append(" $public $double ew = 1.0e5d2+5;\n");
        xml_.append(" $public $double ex = 1. 0. 5. 5;\n");
        xml_.append(" $public $double ey = ..1;\n");
        xml_.append(" $public $double ez = . .1;\n");
        xml_.append(" $public $double fa = 1e e1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl21FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $double fb[(c]);\n");
        xml_.append(" $public ExTwo(){($this());}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl22FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:ExThree<> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl23FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int v = ($int)(pkgtwo.ExThree)$null;\n");
        xml_.append("}\n");
        xml_.append("$class pkgtwo.ExThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl24FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:pkgtwo.ExThree> {\n");
        xml_.append("}\n");
        xml_.append("$class pkgtwo.ExThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl25FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo:ExThree<> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl26FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public int v = (];\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElReadOnlyDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl27FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $void outer(){($new{]);}\n");
        xml_.append(" $public $static $void outer2(){a=$new;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl28FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int a+=1;\n");
        xml_.append(" $public $int b,c+=1;\n");
        xml_.append(" $public $int d+f=e=1;\n");
        xml_.append(" $public $int g,,h;\n");
        xml_.append(" $public $int i==j,k;\n");
        xml_.append(" $public $int l#m;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl28_FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  $foreach($int i;$int v:);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void resolve12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:OuterTwo:OuterThree {\n");
        xml_.append(" $public $static $class Inner:InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.OuterThree {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:OuterTwo:OuterThree {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append("  InnerSup t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.OuterThree {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidate(files_);
    }
    @Test
    public void resolve14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class [$static pkg.Outer.InnerSup;] pkg.Apply {\n");
        xml_.append("  InnerSup t;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Outer:OuterTwo:OuterThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.OuterThree {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidate(files_);
    }
    @Test
    public void resolve15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:OuterTwo:OuterThree {\n");
        xml_.append(" $public $static $class Inner:pkg.Outer.InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.OuterThree {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidateInheritingClasses(files_);
    }
    @Test
    public void resolve16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:OuterTwo:OuterThree {\n");
        xml_.append("  Object t = InnerSup.CST;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.OuterTwo {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.OuterThree {\n");
        xml_.append(" $public $static $class InnerSup {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        failValidate(files_);
    }
    @Test
    public void validateEl55FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final Object field=\"\".splitStrings($vararg($void),0,$firstopt($null));\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl56FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.MyInt1 {}\n");
        xml_.append("$public $interface pkg.MyInt2 {}\n");
        xml_.append("$public $interface pkg.MyInt3:MyInt1:MyInt2 {}\n");
        xml_.append("$public $interface pkg.MyInt4:MyInt1:MyInt2 {}\n");
        xml_.append("$public $enum pkg.ExTwo {;\n");
        xml_.append(" {$int i = $null;}\n");
        xml_.append(" {$var v = 5; $var t = $bool($true,(MyInt3)$null,(MyInt4)$null);$if($true){$int k = 5;}}\n");
        xml_.append(" {$for($var t = $bool($true,(MyInt3)$null,(MyInt4)$null);;){$int k = 5;}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void validateEl141FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$for(String s=`v`){}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl142FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$int v = 0; v*=1d;$return v;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl143FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $void p() {$int v = 0; v+=1d;$return v;}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl148FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $static {\n");
        xml_.append("  $switch (value) {\n");
        xml_.append("   $case 0:\n");
        xml_.append("   \\\\comment\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefaultComment();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl149FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $static {\n");
        xml_.append("  $switch (value) {\n");
        xml_.append("   $case 0:\n");
        xml_.append("   /");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl150FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $static {\n");
        xml_.append("  $switch (value) {\n");
        xml_.append("   $case 0:\n");
        xml_.append("   /\\\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl151FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $static {\n");
        xml_.append("  $switch (value) {\n");
        xml_.append("   $case 0:\n");
        xml_.append("   /* *");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl129FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = a\"\";\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl130FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static $final Object cst = a'';\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl175FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this(int p)\n");
        xml_.append(" {\n");
        xml_.append("  return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" public void this(int value)\n");
        xml_.append(" {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl176FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int[] inst=new int[2];\n");
        xml_.append(" public int this()\n");
        xml_.append(" {\n");
        xml_.append("  return inst[0];\n");
        xml_.append(" }\n");
        xml_.append(" public void this()\n");
        xml_.append(" {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl177FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static{\n");
        xml_.append("  super[0] = 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl178FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" {\n");
        xml_.append("  [0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl179FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" if(true){\n");
        xml_.append("  \n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl180FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" switch (){\n");
        xml_.append("  case 1:\n");
        xml_.append("  default\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl181FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" switch (){\n");
        xml_.append("  case 1:\n");
        xml_.append("  default");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ClassesTest.contextEnElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl5__FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$return 0;$return 0;$public $static $class Inn{}}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElReadOnlyDef();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void validateEl167FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract\t$class pkg.Apply {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $final\t$class pkg.Apply1 {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply2\t{}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public\t$class pkg.Apply3 {}\n");
        files_.put("pkg/ExFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces pkg.Apply4 {}\n");
        files_.put("pkg/ExSix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces( pkg.Apply5 {}\n");
        files_.put("pkg/ExSeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces(\t) pkg.Apply6 {}\n");
        files_.put("pkg/ExEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class $interfaces()\t pkg.Apply7 {}\n");
        files_.put("pkg/ExNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $classe pkg.Apply7 {}\n");
        files_.put("pkg/ExTen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply8 {}/\n");
        files_.put("pkg/ExEleven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply9 {}/* *");
        files_.put("pkg/ExTwelve", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply10 {}/");
        files_.put("pkg/ExThirteen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply11 {}\t\t");
        files_.put("pkg/ExFourteen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator\t");
        files_.put("pkg/ExFifteen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+\t\t");
        files_.put("pkg/ExSixteen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator");
        files_.put("pkg/ExSeventeen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+[]\t");
        files_.put("pkg/ExEighteen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+[](");
        files_.put("pkg/ExNineteen", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$classe pkg.Apply12 {}\n");
        files_.put("pkg/ExTwenty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.Apply13 {}\n");
        xml_.append("$classe pkg.Apply14 {}\n");
        files_.put("pkg/ExTwentyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+ $int\t");
        files_.put("pkg/ExTwentyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+[] $int(]{");
        files_.put("pkg/ExTwentyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("/\\");
        files_.put("pkg/ExTwentyFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("/");
        files_.put("pkg/ExTwentyFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("a/");
        files_.put("pkg/ExTwentySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("a\t");
        files_.put("pkg/ExTwentySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("/* *");
        files_.put("pkg/ExTwentyEight", xml_.toString());
        xml_ = new StringBuilder();
        files_.put("pkg/ExTwentyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("a\t\t");
        files_.put("pkg/ExThirty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+[] $int(a{");
        files_.put("pkg/ExThirtyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$enum pkg.MyEnum{Object(];}");
        files_.put("pkg/ExThirtyThirtyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $class $interfaces(] MyClass{}}");
        files_.put("pkg/ExThirtyThirtyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $public $static $void m(]{}$public OuterBad(]{}}");
        files_.put("pkg/ExThirtyThirtyFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $public OuterBad(a<)>{}}");
        files_.put("pkg/ExThirtyThirtyFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$switch($true){$case(5}{$int i;}}}}");
        files_.put("pkg/ExThirtyThirtySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$while $true {$int i;}}}");
        files_.put("pkg/ExThirtyThirtySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$while [$true) {$int i;}}}");
        files_.put("pkg/ExThirtyThirtyEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$while ($true];}}");
        files_.put("pkg/ExThirtyThirtyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$catch ($true];}}");
        files_.put("pkg/ExThirtyForty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$if ($true];}}");
        files_.put("pkg/ExThirtyFortyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$if [$true);}}");
        files_.put("pkg/ExThirtyFortyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$elseif ($true];}}");
        files_.put("pkg/ExThirtyFortyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$elseif [$true);}}");
        files_.put("pkg/ExThirtyFortyFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$else $if ($true];}}");
        files_.put("pkg/ExThirtyFortyFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$else $if [$true);}}");
        files_.put("pkg/ExThirtyFortySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true);}}");
        files_.put("pkg/ExThirtyFortySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true](;];}}");
        files_.put("pkg/ExThirtyFortyEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true](:;);}}");
        files_.put("pkg/ExThirtyFortyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true](a b:c;);}}");
        files_.put("pkg/ExThirtyFifty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter [$true)();}}");
        files_.put("pkg/ExThirtyFiftyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter (];}}");
        files_.put("pkg/ExThirtyFiftyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter ($int i=0);}}");
        files_.put("pkg/ExThirtyFiftyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter ($int i=0;);}}");
        files_.put("pkg/ExThirtyFiftyFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter ($int i=([0]));}}");
        files_.put("pkg/ExThirtyFiftyFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for [$true)();}}");
        files_.put("pkg/ExThirtyFiftySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (];}}");
        files_.put("pkg/ExThirtyFiftySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for ();}}");
        files_.put("pkg/ExThirtyFiftyEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (a!:;);}}");
        files_.put("pkg/ExThirtyFiftyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$switch (a];}}");
        files_.put("pkg/ExThirtySixty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class");
        files_.put("pkg/ExThirtySixtyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$operator+[] $int(,]{");
        files_.put("pkg/ExThirtySixtyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (a!?;);}}");
        files_.put("pkg/ExThirtySixtyThree", xml_.toString());
        ContextEl cont_ = ProcessMethodCommon.contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }
    @Test
    public void validateEl168FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$switch($true){$case(5}{$int i;}}}}");
        files_.put("pkg/ExThirtyThirtySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$while $true {$int i;}}}");
        files_.put("pkg/ExThirtyThirtySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$while [$true) {$int i;}}}");
        files_.put("pkg/ExThirtyThirtyEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$while ($true];}}");
        files_.put("pkg/ExThirtyThirtyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$catch ($true];}}");
        files_.put("pkg/ExThirtyForty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$if ($true];}}");
        files_.put("pkg/ExThirtyFortyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$if [$true);}}");
        files_.put("pkg/ExThirtyFortyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$elseif ($true];}}");
        files_.put("pkg/ExThirtyFortyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$elseif [$true);}}");
        files_.put("pkg/ExThirtyFortyFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$else $if ($true];}}");
        files_.put("pkg/ExThirtyFortyFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$else $if [$true);}}");
        files_.put("pkg/ExThirtyFortySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true);}}");
        files_.put("pkg/ExThirtyFortySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true](;];}}");
        files_.put("pkg/ExThirtyFortyEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true](:;);}}");
        files_.put("pkg/ExThirtyFortyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$foreach [$true](a b:c;);}}");
        files_.put("pkg/ExThirtyFifty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter [$true)();}}");
        files_.put("pkg/ExThirtyFiftyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter (];}}");
        files_.put("pkg/ExThirtyFiftyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter ($int i=0);}}");
        files_.put("pkg/ExThirtyFiftyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter ($int i=0;);}}");
        files_.put("pkg/ExThirtyFiftyFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$iter ($int i=([0]));}}");
        files_.put("pkg/ExThirtyFiftyFive", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for [$true)();}}");
        files_.put("pkg/ExThirtyFiftySix", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (];}}");
        files_.put("pkg/ExThirtyFiftySeven", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for ();}}");
        files_.put("pkg/ExThirtyFiftyEight", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (a!:;);}}");
        files_.put("pkg/ExThirtyFiftyNine", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$switch (a];}}");
        files_.put("pkg/ExThirtySixty", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$case (a];}}");
        files_.put("pkg/ExThirtySixtyOne", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$case (a];\t}}");
        files_.put("pkg/ExThirtySixtyTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (a b,c d!:){}}}");
        files_.put("pkg/ExThirtySixtyThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkg.OuterBad{ $static{$for (a b,c d!;:){}}}");
        files_.put("pkg/ExThirtySixtyFour", xml_.toString());
        ContextEl cont_ = contextElSingleDotDefault();
        Classes.validateAll(files_, cont_);
        assertTrue( !cont_.isEmptyErrors());
    }

}
