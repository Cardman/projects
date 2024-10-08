package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodInstanceVarArgTest extends ProcessMethodCommon {
    @Test
    public void instanceArgument96Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter($vararg($int),$firstopt(8i),5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(15, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument97Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter(8i,5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(15, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument98Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter(8i,5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($long... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue()*2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(15, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument99Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter(8i,5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int i,$int... j){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=i;\n");
        xml_.append("  $foreach(java.lang.Number e:j){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($long... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue()*2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(15, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument100Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter(8i,5i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int i,$int... j){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=i;\n");
        xml_.append("  $foreach(java.lang.Number e:j){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int i,$long... j){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=i*2i;\n");
        xml_.append("  $foreach(java.lang.Number e:j){\n");
        xml_.append("   t+=e.intValue()*2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(15, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument101Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter($new $int[]{8i,5i});\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... j){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:j){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int i,$long... j){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=i*2i;\n");
        xml_.append("  $foreach(java.lang.Number e:j){\n");
        xml_.append("   t+=e.intValue()*2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(15, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int ordinal(){\n");
        xml_.append("  $return $static($enums).ordinal($this);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal java.lang.String name(){\n");
        xml_.append("  $return $static($enums).name($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $en pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=$static($enums).name(pre);\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("TWO",((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument1021Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Int pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=pre.name();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $normal java.lang.String name(){$return \"OTHER_\"+$enums.name((Ex)$this);}\n");
        xml_.append(" $public $normal $int ordinal(){$return -1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = contextElEnum(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("OTHER_TWO",((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument1022Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Int pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=pre.name();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public java.lang.String name();\n");
        xml_.append(" $public $normal $int ordinal(){$return -1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = contextElEnum(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("TWO",((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int ordinal(){\n");
        xml_.append("  $return $static($enums).ordinal($this);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal java.lang.String name(){\n");
        xml_.append("  $return $static($enums).name($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $en pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public $int inst=$static($enums).ordinal(pre);\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int ordinal(){\n");
        xml_.append("  $return $static($enums).ordinal($this);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal java.lang.String name(){\n");
        xml_.append("  $return $static($enums).name($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public pkg.Int pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=pre.name();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("TWO", ((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int ordinal(){\n");
        xml_.append("  $return $static($enums).ordinal($this);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal java.lang.String name(){\n");
        xml_.append("  $return $static($enums).name($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public pkg.Int pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public $int inst=pre.ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument106Test() {
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
        xml_.append(" $public $int ance=inst.$that.getter();\n");
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
    public void instanceArgument107Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter($vararg($int),$firstopt(8i));\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(10, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument108Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(2, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument109Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer[] array=$new java.lang.Integer[]{1i};\n");
        xml_.append(" $public $int elt=2i;\n");
        xml_.append(" {\n");
        xml_.append("  ref()[0i]=getter($vararg($int));\n");
        xml_.append(" }\n");
        xml_.append(" $public $final java.lang.Object[] ref(){\n");
        xml_.append("  $return array;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int getter($int... i){\n");
        xml_.append("  $int t=0i;\n");
        xml_.append("  $foreach(java.lang.Number e:i){\n");
        xml_.append("   t+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  $return elt+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "array"));
        assertEq(ARR_INTEGER, field_.getClassName(cont_));
        Struct[] array_ = ((ArrayStruct)field_).getInstance();
        assertEq(2, ((NumberStruct) array_[0]).intStruct());
    }
    @Test
    public void instanceArgument110Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkgtwo.ExTwo{\n");
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
    public void instanceArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int ordinal(){\n");
        xml_.append("  $return $static($enums).ordinal($this);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal java.lang.String name(){\n");
        xml_.append("  $return $static($enums).name($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $Enum<pkg.Ex> pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=$static($enums).name(pre);\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("TWO", ((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument112Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super.inst.intValue();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public java.lang.Integer inst=16i;\n");
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
    public void instanceArgument113Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=8i;\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super.inst+0i;\n");
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
    public void instanceArgument114Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int ance=$classchoice(pkg.Ex)inst+0i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExTwo");

        Struct str_ = instanceNormal("pkg.ExTwo", id_, cont_);
        assertEq("pkg.ExTwo", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExTwo", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument115Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.Integer inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int ance=$classchoice(pkg.Ex)inst.intValue();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExTwo");

        Struct str_ = instanceNormal("pkg.ExTwo", id_, cont_);
        assertEq("pkg.ExTwo", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExTwo", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument116Test() {
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
        xml_.append(" $public $int ance=inst.$that.getter();\n");
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
    public void instanceArgument117Test() {
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
        xml_.append(" $public $int ance=(inst.$that.getter());\n");
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
    public void instanceArgument118Test() {
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
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $en pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=pre.$name();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");
        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("TWO",((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $en pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public $int inst=pre.$ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");
        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final String name(){\n");
        xml_.append("  $return $name();\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int ordinal(){\n");
        xml_.append("  $return $ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Int pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public java.lang.String inst=pre.name();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");
        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("TWO",((StringStruct)field_).getInstance());
    }
    @Test
    public void instanceArgument121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex :pkg.Int{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final String name(){\n");
        xml_.append("  $return $name();\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int ordinal(){\n");
        xml_.append("  $return $ordinal();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Int pre=$static(pkg.Ex).TWO;\n");
        xml_.append(" $public $int inst=pre.ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $abstract java.lang.String name();\n");
        xml_.append(" $public $abstract $int ordinal();\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");
        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int... i){\n");
        xml_.append("  first=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        Struct valueOne_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        Struct valueTwo_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", valueOne_.getClassName(cont_));
        assertEq("pkg.Ex", valueTwo_.getClassName(cont_));
        Struct field_;
        field_ = getField(valueOne_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(valueTwo_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex{\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int... i){\n");
        xml_.append("  first=i[0];\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        Struct valueOne_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        Struct valueTwo_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", valueOne_.getClassName(cont_));
        assertEq("pkg.Ex", valueTwo_.getClassName(cont_));
        Struct field_;
        field_ = getField(valueOne_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(valueTwo_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument124Test() {
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
        xml_.append(" $public $int ance=inst.$that .getter();\n");
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
    public void instanceArgument125Test() {
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
        xml_.append(" $public $int ance=inst.$that. getter();\n");
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
    public void instanceArgument126Test() {
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
        xml_.append(" $public $int ance=inst.$that.getter ();\n");
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
    public void coverage170Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().inst($vararg(Ex),$firstopt(null));\n");
        xml_.append(" }\n");
        xml_.append(" public static int inst(Ex... p){\n");
        xml_.append("  return p==null?8:4;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(8, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
}
