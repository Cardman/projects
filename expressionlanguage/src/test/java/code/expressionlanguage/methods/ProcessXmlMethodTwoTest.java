package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EqList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ProcessXmlMethodTwoTest {
    private static final String ARR_NUMBER = "[java.lang.Number";
    private static final String PUBLIC_ACCESS = "PUBLIC";
    private static final String PRIVATE_ACCESS = "PRIVATE";
    private static final String CUST_PKG = "pkg";
    private static final String CUST_ITER = "CustIter";
    private static final String CUST_LIST = "CustList";
    private static final String CUST_ITER_FULL = CUST_PKG+".CustIter";
    private static final String CUST_LIST_FULL = CUST_PKG+".CustList";
    private static final String CUST_ITER_PATH = CUST_PKG+"/CustIter."+Classes.EXT;
    private static final String CUST_LIST_PATH = CUST_PKG+"/CustList."+Classes.EXT;
    private static final String INTEGER = Integer.class.getName();
    @Test
    public void instanceArgument95Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='2i'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, (Number)subField_.getInstance());
    }
    @Test
    public void instanceArgument96Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;(8i)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='[#T'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='#T'>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='$new [#T[](i;.;)'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(ARR_NUMBER, subField_.getClassName(cont_));
        Struct[] nbs_ = (Struct[]) subField_.getInstance();
        assertEq(1, nbs_.length);
        assertEq(8, (Number) nbs_[0].getInstance());
    }
    @Test
    public void instanceArgument97Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;(8i)'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='pkg.ExTwo&lt;java.lang.String&gt;' value='$new pkg.ExTwo&lt;java.lang.String&gt;(8i)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='java.lang.Object'>\n";
        xml_ += "<if condition='$instanceof(&quot;#T&quot;,i;.;)'>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='1i'/>\n";
        xml_ += "</if>\n";
        xml_ += "<else>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='2i'/>\n";
        xml_ += "</else>\n";
        xml_ += "</constructor>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(1, (Number) subField_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq("pkg.ExTwo<java.lang.String>", field_.getClassName(cont_));
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, (Number) subField_.getInstance());
    }
    @Test
    public void instanceArgument98Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExThree&lt;#T&gt;' value='$new pkg.ExThree&lt;#T&gt;()'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='2i'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", subField_.getClassName(cont_));
        Struct subSubField_;
        subSubField_ = subField_.getFields().getVal(new ClassField("pkg.ExThree", "inst"));
        assertEq(INTEGER, subSubField_.getClassName(cont_));
        assertEq(2, (Number)subSubField_.getInstance());
    }
    @Test
    public void instanceArgument99Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument100Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument101Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo' value='$new pkg.ExFour()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get()'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' modifier='abstract' name='ExThree' package='pkg' superclass='pkg.ExTwo'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='abstract'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg' superclass='pkg.ExThree'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExFour", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument102Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;$classchoice$pkg$ExTwo$$get()'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument103Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;$classchoice$pkg$ExTwo&lt;java$lang$Number&gt;$$get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument104Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;$classchoice$pkg$ExTwo$$get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument105Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;$classchoice$pkg$ExTwo$$get(1I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument106Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExThree&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;$super$get(1I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument107Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExThree&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='java.lang.Number'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<affect left='inst;;;get;;;' oper='=' right='3i'/>\n";
        xml_ += "<affect left='inst;;;$super$get;;;' oper='=' right='1i'/>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;$super$get;;;'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#T'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#U'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument108Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;' class0='pkg.Int&lt;#T&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int' package='pkg' template='&lt;#U:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument109Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.Int&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;' class0='pkg.Int&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int' package='pkg' template='&lt;#U:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument110Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExThree&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='java.lang.Number'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<affect left='inst;;;get;;;' oper='=' right='3i'/>\n";
        xml_ += "<affect left='inst;;;$super$get;;;' oper='=' right='1i'/>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;$super$getter()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#T'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#T' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#U'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#U' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument111Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='java.lang.Number'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<affect left='inst;;;$classchoice$pkg$ExThree$$get;;;' oper='=' right='3i'/>\n";
        xml_ += "<affect left='inst;;;get;;;' oper='=' right='1i'/>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;getter()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#T'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#T' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#U'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#U' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument112Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='java.lang.Number'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<affect left='inst;;;$classchoice$pkg$ExThree$$get;;;' oper='=' right='3i'/>\n";
        xml_ += "<affect left='inst;;;get;;;' oper='=' right='1i'/>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;getter()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#T'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#T' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#U'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#U' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument113Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='java.lang.Number'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<affect left='inst;;;$classchoice$pkg$ExThree$$get;;;' oper='=' right='3i'/>\n";
        xml_ += "<affect left='inst;;;get;;;' oper='=' right='1i'/>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;getter()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#T'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#T' modifier='abstract'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#U'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#U' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument114Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get(8I)'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i+i;.;intValue()'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument115Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;pkg.CustNb&gt;' value='$new pkg.ExTwo&lt;pkg.CustNb&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get($new pkg.CustNb(8i))'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:pkg.CustNb&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i+i;.;intValue()'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='CustNb' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='value' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='$int'>\n";
        xml_ += "<affect left='value;;;' oper='=' right='i;.;'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='value;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustNb."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustNb>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument116Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;pkg.CustNb&gt;' value='$new pkg.ExTwo&lt;pkg.CustNb&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get($new pkg.CustInt(8i))'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:pkg.CustNb&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i+i;.;intValue()'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class modifier='abstract' access='"+PUBLIC_ACCESS+"' name='CustNb' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='abstract'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustNb."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='CustInt' package='pkg' superclass='pkg.CustNb'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='value' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='$int'>\n";
        xml_ += "<affect left='value;;;' oper='=' right='i;.;'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='value;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustInt."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustNb>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument117Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;pkg.CustInt&gt;' value='$new pkg.ExTwo&lt;pkg.CustInt&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get($new pkg.CustInt(8i),$new pkg.CustInt(5i))'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:pkg.CustNb&lt;#T&gt;&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T' var1='j' class1='#T'>\n";
        xml_ += "<return expression='1i+i;.;diff(j;.;)'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class modifier='abstract' access='"+PUBLIC_ACCESS+"' name='CustNb' package='pkg' template='&lt;#U:pkg.CustNb&lt;#U&gt;&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='abstract'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='diff' class='$int' modifier='normal' class0='#U' var0='o'>\n";
        xml_ += "<return expression='intValue()-o;.;intValue()'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustNb."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='CustInt' package='pkg' superclass='pkg.CustNb&lt;pkg.CustInt&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='value' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='$int'>\n";
        xml_ += "<affect left='value;;;' oper='=' right='i;.;'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='value;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustInt."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustInt>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument118Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;pkg.CustInt&gt;' value='$new pkg.ExTwo&lt;pkg.CustInt&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;get($new pkg.CustInt(8i),$new pkg.CustInt(5i))'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:pkg.CustNb&lt;#T&gt;&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T' var1='j' class1='#T'>\n";
        xml_ += "<return expression='1i+i;.;diff(j;.;)'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class modifier='abstract' access='"+PUBLIC_ACCESS+"' name='CustNb' package='pkg' template='&lt;#U:pkg.CustNb&lt;#U&gt;&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='abstract'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='diff' class='$int' modifier='abstract' class0='#U' var0='o'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustNb."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='CustInt' package='pkg' superclass='pkg.CustNb&lt;pkg.CustInt&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='value' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='$int'>\n";
        xml_ += "<affect left='value;;;' oper='=' right='i;.;'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='intValue' class='$int' modifier='normal'>\n";
        xml_ += "<return expression='value;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='diff' class='$int' modifier='normal' class0='pkg.CustInt' var0='o'>\n";
        xml_ += "<return expression='intValue()-o;.;intValue()'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/CustInt."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustInt>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument119Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' superclass='pkg.ExTwo'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='normal' name='superaccess' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<return expression='$super$getter()'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='normal' name='getter' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<return expression='2i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.Int'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='normal' name='getter' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<return expression='5i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.Ex' value='$new pkg.Ex()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='inst;;;superaccess()'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExThree");
        ProcessXmlMethod.initializeClass("pkg.ExThree", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.ExThree", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument120Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExTwo&lt;java.lang.Number&gt;($new pkg.ExThree&lt;java.lang.Number&gt;())'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='pkg.ExTwo&lt;java.lang.String&gt;' value='$new pkg.ExTwo&lt;java.lang.String&gt;($new pkg.ExFour&lt;java.lang.Number&gt;())'/>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='java.lang.Object'>\n";
        xml_ += "<if condition='$instanceof(&quot;pkg.ExThree&quot;,i;.;)'>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='1i'/>\n";
        xml_ += "</if>\n";
        xml_ += "<else>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='2i'/>\n";
        xml_ += "</else>\n";
        xml_ += "</constructor>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg' template='&lt;#F&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(1, (Number) subField_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq("pkg.ExTwo<java.lang.String>", field_.getClassName(cont_));
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, (Number) subField_.getInstance());
    }

    @Test
    public void instanceArgument121Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+CUST_LIST_FULL+"&lt;java.lang.Number&gt;' value='$new "+CUST_LIST_FULL+"&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='res' class='$int'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<line expression='inst;;;add(3i)'/>\n";
        xml_ += "<line expression='inst;;;add(1i)'/>\n";
        xml_ += "<line expression='inst;;;add(2i)'/>\n";
        xml_ += "<affect left='res;;;' oper='=' right='inst;;;size()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument122Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+CUST_LIST_FULL+"&lt;java.lang.Number&gt;' value='$new "+CUST_LIST_FULL+"&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='one' class='$int'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='two' class='$int'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='three' class='$int'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<line expression='inst;;;add(3i)'/>\n";
        xml_ += "<line expression='inst;;;add(1i)'/>\n";
        xml_ += "<line expression='inst;;;add(2i)'/>\n";
        xml_ += "<affect left='one;;;' oper='=' right='inst;;;get(0i).intValue()'/>\n";
        xml_ += "<affect left='two;;;' oper='=' right='inst;;;get(1i).intValue()'/>\n";
        xml_ += "<affect left='three;;;' oper='=' right='inst;;;get(2i).intValue()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "one"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "two"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "three"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument123Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+CUST_LIST_FULL+"&lt;java.lang.Number&gt;' value='$new "+CUST_LIST_FULL+"&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='res' class='$int'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<line expression='inst;;;add(3i)'/>\n";
        xml_ += "<line expression='inst;;;add(1i)'/>\n";
        xml_ += "<line expression='inst;;;add(2i)'/>\n";
        xml_ += "<foreach class='java.lang.Number' var='e' expression='inst;;;'>\n";
        xml_ += "<affect left='res;;;' oper='+=' right='e;intValue()'/>\n";
        xml_ += "</foreach>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument124Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+CUST_LIST_FULL+"&lt;java.lang.Number&gt;' value='$new "+CUST_LIST_FULL+"&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='res' class='$int' value='123i'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<foreach class='java.lang.Number' var='e' expression='inst;;;'>\n";
        xml_ += "<affect left='res;;;' oper='+=' right='e;intValue()'/>\n";
        xml_ += "</foreach>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(123, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument125Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='"+CUST_LIST_FULL+"&lt;java.lang.Number&gt;' value='$new "+CUST_LIST_FULL+"&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='res' class='$int'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<line expression='inst;;;add(3i)'/>\n";
        xml_ += "<line expression='inst;;;add(1i)'/>\n";
        xml_ += "<line expression='inst;;;add(2i)'/>\n";
        xml_ += "<affect left='res;;;' oper='=' right='inst;;;iterator().next().intValue()'/>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument126Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='pkg.ExTwo&lt;java.lang.String&gt;'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<try>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='$new pkg.ExTwo&lt;java.lang.String&gt;(8i)'/>\n";
        xml_ += "</try>\n";
        xml_ += "<catch var='e' class='code.expressionlanguage.exceptions.DynamicCastClassException'>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='$new pkg.ExTwo&lt;java.lang.Number&gt;(8i)'/>\n";
        xml_ += "</catch>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='java.lang.Object'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='java.lang.Object'>\n";
        xml_ += "<affect left='inst;;;' oper='=' right='$class(&quot;#T&quot;,i;.;)'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(8, (Number) subField_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertTrue(field_.isNull());
    }

    @Test
    public void instanceArgument127Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<try>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;$classchoice$pkg$ExTwo$$get(\"\")'/>\n";
        xml_ += "</try>\n";
        xml_ += "<catch var='e' class='code.expressionlanguage.exceptions.DynamicCastClassException'>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='2i'/>\n";
        xml_ += "</catch>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "<return expression='1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' class='$int' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "<return expression='3i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='inst' class='pkg.ExTwo&lt;java.lang.Number&gt;' value='$new pkg.ExThree&lt;java.lang.Number&gt;()'/>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='ance' class='java.lang.Number'/>\n";
        xml_ += "<instance>\n";
        xml_ += "<try>\n";
        xml_ += "<affect left='inst;;;$classchoice$pkg$ExThree$$get;;;' oper='=' right='&quot;&quot;'/>\n";
        xml_ += "<affect left='inst;;;get;;;' oper='=' right='1i'/>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='inst;;;getter()'/>\n";
        xml_ += "</try>\n";
        xml_ += "<catch var='e' class='code.expressionlanguage.exceptions.DynamicCastClassException'>\n";
        xml_ += "<affect left='ance;;;' oper='=' right='2i'/>\n";
        xml_ += "</catch>\n";
        xml_ += "</instance>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#T'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#T' modifier='abstract'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExTwo&lt;#U&gt;' template='&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PUBLIC_ACCESS+"' name='get' class='#U'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='getter' class='#U' modifier='normal'>\n";
        xml_ += "<return expression='get;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessXmlMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
    }
    private static Argument instanceArgument(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        int len_ = _id.getClassNames().size();
        EqList<ClassName> constraints_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = _id.getClassNames().get(i).getName();
            constraints_.add(new ClassName(n_,false));
        }
        ConstructorId id_ = new ConstructorId(_id.getName(),constraints_);
        return ProcessXmlMethod.instanceArgument(_class, _global, id_, _args, _cont);
    }

    private static String getCustomList() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' package='"+CUST_PKG+"' name='"+CUST_LIST+"' template='&lt;#U&gt;' class0='"+PredefinedClasses.ITERABLE+"&lt;#U&gt;'>\n";
        xml_ += "<field access='"+PRIVATE_ACCESS+"' name='list' class='[#U'/>\n";
        xml_ += "<field access='"+PRIVATE_ACCESS+"' name='length' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"'>\n";
        xml_ += "<affect left='list;;;' oper='=' right='$new [#U(0i)'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='add' modifier='normal' class='$void' class0='#U' var0='elt'>\n";
        xml_ += "<line expression='add(length;;;,elt;.;)'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='add' modifier='normal' class='$void' class0='$int' var0='index' class1='#U' var1='elt'>\n";
        xml_ += "<declareset class='[#U' var='newlist' expression='$new [#U(length;;;+1i)'/>\n";
        xml_ += "<for init='0i' var='i' class='"+PrimitiveTypeUtil.PRIM_INT+"' expression='index;.;' step='1i'>\n";
        xml_ += "<affect left='newlist;.[i;]' oper='=' right='list;;;[i;]'/>\n";
        xml_ += "</for>\n";
        xml_ += "<affect left='newlist;.[index;.;]' oper='=' right='elt;.;'/>\n";
        xml_ += "<for init='index;.;+1i' var='i' class='"+PrimitiveTypeUtil.PRIM_INT+"' expression='length;;;+1i' step='1i'>\n";
        xml_ += "<affect left='newlist;.[i;]' oper='=' right='list;;;[i;-1i]'/>\n";
        xml_ += "</for>\n";
        xml_ += "<affect left='length;;;' oper='++'/>\n";
        xml_ += "<affect left='list;;;' oper='=' right='newlist;.'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='size' modifier='normal' class='$int'>\n";
        xml_ += "<return expression='length;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='get' modifier='normal' class='#U' class0='$int' var0='index'>\n";
        xml_ += "<return expression='list;;;[index;.;]'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='set' modifier='normal' class='$void' class0='$int' var0='index' class1='#U' var1='elt'>\n";
        xml_ += "<affect left='list;;;[index;.;]' oper='=' right='elt;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='remove' modifier='normal' class='$void' class0='$int' var0='index'>\n";
        xml_ += "<for init='index;.;' var='i' class='"+PrimitiveTypeUtil.PRIM_INT+"' expression='length;;;-1i' step='1i'>\n";
        xml_ += "<affect left='list;;;[i;]' oper='=' right='list;;;[i;+1i]'/>\n";
        xml_ += "</for>\n";
        xml_ += "<affect left='list;;;[length;;;-1i]' oper='=' right='$null'/>\n";
        xml_ += "<affect left='length;;;' oper='--'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='iterator' modifier='normal' class='"+PredefinedClasses.ITERATOR+"&lt;#U&gt;'>\n";
        xml_ += "<return expression='$new "+CUST_ITER_FULL+"&lt;#U&gt;($this)'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>";
        return xml_;
    }
    private static String getCustomIterator() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' package='"+CUST_PKG+"' name='"+CUST_ITER+"' template='&lt;#T&gt;' class0='"+PredefinedClasses.ITERATOR+"&lt;#T&gt;'>\n";
        xml_ += "<field access='"+PRIVATE_ACCESS+"' name='list' class='"+CUST_LIST_FULL+"&lt;#T&gt;'/>\n";
        xml_ += "<field access='"+PRIVATE_ACCESS+"' name='length' class='$int'/>\n";
        xml_ += "<field access='"+PRIVATE_ACCESS+"' name='index' class='$int'/>\n";
        xml_ += "<constructor access='"+PUBLIC_ACCESS+"' var0='i' class0='"+CUST_LIST_FULL+"&lt;#T&gt;'>\n";
        xml_ += "<affect left='list;;;' oper='=' right='i;.;'/>\n";
        xml_ += "<affect left='length;;;' oper='=' right='list;;;size()'/>\n";
        xml_ += "</constructor>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='next' modifier='normal' class='#T'>\n";
        xml_ += "<declareset class='#T' var='out' expression='list;;;get(index;;;)'/>\n";
        xml_ += "<affect left='index;;;' oper='++'/>\n";
        xml_ += "<return expression='out;.'/>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='hasNext' modifier='normal' class='$boolean'>\n";
        xml_ += "<return expression='index;;;&lt;length;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>";
        return xml_;
    }

    private static MethodId getMethodId(String _name, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            cl_.add(new ClassName(c, false));
        }
        return new MethodId(true, _name, cl_);
    }

    private static MethodId getMethodId(String _name, boolean _vararg, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            boolean var_ = _vararg && _classNames.length == cl_.size() + 1;
            cl_.add(new ClassName(c, var_));
        }
        return new MethodId(true, _name, cl_);
    }

    private static ConstructorId getConstructorId(String _name, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            cl_.add(new ClassName(c, false));
        }
        return new ConstructorId(_name, cl_);
    }

    private static ConstructorId getConstructorId(String _name, boolean _vararg, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            boolean var_ = _vararg && _classNames.length == cl_.size() + 1;
            cl_.add(new ClassName(c, var_));
        }
        return new ConstructorId(_name, cl_);
    }
    private ContextEl contextEl(int... _m) {
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = new ContextEl();
        } else {
            ct_ = new ContextEl(_m[0]);
        }
        InitializationLgNames.initAdvStandards(ct_);
        return ct_;
    }
}
