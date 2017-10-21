package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.AccessValueEx;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class RootBlockTest {
    private static final String PUBLIC_ACCESS = "PUBLIC";

    @Test
    public void getAllGenericSuperTypes1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExTwo").getAllGenericSuperTypes(classes_);
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq(Object.class.getName(), superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#S&gt;' superclass='pkg.ExTwo&lt;#S&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExThree").getAllGenericSuperTypes(classes_);
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<#S>", superTypes_.first());
        assertEq("pkg.Ex<#S>", superTypes_.get(1));
        assertEq(Object.class.getName(), superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;java.lang.String&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExThree").getAllGenericSuperTypes(classes_);
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.get(1));
        assertEq(Object.class.getName(), superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' class0='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#S&gt;' class0='pkg.Ex&lt;#S&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg' class0='pkg.ExTwo&lt;java.lang.String&gt;' class1='pkg.ExThree&lt;java.lang.String&gt;'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExFour").getAllGenericSuperTypes(classes_);
        assertEq(4, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.ExThree<java.lang.String>", superTypes_.get(1));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.get(2));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' class0='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#S&gt;' class0='pkg.Ex&lt;#S&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg' class0='pkg.ExTwo&lt;java.lang.Number&gt;' class1='pkg.ExThree&lt;java.lang.String&gt;'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExFour").getAllGenericSuperTypes(classes_);
        assertEq(4, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.Number>", superTypes_.first());
        assertEq("pkg.ExThree<java.lang.String>", superTypes_.get(1));
        assertEq("pkg.Ex<java.lang.Number>", superTypes_.get(2));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
    }

    //TODO use generic super types and generic return types
    @Test
    public void test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }

    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex&lt;java.lang.String&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='java.lang.String'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("java.lang.String", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }

    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#E' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#T' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#E' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex&lt;java.lang.String&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='java.lang.String' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='java.lang.Object' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#E' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;' class0='pkg.Int&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int' package='pkg' template='&lt;#F&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#F'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.get(1));
        assertEq("pkg.Int<#T>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = classes_.getClassBody("pkg.Int").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;' class0='pkg.Int&lt;#T&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int' package='pkg' template='&lt;#F&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#F'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq("pkg.Int<#T>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = classes_.getClassBody("pkg.Int").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T:pkg.Int&lt;java.lang.String&gt;&amp;pkg.Int2&lt;java.lang.String&gt;&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int' package='pkg' template='&lt;#F&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#F'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Int2' package='pkg' template='&lt;#U&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#U'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Int2."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class modifier='abstract' access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='abstract' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface modifier='abstract' access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='abstract' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' class0='pkg.Ex&lt;#T&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#T'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface modifier='abstract' access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='abstract' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#F&gt;' class0='pkg.Ex&lt;#F&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#F'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#T&gt;' class0='pkg.ExTwo&lt;#T&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#F>", superTypes_.first());
        assertEq("pkg.Ex<#F>", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = classes_.getClassBody("pkg.ExThree").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        assertEq(0, classes_.getClassBody("pkg.Ex").getDefaultMethodIds().size());
        ObjectMap<MethodId, String> defs_ = classes_.getClassBody("pkg.ExTwo").getDefaultMethodIds();
        assertEq(0, defs_.size());
        defs_ = classes_.getClassBody("pkg.ExThree").getDefaultMethodIds();
        assertEq(1, defs_.size());
        assertEq("pkg.ExTwo<#T>", defs_.getVal(new MethodId("instancemethod", new EqList<ClassName>(new ClassName("#T", false)))));
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class modifier='abstract' access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='abstract' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(!classes_.getErrorsDet().isEmpty());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface modifier='abstract' access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='abstract' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' class0='pkg.Ex&lt;#T&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), !classes_.getErrorsDet().isEmpty());
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='java.lang.Number' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex' template='&lt;#E:java.lang.Number&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#E' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId("instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='java.lang.Number' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#E' modifier='normal'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), !classes_.getErrorsDet().isEmpty());
    }
    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(_files, cont_);
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
}
