package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.opers.exec.*;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.VariableSuffix;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class AnalyzedOperationNodesTest {
    private static final String COMPOSITE = "code.formathtml.classes.Composite";
    private static final String MY_CLASS = "myimpl.MyClass";
    private static final String MY_GENE_CLASS = "myimpl.MyGeneClass";
    @Test
    public void processEl37Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenTwo($null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.String", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl38Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenTwo((java.lang.Object)$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Object", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl40Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1L)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl41Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl42Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0D)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Double", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl43Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0d)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$double", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl44Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0F)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Double", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl45Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0f)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$double", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl111Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFour($null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl113Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFour(1L)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl114Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFour(1l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl115Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFive(1L)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenFive", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl116Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFive(1l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenFive", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$double", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl117Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSix(1L)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenSix", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl118Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSix(1l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenSix", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl119Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSeven(1l,1l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenSeven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$long", params_.first());
        assertEq("$long", params_.last());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl120Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSeven(1l,1L)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenSeven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$long", params_.first());
        assertEq("$long", params_.last());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl121Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSeven(1l,1D)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenSeven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$long", params_.first());
        assertEq("$double", params_.last());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl122Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenEight(1,2)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenEight", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$long", params_.last());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl1221Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenEight1(1,2)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenEight1", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$long", params_.last());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl1222Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenEight2(1,2)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenEight2", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(3, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.get(1));
        assertEq("$int", params_.last());
        assertEq(2, fct_.getNaturalVararg());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl1223Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenEight3(1,2)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenEight3", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(3, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.get(1));
        assertEq("$int", params_.last());
        assertEq(2, fct_.getNaturalVararg());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl1224Test() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("code.formathtml.classes.CompositeImported");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        CustList<ExecOperationNode> opers_ = f_.getExp();
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("code.formathtml.classes.CompositeImported", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenEight3", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(3, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.get(1));
        assertEq("$int", params_.last());
        assertEq(2, fct_.getNaturalVararg());
        assertTrue(id_.isVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl123Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenNine(1,2)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOverridenNine", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$long", params_.last());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl124Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sample(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl125Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleTwo(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl126Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<java.lang.Integer>");
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sample(1)", "myvar", g_, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#U", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl127Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<java.lang.Integer>");
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleTwo(1)", "myvar", g_, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#V", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    
    @Test
    public void processEl128Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getSampleVararg($vararg($int))", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getSampleVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl129Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sample($null)", "W","myvar", g_, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#U", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl130Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sampleTwo($null)", "W","myvar", g_, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#V", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl131Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sample((W)$null)", "W","myvar", g_, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#U", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl132Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sampleTwo((W)$null)", "W","myvar", g_, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#V", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl133Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(($int[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    
    @Test
    public void processEl134Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(($long[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl135Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTwo(($int[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl136Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTwo(($long[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("[$long", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl137Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargThree(1i,($int[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl138Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargThree(1i,($long[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("[$long", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl139Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTwo(5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl140Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl141Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(5,6l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl142Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFour(5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl143Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFour(5,6l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl144Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFive(5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFive", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl145Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFive(5,6l)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFive", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$long", params_.last());
        assertTrue(id_.isVararg());
        assertEq(1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl146Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSix(5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargSix", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl147Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSix(5l,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargSix", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl148Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSeven(4,5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargSeven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(3, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.get(1));
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(2, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl149Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSeven(4,5l,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargSeven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$long", params_.last());
        assertTrue(id_.isVararg());
        assertEq(1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl150Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEight(4,5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargEight", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(3, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.get(1));
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(2, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl151Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEight(4l,5l,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargEight", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$long", params_.first());
        assertEq("$long", params_.last());
        assertTrue(id_.isVararg());
        assertEq(1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl152Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargNine(4,5,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargNine", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl153Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargNine(4,5l,6)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargNine", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(3, params_.size());
        assertEq("$long", params_.first());
        assertEq("$long", params_.get(1));
        assertEq("$long", params_.last());
        assertTrue(id_.isVararg());
        assertEq(2, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl154Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTen(4,($int[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTen", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    
    @Test
    public void processEl155Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTen(4I,($long[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTen", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("java.lang.Integer", params_.first());
        assertEq("$long", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl156Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleThree(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MySuperOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleThree", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl157Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleFour(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MySuperOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl158Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleFive(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleFive", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl159Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEleven((java.lang.Integer)4,(java.lang.Integer[])$null)", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargEleven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("java.lang.Integer", params_.first());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl160Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleSix(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleSix", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl1601Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleSeven(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleSeven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl1602Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleEight(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleEight", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl1603Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleNine(1)", "myvar", MY_CLASS, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sampleNine", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl161Test() {
        CustList<ExecOperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEleven($vararg(java.lang.Integer),0i,$firstopt(0i))", "composite", COMPOSITE, false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargEleven", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("$int", params_.first());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl162Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:Number> {\n");
        xml_.append(" $public $normal $int get(String i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<?>");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVars("myvar.get($null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.String", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl163Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:Number> {\n");
        xml_.append(" $public $normal $int get(String i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<?>");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get($null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.String", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl164Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<java.lang.Number>");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#T", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl165Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Number... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl166Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl167Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public $normal $long get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExThree:ExTwo {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExThree", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl168Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public $normal $long get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo:ExThree {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExThree", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl169Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl170Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public $normal $long get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo:ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExFour", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl171Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo:ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExFour", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl172Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree:ExFive {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo:ExFive {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo:ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExFour", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl173Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:Number> {\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<java.lang.Number>");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#T", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl174Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Object... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Object[]... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Object[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Object", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl175Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Object[]... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Object... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Object[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Object", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl176Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Object... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Object j,Object... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get($null,$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("java.lang.Object", params_.first());
        assertEq("java.lang.Object", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl177Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Object j,Object... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Object... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get($null,$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(2, params_.size());
        assertEq("java.lang.Object", params_.first());
        assertEq("java.lang.Object", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl178Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl179Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl180Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Integer", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl181Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExThree.get;\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {get($null,$null):}\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl182Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExThree.get;\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {get($null,$null):}\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl183Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append("  $static {get($null,$null):}\n");
        xml_.append("  $public $static $int get(Number... i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo..Inner");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo..Inner", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl184Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $private $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl185Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get($id(pkg.ExTwo,$static,Number...),(Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl186Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:ExPar {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExPar {\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get($id(pkg.ExPar,$static,Number...),(Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExPar", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl187Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $private $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get((Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl188Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {pkg.ExTwo.get($null,$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.ExPar {\n");
        xml_.append("  $public $static $int get(Number... i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExPar {\n");
        xml_.append("  $static $int get(Integer... i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl189Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExThree.get;\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {get($id(ExThree,$static,Number...),$null,$null):}\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExThree", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl190Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:ExPar {\n");
        xml_.append(" $public $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExPar {\n");
        xml_.append(" $public $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo");
        CustList<ExecOperationNode> opers_ =  analyzeIndirectLocalVarsParamFirst("myvar.get($id(pkg.ExPar,Number...),(Integer[])$null)", "myvar", g_, xml_.toString(), false);
        ExecFctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExPar", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl191Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {pkg.ExTwo.get:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.ExPar {\n");
        xml_.append("  $public $static $int get:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExPar {\n");
        xml_.append("  $static $int get:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSettableFieldOperation fct_ = getField(f_.getExp());
        assertNotNull(fct_);
        ClassField cid_ = fct_.getFieldId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        assertEq("get", cid_.getFieldName());
    }
    @Test
    public void processEl192Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {pkg.ExTwo.get:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.ExPar {\n");
        xml_.append("  $private $static $int get:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExPar {\n");
        xml_.append("  $public $static $int get:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSettableFieldOperation fct_ = getField(f_.getExp());
        assertNotNull(fct_);
        ClassField cid_ = fct_.getFieldId();
        assertEq("pkgtwo.ExPar", cid_.getClassName());
        assertEq("get", cid_.getFieldName());
    }
    @Test
    public void processEl193Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($vararg($int),$firstopt(0)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public ExTwo($int... v){}\n");
        xml_.append("  $public ExTwo($int v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.last());
        assertTrue(id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl193FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($vararg($int),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public ExTwo($int... v){}\n");
        xml_.append("  $public ExTwo($int v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl194Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo(0):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public ExTwo($int... v){}\n");
        xml_.append("  $public ExTwo($int v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl195Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.ExTwo..Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo..Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl196Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.ExTwo..Inner<String>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner<T> {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo..Inner<String> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner<java.lang.String>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl197Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.ExTwo..Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo..Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl198Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.ExTwo..Inner<String>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $class Inner<T> {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo..Inner<String> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner<java.lang.String>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl199Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,pkg.ExTwo<#T>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<T> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<#T>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl200Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,pkg.ExTwo..Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo..Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl201Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,java.lang.String[]),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public ExTwo(java.lang.String[] v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("[java.lang.String", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl202Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,pkg.ExTwo<?>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<?> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<?>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl203Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,pkg.ExTwo<?Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<?Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<?java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl204Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,pkg.ExTwo<!Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<!Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<!java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl205Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,$Fct<$void>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo($Fct<$void> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.$Fct<$void>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl206Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,ExTwo<!Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<!Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<!java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl207Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,ExTwo<!Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<!Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<!java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl208Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,ExTwo<!Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<!Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<!java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl209Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,ExTwo<!Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<!Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<!java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl210Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExTwo;pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<String>($id(pkg.ExTwo,ExTwo<!Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public ExTwo(pkg.ExTwo<!Number> v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo<!java.lang.Number>", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl211Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExTwo;pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" $static {$classchoice(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecChoiceFctOperation fct_ = getChoiceFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl212Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExTwo;pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" {$classchoice(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecChoiceFctOperation fct_ = getChoiceFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl213Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExTwo;pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" {ExTwo e = $new ExTwo():e;.$classchoice(ExTwo)m($id(ExTwo)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild().getNextSibling().getNextSibling();
        ExecChoiceFctOperation fct_ = getChoiceFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!m_.isStaticMethod());
    }
    @Test
    public void processEl214Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.ExTwo;] pkgtwo.Apply:ExTwo {\n");
        xml_.append(" $static {$superaccess(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl215Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.ExTwo;] pkgtwo.Apply:ExTwo {\n");
        xml_.append(" {$superaccess(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl216Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExTwo;pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" {ExTwo e = $new ExTwo():e;.$superaccess(ExTwo)m($id(ExTwo)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild().getNextSibling().getNextSibling();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!m_.isStaticMethod());
    }
    @Test
    public void processEl217Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:ExThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void o() {}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $new ExTwo().$classchoice(Apply)m():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void m() {}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecChoiceFctOperation fct_ = getChoiceFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.Apply", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl218Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo:ExThree {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $void o() {}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $new ExTwo().$superaccess(ExThree)o():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExThree", id_.getClassName());
        assertEq("o", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl219Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExThree.get;\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {get($null,$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $private $static $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExThree", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl220Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static;\n");
        xml_.append("$static pkg.ExInexist.get;\n");
        xml_.append("$static pkg.ExInexistBis.*;\n");
        xml_.append("$static pkg.ExThree.get;\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {get($null,$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:pkgtwo.ExFour {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExFour {\n");
        xml_.append(" $static $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int get(Number... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkgtwo.ExFour", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("get", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("java.lang.Number", params_.last());
        assertTrue(id_.isVararg());
        assertEq(0, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl221Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static;\n");
        xml_.append("$static pkg.ExTwo.get;\n");
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {get:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.ExPar {\n");
        xml_.append("  $public $static $int get:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExPar {\n");
        xml_.append("  $static $int get:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSettableFieldOperation fct_ = getField(f_.getExp());
        assertNotNull(fct_);
        ClassField cid_ = fct_.getFieldId();
        assertEq("pkg.ExTwo", cid_.getClassName());
        assertEq("get", cid_.getFieldName());
    }
    @Test
    public void processEl222Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkgtwo.ExPar.*;\n");
        xml_.append("$static pkgtwo.ExTwo.*;\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {one:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExPar {\n");
        xml_.append("  $public $static $int one:\n");
        xml_.append("  $static $int two:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExTwo {\n");
        xml_.append("  $static $int one:\n");
        xml_.append("  $public $static $int two:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSettableFieldOperation fct_ = getField(f_.getExp());
        assertNotNull(fct_);
        ClassField cid_ = fct_.getFieldId();
        assertEq("pkgtwo.ExPar", cid_.getClassName());
        assertEq("one", cid_.getFieldName());
    }
    @Test
    public void processEl223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkgtwo.ExPar.*;\n");
        xml_.append("$static pkgtwo.ExTwo.*;\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {one:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExPar {\n");
        xml_.append("  $public $static $int one:\n");
        xml_.append("  $static $int two:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.ExTwo {\n");
        xml_.append("  $public $int one:\n");
        xml_.append("  $public $static $int two:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSettableFieldOperation fct_ = getField(f_.getExp());
        assertNotNull(fct_);
        ClassField cid_ = fct_.getFieldId();
        assertEq("pkgtwo.ExPar", cid_.getClassName());
        assertEq("one", cid_.getFieldName());
    }
    @Test
    public void processEl224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,ExTwo),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,ExTwo),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl226Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static;\n");
        xml_.append("$static pkg.ExTwo.Inner;\n");
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo.Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl227Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static;\n");
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo.Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl228Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.Inner;\n");
        xml_.append("$operator-- Object(pkg.ExTwo p){\n");
        xml_.append(" $new pkg.ExTwo($id(pkg.ExTwo,Inner),$null):\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo.Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        OperatorBlock r_ = cont_.getClasses().getOperators().first();
        Line f_ = (Line) r_.getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$operator* Object(pkg.ExTwo p){\n");
        xml_.append(" $new pkg.ExTwo($id(pkg.ExTwo,Inner),$null):\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo.Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        OperatorBlock r_ = cont_.getClasses().getOperators().first();
        Line f_ = (Line) r_.getFirstChild();
        ExecStandardInstancingOperation fct_ = getCtor(f_.getExp());
        assertNotNull(fct_);
        ConstructorId id_ =fct_.getConstId();
        assertEq("pkg.ExTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("pkg.ExTwo..Inner", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl230Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {\" \".charAt($id(CharSequence,$int),0):}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
    }
    @Test
    public void processEl2300Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {$var c = \" \": c;.charAt($id(CharSequence,$int),0):}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild().getNextSibling().getNextSibling();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("java.lang.CharSequence", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("charAt", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl231Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static $final String multi=`\n");
        xml_.append("  static {``next\"// /*\t)`,line=`now\nreturn to line\n``but capture all`:\n");
        xml_.append(" $private $static $final String single=\"`\":\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.ExClass");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        StringList names_ = f_.getFieldName();
        assertEq(2, names_.size());
        assertEq("multi", names_.first());
        assertEq("line", names_.last());
        f_ = (FieldBlock)f_.getNextSibling();
        names_ = f_.getFieldName();
        assertEq(1, names_.size());
        assertEq("single", names_.first());
    }
    @Test
    public void processEl232Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static $final String multi=`\n");
        xml_.append("  static {``next\"// /*\t)`,line=`now\nreturn to line\n``but capture all`:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.ExClass");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        StringList names_ = f_.getFieldName();
        assertEq(2, names_.size());
        assertEq("multi", names_.first());
        assertEq("line", names_.last());
    }
    @Test
    public void processEl233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static $final String multi=`\n");
        xml_.append("  static {``next\"// /*\t)`:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.ExClass");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        StringList names_ = f_.getFieldName();
        assertEq(1, names_.size());
        assertEq("multi", names_.first());
    }
    @Test
    public void processEl234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkg.Ex;\n");
        xml_.append("pkg.ExTwo;\n");
        xml_.append("$public $class pkgtwo.Toto {\n");
        xml_.append("\t$private String myf=\"ValueOne\",mys=\"ValueTwo\":\n");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextEl(files_, false);
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Toto");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        StringList names_ = f_.getFieldName();
        assertEq(2, names_.size());
        assertEq("myf", names_.first());
        assertEq("mys", names_.last());
    }
    @Test
    public void processEl235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply:ExTwo {\n");
        xml_.append(" {$superaccess(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply:ExTwo {\n");
        xml_.append(" {$superaccess(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl237Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply:ExTwo<String> {\n");
        xml_.append(" {$superaccess(ExTwo<?>)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl238est() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply:ExTwo<String> {\n");
        xml_.append(" {$superaccess(ExTwo<?>)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl239Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply:ExTwo<Strin> {\n");
        xml_.append(" {$superaccess(ExTwo<?>)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl242est() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkg.*;] pkgtwo.Apply:ExTwo<Strin> {\n");
        xml_.append(" {$superaccess(ExTwo<?>)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl243Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExTwo;pkg.ExTwo;] pkgtwo.Apply {\n");
        xml_.append(" $static {ExTwo e = $null:e;.$superaccess(ExTwo)m($id(ExTwo,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public $static $int i:\n");
        xml_.append("  $public $static $void m(){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild().getNextSibling().getNextSibling();
        ExecSuperFctOperation fct_ = getSuperFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId id_ =fct_.getClassMethodId();
        MethodId m_  =id_.getConstraints();
        assertEq("pkg.ExTwo", id_.getClassName());
        assertEq("m", m_.getName());
        StringList params_ = m_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!m_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(m_.isStaticMethod());
    }
    @Test
    public void processEl244Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {` `.charAt($id(CharSequence,$int),0):}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
    }
    @Test
    public void processEl2440Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {$var c=` `:c;.charAt($id(CharSequence,$int),0):}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild().getNextSibling().getNextSibling();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("java.lang.CharSequence", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("charAt", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl245Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {' '+\" \".charAt($id(CharSequence,$int),0):}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
    }
    @Test
    public void processEl2450Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {$var c =' '+\" \":c;.charAt($id(CharSequence,$int),0):}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.ExTwo");
        Line f_ = (Line) r_.getFirstChild().getFirstChild().getNextSibling().getNextSibling();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("java.lang.CharSequence", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("charAt", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.last());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl2451Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo().method():}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:IntOne:IntTwo {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.IntOne {\n");
        xml_.append("  $final $int method(){$return 0:}\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.IntTwo {\n");
        xml_.append("  $int method():\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkgtwo.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.IntOne", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("method", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl2452Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static {ExEnum.values($id(ExEnum,$static)):}\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);

        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        RootBlock r_ = cont_.getClasses().getClassBody("pkg.Apply");
        Line f_ = (Line) r_.getFirstChild().getFirstChild();
        ExecFctOperation fct_ = getFct(f_.getExp());
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("pkg.ExEnum", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("values", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(0, params_.size());
        assertTrue(!id_.isVararg());
        assertEq(-1, fct_.getNaturalVararg());
        assertTrue(id_.isStaticMethod());
    }
    @Test
    public void processEl1FailTest() {
        analyzeIndirectLocalVars("composite.getOverridenOne($null)", "composite", COMPOSITE, true);
    }
    @Test
    public void processEl2FailTest() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<?W>");
        analyzeIndirectLocalVarsParam("myvar.sampleTwo((W)$null)", "W","myvar", g_, true);
    }

    @Test
    public void processEl3FailTest() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<?W>");
        analyzeIndirectLocalVarsParam("myvar.sampleFour((W[])$null)", "W","myvar", g_, true);
    }
    @Test
    public void processEl4FailTest() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<?W>");
        analyzeIndirectLocalVarsParam("myvar.sampleFour((W)$null,(W)$null)", "W","myvar", g_, true);
    }
    @Test
    public void processEl5FailTest() {
        analyzeIndirectLocalVars("composite.getOvVarargEleven()", "composite", COMPOSITE, true);
    }
    @Test
    public void processEl6FailTest() {
        analyzeIndirectLocalVars("composite.getOvVarargEleven($vararg(java.lang.Integer))", "composite", COMPOSITE, true);
    }
    @Test
    public void processEl7FailTest() {
        analyzeIndirectLocalVars("composite.getOvVarargEleven($vararg(java.lang.Integer),$firstopt(0i))", "composite", COMPOSITE, true);
    }

    @Test
    public void processEl8FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#V:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#V i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<?,?>");
        analyzeIndirectLocalVars("myvar.get($null)", "myvar", g_, xml_.toString(), true);
    }
    @Test
    public void processEl9FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#V:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#V... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<?,?>");
        analyzeIndirectLocalVars("myvar.get($null)", "myvar", g_, xml_.toString(), true);
    }
    @Test
    public void processEl10FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#V:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#V... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExTwo<?,?>");
        analyzeIndirectLocalVars("myvar.get($null,$null)", "myvar", g_, xml_.toString(), true);
    }
    @Test
    public void processEl11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int b:\n");
        xml_.append(" $static $int c:\n");
        xml_.append(" $static $int d:\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = b < c < d:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = :\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl13FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = -:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl14FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int b:\n");
        xml_.append(" $static $int c:\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = b++c:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl15FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int[][] b:\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = b[0]1[2]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl16FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = $new $int[0]1[2]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#V:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(Number i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(String i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        analyzeIndirectLocalVars("myvar.get($null)", "myvar", "pkg.ExTwo<?,?>", xml_.toString(), true);
    }
    @Test
    public void processEl18FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        analyzeIndirectLocalVars("$new pkg.ExTwo($null)", "myvar", "pkg.ExTwo", xml_.toString(), true);
    }
    @Test
    public void processEl19FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = 1 ! 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl110FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = get(,):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl111FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  ($static(ExTwo)).(call()):\n");
        xml_.append(" }\n");
        xml_.append(" $static $int call(){$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl112FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $super():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl113FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  $int a = $true:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl114FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = !1:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl115FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = '':\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl116FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static{\n");
        xml_.append("  Object a = 0x123456789123456789:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl117FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal String get(Integer... i){\n");
        xml_.append("  $return \"\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public $normal Number get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExThree:ExTwo {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), true);
    }
    @Test
    public void processEl119FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal String get(Integer... i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public $normal Number get(Integer... i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo:ExThree {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), true);

    }
    @Test
    public void processEl118FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(Integer... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo:ExThree {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), true);

    }
    @Test
    public void processEl120FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal String get(Integer... i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo:ExThree {\n");
        xml_.append(" $public $normal Number get(Integer... i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExTwo {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), true);

    }
    @Test
    public void processEl121FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {\n");
        xml_.append(" $public $normal String get(Integer... i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExTwo:ExThree {\n");
        xml_.append(" $public $normal Number get(Integer... i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.ExFour:ExThree:ExTwo {\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExFour");
        analyzeIndirectLocalVarsParamFirst("myvar.get(0)", "myvar", g_, xml_.toString(), true);

    }
    @Test
    public void processEl122FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  pkg.ExTwo<?> v = $null:\n");
        xml_.append("  v;.get = $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("  $public T get:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl123FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static String get(String i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Number get(Number i){\n");
        xml_.append("  $return $null:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        String g_ = StringList.concat("pkg.ExThree");
        analyzeIndirectLocalVarsParamFirst("myvar.get($null)", "myvar", g_, xml_.toString(), true);
    }

    @Test
    public void processEl124FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {\n");
        xml_.append("  $new pkg.ExTwo(m()):\n");
        xml_.append(" }\n");
        xml_.append(" $static $void m(){}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        contextEl(files_, true);
    }
    @Test
    public void processEl125FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public ExTwo(String v){}\n");
        xml_.append("  $public ExTwo(Number v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl126FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("  $public ExTwo(String v){}\n");
        xml_.append("  $public ExTwo(Number v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl127FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$operator* Object(pkg.ExTwo p){\n");
        xml_.append(" Inner i:\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo.Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1271FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$operator* Object(pkg.ExTwo p){\n");
        xml_.append(" Inner i:\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $package $static $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("  $public ExTwo(pkg.ExTwo.Inner v){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1272FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo($int){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1273FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo($int,$int){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1274FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  $int p:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1275FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $int p:\n");
        xml_.append("  $for($int p::){}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1276FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $int i = 0:\n");
        xml_.append("  $for($int p:i != 0:){$int p:}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1277FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $int i = 0:\n");
        xml_.append("  $iter($int p=0:0:1){$int p:}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1278FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $try{\n");
        xml_.append("  $int i = 0:}\n");
        xml_.append("  $catch(Object p){Object p:}\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1279FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $int i = 0:\n");
        xml_.append("  $if($true){\n");
        xml_.append("  $int i = 0:}\n");
        xml_.append("  \n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl1280FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public ExTwo(int if){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.NONE);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne("en", opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl128FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.ExTwo..Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl129FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl140FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,pkg.ExTwo<$void>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl141FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,$Fct<$void,Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl142FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,pkg.ExTwo.InnerTwo),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl143FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,#S),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl144FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,#S<Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl145FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,#S..Number),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl146FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply {\n");
        xml_.append(" $static {$new pkg.ExTwo($id(pkg.ExTwo,pkg.ExTwo.Inner),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl147FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,?Number),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl148FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,!),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl149FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,?),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl150FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,$Fct<?Number>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl151FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,a!b),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl152FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,$Fct<?),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl153FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,#),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl154FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(#S..Number)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl155FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(#S<Number>)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl156FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(#T)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl157FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$($Fct<Number>Number)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl158FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(..Number)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl159FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$($Fct<)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl160FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$($Fct<Number>>)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void processEl161FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(Number,Number)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl162FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$($Fct<)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl163FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$($Fct<Number>>)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl164FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(Number,Number)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl165FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$(Number.)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl166FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,pkg.ExTwo<String>),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl167FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.Ex,pkg.ExTwo),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl168FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo,$void),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl169FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id(pkg.ExTwo.Inner,$void),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl170FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$new pkg.ExTwo<Number>($id($void,$int),$null):}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl171FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" $static {$($void)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl172FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" {(pkg.ExTwo<S>)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl173FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.Apply<S> {\n");
        xml_.append(" {($void)$null:}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl174FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator** Object ($int $o,$int $o,$int o,$int o){\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$operator** Object ($int $o,$int $o,$int o,$int o){\n");
        xml_.append(" $return $null:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T:Number> {\n");
        xml_.append(" $public $class InnerTwo {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl175FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ Object ($int o,$int p){\n");
        xml_.append(" $return $new pkg.ExTwo().field:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $int field:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl176FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" pkg.ExTwo.Inner field:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.Sub {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.Sub {\n");
        xml_.append(" $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl177FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" pkg.ExTwo.Inner field:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.Sub {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.Sub {\n");
        xml_.append(" $private $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl178FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" pkg.ExTwo.Inner field:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.Sub {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.Sub {\n");
        xml_.append(" $protected $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void processEl179FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkgtwo.ExThree : pkg.ExTwo.Inner.InnerInner{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkgtwo.Sub {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkgtwo.Sub {\n");
        xml_.append(" $protected $static $class Inner{\n");
        xml_.append("  $protected $static $class InnerInner{\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        
        opt_.setSingleInnerParts(true);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    private static ExecFctOperation getFct(CustList<ExecOperationNode> _f) {
        for (ExecOperationNode o: _f) {
            if (o instanceof ExecFctOperation) {
                return (ExecFctOperation) o;
            }
        }
        return null;
    }
    private static ExecChoiceFctOperation getChoiceFct(CustList<ExecOperationNode> _f) {
        for (ExecOperationNode o: _f) {
            if (o instanceof ExecChoiceFctOperation) {
                return (ExecChoiceFctOperation) o;
            }
        }
        return null;
    }
    private static ExecSuperFctOperation getSuperFct(CustList<ExecOperationNode> _f) {
        for (ExecOperationNode o: _f) {
            if (o instanceof ExecSuperFctOperation) {
                return (ExecSuperFctOperation) o;
            }
        }
        return null;
    }
    private static ExecSettableFieldOperation getField(CustList<ExecOperationNode> _f) {
        for (ExecOperationNode o: _f) {
            if (o instanceof ExecSettableFieldOperation) {
                return (ExecSettableFieldOperation) o;
            }
        }
        return null;
    }
    private static ExecStandardInstancingOperation getCtor(CustList<ExecOperationNode> _f) {
        for (ExecOperationNode o: _f) {
            if (o instanceof ExecStandardInstancingOperation) {
                return (ExecStandardInstancingOperation) o;
            }
        }
        return null;
    }
    private static CustList<ExecOperationNode> analyzeIndirectLocalVars(String _el, String _var, String _className, boolean _mustFail) {
        return analyzeIndirectLocalVars(_el, _var, _className, file(), _mustFail);
    }
    private static CustList<ExecOperationNode> analyzeIndirectLocalVarsParamFirst(String _el, String _var, String _className, String _file, boolean _mustFail) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el, _className,"", _var));
        files_.put("pkg/Ex", _file);
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(files_, cont_);
        if (_mustFail) {
            assertTrue(!cont_.isEmptyErrors());
        } else {
            assertTrue(cont_.isEmptyErrors());
        }
        RootBlock r_ = cont_.getClasses().getClassBody("code.formathtml.classes.Apply");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        f_ = (FieldBlock) f_.getNextSibling();
        return f_.getOpValue();
    }
    private static CustList<ExecOperationNode> analyzeIndirectLocalVars(String _el, String _var, String _className, String _file, boolean _mustFail) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el, _className,"", _var));
        files_.put("pkg/Ex", _file);
        ContextEl cont_ = contextEl(files_, _mustFail);
        RootBlock r_ = cont_.getClasses().getClassBody("code.formathtml.classes.Apply");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        f_ = (FieldBlock) f_.getNextSibling();
        return f_.getOpValue();
    }
    private static CustList<ExecOperationNode> analyzeIndirectLocalVarsParam(String _el, String _param, String _var, String _className, boolean _mustFail) {
        return analyzeIndirectLocalVarsParam(_el, _param, _var, _className, file(), _mustFail);
    }
    private static CustList<ExecOperationNode> analyzeIndirectLocalVarsParam(String _el, String _param, String _var, String _className, String _file, boolean _mustFail) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el, _className, _param,_var));
        files_.put("pkg/Ex", _file);
        ContextEl cont_ = contextEl(files_, _mustFail);
        RootBlock r_ = cont_.getClasses().getClassBody("code.formathtml.classes.Apply");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        f_ = (FieldBlock) f_.getNextSibling();
        return f_.getOpValue();
    }
    private static String addonFileStaticResult(String _el, String _type, String _param, String _var) {
        StringBuilder str_ = new StringBuilder();
        if (_param.isEmpty()) {
            str_.append("$public $class code.formathtml.classes.Apply {\n");
        } else {
            str_.append("$public $class code.formathtml.classes.Apply ");
            str_.append("<");
            str_.append(_param);
            str_.append(">");
            str_.append(" {\n");
        }
        str_.append(" $public ");
        str_.append(_type);
        str_.append(" ");
        str_.append(_var);
        str_.append(":\n");
        str_.append(" $public java.lang.Object result = ");
        str_.append(_el);
        str_.append(":\n");
        str_.append("}");
        return str_.toString();
    }
    private static String file() {
        StringBuilder str_ = new StringBuilder();
        str_.append("$public $class code.formathtml.classes.InheritedComposite : Composite {\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.CompositeImport {\n");
        str_.append("    $public $static java.lang.String getOverridenEight3($int _double,$int... _three) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("$public $class [$static code.formathtml.classes.CompositeImport.getOverridenEight3;] code.formathtml.classes.CompositeImported {\n");
        str_.append("    $static {\n");
        str_.append("     getOverridenEight3(1,2):\n");
        str_.append("    }\n");
        str_.append("    $public $static java.lang.String getOverridenEight3($int _double,$int _do,$int... _three) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.Composite {\n");
        str_.append("\n");
        str_.append("    $public $int integer:\n");
        str_.append("\n");
        str_.append("    $public java.lang.Integer objInteger:\n");
        str_.append("\n");
        str_.append("    $public CompositeSec composite:\n");
        str_.append("\n");
        str_.append("    $public $int privateInt:\n");
        str_.append("\n");
        str_.append("    $public java.lang.String string:\n");
        str_.append("\n");
        str_.append("    $public $char myChar = 't':\n");
        str_.append("\n");
        str_.append("    $public $boolean displayed = $true:\n");
        str_.append("\n");
        str_.append("    $public() {\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $private($int _privateInt) {\n");
        str_.append("        privateInt = _privateInt;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public(java.lang.String..._strings) {\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public($int _param, java.lang.String..._strings) {\n");
        str_.append("        privateInt = _param;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal CompositeSec getComposite() {\n");
        str_.append("        $return composite:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int getInteger() {\n");
        str_.append("        $return integer:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setInteger($int _integer) {\n");
        str_.append("        integer = _integer;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.Integer getObjInteger() {\n");
        str_.append("        $return objInteger:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int getPrivateInt() {\n");
        str_.append("        $return privateInt:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setPrivateInt($int _privateInt) {\n");
        str_.append("        privateInt = _privateInt;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int summum($int _other) {\n");
        str_.append("        $return integer + _other;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int sum(java.lang.Long _other) {\n");
        str_.append("        $return 0i:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $int sum(java.lang.Long _other, java.lang.Long _otherTwo) {\n");
        str_.append("        $return 0i:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String getOverridenOne(java.lang.String _string) {\n");
        str_.append("        $return \"one\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenOne(java.lang.Object _string) {\n");
        str_.append("        $return \"two\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenOne(java.lang.Boolean _string) {\n");
        str_.append("        $return \"three\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenTwo(java.lang.String _string) {\n");
        str_.append("        $return \"one\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenTwo(java.lang.Object _string) {\n");
        str_.append("        $return \"two\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree(java.lang.Double _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree($double _double) {\n");
        str_.append("        $return \"double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenThree($long _double) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFour(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFour($long _double) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFive(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenFive($double _double) {\n");
        str_.append("        $return \"double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSix(java.lang.Long _double) {\n");
        str_.append("        $return \"Long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSix($long _double) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSix(java.lang.Double _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSeven($long _double,$long _do) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenSeven($long _double,$double _do) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight($int _double,$long _do) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight($long _double,$int _do) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight1($long _double,$int _do) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight1($int _double,$long _do) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight2($int _double,$int _do,$int... _three) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight2($int _double,$int... _do) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight3($int _double,$int... _three) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenEight3($int _double,$int _do,$int... _three) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenNine($int _double,$long _do) {\n");
        str_.append("        $return \"long\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOverridenNine($long _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getSampleVararg($int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVararg($int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVararg($long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargTwo($int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargTwo($long[] _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargThree($int _id,$int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargThree($int _id,$long[] _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargFour($long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargFour($int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargFive($int _first,$long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargFive($int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargSix($long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargSix($int _first,$int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargSeven($int _first,$long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargSeven($int _first,$int _second,$int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargEight($long _first,$long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargEight($int _first,$int _second,$int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargNine($long _first,$long _second,$long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargNine($int _first,$int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargTen($int _first,$int... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargTen(java.lang.Integer _first,$long... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargEleven($int _first,java.lang.Integer... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $public $normal java.lang.String getOvVarargEleven(java.lang.Integer _first,java.lang.Number... _double) {\n");
        str_.append("        $return \"Double\":\n");
        str_.append("    }\n");
        str_.append("    $package $normal $int sum() {\n");
        str_.append("        $return integer + privateInt:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String getStringElt($int _ind) {\n");
        str_.append("        $return $null:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String internMethod() {\n");
        str_.append("        $return \"sample\":\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $package $normal java.lang.String privateMethod() {\n");
        str_.append("        $return \"sample\":\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal java.lang.String getString() {\n");
        str_.append("        $return string:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setString(java.lang.String _string) {\n");
        str_.append("        string = _string;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $char getMyChar() {\n");
        str_.append("        $return myChar:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setMyChar($char _myChar) {\n");
        str_.append("        myChar = _myChar;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $boolean isDisplayed() {\n");
        str_.append("        $return displayed:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setDisplayed($boolean _displayed) {\n");
        str_.append("        displayed = _displayed;.;:\n");
        str_.append("    }\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.CompositeSec {\n");
        str_.append("\n");
        str_.append("    $public $int integer:\n");
        str_.append("\n");
        str_.append("    $public $normal $int getInteger() {\n");
        str_.append("        $return integer:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setInteger($int _integer) {\n");
        str_.append("        integer = _integer;.;:\n");
        str_.append("    }\n");
        str_.append("}\n");
        str_.append("\n");
        str_.append("\n");
        str_.append("$public $class code.formathtml.classes.BeanOne {\n");
        str_.append("\n");
        str_.append("    $public Composite composite:\n");
        str_.append("\n");
        str_.append("    $public() {\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("\n");
        str_.append("    $public $normal Composite getComposite() {\n");
        str_.append("        $return composite:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("$public $abstract $class myimpl.MyClass : MyIntOne : MyIntTwo : MySuperOne {\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("$public $interface myimpl.MyIntOne {\n");
        str_.append("    $int sample($int p):\n");
        str_.append("    $long sampleTwo($int p):\n");
        str_.append("    $normal $int sampleFive($int p){$return 0:}\n");
        str_.append("    $normal $int sampleSix($int... p){$return 0:}\n");
        str_.append("    Number sampleSeven($int p):\n");
        str_.append("    $static Number sampleEight($int p){$return 0:}\n");
        str_.append("    $static Object sampleNine($int p){$return 0:}\n");
        str_.append("}\n");
        str_.append("$public $class myimpl.MySuperOne {\n");
        str_.append("    $public $final $int sampleThree($int p){$return 0:}\n");
        str_.append("    $public $int sampleFour($int p){$return 0:}\n");
        str_.append("}\n");
        str_.append("$public $interface myimpl.MyIntTwo {\n");
        str_.append("    $int sample($int p):\n");
        str_.append("    $int sampleTwo($int p):\n");
        str_.append("    $normal $long sampleThree($int p){$return 0:}\n");
        str_.append("    $long sampleFour($int p):\n");
        str_.append("    $int sampleFive($int p):\n");
        str_.append("    $int sampleSix($int... p):\n");
        str_.append("    Object sampleSeven($int p):\n");
        str_.append("    $static Object sampleEight($int p){$return 0:}\n");
        str_.append("    $static Number sampleNine($int p){$return 0:}\n");
        str_.append("}\n");
        str_.append("$public $abstract $class myimpl.MyGeneClass<#T> : MyGeneIntOne<#T> : MyGeneIntTwo<#T> {\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("$public $interface myimpl.MyGeneIntOne<#U> {\n");
        str_.append("    $int sample(#U p):\n");
        str_.append("    $long sampleTwo(#U p):\n");
        str_.append("    $long sampleThree(#U p):\n");
        str_.append("    $long sampleFour(#U... p):\n");
        str_.append("    $long sampleThree(java.lang.Object p):\n");
        str_.append("}\n");
        str_.append("$public $interface myimpl.MyGeneIntTwo<#V> {\n");
        str_.append("    $int sample(#V p):\n");
        str_.append("    $int sampleTwo(#V p):\n");
        str_.append("    $long sampleThree(#V p):\n");
        str_.append("    $long sampleFour(#V... p):\n");
        str_.append("    $long sampleThree(java.lang.Object p):\n");
        str_.append("}\n");
        return str_.toString();
    }
    private static ContextEl contextEl(StringMap<String> _files, boolean _mustFail) {
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdOne(opt_);
        Classes.validateAll(_files, cont_);
        if (_mustFail) {
            assertTrue(!cont_.isEmptyErrors());
        } else {
            assertTrue(cont_.isEmptyErrors());
        }
        return cont_;
    }
}
