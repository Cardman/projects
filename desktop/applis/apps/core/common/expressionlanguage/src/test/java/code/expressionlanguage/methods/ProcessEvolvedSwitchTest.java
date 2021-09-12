package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.DisplayableStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessEvolvedSwitchTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" String\";\n");
        xml_.append("   $case StringBuilder v;\n");
        xml_.append("    t=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case String v;\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v;\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case String v;\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v;\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case T v;\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v;\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $null;\n");
        xml_.append("    o=\"null\";\n");
        xml_.append("   $case T v;\n");
        xml_.append("    o=v+\" String\";\n");
        xml_.append("   $case StringBuilder v;\n");
        xml_.append("    o=v+\" StringBuilder\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=\"string\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case $int v;\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=\"string\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"last \"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case CharSequence v;\n");
        xml_.append("    t=v+\" char seq\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case CharSequence v;\n");
        xml_.append("    t=v+\" char seq\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $if (t $instanceof String){\n");
        xml_.append("     $break;\n");
        xml_.append("    }\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"exit \"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $if (t $instanceof String){\n");
        xml_.append("     $break lab;\n");
        xml_.append("    }\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"exit \"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("    $if (t $instanceof String){\n");
        xml_.append("     $break;\n");
        xml_.append("    }\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("    $return \"\"+t;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"exit \"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String[] v;\n");
        xml_.append("    t[0]=v[0]+\" string\";\n");
        xml_.append("   $case $int[] v;\n");
        xml_.append("    t[0]=v[0]+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8;\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10;\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8;\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10;\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("   $case $double v;\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8.0;\n");
        xml_.append("      o=\"eight dec\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10.0;\n");
        xml_.append("      o=\"ten dec\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8;\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10;\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("   $case $double v;\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8.0;\n");
        xml_.append("      o=\"eight dec\";\n");
        xml_.append("      $break;\n");
        xml_.append("     $case 10.0;\n");
        xml_.append("      o=\"ten dec\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        xml_.append("   $case String v;\n");
        xml_.append("    o=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    $switch(v){\n");
        xml_.append("     $case 8;\n");
        xml_.append("      o=\"eight\";\n");
        xml_.append("      $break lab;\n");
        xml_.append("     $case 10;\n");
        xml_.append("      o=\"ten\";\n");
        xml_.append("    }\n");
        xml_.append("    o=8+o;\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("eight", getString(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  ExParam<?> t;\n");
        xml_.append("  t=$new ExParam<$int>();\n");
        xml_.append("  ((ExParam<$int>)t).field=8;\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ExParam<$int> v;\n");
        xml_.append("    o=v.field+\" int\";\n");
        xml_.append("   $case ExParam<?> v;\n");
        xml_.append("    o=v.field+\" ?\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExParam<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 int", getString(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  ExParam<?> t;\n");
        xml_.append("  t=$new ExParam<$byte>();\n");
        xml_.append("  ((ExParam<$byte>)t).field=($byte)8;\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ExParam<$int> v;\n");
        xml_.append("    o=v.field+\" int\";\n");
        xml_.append("   $case ExParam<?> v;\n");
        xml_.append("    o=v.field+\" byte\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExParam<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 byte", getString(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $Fct<$int,$int> t=x->2*x;\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $Fct<$int,Object> v;\n");
        xml_.append("    o=v.call(5)+\" int\";\n");
        xml_.append("   $case $Fct v;\n");
        xml_.append("    o=v+\" ?\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExParam<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("10 int", getString(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $Fct<$int,$int> t=x->2*x;\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case $Fct<Object,Object> v;\n");
        xml_.append("    o=v+\" int\";\n");
        xml_.append("   $case $Fct<$int,$int> v;\n");
        xml_.append("    o=v.call(5)+\" default\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExParam<T> {\n");
        xml_.append(" T field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("10 default", getString(ret_));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  ExAbs t;\n");
        xml_.append("  t=$new ExOne();\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ExOne v;\n");
        xml_.append("    o+=(v.fieldOne+5)+\" int\";\n");
        xml_.append("   $case ExAbs v;\n");
        xml_.append("    o+=v.field+\" ?\";\n");
        xml_.append("  }\n");
        xml_.append("  t=$new ExTwo();\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ExTwo v;\n");
        xml_.append("    o+=(v.fieldSec+10)+\" int\";\n");
        xml_.append("   $case ExAbs v;\n");
        xml_.append("    o+=v.field+\" ?\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExOne:ExAbs {\n");
        xml_.append(" $int fieldOne=10;\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExTwo:ExAbs {\n");
        xml_.append(" $int fieldSec=15;\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $int field=20;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("15 int25 int", getString(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  ExAbs t;\n");
        xml_.append("  t=$new ExOne();\n");
        xml_.append("  String o=\"\";\n");
        xml_.append("  $switch((ExOne)t){\n");
        xml_.append("   $case ExOne v;\n");
        xml_.append("    o+=(v.fieldOne+5)+\" int\";\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o+=\" ?\";\n");
        xml_.append("  }\n");
        xml_.append("  t=$new ExTwo();\n");
        xml_.append("  $switch((ExTwo)t){\n");
        xml_.append("   $case ExTwo v;\n");
        xml_.append("    o+=(v.fieldSec+10)+\" int\";\n");
        xml_.append("   $case $null;\n");
        xml_.append("    o+=\" ?\";\n");
        xml_.append("  }\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExOne:ExAbs {\n");
        xml_.append(" $int fieldOne=10;\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkg.ExTwo:ExAbs {\n");
        xml_.append(" $int fieldSec=15;\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $int field=20;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("15 int25 int", getString(ret_));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\"alone\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("alone", getString(ret_));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("    t=\"alone\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("8 int", getString(ret_));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\"alone\";\n");
        xml_.append("   $case 10;\n");
        xml_.append("    t=\"sec alone\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("alone", getString(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("    t=\"alone\";\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=\"sec alone\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("sec alone", getString(ret_));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.ONE;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case Ex.TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument37_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.ONE;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case Ex.TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument37__Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.ONE;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 2;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.TWO;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case Ex.TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("TWO", getString(ret_));
    }
    @Test
    public void calculateArgument38_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.TWO;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case Ex.TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("TWO", getString(ret_));
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.ONE;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case Ex.TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.TWO;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case Ex.ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case Ex.TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("TWO", getString(ret_));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.ONE;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.TWO;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("TWO", getString(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.ONE;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
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
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=Ex.TWO;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case TWO;\n");
        xml_.append("    t=\"TWO\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("TWO", getString(ret_));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Ex)$null){\n");
        xml_.append("   $default;\n");
        xml_.append("    t=\"ONE\";\n");
        xml_.append("   $case $null;\n");
        xml_.append("    t=\"null\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("null", getString(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch(1){\n");
        xml_.append("   $case 2,3;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch(2){\n");
        xml_.append("   $case 2,3;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("plus", getString(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>,TWO<Integer>{};\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch(3){\n");
        xml_.append("   $case 2,3;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case 1;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("plus", getString(ret_));
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)ONE){\n");
        xml_.append("   $case TWO,THREE;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)TWO){\n");
        xml_.append("   $case TWO,THREE;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("plus", getString(ret_));
    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)THREE){\n");
        xml_.append("   $case TWO,THREE;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("plus", getString(ret_));
    }
    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch(ONE){\n");
        xml_.append("   $case TWO,THREE;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch(TWO){\n");
        xml_.append("   $case TWO,THREE;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("plus", getString(ret_));
    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch(THREE){\n");
        xml_.append("   $case TWO,THREE;\n");
        xml_.append("    t=\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("plus", getString(ret_));
    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)ONE){\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1", getString(ret_));
    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case $int v: v == 1;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case $int v: v == v();\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int v(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case $int v: v() == v;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int v(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)2){\n");
        xml_.append("   $case $int v: v == 1;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2 plus", getString(ret_));
    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)2){\n");
        xml_.append("   $case $int v: v == 1/0;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasDivisionZero(),ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=$switch((Object)1){\n");
        xml_.append("   $case $int v: v == 1;\n");
        xml_.append("    $return v+\"plus\";\n");
        xml_.append("   $default v;\n");
        xml_.append("    $return \"1\";\n");
        xml_.append("  };\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=$switch((Object)1){\n");
        xml_.append("   $case $int v: v == v();\n");
        xml_.append("    $return v+\"plus\";\n");
        xml_.append("   $default v;\n");
        xml_.append("    $return \"1\";\n");
        xml_.append("  };\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int v(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case $int w: w == 2;\n");
        xml_.append("    t=w+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v: v == 1;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)2){\n");
        xml_.append("   $case $int v: v == 1;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case 2;\n");
        xml_.append("    t=\"2 plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("2 plus", getString(ret_));
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)2){\n");
        xml_.append("   $case $int v: v == 1/0;\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DivideZeroException\n" +
                "\n" +
                "pkg/Ex:7,24:138\n" +
                "pkg.Ex.$static exmeth()",((DisplayableStruct)ret_.getStruct()).getDisplayedString(cont_).getInstance());
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case $int w: w == w();\n");
        xml_.append("    t=w+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v: v == v();\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int v(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int w(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case String w: w == w();\n");
        xml_.append("    t=w+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v: v == v();\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int v(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int w(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO,THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"\";\n");
        xml_.append("  $switch((Object)1){\n");
        xml_.append("   $case String w: w == w();\n");
        xml_.append("    t=w+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case Ex w: w == w();\n");
        xml_.append("    t=w+\" plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case $int v: v == v();\n");
        xml_.append("    t=v+\"plus\";\n");
        xml_.append("    $break;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\"1\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int v(){\n");
        xml_.append("  $return 1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int w(){\n");
        xml_.append("  $return 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1plus", getString(ret_));
    }
    @Test
    public void calculateArgumentFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $default v;\n");
        xml_.append("    t=v+\" string\";\n");
        xml_.append("   $case $int v;\n");
        xml_.append("    t=v+\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument0FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $public $static Ex THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case THREE;\n");
        xml_.append("    t=\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $public $static Ex THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $default;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("   $case ONE;\n");
        xml_.append("    t=\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $public $static Ex THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case ONE,ONE;\n");
        xml_.append("    t=\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $public $static Ex THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 1,1;\n");
        xml_.append("    t=\" int\";\n");
        xml_.append("  }\n");
        xml_.append("  $return \"\"+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $public $static Ex THREE;\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  Object t;\n");
        xml_.append("  t=\"8\";\n");
        xml_.append("  $return $switch(t){\n");
        xml_.append("   $case 1;\n");
        xml_.append("   $case 2;\n");
        xml_.append("    $return \" int\";\n");
        xml_.append("   $default d;\n");
        xml_.append("    $return \" int\";\n");
        xml_.append("  };\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
}
