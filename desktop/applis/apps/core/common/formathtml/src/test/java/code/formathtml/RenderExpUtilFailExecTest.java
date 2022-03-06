package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.NoExiting;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class RenderExpUtilFailExecTest extends CommonRenderExpUtil {
    private static final String ARR_INTEGER = "[java.lang.Integer";
    @Test
    public void processEl7FailTest() {
        assertNotNull(ex(new StringMap<String>(), "$new $int[-1i]"));
    }
    @Test
    public void processEl8FailTest() {
        assertNotNull(ex(new StringMap<String>(), "$new java.lang.Integer[-1i]"));
    }
    /*@Test
    public void processEl182Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(false));
        lv_.setClassName(context_.getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v&=1/0 > 0";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasDivisionZero(context_), exClass(context_));
    }

    @Test
    public void processEl183Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(true));
        lv_.setClassName(context_.getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v|=1/0 > 0";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasDivisionZero(context_), exClass(context_));
    }

    @Test
    public void processEl184Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v&=1 > 0";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasNullPe(context_), exClass(context_));
    }

    @Test
    public void processEl185Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v|=1 > 0";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(getAliasNullPe(context_), exClass(context_));
    }*/
    @Test
    public void processEl202Test() {
        Struct context_ = getAnalyzedTestConfiguration(new StringMap<String>(), "$(java.lang.Byte)\"not cast\"");
        assertNotNull(context_);
    }

    /*
    @Test
    public void processEl204Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        String el_ = "!v";
        processElLow(el_, context_);
        
        assertNotNull(getException(context_));
        assertEq(getAliasNullPe(context_), exClass(context_));
    }
    @Test
    public void processEl205Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasByte());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        String el_ = "$($byte)v";
        processElLow(el_, context_);

        assertEq(getAliasNullPe(context_), exClass(context_));
    }
    @Test
    public void processEl206Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "++v";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), exClass(context_));
    }
    @Test
    public void processEl207Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v++";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), exClass(context_));
    }
    @Test
    public void processEl208Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setValue(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "v[0i]++";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);
        
        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), exClass(context_));
    }

    @Test
    public void processEl209Test() {
        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        setValue(lv_);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_, localVars_);
        setupAnalyzing(context_);
        String elr_ = "++v[0i]";
        Delimiters d_ = checkSyntax(context_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        OperationsSequence opTwo_ = getOperationsSequence(0, elr_, context_, d_);
        OperationNode op_ = getOperationNode(0, IndexConstants.FIRST_INDEX, null, opTwo_, context_);
        assertNotNull(op_);
        CustList<OperationNode> all_ = getSortedDescNodes(context_, op_);

        calculate(all_, context_);

        assertEq(getAliasNullPe(context_), exClass(context_));
    }*/

    @Test
    public void processEl242Test() {
        assertNotNull(ex(new StringMap<String>(), "$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke($null)"));
    }
    @Test
    public void processEl243Test() {
        assertNotNull(ex(new StringMap<String>(), "$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke(1i)"));
    }
    @Test
    public void processEl244Test() {
        assertNotNull(ex(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i)"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
    }
    @Test
    public void processEl246Test() {
        assertNotNull(ex(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,\"\")"));
    }
    @Test
    public void processEl247Test() {
        assertNotNull(ex(new StringMap<String>(), "$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,$null)"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        assertNotNull(ex(files_, "$static(pkg.ExTwo).exmeth()"));
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
        Struct cont_ = getAnalyzedTestConfiguration(files_, "$static(pkg.ExTwo).exmeth()");
        assertNotNull(cont_);
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
        Struct cont_ = getAnalyzedTestConfiguration(files_, "$static(pkg.ExTwo).exmeth()");
        assertNotNull(cont_);
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
        Struct cont_ = getAnalyzedTestConfiguration(files_, "$static(pkg.ExTwo).exmeth()");
        assertNotNull(cont_);
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
        Struct cont_ = getAnalyzedTestConfiguration(files_, "$static(pkg.ExTwo).exmeth()");
        assertNotNull(cont_);
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
        Struct cont_ = getAnalyzedTestConfiguration(files_, "$static(pkg.ExTwo).exmeth()");
        assertNotNull(cont_);
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
        Struct cont_ = getAnalyzedTestConfiguration(files_, "$static(pkg.ExTwo).exmeth()");
        assertNotNull(cont_);
    }

    @Test
    public void processEl306Test() {
        assertNotNull(ex(new StringMap<String>(), "$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke(\"\")"));
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
        Struct conf_ = getAnalyzedTestConfiguration(files_, "pkg.ExTwo.exmeth()+pkg.Ex.inst+1/0");
        assertNotNull(conf_);
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
        Struct conf_ = getAnalyzedTestConfiguration(files_, "$new{} pkg.Ex(5).inst+1/0");
        assertNotNull(conf_);
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
        Struct conf_ = getAnalyzedTestConfiguration(files_, "$new pkg.Ex(5).inst+1/0");
        assertNotNull(conf_);
    }

//    @Test
//    public void processEl367Test() {
//        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setClassName(context_.getAliasPrimBoolean());
//        lv_.setStruct(BooleanStruct.of(true));
//        localVars_.put("arg", lv_);
//        lv_ = new LocalVariable();
//        lv_.setClassName(context_.getAliasPrimInteger());
//        lv_.setStruct(new IntStruct(0));
//        localVars_.put("arg2", lv_);
//        CommonRender.setLocalVars(context_, localVars_);
//        processElLow("($int)arg", context_);
//        assertNotNull(getException(context_));
//    }
//    @Test
//    public void processEl368Test() {
//        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setClassName(context_.getAliasPrimBoolean());
//        lv_.setStruct(BooleanStruct.of(true));
//        localVars_.put("arg", lv_);
//        lv_ = new LocalVariable();
//        lv_.setClassName(context_.getAliasPrimInteger());
//        lv_.setStruct(new IntStruct(0));
//        localVars_.put("arg2", lv_);
//        CommonRender.setLocalVars(context_, localVars_);
//        processElLow("($boolean)arg2", context_);
//        assertNotNull(getException(context_));
//    }
//    @Test
//    public void processEl375Test() {
//        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getAliasString()));
//        lv_.setStruct(NullStruct.NULL_VALUE);
//        localVars_.put("arg", lv_);
//        CommonRender.setLocalVars(context_, localVars_);
//        processElLow("arg.length", context_);
//        assertNotNull(getException(context_));
//    }
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
        assertNotNull(ex(files_, "$new{} pkg.Ex(5).inst/=0"));
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
        assertNotNull(ex(files_, "pkg.Ex.inst/=0"));
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
        assertNotNull(ex(files_, "pkg.Ex.elt.inst=10"));
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
        assertNotNull(ex(files_, "pkg.Ex.elt.inst"));
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
        assertNotNull(ex(files_, "pkg.Ex.inst=10/0"));
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
        assertNotNull(ex(files_, "pkg.Ex.res(8)/0"));
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
        assertNotNull(ex(files_, "pkg.Ex.elt.res(8)"));
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
        assertNotNull(ex(files_, "(pkg.Ex.inst).$classchoice(pkg.Ex<java.lang.Integer>)res(8)"));
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
        assertNotNull(ex(files_, "pkg.Ex.elt.$classchoice(pkg.Ex)res(8)"));
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
        assertNotNull(ex(files_, "pkg.Ex.$classchoice(pkg.Ex)res(8)/0"));
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
        assertNotNull(ex(files_, "pkg.Ex.elt.$superaccess(pkg.Ex)res(8)"));
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
        assertNotNull(ex(files_, "pkg.Ex.$superaccess(pkg.Ex)res(8)/0"));
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
        assertNotNull(ex(files_, "$values(pkg.ExTwo).length/0"));
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
        assertNotNull(ex(files_, "$valueOf(pkg.ExTwo,\"ONE\").myval"));
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
        assertNotNull(ex(files_, "$valueOf(pkg.ExTwo,\"TWO\").myval"));
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
        assertNotNull(ex(files_, "($(pkg.Int)(5,$interfaces(pkg.Int2)(),$interfaces(pkg.Int)())).pl(6,8)"));
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
        assertNotNull(ex(files_, "($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int2)(),$interfaces(pkg.Int)()))"));
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
        assertNotNull(ex(files_, "$new pkg.Ex<java.lang.Integer>(5).$classchoice(pkg.Ex<java.lang.String>)[\"\"]"));
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
        Struct conf_ = getAnalyzedTestConfiguration(files_, "$new pkg.Ex(5)[0]/=0");
        assertNotNull(conf_);
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
        assertNotNull(getAnalyzedTestConfiguration(files_,"pkg.Ex.st[0].myval"));
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
        assertNotNull(ex(files_, "pkg.Apply.test()"));
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
        assertNotNull(ex(files_, "\"\"+$new pkg.Ex()"));
    }
    @Test
    public void processEl467Test() {
        StringMap<String> files_ = new StringMap<String>();
        assertNotNull(ex(files_, "explicit($int)\"5\""));

    }
    @Test
    public void processEl479Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $operator++ $int(pkg.Ex a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a.inst+1/0;\n");
        xml_.append("  $return o.inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static pkg.Ex $($int a){\n");
        xml_.append("  $var o = $new pkg.Ex();\n");
        xml_.append("  o.inst = a;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static Ex res = $new Ex(6);\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertNotNull(ex(files_, "(pkg.Ex.res++).inst"));
    }
//    @Test
//    public void processAffect8FailTest() {
//        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setStruct(new IntStruct(1));
//        lv_.setClassName(context_.getAliasPrimInteger());
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setClassName(context_.getAliasInteger());
//        localVars_.put("v2", lv_);
//        CommonRender.setLocalVars(context_, localVars_);
//        processElLow("v=v2", context_);
//
//        assertNotNull(getException(context_));
//    }
//    @Test
//    public void processAffect11FailTest() {
//        StringBuilder xml_ = new StringBuilder();
//        xml_.append("$public $class pkg.Ex {\n");
//        xml_.append(" $public $static $int inst;\n");
//        xml_.append(" $public $static $int exmeth($int e){\n");
//        xml_.append("  $long t;\n");
//        xml_.append("  t=8;\n");
//        xml_.append("  $return 1i+$($int)t+e;\n");
//        xml_.append(" }\n");
//        xml_.append("}\n");
//        StringMap<String> files_ = new StringMap<String>();
//        files_.put("pkg/Ex", xml_.toString());
//        AnalyzedTestConfiguration cont_ = getConfigurationQuick(files_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setStruct(NullStruct.NULL_VALUE);
//        lv_.setClassName(cont_.getAliasInteger());
//        localVars_.put("v", lv_);
//        CommonRender.setLocalVars(cont_, localVars_);
//        processElLow("$classchoice(pkg.Ex)inst=v", cont_);
//
//        assertNotNull(getException(cont_));
//    }
//    @Test
//    public void processAffect12FailTest() {
//        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
//        String stringType_ = context_.getAliasString();
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        ArrayStruct array_ = new ArrayStruct(1, StringExpUtil.getPrettyArrayType(stringType_));
//        array_.set(0,NullStruct.NULL_VALUE);
//        lv_.setStruct(array_);
//        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getAliasObject()));
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setStruct(new IntStruct(1));
//        lv_.setClassName(context_.getAliasInteger());
//        localVars_.put("v2", lv_);
//        CommonRender.setLocalVars(context_, localVars_);
//        processElLow("v[0i]=v2", context_);
//
//        assertNotNull(getException(context_));
//    }
//    @Test
//    public void processAffect16FailTest() {
//        AnalyzedTestConfiguration context_ = getConfigurationQuick(new StringMap<String>());
//        String primIntType_ = context_.getAliasPrimInteger();
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        ArrayStruct array_ = new ArrayStruct(1, StringExpUtil.getPrettyArrayType(primIntType_));
//        array_.set(0,new IntStruct(0));
//        lv_.setStruct(array_);
//        lv_.setClassName(StringExpUtil.getPrettyArrayType(primIntType_));
//        localVars_.put("v", lv_);
//        CommonRender.setLocalVars(context_, localVars_);
//        processElLow("v[0i]/=0", context_);
//
//        assertNotNull(getException(context_));
//    }

    private static RendStackCall processElLow(String _el, DualNavigationContext _cont, StringMap<LocalVariable> _localVariables, StringMap<LoopVariable> _vars, AnalyzingDoc analyzingDoc) {
//        String gl_ = _cont.getArgument().getStruct().getClassName(_cont.getContext());
//        _cont.getAnalyzing().setGlobalType(new AnaFormattedRootBlock(_cont.getAnalyzing(),gl_));
        analyzingDoc.setup(_cont.getNavigation().getSession(), _cont.getDualAnalyzedContext().getContext());
        setupAnalyzing(_cont, analyzingDoc, _localVariables, _vars);
//        Argument argGl_ = _cont.getArgument();
        boolean static_ = true;
        _cont.getDualAnalyzedContext().getAnalyzed().setAccessStaticContext(MethodId.getKind(static_));
        Delimiters d_ = checkSyntax(_cont, _el);
        OperationsSequence opTwo_ = rendOpSeq(0, _cont, d_, _el, analyzingDoc);
        OperationNode op_ = rendOp(0, _cont, opTwo_, analyzingDoc);
        CustList<OperationNode> all_ = getSortedDescNodes(_cont, op_, analyzingDoc);
        generalForward(_cont);
        CustList<RendDynOperationNode> executableNodes_ = getQuickExecutableNodes(_cont, all_);
        ContextEl ctx_ = getGenerate(_cont);
        //        Classes.forwardAndClear(_cont.getContext());
        assertTrue(_cont.getDualAnalyzedContext().getAnalyzed().isEmptyErrors());
//        ExecClassesUtil.forwardClassesMetaInfos(_cont.getContext());
        ExecClassesUtil.tryInitStaticlyTypes(ctx_,_cont.getDualAnalyzedContext().getForwards().getOptions());
        ctx_.setExiting(new NoExiting());
        RendStackCall reuse_ = reuse(ctx_, _cont, executableNodes_, _localVariables, _vars);
        assertTrue(_cont.getDualAnalyzedContext().getAnalyzed().isEmptyErrors());
        assertNotNull(getException(reuse_));
        return reuse_;
    }

//    private static void processQuickEl(String _el, AnalyzedTestConfiguration _cont) {
//        String gl_ = _cont.getArgument().getStruct().getClassName(_cont.getContext());
//        _cont.getAnalyzing().setGlobalType(new AnaFormattedRootBlock(_cont.getAnalyzing(),gl_));
//        CustList<OperationNode> all_ = getQuickAnalyzed(_el, _cont, _cont.getAnalyzingDoc());
//        generalForward(_cont);
//        CustList<RendDynOperationNode> executableNodes_ = getQuickExecutableNodes(_cont, all_);
//        ContextEl ctx_ = getGenerate(_cont);
//        assertTrue(_cont.isEmptyErrors());
//        ExecClassesUtil.forwardClassesMetaInfos(ctx_);
//        ExecClassesUtil.tryInitStaticlyTypes(ctx_,_cont.getOpt());
//        ctx_.setExiting(new NoExiting());
//        reuse(ctx_, _cont, executableNodes_);
//        assertTrue(_cont.isEmptyErrors());
//        assertNotNull(getException(_cont));
//    }
//
//    private static void calculate(CustList<OperationNode> _ops, AnalyzedTestConfiguration _an) {
//        CustList<RendDynOperationNode> executableNodes_ = getQuickExecutableNodes(_an, _ops);
//        ContextEl ctx_ = getGenerate(_an);
//        ctx_.setExiting(new NoExiting());
//        reuse(ctx_, _an, executableNodes_);
//        assertNotNull(getException(_an));
//    }

    private static RendStackCall reuse(ContextEl ctx_, DualNavigationContext _an, CustList<RendDynOperationNode> executableNodes_, StringMap<LocalVariable> _localVariables, StringMap<LoopVariable> _vars) {
        RendStackCall build_ = new RendStackCall(InitPhase.NOTHING, ctx_);
//        RendStackCall build_ = _an.build(InitPhase.NOTHING, ctx_);
        build_.addPage(new ImportingPage());
        setupValues(build_.getLastPage(), _localVariables, _vars);
        RenderExpUtil.calculateReuse(executableNodes_, _an.getDualAnalyzedContext().getStds(), ctx_, build_);
        return build_;
    }

    private static DualNavigationContext getConfigurationQuick(StringMap<String> _files, String... _types) {
        Configuration conf_ = EquallableRenderUtil.newConfiguration();
        DualNavigationContext a_ = buildNav(conf_,_types);
        Classes.validateWithoutInit(_files, a_.getDualAnalyzedContext().getAnalyzed());
        assertTrue(isEmptyErrors(a_));
        return a_;
    }

    private static Struct ex(StringMap<String> _files, String _el) {
        DualNavigationContext configurationQuick = getConfigurationQuick(_files);
        return checkEx3(configurationQuick, _el, new StringMap<LocalVariable>(), new StringMap<LoopVariable>(), new AnalyzingDoc());
    }

//    private static String getAliasCastType(AnalyzedTestConfiguration _context) {
//        return _context.getStandards().getContent().getCoreNames().getAliasCastType();
//    }

//    private static String exClass(AnalyzedTestConfiguration _context) {
//        return exClass(_context, getException(_context));
//    }
//
//    private static String getAliasDivisionZero(AnalyzedTestConfiguration _context) {
//        return _context.getStandards().getContent().getCoreNames().getAliasDivisionZero();
//    }
//
//    private static void setupAnalyzing(AnalyzedTestConfiguration _context) {
//        _context.getAnalyzingDoc().setup(_context.getConfiguration(), _context.getDual());
//        setupAnalyzing(_context, _context.getAnalyzingDoc());
//    }
//
//    private static void setValue(LocalVariable _lv) {
//        ArrayStruct array_ = new ArrayStruct(1, ARR_INTEGER);
//        array_.set(0,NullStruct.NULL_VALUE);
//        _lv.setStruct(array_);
//        _lv.setClassName(ARR_INTEGER);
//    }

//    private static String exClass(AnalyzedTestConfiguration _cont, Struct _cause) {
//        return _cause.getClassName(_cont.getContext());
//    }

//    private static String getAliasClassNotFoundError(AnalyzedTestConfiguration _cont) {
//        return _cont.getStandards().getContent().getReflect().getAliasClassNotFoundError();
//    }
//
//    private static String getAliasNullPe(AnalyzedTestConfiguration _cont) {
//        return _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
//    }
//
//
//    private static boolean isSuccessfulInitialized(AnalyzedTestConfiguration _cont) {
//        return _cont.getContext().getLocks().getState("pkg.Ex") == InitClassState.SUCCESS;
//    }

    private static Struct checkEx3(DualNavigationContext _cont, String _s, StringMap<LocalVariable> _localVariables, StringMap<LoopVariable> _vars, AnalyzingDoc analyzingDoc) {
//        addInnerPage(_cont);
//        String gl_ = _cont.getArgumentClass();
//        _cont.getAnalyzing().setGlobalType(new AnaFormattedRootBlock(_cont.getAnalyzing(),gl_));
        CustList<OperationNode> all_ = getQuickAnalyzed(_s, _cont, analyzingDoc);
        generalForward(_cont);
        CustList<RendDynOperationNode> executableNodes_ = getQuickExecutableNodes(_cont, all_);
        ContextEl ctx_ = getGenerate(_cont);
        assertTrue(_cont.getDualAnalyzedContext().getAnalyzed().isEmptyErrors());
        ExecClassesUtil.tryInitStaticlyTypes(ctx_, _cont.getDualAnalyzedContext().getForwards().getOptions());
        ctx_.setExiting(new NoExiting());
        RendStackCall st_ = reuse(ctx_, _cont, executableNodes_, _localVariables, _vars);
        assertTrue(_cont.getDualAnalyzedContext().getAnalyzed().isEmptyErrors());
        assertNotNull(getException(st_));
        return getException(st_);
    }

    private Struct getAnalyzedTestConfiguration(StringMap<String> files_, String _el) {
        DualNavigationContext conf_ = getConfigurationQuick(files_);
        RendStackCall rendStackCall_ = processElLow(_el, conf_, new StringMap<LocalVariable>(), new StringMap<LoopVariable>(), new AnalyzingDoc());
        return getException(rendStackCall_);
    }


}
