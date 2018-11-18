package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.VariableSuffix;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.classes.CustLgNames;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public class AnalyzedOperationNodesTest {
    private static final String COMPOSITE = "code.formathtml.classes.Composite";
    private static final String MY_CLASS = "myimpl.MyClass";
    private static final String MY_GENE_CLASS = "myimpl.MyGeneClass";
    @Test
    public void processEl37Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenTwo($null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenTwo((java.lang.Object)$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1L)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0D)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0d)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0F)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
    public void processEl45Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenThree(1.0f)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFour($null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFour(1L)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFour(1l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFive(1L)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenFive(1l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSix(1L)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSix(1l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSeven(1l,1l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSeven(1l,1L)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenSeven(1l,1D)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenEight(1,2)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
    public void processEl123Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOverridenNine(1,2)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sample(1)", "myvar", MY_CLASS, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntTwo", cid_.getClassName());
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleTwo(1)", "myvar", MY_CLASS, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sample(1)", "myvar", g_, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#V", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl127Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<java.lang.Integer>");
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleTwo(1)", "myvar", g_, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getSampleVararg($vararg($int))", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getSampleVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl129Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<OperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sample($null)", "W","myvar", g_, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#V", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl130Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<OperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sampleTwo($null)", "W","myvar", g_, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sample((W)$null)", "W","myvar", g_, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyGeneIntTwo", cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("sample", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("#V", params_.first());
        assertTrue(!id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl132Test() {
        String g_ = StringList.concat(MY_GENE_CLASS,"<W>");
        CustList<OperationNode> opers_ = analyzeIndirectLocalVarsParam("myvar.sampleTwo((W)$null)", "W","myvar", g_, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(($int[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    
    @Test
    public void processEl134Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(($long[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl135Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTwo(($int[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl136Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTwo(($long[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargThree(1i,($int[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl138Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargThree(1i,($long[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl139Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTwo(5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargTwo", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl140Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl141Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVararg(5,6l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVararg", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl142Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFour(5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl143Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFour(5,6l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFour", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl144Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFive(5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargFive", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$int", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl145Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargFive(5,6l)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl146Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSix(5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl147Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSix(5l,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq(COMPOSITE, cid_.getClassName());
        MethodId id_ = cid_.getConstraints();
        assertEq("getOvVarargSix", id_.getName());
        StringList params_ = id_.getParametersTypes();
        assertEq(1, params_.size());
        assertEq("$long", params_.first());
        assertTrue(id_.isVararg());
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl148Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSeven(4,5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl149Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargSeven(4,5l,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl150Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEight(4,5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl151Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEight(4l,5l,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl152Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargNine(4,5,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl153Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargNine(4,5l,6)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    @Test
    public void processEl154Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTen(4,($int[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }
    
    @Test
    public void processEl155Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargTen(4I,($long[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
    }

    @Test
    public void processEl156Test() {
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleThree(1)", "myvar", MY_CLASS, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleFour(1)", "myvar", MY_CLASS, false);
        FctOperation fct_ = getFct(opers_);
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("myvar.sampleFive(1)", "myvar", MY_CLASS, false);
        FctOperation fct_ = getFct(opers_);
        assertNotNull(fct_);
        ClassMethodId cid_ = fct_.getClassMethodId();
        assertEq("myimpl.MyIntTwo", cid_.getClassName());
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
        CustList<OperationNode> opers_ = analyzeIndirectLocalVars("composite.getOvVarargEleven((java.lang.Integer)4,(java.lang.Integer[])$null)", "composite", COMPOSITE, false);
        FctOperation fct_ = getFct(opers_);
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
        assertTrue(!id_.isStaticMethod());
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

    private static FctOperation getFct(CustList<OperationNode> _f) {
        for (OperationNode o: _f) {
            if (o instanceof FctOperation) {
                return (FctOperation) o;
            }
        }
        return null;
    }
    private CustList<OperationNode> analyzeIndirectLocalVars(String _el, String _var, String _className, boolean _mustFail) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el, _className,"", _var));
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_, _mustFail);
        RootBlock r_ = cont_.getClasses().getClassBody("code.formathtml.classes.Apply");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        f_ = (FieldBlock) f_.getNextSibling();
        CustList<OperationNode> list_ = f_.getOpValue();
        return list_;
    }
    private CustList<OperationNode> analyzeIndirectLocalVarsParam(String _el, String _param, String _var, String _className, boolean _mustFail) {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/ExTwo", addonFileStaticResult(_el, _className, _param,_var));
        files_.put("pkg/Ex", file());
        ContextEl cont_ = contextEl(files_, _mustFail);
        RootBlock r_ = cont_.getClasses().getClassBody("code.formathtml.classes.Apply");
        FieldBlock f_ = (FieldBlock) r_.getFirstChild();
        f_ = (FieldBlock) f_.getNextSibling();
        CustList<OperationNode> list_ = f_.getOpValue();
        return list_;
    }
    private String addonFileStaticResult(String _el, String _type, String _param, String _var) {
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
    private String file() {
        StringBuilder str_ = new StringBuilder();
        str_.append("$public $class code.formathtml.classes.InheritedComposite : Composite {\n");
        str_.append("\n");
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
        str_.append("    $public code.util.StringList strings:\n");
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
        str_.append("    $public $normal code.util.StringList getStrings() {\n");
        str_.append("        $return strings:\n");
        str_.append("    }\n");
        str_.append("\n");
        str_.append("    $public $normal $void setStrings(code.util.StringList _strings) {\n");
        str_.append("        strings = _strings;.;:\n");
        str_.append("    }\n");
        str_.append("\n");
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
        str_.append("    $normal $int sampleFive($int p){$return 0:}\n");
        str_.append("}\n");
        str_.append("$public $abstract $class myimpl.MyGeneClass<#T> : MyGeneIntOne<#T> : MyGeneIntTwo<#T> {\n");
        str_.append("\n");
        str_.append("}\n");
        str_.append("$public $interface myimpl.MyGeneIntOne<#U> {\n");
        str_.append("    $int sample(#U p):\n");
        str_.append("    $long sampleTwo(#U p):\n");
        str_.append("    $long sampleThree(#U p):\n");
        str_.append("    $long sampleThree(java.lang.Object p):\n");
        str_.append("}\n");
        str_.append("$public $interface myimpl.MyGeneIntTwo<#V> {\n");
        str_.append("    $int sample(#V p):\n");
        str_.append("    $int sampleTwo(#V p):\n");
        str_.append("    $long sampleThree(#V p):\n");
        str_.append("    $long sampleThree(java.lang.Object p):\n");
        str_.append("}\n");
        return str_.toString();
    }
    private ContextEl contextEl(StringMap<String> _files, boolean _mustFail) {
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setEndLineSemiColumn(false);
        cont_.getOptions().setSpecialEnumsMethods(false);
        cont_.getOptions().setSuffixVar(VariableSuffix.DISTINCT);
        initAdvStandards(cont_);
        Classes.validateAll(_files, cont_);
        if (_mustFail) {
            assertTrue(!cont_.getClasses().isEmptyErrors());
        } else {
            assertTrue(cont_.getClasses().isEmptyErrors());
        }
        cont_.initError();
        return cont_;
    }
    public static LgNames initAdvStandards(ContextEl _context) {
        LgNames lgNames_ = new CustLgNames();
        lgNames_.setContext(_context);
        InitializationLgNames.basicStandards(lgNames_);
        lgNames_.build();
        _context.setStandards(lgNames_);
        lgNames_.setupOverrides(_context);
        return lgNames_;
    }
}
