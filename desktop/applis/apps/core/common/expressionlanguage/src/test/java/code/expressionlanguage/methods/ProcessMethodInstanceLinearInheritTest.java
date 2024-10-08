package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodInstanceLinearInheritTest extends
        ProcessMethodCommon {

    @Test
    public void instanceArgument17Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument171Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $private ($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument172Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $protected (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument18Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument19Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(34, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument20Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
    }

    @Test
    public void instanceArgument20_Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public $int sec=third .ance;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(58, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
    }

    @Test
    public void instanceArgument21Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }

    @Test
    public void instanceArgument22Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }

    @Test
    public void instanceArgument23Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree[] third=$new pkg.ExThree[1i];\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i]=$new pkg.ExThree();\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct elt_ = (((ArrayStruct)field_).getInstance()) [0];
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());



    }

    @Test
    public void instanceArgument24Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree[] third=$new pkg.ExThree[1i];\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i]=$new pkg.ExThree();\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct elt_ = (((ArrayStruct)field_).getInstance()) [0];
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());



    }

    @Test
    public void instanceArgument25Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument26Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }


    @Test
    public void instanceArgument27Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree[] third=$new pkg.ExThree[]{$new pkg.ExThree()};\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct elt_ = (((ArrayStruct)field_).getInstance()) [0];
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }

    @Test
    public void instanceArgument28Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree[][] third=$new pkg.ExThree[1i][1i];\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i][0i]=$new pkg.ExThree();\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct subArray_ = (((ArrayStruct)field_).getInstance()) [0];
        assertEq(ARR_CUST, subArray_.getClassName(cont_));
        Struct[] inter_ = ((ArrayStruct) subArray_).getInstance();
        assertEq(1, inter_.length);
        Struct elt_ = inter_[0];
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }


    @Test
    public void instanceArgument29Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=17i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }

    @Test
    public void instanceArgument30Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append("  sec+=third.doubleValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=17i;\n");
        xml_.append(" $public $normal $int doubleValue(){\n");
        xml_.append("  $return doubleValue($this);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int doubleValue(pkg.ExThree param){\n");
        xml_.append("  $return param.ance;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);

        Struct str_ = instanceNormalCtor("pkg.ExTwo", 1, cont_);
        assertEq("pkg.ExTwo", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(41, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument31Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=17i;\n");
        xml_.append(" $public $normal $int doubleValue(){\n");
        xml_.append("  $return ance;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int inst=$new pkg.ExThree().doubleValue();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExTwo");

        Struct str_ = instanceNormal("pkg.ExTwo", id_, cont_);
        assertEq("pkg.ExTwo", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument32Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int three=17i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree two=$new pkg.ExThree();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExOne {\n");
        xml_.append(" $public pkg.ExTwo one=$new pkg.ExTwo();\n");
        xml_.append(" $public $int four=one.two.three;\n");
        xml_.append("}\n");
        files_.put("pkg/ExOne", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExOne");

        Struct str_ = instanceNormal("pkg.ExOne", id_, cont_);
        assertEq("pkg.ExOne", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExOne", "four"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExEnum inst=$static(pkg.ExEnum).ONE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append(" ONE;\n");
        xml_.append(" $public $int ance=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExEnum", xml_.toString());
        
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExEnum", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExEnum", "ance"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, ((NumberStruct)subField_).intStruct());
    }
    @Test
    public void instanceArgument34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExEnum inst=$static(pkg.ExEnum).ONE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExEnum {\n");
        xml_.append(" $public $static pkg.ExEnum ONE=$new pkg.ExEnum();\n");
        xml_.append(" $public $int ance=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExEnum", xml_.toString());
        
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExEnum", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExEnum", "ance"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, ((NumberStruct)subField_).intStruct());
        assertSame(field_, getStaticField(cont_, new ClassField("pkg.ExEnum", "ONE")));
    }

    @Test
    public void instanceArgument35Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        Struct str_ = instanceNormalCtorParam("pkg.Ex", id_, new IntStruct(8), cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(16, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument36Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $protected ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        Struct str_ = instanceNormalCtorParam("pkg.Ex", id_, new IntStruct(8), cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(16, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument37Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $package ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        Struct str_ = instanceNormalCtorParam("pkg.Ex", id_, new IntStruct(8), cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(16, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument38Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=$static(pkg.ExTwo).getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $package $static $int getter(){\n");
        xml_.append("  $return 16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        Struct str_ = instanceNormalCtorParam("pkg.Ex", id_, new IntStruct(8), cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument39Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $private $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=$static(pkg.ExTwo).getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $package $static $int getter(){\n");
        xml_.append("  $return 16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        Struct str_ = instanceNormalCtorParam("pkg.Ex", id_, new IntStruct(8), cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument40Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $super(16i);\n");
        xml_.append("  inst=i;\n");
        xml_.append("  inst+=getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return inst+1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  sec=getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return sec;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        Struct str_ = instanceNormalCtorParam("pkg.Ex", id_, new IntStruct(8), cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument41Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.ExTwo inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.getter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument42Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkgtwo.ExTwo{\n");
        xml_.append(" $package $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {\n");
        xml_.append(" $package $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" $public pkgtwo.ExTwo inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.getter();\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkgtwo.ExThree");

        Struct str_ = instanceNormal("pkgtwo.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExThree"));
        assertEq("pkgtwo.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkgtwo.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument43Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkgtwo.ExTwo{\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {\n");
        xml_.append(" $package $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" $public pkgtwo.ExTwo inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.getter();\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkgtwo.ExThree");

        Struct str_ = instanceNormal("pkgtwo.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExThree"));
        assertEq("pkgtwo.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkgtwo.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument44Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super.getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument45Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int getter(java.lang.Integer p){\n");
        xml_.append("  $return p+1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(java.lang.Number p){\n");
        xml_.append("  $return p.intValue()+2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=$static(pkg.Ex).getter(5i);\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument46Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $private $static $int getter(java.lang.Integer p){\n");
        xml_.append("  $return p+1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(java.lang.Number p){\n");
        xml_.append("  $return p.intValue()+2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=$static(pkg.Ex).getter(5i);\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument47Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (java.lang.Integer p){\n");
        xml_.append("  inst=p+1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (java.lang.Number p){\n");
        xml_.append("  inst=p.intValue()+2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=$new pkg.Ex(5i).inst;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument48Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $private (java.lang.Integer p){\n");
        xml_.append("  inst=p+1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (java.lang.Number p){\n");
        xml_.append("  inst=p.intValue()+2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance=$new pkg.Ex(5i).inst;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument49Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(16, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument50Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $classchoice(pkg.ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument51Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $classchoice(pkg.ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int absgetter(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.ExTwo inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument52Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $classchoice(pkg.ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int absgetter(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo {\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument53Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $classchoice(pkg.ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int absgetter(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int absgetter(){\n");
        xml_.append("  $return 11i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.ExTwo inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument54Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $classchoice(pkg.ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int absgetter(){\n");
        xml_.append("  $return 9i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExTwo :pkg.ExFour{\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $int inst=16i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int absgetter(){\n");
        xml_.append("  $return 11i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.ExFour inst=$new pkg.ExFour();\n");
        xml_.append(" $public $int ance=inst.absgetter();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(11, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument55Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkgtwo.ExTwo{\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkgtwo.ExTwo {\n");
        xml_.append(" $package $final $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" $public pkgtwo.ExTwo inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.getter();\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkgtwo.ExThree");

        Struct str_ = instanceNormal("pkgtwo.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExThree"));
        assertEq("pkgtwo.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkgtwo.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument56Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public java.lang.Object[] third=$new java.lang.Object[1i];\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public pkg.ExThree elt;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i]=$new pkg.ExThree();\n");
        xml_.append("  elt=$(pkg.ExThree)third[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "elt"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        assertEq(17, ((NumberStruct) getField(field_, new ClassField("pkg.ExThree", "ance"))).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_OBJECT, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct[] inter_ = ((ArrayStruct)field_).getInstance();
        assertEq(1, inter_.length);
        Struct elt_ = inter_[0];
        assertEq("pkg.ExThree", elt_.getClassName(cont_));
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument57Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public java.lang.Object[][] third=$new java.lang.Object[1i][1i];\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public pkg.ExThree elt;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i][0i]=$new pkg.ExThree();\n");
        xml_.append("  elt=$(pkg.ExThree)third[0i][0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "elt"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        assertEq(17, ((NumberStruct) getField(field_, new ClassField("pkg.ExThree", "ance"))).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_ARR_OBJECT, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct[] inter_ = ((ArrayStruct)field_).getInstance();
        assertEq(1, inter_.length);
        Struct elt_ = (((ArrayStruct)inter_[0]).getInstance())[0];
        assertEq("pkg.ExThree", elt_.getClassName(cont_));
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument58Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public java.lang.Object[] third=$new java.lang.Object[1i];\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public java.lang.Object elt;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i]=$new pkg.ExThree();\n");
        xml_.append("  elt=third[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "elt"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        assertEq(17, ((NumberStruct) getField(field_, new ClassField("pkg.ExThree", "ance"))).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_OBJECT, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct[] inter_ = ((ArrayStruct)field_).getInstance();
        assertEq(1, inter_.length);
        Struct elt_ = inter_[0];
        assertEq("pkg.ExThree", elt_.getClassName(cont_));
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument59Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public java.lang.Object[][] third=$new java.lang.Object[1i][1i];\n");
        xml_.append(" $public $int fourth=third.length;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public java.lang.Object elt;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i][0i]=$new pkg.ExThree();\n");
        xml_.append("  elt=third[0i][0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "elt"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        assertEq(17, ((NumberStruct) getField(field_, new ClassField("pkg.ExThree", "ance"))).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_ARR_OBJECT, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct[] inter_ = ((ArrayStruct)field_).getInstance();
        assertEq(1, inter_.length);
        Struct elt_ = (((ArrayStruct)inter_[0]).getInstance())[0];
        assertEq("pkg.ExThree", elt_.getClassName(cont_));
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument60Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=2i*i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument61Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance=34i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument62Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int[] ance=$new $int[]{34i};\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq("[$int", intern_.getClassName(cont_));
        Struct[] a_ = ((ArrayStruct)intern_).getInstance();
        assertEq(1, a_.length);
        Struct int_ = a_[0];
        assertEq(INTEGER, int_.getClassName(cont_));
        assertEq(34, ((NumberStruct)int_).intStruct());
    }
    @Test
    public void instanceArgument63Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ance=2i*i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument64Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public pkg.ExThree fourth=$new pkg.ExThree(third);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=2i*i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (pkg.ExThree i){\n");
        xml_.append("  ance=i.ance+1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(35, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument65Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public pkg.ExThree fourth=$new pkg.ExThree(third);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=2i*i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (pkg.ExThree i){\n");
        xml_.append("  $this.ance=i.ance+1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(35, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument66Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=1i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(1, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument67Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree();\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public $final $int ancetwo;\n");
        xml_.append(" {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=1i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ancetwo=10i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ancetwo=20i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(1, ((NumberStruct)intern_).intStruct());
        intern_ = getField(field_, new ClassField("pkg.ExThree", "ancetwo"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(10, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument68Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=1i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);

        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(1, ((NumberStruct)str_).intStruct());
    }
    @Test
    public void instanceArgument69Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $public $static $final $int ancetwo;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ance=1i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=1i;\n");
        xml_.append("  $if (loc==1i){\n");
        xml_.append("   ancetwo=10i;\n");
        xml_.append("  } $else {\n");
        xml_.append("   ancetwo=20i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);

        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        Struct field_ = getStaticField(cont_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getStaticField(cont_, new ClassField("pkg.ExThree", "ancetwo"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(10, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument70Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public pkg.ExThree fourth=$new pkg.ExThree(third);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public $int ancetwo=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=2i*i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (pkg.ExThree i){\n");
        xml_.append("  $this(i.ance);\n");
        xml_.append("  ancetwo+=ance+1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
        intern_ = getField(field_, new ClassField("pkg.ExThree", "ancetwo"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(2, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(68, ((NumberStruct)intern_).intStruct());
        intern_ = getField(field_, new ClassField("pkg.ExThree", "ancetwo"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(71, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument71Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree third=$new pkg.ExThree(17i);\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append("  sec+=third.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $final $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i<0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=2i*i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return ance;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(58, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        Struct intern_ = getField(field_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(34, ((NumberStruct)intern_).intStruct());
    }
    @Test
    public void instanceArgument72Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree[] third=$new pkg.ExThree[1i];\n");
        xml_.append(" $public pkg.ExThree[] thirdCopy=third.clone();\n");
        xml_.append(" $public pkg.ExThree[] fourth=third;\n");
        xml_.append(" $public pkg.ExThree[] five;\n");
        xml_.append(" $public $boolean eqone = third == fourth;\n");
        xml_.append(" $public $boolean eqtwo = third == thirdCopy;\n");
        xml_.append(" $public $boolean eqthree = $true;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append("  third[0i]=$new pkg.ExThree();\n");
        xml_.append("  five=third.clone();\n");
        xml_.append("  eqthree=third==five;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct elt_ = (((ArrayStruct)field_).getInstance()) [0];
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "thirdCopy"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct)field_).getInstance())[0]);
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        elt_ = (((ArrayStruct)field_).getInstance()) [0];
        intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "five"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        elt_ = (((ArrayStruct)field_).getInstance()) [0];
        intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "eqone"));
        assertEq(BOOLEAN, field_.getClassName(cont_));
        assertTrue(field_);
        field_ = getField(str_, new ClassField("pkg.ExTwo", "eqtwo"));
        assertEq(BOOLEAN, field_.getClassName(cont_));
        assertFalse(field_);
        field_ = getField(str_, new ClassField("pkg.ExTwo", "eqthree"));
        assertEq(BOOLEAN, field_.getClassName(cont_));
        assertFalse(field_);
    }
    @Test
    public void instanceArgument73Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public pkg.ExThree[] third=$new pkg.ExThree[1i];\n");
        xml_.append(" $public pkg.ExThree[] thirdCopy=third.clone();\n");
        xml_.append(" $public pkg.ExThree[] fourth=third;\n");
        xml_.append(" $public pkg.ExThree[] five;\n");
        xml_.append(" $public $int[] six = $new $int[]{0i};\n");
        xml_.append(" $public $boolean eqone = third == fourth;\n");
        xml_.append(" $public $boolean eqtwo = third == thirdCopy;\n");
        xml_.append(" $public $boolean eqthree = $true;\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i+(six[0i]);\n");
        xml_.append("  third[0i]=$new pkg.ExThree();\n");
        xml_.append("  five=third.clone();\n");
        xml_.append("  eqthree=third==five;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(17i);\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $if(i>0){\n");
        xml_.append("   ance=i;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  ance=i;\n");
        xml_.append("  ance+=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "third"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        Struct elt_ = (((ArrayStruct)field_).getInstance()) [0];
        Struct intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "thirdCopy"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct)field_).getInstance())[0]);
        field_ = getField(str_, new ClassField("pkg.ExTwo", "fourth"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        elt_ = (((ArrayStruct)field_).getInstance()) [0];
        intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "five"));
        assertEq(ARR_CUST, field_.getClassName(cont_));
        assertEq(1, (((ArrayStruct)field_).getInstance()).length);
        elt_ = (((ArrayStruct)field_).getInstance()) [0];
        intern_ = getField(elt_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, intern_.getClassName(cont_));
        assertEq(17, ((NumberStruct)intern_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "eqone"));
        assertEq(BOOLEAN, field_.getClassName(cont_));
        assertTrue(field_);
        field_ = getField(str_, new ClassField("pkg.ExTwo", "eqtwo"));
        assertEq(BOOLEAN, field_.getClassName(cont_));
        assertFalse(field_);
        field_ = getField(str_, new ClassField("pkg.ExTwo", "eqthree"));
        assertEq(BOOLEAN, field_.getClassName(cont_));
        assertFalse(field_);
    }
    @Test
    public void instanceArgument174Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument175Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $classchoice(ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument176Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)getter()+parinst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int parinst = 6;\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(11, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument177Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)getter()+parinst+cst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int cst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int parinst;\n");
        xml_.append(" $static{\n");
        xml_.append("  cst = 10;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append(" {\n");
        xml_.append("  parinst = 6;\n");
        xml_.append("  $return;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(21, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument178Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)getter()+parinst+cst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int cst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int parinst;\n");
        xml_.append(" $static{\n");
        xml_.append("  cst = 10;\n");
        xml_.append(" }\n");
        xml_.append(" {\n");
        xml_.append("  parinst = 6;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(21, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument79Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument80Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)get;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int get = 5i;\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument81Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super (17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument82Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super. getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument83Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super.getter ();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1740Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess (ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1741Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo) getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1742Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo)getter ();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1743Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess(ExTwo )getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1744Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $superaccess( ExTwo)getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExFour.ance++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.Ex res = $new pkg.Ex();\n");
        xml_.append("  res.superaccess();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Struct str_ = instanceNormal("pkg.ExThree", id_, cont_);
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree;pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgumentNullPe() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $void execute(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void execute(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.execute();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("execute");
        Struct arg_ = calculateError("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("code.util.exceptions.NullObjectException", arg_.getClassName(cont_));
    }
    @Test
    public void instanceArgumentNullPe2() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $void execute(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void execute(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.$superaccess(Ex)execute();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("execute");
        Struct arg_ = calculateError("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("code.util.exceptions.NullObjectException", arg_.getClassName(cont_));
    }
    @Test
    public void instanceArgumentNullPe3() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $void execute(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void execute(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.$classchoice(ExTwo)execute();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("execute");
        Struct arg_ = calculateError("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("code.util.exceptions.NullObjectException", arg_.getClassName(cont_));
    }
    @Test
    public void instanceArgumentNullPe4() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Object execute;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void execute(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.execute;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("execute");
        Struct arg_ = calculateError("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("code.util.exceptions.NullObjectException", arg_.getClassName(cont_));
    }
    @Test
    public void instanceArgumentNullPe5() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public Object execute;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void execute(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.execute = $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("execute");
        Struct arg_ = calculateError("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("code.util.exceptions.NullObjectException", arg_.getClassName(cont_));
    }
    @Test
    public void instanceArgument9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $void execute(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void execute(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.$classchoice(Ex)execute();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument74Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int infoOne = ++ExTwo.infoTwo;\n");
        xml_.append(" $public $static $int infoThree = infoOne;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int infoTwo;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int res = res();\n");
        xml_.append(" $public $static $int execute(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res(){\n");
        xml_.append("  Ex variable = $null;\n");
        xml_.append("  variable.infoOne = 5i;\n");
        xml_.append("  variable.infoOne += variable.infoThree;\n");
        xml_.append("  $return variable.infoOne;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.ExThree");
        MethodId id_ = getMethodId("execute");
        Struct ret_ = calculateNormal("pkg.ExThree", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void instanceArgumentFailTest() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int p){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument22FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public (){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int p){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
