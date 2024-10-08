package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.structs.*;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodInstanceEnumTest extends ProcessMethodCommon {

    @Test
    public void initializeClass1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        ProcessMethod.initializeClass("pkg.Ex",cont_.getClasses().getClassBody("pkg.Ex"),cont_, stackCall_);
    }

    @Test
    public void initializeClass2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
    }

    @Test
    public void initializeClass3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE;\n");
        xml_.append(" $public $int first=4i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i);\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $static $int stFirst;\n");
        xml_.append(" $static {\n");
        xml_.append("  $try {\n");
        xml_.append("   stFirst=1i/0i;\n");
        xml_.append("  } $catch (java.lang.Object e) {\n");
        xml_.append("   stFirst++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "stFirst"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(1, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void initializeClass8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Super {\n");
        xml_.append(" $protected $int sup;\n");
        xml_.append(" $protected Super($int sup){\n");
        xml_.append("  $this.sup = sup;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.Ex:Super {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(i+2);\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Super", "sup"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Super", "sup"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{$public $int first=4i;};\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex-ONE", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{(){$super(4);}};\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append(" $public $static $int out = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int custMethod();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "out"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(4, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void initializeClass12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append(" $public $static $int out = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int custMethod();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "out"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void initializeClass13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO;\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  first = 5;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outOne"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outTwo"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(5, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void initializeClass14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO{\n");
        xml_.append(" (){$super(5);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 10, w = 7;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }};\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outOne"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outTwo"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(22, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void initializeClass15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExOther {}\n");
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO<ExOther>{\n");
        xml_.append(" (){$super(5);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 10, w = 7;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }};\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outOne"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outTwo"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(22, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex-TWO", str_.getClassName(cont_));
    }
    @Test
    public void initializeClass151Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExOther {}\n");
        xml_.append("public enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" (){super(4);}\n");
        xml_.append(" public int custMethod(){\n");
        xml_.append("  int v = 8, w = 6;\n");
        xml_.append("  return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO<ExOther>{\n");
        xml_.append(" (){super(5);}\n");
        xml_.append(" public int custMethod(){\n");
        xml_.append("  int v = 10, w = 7;\n");
        xml_.append("  return first+v+w;\n");
        xml_.append(" }};\n");
        xml_.append(" public static int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" public static int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" public int first;\n");
        xml_.append(" public Ex(int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" public int custMethod(){\n");
        xml_.append("  return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outOne"));
        assertEq("$core.Integer", str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outTwo"));
        assertEq("$core.Integer", str_.getClassName(cont_));
        assertEq(22, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex-TWO", str_.getClassName(cont_));
    }
    @Test
    public void initializeClass16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Super {\n");
        xml_.append(" $protected $int sup;\n");
        xml_.append(" $protected Super($int sup){\n");
        xml_.append("  $this.sup = sup;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.SuperInt {\n");
        xml_.append(" $protected $int supTwo;\n");
        xml_.append(" $protected SuperInt($int sup){\n");
        xml_.append("  supTwo = sup;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.Ex:Super:SuperInt {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(i+2);\n");
        xml_.append("  $interfaces(SuperInt)(i+4);\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Super", "sup"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.SuperInt", "supTwo"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Super", "sup"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.SuperInt", "supTwo"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());

    }
    @Test
    public void initializeClass17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExOther {}\n");
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" (){$super(4);second=\"\";}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO<ExOther>{\n");
        xml_.append(" (){$super(5);second=$new ExOther();}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 10, w = 7;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }};\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public T second;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outOne"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outTwo"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(22, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        assertEq(STRING, getField(str_, new ClassField("pkg.Ex", "second")).getClassName(cont_));
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex-TWO", str_.getClassName(cont_));
        assertEq("pkg.ExOther", getField(str_, new ClassField("pkg.Ex", "second")).getClassName(cont_));
    }

    @Test
    public void initializeClass18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i);\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=$stack.current();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,2:24\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
        assertEq("pkg/Ex:7,17:126\npkg.Ex.($int)", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[1]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i,$stack.current());\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i,$stack[] st){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=st;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(1, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,16:38\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4){($int i){$super(i);}};\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=$stack.current();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(3, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,2:24\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
        assertEq("pkg/Ex:2,24:46\npkg.Ex-ONE.($int)", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[1]).getDisplayedString(cont_).getInstance());
        assertEq("pkg/Ex:7,17:147\npkg.Ex.($int)", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[2]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{(){$super(4i,$stack.current());}};\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i,$stack[] st){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=st;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,2:24\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
        assertEq("pkg/Ex:2,26:48\npkg.Ex-ONE.()", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[1]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4,$stack.current()){($int i,$stack[] st){$super(i,st);}};\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i,$stack[] st){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=st;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(1, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,15:37\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i,tr());\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i, $stack[] st){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=st;\n");
        xml_.append(" }\n");
        xml_.append(" $private $static $stack[] tr(){\n");
        xml_.append("  $return $stack.current();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,9:31\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
        assertEq("pkg/Ex:10,18:194\npkg.Ex.$static tr()", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[1]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4,tr()){($int i,$stack[] st){$super(i,st);}};\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $stack[] second;\n");
        xml_.append(" $public ($int i,$stack[] st){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=st;\n");
        xml_.append(" }\n");
        xml_.append(" $private $static $stack[] tr(){\n");
        xml_.append("  $return $stack.current();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("[java.lang.$stack", field_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)field_).getInstance().length);
        assertEq("pkg/Ex:2,8:30\npkg.Ex.", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[0]).getDisplayedString(cont_).getInstance());
        assertEq("pkg/Ex:10,18:229\npkg.Ex.$static tr()", ((StackTraceElementStruct)((ArrayStruct)field_).getInstance()[1]).getDisplayedString(cont_).getInstance());
    }

    @Test
    public void initializeClass25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $int first=4i;};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex..ExInner-ONE", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $int first=4i;};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = ExInner.ONE.first;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex..ExInner-ONE", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void initializeClass27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = ExInner.ONE.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static $int field = INSTANCE.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = pkg.Ex.ExInner..ONE.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = Ex.ExInner..ONE.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = ExInner..ONE.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = $static(ONE).first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $final $int FIRST = 6;};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = ExInner..ONE.FIRST;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void initializeClass34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $static $boolean field = ExInner.ONE $instanceof ExInner..ONE;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertTrue(field_);
    }
    @Test
    public void initializeClass35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $static $boolean field = method($id(Ex,$static,ExInner..ONE),ExInner.ONE);\n");
        xml_.append("$public $static $boolean method(ExInner..ONE e){\n");
        xml_.append(" $return e $instanceof ExInner..ONE;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertTrue(field_);
    }
    @Test
    public void initializeClass36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExOther {}\n");
        xml_.append("$public $enum pkg.Ex<T>:ExSuper {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO<ExOther>{\n");
        xml_.append(" (){$super(5);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 10, w = 7;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }};\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSuper {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outOne"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(18, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "outTwo"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(22, ((NumberStruct)str_).intStruct());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex-ONE", str_.getClassName(cont_));
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex-TWO", str_.getClassName(cont_));
    }
    @Test
    public void initializeClass4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $static $class Sample{}\n");
        xml_.append(" },TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  first = 5;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex..ONE.Sample INSTANCE = $new Ex..ONE.Sample();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "INSTANCE"));
        assertEq("pkg.Ex-ONE..Sample", str_.getClassName(cont_));
    }
    @Test
    public void initializeClass1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public $static $int stFirst;\n");
        xml_.append(" $static {\n");
        xml_.append("   stFirst=1i/0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,cont_);
        ProcessMethod.initializeClass("pkg.Ex", classBody_, cont_, stackCall_);
        InitClassState state_ = cont_.getLocks().getState(classBody_);
        assertSame(InitClassState.ERROR, state_);
        Struct exc_ = getTrueException(stackCall_);
        assertTrue(exc_ instanceof CausingErrorStruct);
        CausingErrorStruct cause_ = (CausingErrorStruct) exc_;
        Struct c_ = cause_.getCause();
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",c_.getClassName(cont_));
    }

    @Test
    public void initializeClass2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {\n");
        xml_.append(" $int $m();\n");
        xml_.append(" $int $m();\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" $ONE,$ONE;\n");
        xml_.append(" $public $int $first;\n");
        xml_.append(" $public $int $first;\n");
        xml_.append(" $public $int ,;\n");
        xml_.append(" $public $int +;\n");
        xml_.append(" $public (){\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $m($int $p,$int $p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $m($int $p,$int $p){\n");
        xml_.append(" }\n");
        xml_.append(" $public String $name(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $ordinal(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex valueOf(String p){\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex[] values(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int $toString(){\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString($int p){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $private String $toString(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO;\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  first = 5;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int custMethod();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void initializeClass5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExOther {}\n");
        xml_.append("$public $enum pkg.Ex<T> {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" (){$super(4);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  T x;\n");
        xml_.append("  $int v = 8, w = 6;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }\n");
        xml_.append(" },TWO<ExOther>{\n");
        xml_.append(" (){$super(5);}\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $int v = 10, w = 7;\n");
        xml_.append("  $return first+v+w;\n");
        xml_.append(" }};\n");
        xml_.append(" $public $static $int outOne = Ex.values()[0].custMethod();\n");
        xml_.append(" $public $static $int outTwo = Ex.values()[1].custMethod();\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  first = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int custMethod(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..TWO INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static $int field = INSTANCE.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = pkg.Ex.ExInner..TWO.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = Ex.ExInner..TWO.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = ExInner..TWO.first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $int first($int add){$return 4i+add;}};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = $static(TWO).first(2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{$public $static $final $int FIRST = 6;};\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = ExInner..TWO.FIRST;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass12FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $static $boolean field = ExInner.ONE $instanceof ExInner..TWO;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass13FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex.ExInner..ONE;\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{};\n");
        xml_.append("}\n");
        xml_.append("$public $static $boolean field = ExInner.ONE $instanceof Object..TWO;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass14FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner<S> {\n");
        xml_.append(" ONE<$int>{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public InnerInner(T p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner<$int>..ONE.InnerInner<$int> INSTANCE2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass15FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner<S> {\n");
        xml_.append(" ONE<$int>{\n");
        xml_.append(" $public $class InnerInner{\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public InnerInner($int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner<$int>..ONE.InnerInner INSTANCE2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass16FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner<S> {\n");
        xml_.append(" ONE<$int>{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public InnerInner(T p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE<$int>.InnerInner<$int> INSTANCE2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void initializeClass17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner<S> {\n");
        xml_.append(" ONE<$int>{\n");
        xml_.append(" $public $class InnerInner{\n");
        xml_.append("  $public $int field;\n");
        xml_.append("  $public InnerInner($int p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE<$int>.InnerInner INSTANCE2;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
