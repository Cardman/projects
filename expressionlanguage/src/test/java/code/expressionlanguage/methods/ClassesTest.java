package code.expressionlanguage.methods;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ClassesTest {
    private static final String PUBLIC_ACCESS = "PUBLIC";

    @Test
    public void getMethodBodiesByFormattedId1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList(PrimitiveTypeUtil.PRIM_INT), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq(PrimitiveTypeUtil.PRIM_INT, methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#F"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getClassNames().size());
        assertEq("$int", methods_.first().getId().getClassNames().first().getName());
        assertEq("#E", methods_.first().getId().getClassNames().last().getName());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId6Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getClassNames().size());
        assertEq("$int", methods_.first().getId().getClassNames().first().getName());
        assertEq("#E", methods_.first().getId().getClassNames().last().getName());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId7Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId8Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId9Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.String"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId10Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='java.lang.Object'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(2, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
        assertEq("instancemethod", methods_.last().getName());
        assertEq(1, methods_.last().getId().getClassNames().size());
        assertEq("java.lang.Object", methods_.last().getId().getClassNames().first().getName());
        assertTrue(!methods_.last().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId11Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getClassNames().size());
        assertEq("$int", methods_.first().getId().getClassNames().first().getName());
        assertEq("#E", methods_.first().getId().getClassNames().last().getName());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId12Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(0, methods_.first().getId().getClassNames().size());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId13Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='$int'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='$int' var1='j' class1='#E...'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getClassNames().size());
        assertEq("$int", methods_.first().getId().getClassNames().first().getName());
        assertEq("#E", methods_.first().getId().getClassNames().last().getName());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId14Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getSortedSuperInterfaces1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(1, s_.size());
        assertEq("pkg.Ex", s_.first());
    }

    @Test
    public void getSortedSuperInterfaces2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(2, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(3, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree' class2='pkg.ExFour'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(4, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.ExFour", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(4, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces6Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex","pkg.ExFive"));
        assertEq(5, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.get(3));
        assertEq("pkg.ExFive", s_.last());
    }

    @Test
    public void getAllOverridingMethods1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        InterfaceBlock i_ = (InterfaceBlock) classes_.getClassBody("pkg.ExFour");
        ObjectMap<MethodId, StringList> sgn_ = toList(i_.getAllInstanceSignatures(context_));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExFour"),sgn_.getVal(new MethodId(false, "absgetter", new EqList<ClassName>())));
    }

    @Test
    public void getAllOverridingMethods2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        InterfaceBlock i_ = (InterfaceBlock) classes_.getClassBody("pkg.Ex");
        ObjectMap<MethodId, StringList> sgn_ = toList(i_.getAllInstanceSignatures(context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex","pkg.ExFour"),sgn_.getVal(new MethodId(false, "absgetter", new EqList<ClassName>())));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex"),sgn_.getVal(new MethodId(false, "absgetter", new EqList<ClassName>())));
    }

    @Test
    public void getAllOverridingMethods3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        InterfaceBlock i_ = (InterfaceBlock) classes_.getClassBody("pkg.Ex");
        ObjectMap<MethodId, StringList> sgn_ = toList(i_.getAllInstanceSignatures(context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new MethodId(false,"absgetter", new EqList<ClassName>())));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new MethodId(false,"absgetter", new EqList<ClassName>())));
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(_files, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
    private ContextEl unfullValidateOverridingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        InitializationLgNames.initAdvStandards(cont_);
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(_files, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }

    private ObjectMap<MethodId, StringList> toList(ObjectMap<MethodId, EqList<ClassMethodId>> _m) {
        ObjectMap<MethodId, StringList> m_ = new ObjectMap<MethodId, StringList>();
        for (EntryCust<MethodId, EqList<ClassMethodId>> e: _m.entryList()) {
            StringList l_ = new StringList();
            for (ClassMethodId c: e.getValue()) {
                l_.add(c.getClassName());
            }
            m_.put(e.getKey(), l_);
        }
        return m_;
    }

    private ObjectMap<MethodId, EqList<ClassMethodId>> toId(ObjectMap<MethodId, StringList> _m) {
        ObjectMap<MethodId, EqList<ClassMethodId>> m_ = new ObjectMap<MethodId, EqList<ClassMethodId>>();
        for (EntryCust<MethodId, StringList> e: _m.entryList()) {
            EqList<ClassMethodId> l_ = new EqList<ClassMethodId>();
            for (String c: e.getValue()) {
                l_.add(new ClassMethodId(c, e.getKey()));
            }
            m_.put(e.getKey(), l_);
        }
        return m_;
    }
}
