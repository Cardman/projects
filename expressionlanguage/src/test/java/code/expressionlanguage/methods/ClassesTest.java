package code.expressionlanguage.methods;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.common.TypeUtil;
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

    @Test
    public void getMethodBodiesByFormattedId1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList(PrimitiveTypeUtil.PRIM_INT), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq(PrimitiveTypeUtil.PRIM_INT, methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#F"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E... i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
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
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
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
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.String"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(java.lang.Object i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
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
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
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
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(0, methods_.first().getId().getClassNames().size());
        assertTrue(!methods_.first().isVarargs());
    }

    @Test
    public void getMethodBodiesByFormattedId13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod($int i,$int j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod($int i,#E... j){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void instancemethod(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("$int","java.lang.Object"), true);
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
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<GeneMethod> methods_ =TypeUtil.getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }

    @Test
    public void getSortedSuperInterfaces1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        classes_.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(1, s_.size());
        assertEq("pkg.Ex", s_.first());
    }

    @Test
    public void getSortedSuperInterfaces2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        classes_.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(2, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        classes_.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(3, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree:pkg.ExFour{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        classes_.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(4, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.ExFour", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        classes_.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(4, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        classes_.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex","pkg.ExFive"),cont_);
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
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        InterfaceBlock i_ = (InterfaceBlock) context_.getClassBody("pkg.ExFour");
        ObjectMap<MethodId, StringList> sgn_ = toList(TypeUtil.getAllInstanceSignatures(i_, context_));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExFour"),sgn_.getVal(new MethodId(false, "absgetter", new EqList<ClassName>())));
    }

    @Test
    public void getAllOverridingMethods2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        InterfaceBlock i_ = (InterfaceBlock) context_.getClassBody("pkg.Ex");
        ObjectMap<MethodId, StringList> sgn_ = toList(TypeUtil.getAllInstanceSignatures(i_, context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex","pkg.ExFour"),sgn_.getVal(new MethodId(false, "absgetter", new EqList<ClassName>())));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex"),sgn_.getVal(new MethodId(false, "absgetter", new EqList<ClassName>())));
    }

    @Test
    public void getAllOverridingMethods3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{\n");
        xml_.append(" $public $abstract $int absgetter():\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        InterfaceBlock i_ = (InterfaceBlock) context_.getClassBody("pkg.Ex");
        ObjectMap<MethodId, StringList> sgn_ = toList(TypeUtil.getAllInstanceSignatures(i_, context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new MethodId(false,"absgetter", new EqList<ClassName>())));
        sgn_ = toList(RootBlock.getAllOverridingMethods(toId(sgn_), context_));
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new MethodId(false,"absgetter", new EqList<ClassName>())));
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        classes_.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
    private ContextEl unfullValidateOverridingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        cont_.initError();
        classes_.tryBuildBracedClassesBodies(_files, cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().display(), classes_.getErrorsDet().isEmpty());
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
