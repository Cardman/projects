package code.expressionlanguage.inherits;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.common.GeneMethod;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class TypeUtilTest {
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger()), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#F"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), false);
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.String"), false);
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"), false);
        assertEq(2, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getParametersTypes().size());
        assertEq("#E", methods_.first().getId().getParametersTypes().first());
        assertTrue(!methods_.first().isVarargs());
        assertEq("instancemethod", methods_.last().getName());
        assertEq(1, methods_.last().getId().getParametersTypes().size());
        assertEq("java.lang.Object", methods_.last().getId().getParametersTypes().first());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(), false);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(0, methods_.first().getId().getParametersTypes().size());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<java.lang.Object>", "instancemethod", new StringList(context_.getStandards().getAliasPrimInteger(),"java.lang.Object"), true);
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(2, methods_.first().getId().getParametersTypes().size());
        assertEq(context_.getStandards().getAliasPrimInteger(), methods_.first().getId().getParametersTypes().first());
        assertEq("#E", methods_.first().getId().getParametersTypes().last());
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
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }
    @Test
    public void getMethodBodiesByFormattedId15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkg.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkg.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }
    @Test
    public void getMethodBodiesByFormattedId16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$protected $class pkgtwo.Ex<#E> {\n");
        xml_.append(" $public $normal $void instancemethod(#E i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> :pkgtwo.Ex<#T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        CustList<MethodBlock> methods_ =getMethodBodiesByFormattedId(context_, false, "pkg.Ex<#F>", "instancemethod", new StringList("#G"), false);
        assertEq(0, methods_.size());
    }
    private ContextEl unfullValidateOverridingClasses(StringMap<String> _files) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes classes_ = cont_.getClasses();
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(_files, cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateInheritingClasses(cont_, false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        classes_.validateIds(cont_,false);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        return cont_;
    }
    private static CustList<MethodBlock> getMethodBodiesByFormattedId(ContextEl _context, boolean _static, String _genericClassName, String _methodName, StringList _parametersTypes, boolean _vararg) {
        CustList<MethodBlock> ms_ = new CustList<MethodBlock>();
        for (GeneMethod g: TypeUtil.getMethodBodiesByFormattedId(_context, _static, _genericClassName, _methodName, _parametersTypes, _vararg)) {
            if (g instanceof MethodBlock) {
                ms_.add((MethodBlock) g);
            }
        }
        return ms_;
    }
}
