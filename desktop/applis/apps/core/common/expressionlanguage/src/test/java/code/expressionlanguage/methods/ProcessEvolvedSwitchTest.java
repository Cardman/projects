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

public final class ProcessEvolvedSwitchTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 string", getString(ret_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 int", getString(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 int", getString(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("null", getString(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  CharSequence t;\n");
        xml_.append("  t=\"string\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" String\";\n");
        xml_.append("   $case StringBuilder v:\n");
        xml_.append("    t=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("string String", getString(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $staticCall(ExTwo<String>).exmeth(\"string\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $staticCall String exmeth(T t){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case String v:\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v:\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("string String", getString(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $staticCall(ExTwo<String>).exmeth($null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $staticCall String exmeth(T t){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case String v:\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v:\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("null", getString(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $staticCall(ExTwo<String>).exmeth(\"string\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $staticCall String exmeth(T t){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case T v:\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v:\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("string String", getString(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $staticCall(ExTwo<StringBuilder>).exmeth(\"string\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $staticCall String exmeth(Object t){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $null:\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case T v:\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v:\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("", getString(ret_));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 string", getString(ret_));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=\"string\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("string", getString(ret_));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8", getString(ret_));
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 int", getString(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $int v:\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=\"string\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("string", getString(ret_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
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
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 string", getString(ret_));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"last \"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 string", getString(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case CharSequence v:\n");
        xml_.append("    t=v+\" char seq\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 string", getString(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=$new StringBuilder(\"8\");\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case CharSequence v:\n");
        xml_.append("    t=v+\" char seq\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 char seq", getString(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $if (t $instanceof String){\n");
        xml_.append("     $break;\n");
        xml_.append("    }\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"exit \"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("exit 8 string", getString(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t)lab{\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $if (t $instanceof String){\n");
        xml_.append("     $break lab;\n");
        xml_.append("    }\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"exit \"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("exit 8 string", getString(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t)lab{\n");
        xml_.append("   $case String v:\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $if (t $instanceof String){\n");
        xml_.append("     $break;\n");
        xml_.append("    }\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v:\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"exit \"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("exit 8 string", getString(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String[] t;\n");
        xml_.append("  t={\"8\"};\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String[] v:\n");
        xml_.append("    t[0]=v[0]+\" string\";\n");
        xml_.append("   $case $int[] v:\n");
        xml_.append("    t[0]=v[0]+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 string", getString(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  Number t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8:\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10:\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8eight", getString(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  Number t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8:\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10:\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("   $case $double v:\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8.0:\n");
        xml_.append("      o=\"eight dec\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10.0:\n");
        xml_.append("      o=\"ten dec\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8eight", getString(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  Number t;\n");
        xml_.append("  t=8.0;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v:\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8:\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10:\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("   $case $double v:\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8.0:\n");
        xml_.append("      o=\"eight dec\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10.0:\n");
        xml_.append("      o=\"ten dec\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8eight dec", getString(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  Number t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t)lab{\n");
        xml_.append("   $case String v:\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v:\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8:\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break lab;\n");
        xml_.append("     $case 10:\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("eight", getString(ret_));
    }
}
