package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.Options;
import code.util.*;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;


public final class RootBlockTest {

    @Test
    public void getAllGenericSuperTypes1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExTwo").getAllGenericSuperTypes(cont_);
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
    }

    @Test
    public void getAllGenericSuperTypes2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#S> :pkg.ExTwo<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExThree").getAllGenericSuperTypes(cont_);
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#S>", superTypes_.first());
        assertEq("pkg.Ex<#S>", superTypes_.get(1));
    }

    @Test
    public void getAllGenericSuperTypes3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo<java.lang.String>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExThree").getAllGenericSuperTypes(cont_);
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.get(1));
    }

    @Test
    public void getAllGenericSuperTypes4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree<#S> :pkg.Ex<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour :pkg.ExTwo<java.lang.String>:pkg.ExThree<java.lang.String>{}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExFour").getAllGenericSuperTypes(cont_);
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.ExThree<java.lang.String>", superTypes_.get(1));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree<#S> :pkg.Ex<#S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour :pkg.ExTwo<java.lang.Number>:pkg.ExThree<java.lang.String>{}\n");
        files_.put("pkg/ExFour", xml_.toString());
        failValidateOverridingMethods(files_);
    }


    @Test
    public void test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T"));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $private $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(0, map_.size());
        StringList superTypes_;
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.String>{\n");
        xml_.append(" $public $normal $void instancemethod(java.lang.String i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("java.lang.String")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }

    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal #E instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal #T instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal #E instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.String>{\n");
        xml_.append(" $public $normal java.lang.String instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $normal java.lang.Object instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#E> :pkg.Ex{\n");
        xml_.append(" $public $normal #E instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.Int<#T>{\n");
        xml_.append(" $public $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#T")));
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.get(1));
        assertEq("pkg.Int<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#F")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Int");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T"));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Int"));
        res_ = concrete_.getVal("pkg.Int");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.Int", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.Int<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq("pkg.Int<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#F")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:pkg.Int<java.lang.String>&pkg.Int2<java.lang.String>> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int2<#U> {\n");
        xml_.append(" $public $normal $void instancemethod(#U i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int2", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.Ex<#E> {\n");
        xml_.append(" $public $abstract $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $interface pkg.Ex<#E> {\n");
        xml_.append(" $public $abstract $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $interface pkg.Ex<#E> {\n");
        xml_.append(" $public $abstract $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#F> :pkg.Ex<#F>{\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#T> :pkg.ExTwo<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#F>", superTypes_.first());
        assertEq("pkg.Ex<#F>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.ExThree").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.ExTwo");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        r_ = classes_.getClassBody("pkg.Ex");
        concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.Ex<#E> {\n");
        xml_.append(" $public $abstract $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $interface pkg.Ex<#E> {\n");
        xml_.append(" $public $abstract $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $normal java.lang.Number instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#E:java.lang.Number> :pkg.Ex{\n");
        xml_.append(" $public $normal #E instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#E>", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $normal java.lang.Number instancemethod($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#E> :pkg.Ex{\n");
        xml_.append(" $public $normal #E instancemethod($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), !cont_.isEmptyErrors());
    }
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $package $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{\n");
        xml_.append(" $protected $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree :pkg.ExTwo{\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkgtwo/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkgtwo.ExThree").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(3, superTypes_.size());
        assertEq("pkgtwo.ExThree", superTypes_.first());
        assertEq("pkg.ExTwo", superTypes_.get(1));
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        assertEq("pkg.Ex", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
    }
    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $private $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{\n");
        xml_.append(" $package $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        StringList superTypes_;
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExTwo", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList()));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList());
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList());
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList());
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        r_ = classes_.getClassBody("pkg.ExTwo");
        concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(1, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList());
        assertEq("pkg.ExTwo", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.Int<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#T")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#T>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(0, map_.size());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#F")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Int");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.Int", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Int"));
        res_ = concrete_.getVal("pkg.Int");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#F"));
        assertEq("pkg.Int", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> :pkg.Int<#E>{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#T")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#T>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#E>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Int").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#F")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Int<#F>", superTypes_.first());
    }
    @Test
    public void test20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#E> {\n");
        xml_.append(" $private $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(0, map_.size());
        StringList superTypes_;
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#E> {\n");
        xml_.append(" $private $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree<#S> {\n");
        xml_.append(" $protected $normal $void instancemethod(#S i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.ExThree<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_;
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExThree<#T>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        r_ = classes_.getClassBody("pkg.ExThree");
        concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#E> {\n");
        xml_.append(" $private $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree<#S>:Ex<#S> {\n");
        xml_.append(" $protected $normal $void instancemethod(#S i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.ExThree<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_;
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.ExThree<#T>", superTypes_.first());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(3, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        r_ = classes_.getClassBody("pkg.ExThree");
        concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<#E> {\n");
        xml_.append(" $protected $final $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree<#S> {\n");
        xml_.append(" $protected $normal $void instancemethod(#S i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.ExThree<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_;
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq("pkg.ExThree<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        r_ = classes_.getClassBody("pkg.ExThree");
        concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void test24() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $protected $normal #E instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree<#S> {\n");
        xml_.append(" $protected $normal #S instancemethod(#S i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.ExThree<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_;
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq("pkg.ExThree<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        MethodId resId_;
        ClassMethodId res_;
        RootBlock r_ = classes_.getClassBody("pkg.Ex");
        StringMap<ClassMethodId> concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.Ex"));
        res_ = concrete_.getVal("pkg.Ex");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        id_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        r_ = classes_.getClassBody("pkg.ExThree");
        concrete_ = TypeUtil.getConcreteMethodsToCall(r_, id_, cont_);
        assertEq(2, concrete_.size());
        assertTrue(concrete_.contains("pkg.ExTwo"));
        res_ = concrete_.getVal("pkg.ExTwo");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#E"));
        assertEq("pkg.Ex", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
        assertTrue(concrete_.contains("pkg.ExThree"));
        res_ = concrete_.getVal("pkg.ExThree");
        resId_ = new MethodId(MethodAccessKind.INSTANCE,"instancemethod", new StringList("#S"));
        assertEq("pkg.ExThree", res_.getClassName());
        assertEq(resId_, res_.getConstraints(),cont_);
    }
    @Test
    public void overrideFailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U i){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:ExTwo<java.lang.Number,java.lang.Number> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void test25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal $void instancemethod(#T e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#F>:pkg.ExTwo<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour<#U> :pkg.ExThree<#U>{\n");
        xml_.append(" $public $normal $void instancemethod(#U e){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        ObjectMap<MethodId, StringList> map_ = toList(classes_.getClassBody("pkg.ExFour").getAllOverridingMethods());
        assertEq(1, map_.size());
        StringList superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#U")));
        assertEq(4, superTypes_.size());
        assertEq("pkg.ExFour<#U>", superTypes_.first());
        assertEq("pkg.ExThree<#U>", superTypes_.get(1));
        assertEq("pkg.ExTwo<#U>", superTypes_.get(2));
        assertEq("pkg.Ex<#U>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.ExThree").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#F")));
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExThree<#F>", superTypes_.first());
        assertEq("pkg.ExTwo<#F>", superTypes_.get(1));
        assertEq("pkg.Ex<#F>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.ExTwo").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#T")));
        assertEq(2, superTypes_.size());
        assertEq("pkg.ExTwo<#T>", superTypes_.first());
        assertEq("pkg.Ex<#T>", superTypes_.last());
        map_ = toList(classes_.getClassBody("pkg.Ex").getAllOverridingMethods());
        assertEq(1, map_.size());
        superTypes_ = map_.getVal(new MethodId(MethodAccessKind.INSTANCE, "instancemethod", new StringList("#E")));
        assertEq(1, superTypes_.size());
        assertEq("pkg.Ex<#E>", superTypes_.first());
    }
    @Test
    public void testFail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void test2Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $normal $void instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.ExInt<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void test3Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal Object instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $normal String instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.ExInt<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void test4Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal Object instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $final Object instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo<#G> {\n");
        xml_.append(" $public $final Object instancemethod(#G i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>:pkg.ExInt<#T>:pkg.ExIntTwo<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void test5Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $final Object instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public Object instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(classes_.displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void test6Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $final Object instancemethod(#F i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo<#G> {\n");
        xml_.append(" $public $final Object instancemethod(#G i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.ExInt<#T>:pkg.ExIntTwo<#T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test7Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $protected $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test8Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $normal Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<String> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExIntTwo:pkg.ExIntThree{\n");
        xml_.append(" $public $normal Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test9Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $normal Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<String> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:pkg.ExIntTwo&pkg.ExIntThree>{\n");
        xml_.append(" $public $normal Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test10Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append(" $public $normal Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<String> {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:pkg.ExIntTwo:pkg.ExIntThree{\n");
        xml_.append(" $public $normal Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExImp<T:pkg.ExTwo>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test11Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append(" $public $final Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<Number> {\n");
        xml_.append(" $public $final Object instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:pkg.ExIntTwo:pkg.ExIntThree{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExImp<T:pkg.ExTwo>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test12Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append(" $public $normal Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<Number> {\n");
        xml_.append(" $public $normal String instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp<T:pkg.ExIntTwo&pkg.ExIntThree>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test13Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkgtwo.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append(" $package $final Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkgtwo.ExIntThree:pkg.ExInt<Number> {\n");
        xml_.append(" $package $abstract Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp:pkgtwo.ExIntTwo:pkgtwo.ExIntThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test14Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntSupSup<#J> {\n");
        xml_.append(" $public $normal Number instancemethod(#J i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntSup<#I>:ExIntSupSup<?#I[]> {\n");
        xml_.append(" $public $normal Number instancemethod(#I i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo<#G>:pkg.ExInt<Number> {\n");
        xml_.append(" $private $void hidden($int i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal Number instancemethod(#G i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<Number> {\n");
        xml_.append(" $public $normal String instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp<T:pkg.ExIntTwo<?>&pkg.ExIntThree&pkg.ExIntSup<?T>>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test15Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkgtwo.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append(" $package $final Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:pkg.ExInt<Number> {\n");
        xml_.append(" $public $abstract Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp:pkgtwo.ExIntTwo:pkg.ExIntThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test16Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExIntTwo {\n");
        xml_.append(" $public $abstract String instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree:ExIntTwo {\n");
        xml_.append(" $public $final Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp:pkg.ExIntThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test17Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp{\n");
        xml_.append(" $public $abstract String instancemethod(Object i){\n");
        xml_.append("  $return \"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test18Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExIntTwo {\n");
        xml_.append(" $public $final Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntThree {\n");
        xml_.append(" $public $normal String instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp:pkg.ExIntTwo:pkg.ExIntThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test19Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal Integer instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal $void instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test20Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $int instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal Integer instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test21Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{\n");
        xml_.append(" $public $normal Integer instancemethod(#T i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = cont_.getClasses();
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void test22Fail() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExInt<#F> {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExIntTwo:pkg.ExInt<Number> {\n");
        xml_.append(" $package $final Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkgtwo.ExIntThree:pkg.ExInt<Number> {\n");
        xml_.append(" $package $abstract Number instancemethod(Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExImp:pkg.ExIntTwo:pkgtwo.ExIntThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_, false);
        classes_.validateInheritingClasses(cont_, false);
        classes_.validateIds(cont_,false);
        classes_.validateOverridingInherit(cont_, false);
        assertTrue(!cont_.isEmptyErrors());
    }
    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        return cont_;
    }
    private static void failValidateOverridingMethods(StringMap<String> _files) {
        Options opt_ = new Options();
        
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), cont_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), !cont_.isEmptyErrors());
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
