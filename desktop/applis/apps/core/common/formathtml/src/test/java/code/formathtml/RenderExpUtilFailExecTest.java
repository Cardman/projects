package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.NoExiting;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.structs.*;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.*;

public final class RenderExpUtilFailExecTest extends CommonRender {
    private static final String ARR_INTEGER = "[java.lang.Integer";
    @Test
    public void processEl7FailTest() {
        checkSimEx(new StringMap<String>(), "$new $int[-1i]");
    }
    @Test
    public void processEl8FailTest() {
        checkSimEx(new StringMap<String>(), "$new java.lang.Integer[-1i]");
    }
    @Test
    public void processEl182Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(false));
        lv_.setClassName(context_.getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "v&=1/0 > 0";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasDivisionZero(context_), getClassName(context_));
    }

    private static String getClassName(AnalyzedTestConfiguration context_) {
        return getClassName2(context_);
    }

    @Test
    public void processEl183Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(true));
        lv_.setClassName(context_.getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "v|=1/0 > 0";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasDivisionZero(context_), getClassName(context_));
    }

    private static String getAliasDivisionZero(AnalyzedTestConfiguration context_) {
        return context_.getStandards().getContent().getCoreNames().getAliasDivisionZero();
    }

    @Test
    public void processEl184Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "v&=1 > 0";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasNullPe(context_), getClassName(context_));
    }

    public static void setupAnalyzing(AnalyzedTestConfiguration context_) {
        context_.getAnalyzingDoc().setup(context_.getConfiguration(), context_.getAdvStandards(), context_.getDual());
        setupAnalyzing(context_.getAnalyzing(), context_.getLastPage(), context_.getAnalyzingDoc());
    }

    @Test
    public void processEl185Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "v|=1 > 0";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasNullPe(context_), getClassName(context_));
    }
    @Test
    public void processEl202Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        processEl("$(java.lang.Byte)\"not cast\"", context_);
        assertEq(getAliasCastType(context_), getClassName2(context_));
    }

    private static String getAliasCastType(AnalyzedTestConfiguration context_) {
        return context_.getStandards().getContent().getCoreNames().getAliasCastType();
    }

    private static String getClassName2(AnalyzedTestConfiguration context_) {
        return getClassName3(context_, getException(context_));
    }

    @Test
    public void processEl204Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        String el_ = "!v";
        processEl(el_, context_);
        
        assertNotNull(getException(context_));
        assertEq(getAliasNullPe(context_), getClassName(context_));
    }
    @Test
    public void processEl205Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasByte());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        String el_ = "$($byte)v";
        processEl(el_, context_);

        assertEq(getAliasNullPe(context_), getClassName(context_));
    }
    @Test
    public void processEl206Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "++v";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), getClassName(context_));
    }
    @Test
    public void processEl207Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "v++";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), getClassName(context_));
    }
    @Test
    public void processEl208Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setValue(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "v[0i]++";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), getClassName(context_));
    }

    @Test
    public void processEl209Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setValue(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        setupAnalyzing(context_);
        String elr_ = "++v[0i]";
        Delimiters d_ = checkSyntax(context_, elr_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = getOperationsSequence(0, el_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), getClassName(context_));
    }

    private static void setValue(LocalVariable lv_) {
        ArrayStruct array_ = new ArrayStruct(1, ARR_INTEGER);
        array_.set(0,NullStruct.NULL_VALUE);
        lv_.setStruct(array_);
        lv_.setClassName(ARR_INTEGER);
    }

    @Test
    public void processEl242Test() {
        checkSimEx(new StringMap<String>(), "$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke($null)");
    }
    @Test
    public void processEl243Test() {
        checkSimEx(new StringMap<String>(), "$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke(1i)");
    }
    @Test
    public void processEl244Test() {
        checkSimEx(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i)");
    }
    @Test
    public void processEl245Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.ExAbs).getDeclaredMethods(\"exmeth\",$false,$false)[0i];\n");
        xml_.append("  $return $(java.lang.String) m.invokeDirect($new pkg.ExConc());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl246Test() {
        checkSimEx(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,\"\")");
    }
    @Test
    public void processEl247Test() {
        checkSimEx(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,$null)");
    }
    @Test
    public void processEl248Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$true,$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke($null,$new $int[]{4i,6i});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl249Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$true,$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke($null,$new java.lang.Object[]{$new java.lang.Object[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl262Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.ExAbs).getDeclaredConstructors($false)[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(),$new java.lang.Object[]{$new $int[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl263Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (){\n");
        xml_.append("  inst=0i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false)[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl264Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst=p+q;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(1i),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl265Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst=p+q;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(1i,$null),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl266Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst=p+q;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:e){\n");
        xml_.append("   t+=i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(1i,\"\"),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl272Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (T... e){\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   inst+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance(9i,4i),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl273Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (T... e){\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    inst+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach(T i:e){\n");
        xml_.append("   t+=$($int)i;;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t+inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke(c.newInstance($null),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl274Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $return $($int) m.invoke($new pkg.Ex<java.lang.Integer>(),$null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl279Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $final $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,16i);\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl280Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $($int)$class(pkg.Ex).getDeclaredFields(\"inst\")[0i].get($null);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl281Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $($int)$class(pkg.Ex).getDeclaredFields(\"inst\")[0i].get(\"$null\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl284Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,$null);\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }

    @Test
    public void processEl285Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,\"16i\");\n");
        xml_.append("  $return $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl287Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public java.lang.Integer inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,$null);\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl288Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public java.lang.Integer inst=15i;\n");
        xml_.append(" $public $normal $int exmeth(T... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach(T i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(\"\",$null);\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl289Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($new pkg.Ex(),$null);\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl290Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($new pkg.Ex(),\"\");\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl294Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public T inst;\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $if(e!=$null){\n");
        xml_.append("   $foreach($int i:e){\n");
        xml_.append("    t+=$($int)i;;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex<java.lang.Integer> out = $new pkg.Ex<java.lang.Integer>();\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out,\"16i\");\n");
        xml_.append("  $return $(java.lang.Integer) out.inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        checkSimEx(files_, "$static(pkg.ExTwo).exmeth()");
    }
    @Test
    public void processEl295Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null);\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i];\n");
        xml_.append("  m.invoke($null,$new Object[]{$null});\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        
        Struct exc_ = getException(cont_);
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertEq(getAliasNullPe(cont_), getClassName3(cont_, cause_));
    }

    private static String getClassName3(AnalyzedTestConfiguration cont_, Struct cause_) {
        return cause_.getClassName(cont_.getContext());
    }

    @Test
    public void processEl296Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i];\n");
        xml_.append("  m.invoke($null,$(java.lang.Object)$null);\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        
        Struct exc_ = getException(cont_);
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertEq(getAliasNullPe(cont_), getClassName3(cont_, cause_));
    }
    @Test
    public void processEl297Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null);\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $null;\n");
        xml_.append("  m.invoke($null,8i);\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        
        Struct exc_ = getException(cont_);
        assertEq(getAliasNullPe(cont_), getClassName3(cont_, exc_));
    }
    @Test
    public void processEl298Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null);\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $null;\n");
        xml_.append("  m.invoke($null,8i);\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth();\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        
        Struct exc_ = getException(cont_);
        assertEq(getAliasNullPe(cont_), getClassName3(cont_, exc_));
    }
    @Test
    public void processEl303Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Object exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"Ex\",$true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);

        assertEq(getAliasClassNotFoundError(cont_), getClassName2(cont_));
    }

    private static String getAliasClassNotFoundError(AnalyzedTestConfiguration cont_) {
        return cont_.getStandards().getContent().getReflect().getAliasClassNotFoundError();
    }

    @Test
    public void processEl304Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i);\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static java.lang.Object exmeth(){\n");
        xml_.append("  $return $static($Class).forName($null,$true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);

        assertEq(getAliasNullPe(cont_), getClassName2(cont_));
    }

    private static String getAliasNullPe(AnalyzedTestConfiguration cont_) {
        return cont_.getStandards().getContent().getCoreNames().getAliasNullPe();
    }

    @Test
    public void processEl306Test() {
        checkSimEx(new StringMap<String>(), "$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke(\"\")");
    }
    @Test
    public void processEl320FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return Ex.inst;\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   $return 5;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        processEl("pkg.ExTwo.exmeth()+pkg.Ex.inst+1/0", cont_);
        assertNotNull(getException(cont_));
        assertTrue(!isSuccessfulInitialized(cont_));
    }

    private static boolean isSuccessfulInitialized(AnalyzedTestConfiguration cont_) {
        return cont_.getContext().getLocks().getState("pkg.Ex") == InitClassState.SUCCESS;
    }

    @Test
    public void processEl362Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfiguration(files_);
        addImportingPage(conf_);
        processEl("$new{} pkg.Ex(5).inst+1/0", conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void processEl363Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfiguration(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex(5).inst+1/0", conf_);
        assertNotNull(getException(conf_));
    }

    private static boolean isInitialized(AnalyzedTestConfiguration conf_) {
        return conf_.getContext().getLocks().getState("pkg.Ex") != InitClassState.NOT_YET;
    }

    @Test
    public void processEl367Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("($int)arg", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl368Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("($boolean)arg2", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl375Test() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getAliasString()));
        lv_.setStruct(NullStruct.NULL_VALUE);
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("arg.length", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl382Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "$new{} pkg.Ex(5).inst/=0");
    }

    private static void checkEx(StringMap<String> files_, String s) {
        checkEx2(getConfiguration(files_), s);
    }

    @Test
    public void processEl383Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.inst/=0");
    }
    @Test
    public void processEl385Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt;\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.elt.inst=10");
    }
    @Test
    public void processEl386Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt;\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.elt.inst");
    }
    @Test
    public void processEl388Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.inst=10/0");
    }
    @Test
    public void processEl391Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.res(8)/0");
    }
    @Test
    public void processEl392Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt;\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.elt.res(8)");
    }
    @Test
    public void processEl395Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static Ex<String> inst=$new Ex<>();\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "(pkg.Ex.inst).$classchoice(pkg.Ex<java.lang.Integer>)res(8)");
    }
    @Test
    public void processEl396Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt;\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.elt.$classchoice(pkg.Ex)res(8)");
    }
    @Test
    public void processEl397Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.$classchoice(pkg.Ex)res(8)/0");
    }
    @Test
    public void processEl400Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt;\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.elt.$superaccess(pkg.Ex)res(8)");
    }
    @Test
    public void processEl401Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0;\n");
        xml_.append("  inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "pkg.Ex.$superaccess(pkg.Ex)res(8)/0");
    }
    @Test
    public void processEl405Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO;\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v/=0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "$values(pkg.ExTwo).length/0");
    }
    @Test
    public void processEl406Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5;}},TWO{(){myval=7;}};\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v/=0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "$valueOf(pkg.ExTwo,\"ONE\").myval");
    }
    @Test
    public void processEl407Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5;}},TWO{(){myval=7;}};\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v/=0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        checkEx(files_, "$valueOf(pkg.ExTwo,\"TWO\").myval");
    }
    @Test
    public void processEl420___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Int2 {\n");
        xml_.append(" $int pl($int a, $int b);\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        checkEx(files_, "($(pkg.Int)(5,$interfaces(pkg.Int2)(),$interfaces(pkg.Int)())).pl(6,8)");
    }
    @Test
    public void processEl421___Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int:Int2 {\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int2 {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        checkEx(files_, "($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int2)(),$interfaces(pkg.Int)()))");
    }
    @Test
    public void processEl444Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.ExTwo(pkg.ExTwo a, pkg.ExTwo b) {\n");
        xml_.append(" $var o =$new pkg.ExTwo();\n");
        xml_.append(" o.myval=a.myval+b.myval;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this(T p){\n");
        xml_.append("  $return inst[$($int)p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(T p){\n");
        xml_.append("  inst[$($int)p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        checkEx2(files_);
    }

    private static void checkEx2(StringMap<String> files_) {
        checkEx2(getConfiguration(files_), "$new pkg.Ex<java.lang.Integer>(5).$classchoice(pkg.Ex<java.lang.String>)[\"\"]");
    }

    private static void checkEx2(AnalyzedTestConfiguration configuration, String s) {
        AnalyzedTestConfiguration conf_ = configuration;
        addImportingPage(conf_);
        processEl(s, conf_);
        assertNotNull(getException(conf_));
    }

    @Test
    public void processEl446Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1];\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfiguration(files_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = init(conf_, "pkg.Ex");
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_.getLastPage(), localVars_);
        processEl("$new pkg.Ex(5)[0]/=0", conf_);
        assertNotNull(getException(conf_));
    }
    @Test
    public void processEl447Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval;\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub(){}\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex st;\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()};\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p]=$value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration conf_ = getConfiguration(files_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = init(conf_, "pkg.Ex");
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_.getLastPage(), localVars_);
        processEl("pkg.Ex.st[0].myval", conf_);
        assertNotNull(getException(conf_));
    }

    private static Struct init(AnalyzedTestConfiguration cont_, String _cl) {
        return cont_.getContext().getInit().processInit(cont_.getContext(), NullStruct.NULL_VALUE, _cl,cont_.getClasses().getClassBody(_cl), "", -1);
    }

    @Test
    public void processEl450Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]/0+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        checkEx(files_, "pkg.Apply.test()");
    }
    @Test
    public void processEl451Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]/0+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        checkEx(files_, "\"\"+$new pkg.Ex()");
    }
    @Test
    public void processEl467Test() {
        StringMap<String> files_ = new StringMap<String>();
        checkSimEx(files_, "explicit($int)\"5\"");

    }
    @Test
    public void processAffect8FailTest() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasInteger());
        localVars_.put("v2", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v=v2", context_);
        
        assertNotNull(getException(context_));
    }
    @Test
    public void processAffect11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestConfiguration cont_ = getConfiguration(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_.getLastPage(), localVars_);
        processEl("$classchoice(pkg.Ex)inst=v", cont_);
        
        assertNotNull(getException(cont_));
    }
    @Test
    public void processAffect12FailTest() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        String stringType_ = context_.getAliasString();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct array_ = new ArrayStruct(1, StringExpUtil.getPrettyArrayType(stringType_));
        array_.set(0,NullStruct.NULL_VALUE);
        lv_.setStruct(array_);
        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getAliasObject()));
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getAliasInteger());
        localVars_.put("v2", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v[0i]=v2", context_);
        
        assertNotNull(getException(context_));
    }
    @Test
    public void processAffect16FailTest() {
        AnalyzedTestConfiguration context_ = getConfiguration(new StringMap<String>());
        String primIntType_ = context_.getAliasPrimInteger();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayStruct array_ = new ArrayStruct(1, StringExpUtil.getPrettyArrayType(primIntType_));
        array_.set(0,new IntStruct(0));
        lv_.setStruct(array_);
        lv_.setClassName(StringExpUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v[0i]/=0", context_);
        
        assertNotNull(getException(context_));
    }

    private static void processEl(String _el, AnalyzedTestConfiguration _cont) {
        _cont.getAnalyzing().setGlobalType(_cont.getLastPage().getGlobalArgument().getStruct().getClassName(_cont.getContext()));
        processEl(_el, 0, _cont);
        assertTrue(_cont.isEmptyErrors());
        assertNotNull(getException(_cont));
    }

    private static void checkSimEx(StringMap<String> files_, String _el) {
        checkEx2(getConfiguration(files_), _el);
    }

    private static void calculate(CustList<OperationNode> _ops, AnalyzedTestConfiguration _an) {
        CustList<RendDynOperationNode> out_ = getExecutableNodes(_an, _ops);
        out_ = CommonRender.getReducedNodes(out_.last());
        _an.getContext().setExiting(new NoExiting());
        calculateReuse(_an,out_);
        assertNotNull(getException(_an));
    }

    private static AnalyzedTestConfiguration getConfiguration(StringMap<String> _files, String... _types) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        AnalyzedTestConfiguration a_ = build(conf_,_types);
        Classes.validateWithoutInit(_files, a_.getAnalyzing());
        assertTrue(isEmptyErrors(a_));
        ForwardInfos.generalForward( a_.getAnalyzing(), a_.getForwards(), a_.getContext());
        AnalyzedPageEl page_ = a_.getAnalyzing();
        Classes.tryInitStaticlyTypes(a_.getContext(), page_.getOptions());
        return a_;
    }

    private static void processEl(String _el, int _index, AnalyzedTestConfiguration _conf) {
        CustList<RendDynOperationNode> out_ = getAnalyzed(_el,_index,_conf, _conf.getAnalyzingDoc());
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        assertTrue(_conf.isEmptyErrors());
        ContextEl context_ = _conf.getContext();
        Classes.forwardAndClear(context_, page_, _conf.getForwards());
        for (ClassMetaInfo c: context_.getClasses().getClassMetaInfos()) {
            String name_ = c.getName();
            ClassMetaInfo.forward(ExecutingUtil.getClassMetaInfo(context_, name_), c);
        }
        out_ = CommonRender.getReducedNodes(out_.last());
        _conf.getContext().setExiting(new NoExiting());
        calculateReuse(_conf,out_);
    }

}
