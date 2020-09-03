package code.formathtml;
import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import code.expressionlanguage.*;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.formathtml.util.*;
import org.junit.Test;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;
import code.util.StringMap;

public final class RenderExpUtilFailExecTest extends CommonRender {
    private static final String ARR_INTEGER = "[java.lang.Integer";
    @Test
    public void processEl7FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$new $int[-1i]", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl8FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$new java.lang.Integer[-1i]", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl182Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "v&=1/0 > 0";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(ctx_.getStandards().getAliasDivisionZero(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl183Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(BooleanStruct.of(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "v|=1/0 > 0";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(ctx_.getStandards().getAliasDivisionZero(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl184Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "v&=1 > 0";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }

    private static Delimiters checkSyntax(ContextEl ctx_, String elr_) {
        return ElResolver.checkSyntax(elr_, ctx_, 0);
    }

    @Test
    public void processEl185Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "v|=1 > 0";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertNotNull(getException(context_));
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl202Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$(java.lang.Byte)\"not cast\"", context_);
        assertTrue(context_.isEmptyErrors());
        Struct exc_ = getException(context_);
        assertNotNull(exc_);
        assertEq(context_.getStandards().getAliasCastType(),exc_.getClassName(context_.getContext()));
    }
    @Test
    public void processEl204Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        String el_ = "!v";
        processEl(el_, context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
        ContextEl ctx_= context_.getContext();
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl205Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasByte());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        String el_ = "$($byte)v";
        processEl(el_, context_);
        assertTrue(context_.isEmptyErrors());
        ContextEl ctx_= context_.getContext();
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl206Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "++v";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl207Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "v++";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl208Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(in_, ARR_INTEGER));
        lv_.setClassName(ARR_INTEGER);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "v[0i]++";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }

    @Test
    public void processEl209Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(in_, ARR_INTEGER));
        lv_.setClassName(ARR_INTEGER);
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        ContextEl ctx_ = context_.getContext();
        setupAnalyzing(context_);
        String elr_ = "++v[0i]";
        Delimiters d_ = checkSyntax(ctx_, elr_);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getPageEl().getGlobalArgument();
        boolean static_ = setupStaticCtx(ctx_, argGl_);
        CustList<OperationNode> all_ = RenderExpUtil.getSortedDescNodes(op_, context_);
        assertTrue(context_.isEmptyErrors());
        calculate(all_, context_);
        assertTrue(context_.isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), getException(context_).getClassName(ctx_));
    }
    @Test
    public void processEl242Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke($null)", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl243Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke(1i)", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl244Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i)", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
    }
    @Test
    public void processEl246Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,\"\")", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl247Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,$null)", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
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
        xml_.append("  $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(java.lang.String),$class($boolean))[0i];\n");
        xml_.append("  m.invoke($null,\"pkg.Ex\",$true);\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        Struct exc_ = getException(cont_);
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertTrue(cause_ instanceof CausingErrorStruct);
        cause_ = ((CausingErrorStruct)cause_).getCause();
        assertEq(cont_.getStandards().getAliasNullPe(), cause_.getClassName(cont_.getContext()));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        Struct exc_ = getException(cont_);
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertEq(cont_.getStandards().getAliasNullPe(), cause_.getClassName(cont_.getContext()));
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
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i];\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        Struct exc_ = getException(cont_);
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertTrue(cause_ instanceof CausingErrorStruct);
        cause_ = ((CausingErrorStruct)cause_).getCause();
        assertEq(cont_.getStandards().getAliasNullPe(), cause_.getClassName(cont_.getContext()));
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
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i];\n");
        xml_.append("  $try{\n");
        xml_.append("   m.invoke($null,8i);\n");
        xml_.append("  } $catch(java.lang.Object e);\n");
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        Struct exc_ = getException(cont_);
        assertTrue(exc_ instanceof CausingErrorStruct);
        Struct cause_ = ((CausingErrorStruct)exc_).getCause();
        assertSame(NullStruct.NULL_VALUE,cause_);
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        Struct exc_ = getException(cont_);
        assertEq(cont_.getStandards().getAliasClassNotFoundError(), exc_.getClassName(cont_.getContext()));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.isEmptyErrors());
        Struct exc_ = getException(cont_);
        assertEq(cont_.getStandards().getAliasNullPe(), exc_.getClassName(cont_.getContext()));
    }
    @Test
    public void processEl306Test() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        processEl("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke(\"\")", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("pkg.ExTwo.exmeth()+pkg.Ex.inst", cont_);
        assertNotNull(getException(cont_));
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(!cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        processEl("$new{} pkg.Ex(5).inst", conf_);
        assertNotNull(getException(conf_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(!conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex(5).inst", conf_);
        assertNotNull(getException(conf_));
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(!conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl367Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("($int)arg", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl368Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(BooleanStruct.of(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("($boolean)arg2", context_);
        assertNotNull(getException(context_));
    }
    @Test
    public void processEl375Test() {
        Configuration context_ = getConfiguration4();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getStandards().getAliasString()));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        processEl("$new{} pkg.Ex(5).inst/=0", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        processEl("pkg.Ex.inst/=0", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        processEl("pkg.Ex.elt.inst=10", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        processEl("pkg.Ex.elt.inst", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_,false);
        addImportingPage(conf_);
        processEl("pkg.Ex.inst=10", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.elt.res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("(pkg.Ex.inst).$classchoice(pkg.Ex<java.lang.Integer>)res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.elt.$classchoice(pkg.Ex)res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.$classchoice(pkg.Ex)res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.elt.$superaccess(pkg.Ex)res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("pkg.Ex.$superaccess(pkg.Ex)res(8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$values(pkg.ExTwo).length", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$valueOf(pkg.ExTwo,\"ONE\").myval", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("$valueOf(pkg.ExTwo,\"TWO\").myval", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("($(pkg.Int)(5,$interfaces(pkg.Int2)(),$interfaces(pkg.Int)())).pl(6,8)", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("($(pkg.Int)($static().$lambda($math,plus,$int,$int),$interfaces(pkg.Int2)(),$interfaces(pkg.Int)()))", conf_);
        assertNotNull(getException(conf_));
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_.getLastPage(), localVars_);
        processEl("$new pkg.Ex<java.lang.Integer>(5).$classchoice(pkg.Ex<java.lang.String>)[\"\"]", conf_);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex",cont_.getClasses().getClassBody("pkg.Ex"), "", -1);
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
        Configuration conf_ = getConfiguration4(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex",cont_.getClasses().getClassBody("pkg.Ex"), "", -1);
        setStruct(value_,new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        CommonRender.setLocalVars(conf_.getLastPage(), localVars_);
        processEl("pkg.Ex.st[0].myval", conf_);
        assertNotNull(getException(conf_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("pkg.Apply.test()", cont_);
        assertNotNull(getException(cont_));
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        processEl("\"\"+$new pkg.Ex()", cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void processEl467Test() {
        StringMap<String> files_ = new StringMap<String>();
        Configuration conf_ = getConfiguration4(files_);
        addImportingPage(conf_);
        processEl("explicit($int)\"5\"", conf_);
        assertTrue(conf_.isEmptyErrors());
        assertNotNull(getException(conf_));

    }
    @Test
    public void processAffect8FailTest() {
        Configuration context_ = getConfiguration4();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v=v2", context_);
        assertTrue(context_.isEmptyErrors());
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
        Configuration cont_ = getConfiguration4(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(cont_.getLastPage(), localVars_);
        processEl("$classchoice(pkg.Ex)inst=v", cont_);
        assertTrue(cont_.isEmptyErrors());
        assertNotNull(getException(cont_));
    }
    @Test
    public void processAffect12FailTest() {
        Configuration context_ = getConfiguration4();
        LgNames custLgNames_ = context_.getContext().getStandards();
        String stringType_ = custLgNames_.getAliasString();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(exp_, StringExpUtil.getPrettyArrayType(stringType_)));
        lv_.setClassName(StringExpUtil.getPrettyArrayType(context_.getStandards().getAliasObject()));
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v[0i]=v2", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }
    @Test
    public void processAffect16FailTest() {
        Configuration context_ = getConfiguration4();
        LgNames custLgNames_ = context_.getContext().getStandards();
        String primIntType_ = custLgNames_.getAliasPrimInteger();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(exp_, StringExpUtil.getPrettyArrayType(primIntType_)));
        lv_.setClassName(StringExpUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        CommonRender.setLocalVars(context_.getLastPage(), localVars_);
        processEl("v[0i]/=0", context_);
        assertTrue(context_.isEmptyErrors());
        assertNotNull(getException(context_));
    }

    private static void processEl(String _el, Configuration _cont) {
        if (_cont.hasPages() && _cont.getContext().getAnalyzing() != null) {
            _cont.getContext().setGlobalClass(_cont.getLastPage().getGlobalClass());
        }
        processEl(_el, 0, _cont);
        assertTrue(_cont.isEmptyErrors());
        assertNotNull(getException(_cont));
    }

    private static boolean setupStaticCtx(ContextEl _ctx, Argument _argGl) {
        boolean static_ = _argGl == null || _argGl.isNull();
        _ctx.getAnalyzing().setAccessStaticContext(MethodId.getKind(static_));
        return static_;
    }

    private static void calculate(CustList<OperationNode> _ops, Configuration _an) {
        CustList<RendDynOperationNode> out_ = RenderExpUtil.getExecutableNodes(_ops,_an.getContext());
        out_ = RenderExpUtil.getReducedNodes(out_.last());
        Argument arg_ = RenderExpUtil.calculateReuse(out_, _an);
        assertNotNull(getException(_an));
    }

    private static Configuration getConfiguration4() {
        return getConfiguration4(new StringMap<String>());
    }
    private static Configuration getConfiguration4(StringMap<String> _files) {
        return getConfiguration4(_files,true);
    }
    private static Configuration getConfiguration4(StringMap<String> _files, boolean _init) {
        Configuration conf_ = getConfiguration(_files, _init);
        return conf_;
    }

    private static Configuration getConfiguration(StringMap<String> _files, boolean _init) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setReadOnly(true);
        ContextEl cont_ = InitializationLgNames.buildStdThree(opt_);
        conf_.setContext(cont_);
        cont_.setFullStack(new AdvancedFullStack(conf_));
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        Classes.validateWithoutInit(_files, cont_);
        assertTrue(cont_.isEmptyErrors());
        Classes.tryInitStaticlyTypes(cont_);
        ((BeanCustLgNames)standards_).buildIterables(conf_);
        return conf_;
    }

}
