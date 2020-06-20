package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Classes;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;


public final class ProcessMethodTryCatchTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument16_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i%0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DivideZeroException e){\n");
        xml_.append("   t=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t=2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = $null;\n");
        xml_.append("   $return array[1i/0i];\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DivideZeroException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = $null;\n");
        xml_.append("   array[1i/0i] = 0i;\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DivideZeroException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = {0};\n");
        xml_.append("   $return array[1i];\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.BadIndexException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = {0};\n");
        xml_.append("   array[1i] = 0i;\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.BadIndexException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = {0};\n");
        xml_.append("   $return array[-1i];\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.BadIndexException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = {0};\n");
        xml_.append("   array[-1i] = 0i;\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.BadIndexException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = $null;\n");
        xml_.append("   $return array[0i];\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = $null;\n");
        xml_.append("   array[0i] = 0i;\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = $null;\n");
        xml_.append("   $return array.length;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   res = 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   res = 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $int[] array = {0};\n");
        xml_.append("   $int zero = 0;\n");
        xml_.append("   array[0i]/=zero;\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DivideZeroException e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(0i==t){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument630Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Out {\n");
        xml_.append("  $public $int sum = 0;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Out o = $new Out();\n");
        xml_.append("  o.sum = sum;\n");
        xml_.append("  $throw o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth(u);\n");
        xml_.append("  } $catch(Out i) {\n");
        xml_.append("   $return (i.sum);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $catch(java.lang.Exception e){\n");
        xml_.append("    t=1i;\n");
        xml_.append("    $throw e;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   $return 100i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw \"\";\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw \"\";\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw \"\";\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t==0i){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$elseif($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t==0i){\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }$elseif($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else{\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$elseif(t>0i){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Boolean f = $null;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(f){\n");
        xml_.append("    $return 1i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw $null;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateError("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,getException(cont_));
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw $null;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw \"\";\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 5i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw $null;\n");
        xml_.append("   }\n");
        xml_.append("   $catch{\n");
        xml_.append("    t=10i;\n");
        xml_.append("    $return t;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t++;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  }\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  }\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   $if(i>0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return nullValue();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.Integer nullValue(){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return nullValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static java.lang.Integer nullValue(){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getAliasNullPe(), getException(cont_).getClassName(cont_));
    }
    @Test
    public void calculateArgument93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw $null;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object o){\n");
        xml_.append("   t+=2;\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return (String)(1i/0i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0].toString()+';'+e.current().length;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:4,23:90\npkg.Ex.$static catching();1", getString(ret_));
    }
    @Test
    public void calculateArgument941Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return (String)(1i/0i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return java.lang.Exception.current(e)[0].toString()+';'+e.current().length;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:4,23:90\npkg.Ex.$static catching();1", getString(ret_));
    }
    @Test
    public void calculateArgument942Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return (String)(1i/0i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return \"\"+java.lang.Exception.current($null).length+';';\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1;", getString(ret_));
    }
    @Test
    public void calculateArgument95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return (String)(ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0].toString()+';'+e.current()[1].toString()+';'+e.current().length;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:4,12:79\npkg.Ex.$static catching();pkg/Ex:13,2:280\npkg.ExTwo.;2", getString(ret_));
    }
    @Test
    public void calculateArgument96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return (String)$Class.forName(\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0].toString()+';'+e.current()[1].toString()+';'+e.current().length;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:4,27:94\npkg.Ex.$static catching();pkg/Ex:13,2:304\npkg.ExTwo.;2", getString(ret_));
    }
    @Test
    public void calculateArgument97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return (String)m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0].toString()+';'+e.current()[1].toString()+';'+e.current().length;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:5,22:198\npkg.Ex.$static catching();:0,0:0\n.;2", getString(ret_));
    }
    @Test
    public void calculateArgument971Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   MyValue n = $new MyValue();\n");
        xml_.append("   n.value = 1;\n");
        xml_.append("   MyValue d = $new MyValue();\n");
        xml_.append("   d.value = 0;\n");
        xml_.append("   $var i = (n/d).value;\n");
        xml_.append("   $return \"\";\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0].toString()+';'+e.current()[1].toString()+';'+e.current().length;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$operator/ pkg.MyValue(pkg.MyValue n, pkg.MyValue d){\n");
        xml_.append(" pkg.MyValue r = $new pkg.MyValue();\n");
        xml_.append(" r.value = n.value/d.value;\n");
        xml_.append(" $return r;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.MyValue {\n");
        xml_.append(" $public $int value;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex:8,15:176\npkg.Ex.$static catching();pkg/Ex:18,19:448\njava.lang.Object.$static /(pkg.MyValue,pkg.MyValue);2", getString(ret_));
    }
    @Test
    public void calculateArgument98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return (String)m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.getMessage();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("", getString(ret_));
    }
    @Test
    public void calculateArgument99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return (String)m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.toString();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$invokeTaget\n\npkg/Ex:5,22:198\npkg.Ex.$static catching()\n:0,0:0\n.", getString(ret_));
    }
    @Test
    public void calculateArgument990Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return java.lang.Exception.toString($null);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("code.util.exceptions.NullObjectException\n\npkg/Ex:7,32:163\n" +
                "pkg.Ex.$static catching()", getString(ret_));
    }
    @Test
    public void calculateArgument991Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return java.lang.Exception.toString(e);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$defErrorClass\n\npkg/Ex:4,12:79\npkg.Ex.$static catching()\npkg/Ex:13,2:230\npkg.ExTwo.", getString(ret_));
    }
    @Test
    public void calculateArgument100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.toString();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$defErrorClass\n\npkg/Ex:4,12:79\npkg.Ex.$static catching()\npkg/Ex:13,2:211\npkg.ExTwo.", getString(ret_));
    }
    @Test
    public void calculateArgument1001Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return java.lang.Exception.toString(e);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$defErrorClass\n\npkg/Ex:4,12:79\npkg.Ex.$static catching()\npkg/Ex:13,2:230\npkg.ExTwo.", getString(ret_));
    }
    @Test
    public void calculateArgument101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i.length();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.toString();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static String i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("code.util.exceptions.NullObjectException\n\npkg/Ex:4,27:94\npkg.Ex.$static catching()", getString(ret_));
    }
    @Test
    public void calculateArgument1011Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i.length();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return java.lang.Exception.toString(e);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static String i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("code.util.exceptions.NullObjectException\n\npkg/Ex:4,27:94\npkg.Ex.$static catching()", getString(ret_));
    }
    @Test
    public void calculateArgument102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.getMessage();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("", getString(ret_));
    }
    @Test
    public void calculateArgument103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(\"\"+pkg.ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e==e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(\"\"+pkg.ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(\"\"+pkg.ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception f){\n");
        xml_.append("   $return e==f;\n");
        xml_.append("  }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(\"\"+pkg.ExTwo.i.length());\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e==e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static String i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(\"\"+pkg.ExTwo.i.length());\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception f){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(\"\"+pkg.ExTwo.i.length());\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e==f;\n");
        xml_.append("  }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static String i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return ($boolean)m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e==e;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return ($boolean)m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return ($boolean)m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception f){\n");
        xml_.append("   $return e==f;\n");
        xml_.append("  }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return $ObjectsUtil.getParent(e);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(getException(cont_));
    }
    @Test
    public void calculateArgument110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return \"\"+pkg.ExTwo.i.length();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return $ObjectsUtil.getParent(e);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static String i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(getException(cont_));
    }
    @Test
    public void calculateArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return $ObjectsUtil.getParent(e);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(getException(cont_));
    }
    @Test
    public void calculateArgument112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0]==e.current()[0];\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument1112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return java.lang.Exception.current(e)[0]==e.current()[0];\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0]==e.current()[1];\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument114Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0]==e.current()[1];\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return ($boolean)(ExTwo.i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return e.current()[0]==$null;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return $ObjectsUtil.getParent(e.current()[0]);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(getException(cont_));
    }
    @Test
    public void calculateArgument1161Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(String),$class($boolean))[0];\n");
        xml_.append("   $return m.invoke($null,\"pkg.ExTwo\",$true);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return $ObjectsUtil.getParent(java.lang.Exception.current(e)[0]);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int i;\n");
        xml_.append(" $static{\n");
        xml_.append("  i = 1i/0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
        assertNull(getException(cont_));
    }

    @Test
    public void calculateArgument117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $final $int den = 0;\n");
        xml_.append("   $int num = 1;\n");
        xml_.append("   num /= den;\n");
        xml_.append("   $return num;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $final $int den = 0;\n");
        xml_.append("   $final $int out = 0;\n");
        xml_.append("   $for ($int num = 0;out>=0;){\n");
        xml_.append("    num /= den;\n");
        xml_.append("   }\n");
        xml_.append("   $return 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $int u;\n");
        xml_.append("   $int v;\n");
        xml_.append("   $int w;\n");
        xml_.append("   $try{\n");
        xml_.append("    v=5;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    u=2i;\n");
        xml_.append("   }\n");
        xml_.append("   w = 1;\n");
        xml_.append("   t = u+w;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $int u;\n");
        xml_.append("   $int v;\n");
        xml_.append("   $try label{\n");
        xml_.append("    v=5;\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    u=2i;\n");
        xml_.append("   }\n");
        xml_.append("   t = u;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $int u;\n");
        xml_.append("   $int v=0;\n");
        xml_.append("   $while (v==0){\n");
        xml_.append("    v=1/0;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   u=2i;\n");
        xml_.append("   t = u;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $int u;\n");
        xml_.append("   Integer v=$null;\n");
        xml_.append("   $iter (Integer i=v;1;1){\n");
        xml_.append("    v=1/0;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   u=2i;\n");
        xml_.append("   t = u;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $int u;\n");
        xml_.append("   Integer v=$null;\n");
        xml_.append("   $iter (Integer i=1;v;1){\n");
        xml_.append("    v=1/0;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   u=2i;\n");
        xml_.append("   t = u;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $int u;\n");
        xml_.append("   Integer v=$null;\n");
        xml_.append("   $iter (Integer i=1;1;v){\n");
        xml_.append("    v=1/0;\n");
        xml_.append("    $continue;\n");
        xml_.append("   }\n");
        xml_.append("   u=2i;\n");
        xml_.append("   t = u;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $int p;\n");
        xml_.append("  $try{\n");
        xml_.append("   $throw 0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(Number e){\n");
        xml_.append("   t=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t=2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument126Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $throw 1;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 6i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch($int e){\n");
        xml_.append("   $if(i>=0i){\n");
        xml_.append("    $break label;\n");
        xml_.append("   }\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument127Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 6i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument128Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int i=0i;\n");
        xml_.append("  $try label{\n");
        xml_.append("   $throw 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch($int e){\n");
        xml_.append("   $return 6i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   i++;\n");
        xml_.append("   $break label;\n");
        xml_.append("  }\n");
        xml_.append("  $return 3i+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }

    @Test
    public void calculateArgument129Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1l/0l;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1l;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculateArgument130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1l%0l;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1l;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $finally{\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else{\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else{\n");
        xml_.append("    t=0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("  }\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t==0i){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($false){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($false){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(0){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($false){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument12FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if(0){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t==0i){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if($true){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }$else $if(t==0i){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }

    @Test
    public void calculateArgument14FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }

    @Test
    public void calculateArgument15FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument16FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $return 0i;\n");
        xml_.append("  $try lab{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   $break lab;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object $e){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object $e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument18FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $catch(java.lang.Number e){\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }$catch(java.lang.Object e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void calculateArgument19FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try lab{\n");
        xml_.append("   $int i=1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   $if ($false){\n");
        xml_.append("    $break lab;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
}
