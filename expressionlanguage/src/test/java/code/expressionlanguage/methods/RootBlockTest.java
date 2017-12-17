package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.AccessValueEx;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.MethodId;
import code.util.EntryCust;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class RootBlockTest {
    private static final String PUBLIC_ACCESS = "PUBLIC";
    private static final String PROTECTED_ACCESS = "PROTECTED";
    private static final String PACKAGE_ACCESS = "PACKAGE";
    private static final String PRIVATE_ACCESS = "PRIVATE";

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
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
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
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#S>", superTypes_.first());
        assertEq("pkg.Ex<#S>", superTypes_.get(1));
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
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.get(1));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false)));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("java.lang.String", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.get(1));
        assertEq("pkg.Int<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
        MethodId id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Int");
        StringMap<ClassMethodId> concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(1, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false)));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq("pkg.Int<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#F>", superTypes_.first());
        assertEq("pkg.Ex<#F>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.ExThree").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        MethodId id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        StringMap<ClassMethodId> concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(1, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
        id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        r_ = classes_.getClassBody("pkg.Ex");
        concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(1, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false,"instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='java.lang.Number' var0='i' modifier='normal' class0='$int'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='#E' var0='i' modifier='normal' class0='$int'>\n";
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
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PACKAGE_ACCESS+"' name='instancemethod' modifier='normal' class='$void'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex'>\n";
        xml_ += "<method access='"+PROTECTED_ACCESS+"' name='instancemethod' modifier='normal' class='$void'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkgtwo' superclass='pkg.ExTwo'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' modifier='normal' class='$void'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkgtwo/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateOverridingInherit(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkgtwo.ExThree").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(3, superTypes_.size());
        assertEq("pkgtwo.ExThree", superTypes_.first());
        assertEq("pkg.ExTwo", superTypes_.get(1));
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PRIVATE_ACCESS+"' name='instancemethod' modifier='normal' class='$void'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' superclass='pkg.Ex'>\n";
        xml_ += "<method access='"+PACKAGE_ACCESS+"' name='instancemethod' modifier='normal' class='$void'>\n";
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#T>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(0, map_.size());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
        MethodId id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Int");
        StringMap<ClassMethodId> concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(1, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        assertEq("pkg.Int", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
    }
    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;' class0='pkg.Int&lt;#E&gt;'>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'>\n";
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
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#T", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#T>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#E", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(false, "instancemethod", new EqList<ClassName>(new ClassName("#F", false))));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
    }
    @Test
    public void testMockOverrides() {
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
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        MethodId geneId_;
        ClassMethodId geneClassId_;
        MethodId realId_;
        map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        map_.clear();
        geneId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false)));
        realId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#T", false)));
        geneClassId_ = new ClassMethodId("pkg.ExTwo<#T>",realId_);
        map_.put(geneId_, new EqList<ClassMethodId>(geneClassId_));
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        map_.clear();
        geneId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        realId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        geneClassId_ = new ClassMethodId("pkg.Ex<#E>",realId_);
        map_.put(geneId_, new EqList<ClassMethodId>(geneClassId_));
        MethodId id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(2, concrete_.size());
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
    }
    @Test
    public void testMockOverrides2() {
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
        ObjectMap<MethodId, EqList<ClassMethodId>> map_;
        MethodId geneId_;
        ClassMethodId geneClassId_;
        ClassMethodId geneClassIdTwo_;
        MethodId realId_;
        map_ = classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods();
        map_.clear();
        geneId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        realId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        geneClassId_ = new ClassMethodId("pkg.ExTwo<#F>",realId_);
        realId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        geneClassIdTwo_ = new ClassMethodId("pkg.Ex<#F>",realId_);
        map_.put(geneId_, new EqList<ClassMethodId>(geneClassId_,geneClassIdTwo_));
        map_ = classes_.getClassBody("pkg.Ex").getAllOverridingMethods();
        map_.clear();
        geneId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        realId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        geneClassId_ = new ClassMethodId("pkg.Ex<#E>",realId_);
        map_.put(geneId_, new EqList<ClassMethodId>(geneClassId_));
        MethodId id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        StringMap<ClassMethodId> concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(1, concrete_.size());
        MethodId resId_;
        ClassMethodId res_;
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
        id_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#E", false)));
        r_ = classes_.getClassBody("pkg.Ex");
        concrete_ = r_.getConcreteMethodsToCall(id_, cont_);
        assertEq(1, concrete_.size());
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(false,"instancemethod", new EqList<ClassName>(new ClassName("#F", false)));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints());
    }
    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        classes_.tryBuildClassesBodies(_files, cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
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
}
