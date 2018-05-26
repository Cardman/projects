package code.formathtml;

import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Delimiters;
import code.expressionlanguage.ElResolver;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.classes.ArrayContainer;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.Composite;
import code.formathtml.classes.CustLgNames;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public final class ElRenderUtilTest {

    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";
    private static final String COMPOSITE = "code.expressionlanguage.classes.Composite";

    private static final String ALIAS_BEAN_ONE = "code.expressionlanguage.classes.BeanOne";

    @Test
    public void processEl1Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("5",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(5L, (Number)res_);
    }
    @Test
    public void processEl2Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static(java.lang.Long).MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(Long.MAX_VALUE, (Number)res_);
    }

    @Test
    public void processEl3Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(1+2)*3",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl4Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1--1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(2L, (Number)res_);
    }

    @Test
    public void processEl5Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+2*3",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(7L, (Number)res_);
    }

    @Test
    public void processEl6Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("--1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(1L, (Number)res_);
    }

    @Test
    public void processEl7Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(-8l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl8Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(8l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl9Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = ElRenderUtil.processEl("composite.integer",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl10Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("40908c",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq((char)40908, ((Character)res_).charValue());
    }

    @Test
    public void processEl11Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'\\u9fcb'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq((char)40907, ((Character)res_).charValue());
    }

    @Test
    public void processEl12Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'\\\\'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq('\\', ((Character)res_).charValue());
    }

    @Test
    public void processEl13Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'\\''",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq('\'', ((Character)res_).charValue());
    }

    @Test
    public void processEl14Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'\"'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq('"', ((Character)res_).charValue());
    }

    @Test
    public void processEl15Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'\\n'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq('\n', ((Character)res_).charValue());
    }

    @Test
    public void processEl16Test() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StdStruct(compos_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("v;.integer",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl17Test() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setStruct(new StdStruct(compos_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        Argument arg_ = ElRenderUtil.processEl("v;integer",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0,(Number) res_);
    }

    @Test
    public void processEl18Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("5 $instanceof java.lang.Number",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl19Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'5' $instanceof java.lang.Number",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl20Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("!('5' $instanceof java.lang.Number)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }


    @Test
    public void processEl21Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1=2",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl22Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1!=2",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl23Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1=2&1+0=8",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl24Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1!=2|1+7=8",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl25Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1=2&(1+0=8|3*3=9)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }
    @Test
    public void processEl26Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1=2|1+6=8&1=1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl27Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);

        Argument arg_ = ElRenderUtil.processEl("$new code.util.StringList()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof StringList);
        assertEq(new StringList(), (StringList)res_);
    }

    @Test
    public void processEl28Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = ElRenderUtil.processEl("composite.integer",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl29Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1+1=2|1/0>8",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }














    @Test
    public void processEl31Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(-8i)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(8, (Number)res_);
    }

    @Test
    public void processEl32Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(8i)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(8, (Number)res_);
    }


    @Test
    public void processEl33Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(-8I)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(8, (Number)res_);
    }

    @Test
    public void processEl34Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(8I)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(8, (Number)res_);
    }


    @Test
    public void processEl35Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(-8L)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl36Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(8L)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl37Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenTwo($null)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("one", (String)res_);
    }

    @Test
    public void processEl38Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenTwo($(java.lang.Object)$null)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("two", (String)res_);
    }

    @Test
    public void processEl39Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$null $instanceof java.lang.Object",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl40Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(1)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl41Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("long", (String)res_);
    }

    @Test
    public void processEl42Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(1.0)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Double", (String)res_);
    }

    @Test
    public void processEl43Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(1.0d)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("double", (String)res_);
    }

    @Test
    public void processEl44Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(1.0F)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("double", (String)res_);
    }

    @Test
    public void processEl45Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(1.0f)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("double", (String)res_);
    }











































































































    @Test
    public void processEl56Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new code.expressionlanguage.classes.InheritedComposite().getPrivateInt()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }























    @Test
    public void processEl58Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs:{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("f;.format($vararg(java.lang.String),$firstopt(v;.),2;.,v;.)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("varargs:7 8 7", (String)res_);
    }

    @Test
    public void processEl59Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs:{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("f;.format($vararg(java.lang.String))",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("varargs:{0} {1} {2}", (String)res_);
    }

    @Test
    public void processEl60Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs:{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("f;.format(v;.,2;.,v;.)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("varargs:7 8 7", (String)res_);
    }

    @Test
    public void processEl61Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs:{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("f;.format()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("varargs:{0} {1} {2}", (String)res_);
    }

    @Test
    public void processEl62FailTest() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement("bonjour");
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement("tout");
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("$new code.expressionlanguage.classes.Composite($vararg(java.lang.String),$firstopt(v;.),2;.).getStrings()",0, context_);
        assertNotNull(context_.getContext().getException());
//        Object res_ = arg_.getObject();
//        assertTrue(res_ instanceof StringList);
//        assertEq(new StringList("bonjour","tout"), (StringList)res_);
    }

    @Test
    public void processEl63Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [$int(1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertEq(0, (Number) ((Struct[])res_.getInstance())[0].getInstance());
    }

    @Test
    public void processEl64Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [[$int(1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[0]);
    }

    @Test
    public void processEl65Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [java.lang.Integer(2i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(2, ((Struct[])res_.getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[1]);
    }

    @Test
    public void processEl66Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [[java.lang.Integer(2i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(2, ((Struct[])res_.getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[1]);
    }

    @Test
    public void processEl67Test() {
        Configuration context_ = contextEl();
        Composite composite_ = new Composite();
        addImportingPage(context_);
        addBean(context_,composite_, COMPOSITE);
        assertEq(0, composite_.getPrivateInt());
        ElRenderUtil.processEl("setPrivateInt(2i)",0, context_);
        assertEq(2, composite_.getPrivateInt());
    }

    @Test
    public void processEl68Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] ints_ = new Struct[2];
        ints_[0] = new IntStruct(0);
        ints_[1] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(ints_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("arrays", lv_);
        Configuration context_ = contextEl();
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("arrays;.[0i]",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }


    @Test
    public void processEl69Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] ints_ = new Struct[2];
        Struct[] elt_ = new Struct[2];
        elt_[0] = new IntStruct(0);
        elt_[1] = new IntStruct(0);
        ints_[0] = new ArrayStruct(elt_, ARR_INT);
        elt_ = new Struct[2];
        elt_[0] = new IntStruct(0);
        elt_[1] = new IntStruct(0);
        ints_[1] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(ints_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("arrays", lv_);
        Configuration context_ = contextEl();
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("arrays;.[0i].length",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(2, (Number)res_);
    }

    @Test
    public void processEl70Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("!!$false",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl71Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        addBeanClassName(context_,context_.getStandards().getAliasByte());
        Argument arg_ = ElRenderUtil.processEl("MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Byte);
        assertEq((byte)127, (Number)res_);
    }

    @Test
    public void processEl72Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static(java.lang.Byte).MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Byte);
        assertEq((byte)127, (Number)res_);
    }

    @Test
    public void processEl73Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new code.util.StringList()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof StringList);
        assertEq(new StringList(), (StringList)res_);
    }



































    @Test
    public void processEl77Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(\"Hello\\\\\"+\"World\").length()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(11, (Number)res_);
    }

    @Test
    public void processEl78Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(\"Hello\\\"\"+\"World\").length()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(11, (Number)res_);
    }

    @Test
    public void processEl79Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(\"Hello\\\\\"+'\\\\').length()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(7, (Number)res_);
    }
    @Test
    public void processEl80Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(\"Hello\\\"\"+'\\'').length()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(7, (Number)res_);
    }

    @Test
    public void processEl81Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String stringType_ = context_.getStandards().getAliasString();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("7"));
        lv_.setClassName(stringType_);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("8"));
        lv_.setClassName(stringType_);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("varargs:{0} {1} {2}"));
        lv_.setClassName(stringType_);
        localVars_.put("f", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("(f;.format($vararg(java.lang.String),$firstopt(v;.),2;.,v;.)+'\\'').length()",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(14, (Number)res_);
    }

    @Test
    public void processEl82Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).abs(v;.[0i]+2)*2",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(20L, (Number)res_);
    }

    @Test
    public void processEl83Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("(v;.[0i]+2)*2",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(20L, (Number)res_);
    }



















































    @Test
    public void processEl87Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("$bool(1>0,0i,1i)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl88Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("$bool(1<0,0i,1i)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(1, (Number)res_);
    }

    @Test
    public void processEl89Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("$bool(1>0,0i,1i/0i)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl90Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("$bool(1<0,1i/0i,1i)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(1,(Number) res_);
    }





































































    @Test
    public void processEl95Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("$new [$int(1i)[0i]",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl96Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [$int[](2i)", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((Number)o_[0].getInstance()).intValue());
    }

    @Test
    public void processEl97Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [$int[](3i,7i)", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((Number)o_[0].getInstance()).intValue());
        assertEq(7, ((Number)o_[1].getInstance()).intValue());
    }

    @Test
    public void processEl98Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [$int[]()", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(0, o_.length);
    }

    @Test
    public void processEl99Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [java.lang.Integer[](3i,7i)", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_.getContext()));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((Number)o_[0].getInstance()).intValue());
        assertEq(7, ((Number)o_[1].getInstance()).intValue());
    }

    @Test
    public void processEl100Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("9 hello world {every body ;)", (String)res_);
        assertEq(43, context_.getNextIndex());
    }

    @Test
    public void processEl101Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(" {(\"hello \"+\"world\").length()} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(11, (Number)res_);
        assertEq(30, context_.getNextIndex());
    }

    @Test
    public void processEl102Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(arg;.)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl103Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(context_.getStandards().getAliasPrimLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = ElRenderUtil.processEl("getOverridenThree(arg;.)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("long", (String)res_);
    }












    @Test
    public void processEl105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElRenderUtil.processEl("$static(pkg.Ex).exmeth()", 0, cont_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(9, (Number)res_);
    }


    @Test
    public void processEl106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElRenderUtil.processEl("$static(pkg.Ex).exmeth(6i)", 0, cont_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(15,(Number) res_);
    }

    @Test
    public void processEl107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElRenderUtil.processEl("$new pkg.Ex()", 0, cont_);
        Struct res_ = arg_.getStruct();
        assertEq("pkg.Ex",res_.getClassName(cont_.getContext()));
    }












    @Test
    public void processEl109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElRenderUtil.processEl("$classchoice(pkg.Ex)exmeth(6i)", 0, cont_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(15,(Number) res_);
    }

    @Test
    public void processEl110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=2i:\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElRenderUtil.processEl("$classchoice(pkg.Ex)inst", 0, cont_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(2,(Number) res_);
    }

    @Test
    public void processEl111Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenFour($null)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl112Test() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StdStruct(compos_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().getCatchVars().putAllMap(localVars_);
        Argument arg_ = ElRenderUtil.processEl("v;..integer",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl113Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenFour(1L)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl114Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenFour(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("long", (String)res_);
    }

    @Test
    public void processEl115Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenFive(1L)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl116Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenFive(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("double", (String)res_);
    }

    @Test
    public void processEl117Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenSix(1L)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl118Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElRenderUtil.processEl("getOverridenSix(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("long",(String) res_);
    }

    @Test
    public void processEl119Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(1b+2b)*3",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl120Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(1s+2b)*3",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl121Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("--1b",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(1, (Number)res_);
    }

    @Test
    public void processEl122Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-1b",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Byte);
        assertEq(-1, (Number)res_);
    }

    @Test
    public void processEl123FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("+1b",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl124Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("+-1b",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl125Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-.25e0+.5",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.25d, (Number)res_);
    }

    @Test
    public void processEl126Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("9 hello world {every body ;)", (String)res_);
        int nextIndex_ = context_.getNextIndex();
        assertEq(43, nextIndex_);
        arg_ = ElRenderUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, nextIndex_+1 ,'{','}');
        res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(40, (Number) res_);
        nextIndex_ = context_.getNextIndex();
        assertEq(48, nextIndex_);
        
    }

    @Test
    public void processEl127Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(" {(\"hello \"+\"world\").length()}{5*8} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(11, (Number)res_);
        int nextIndex_ = context_.getNextIndex();
        assertEq(30, nextIndex_);
        arg_ = ElRenderUtil.processEl(" {(\"hello \"+\"world\").length()}{5*8} ", context_, nextIndex_+1 ,'{','}');
        res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(40, (Number) res_);
        nextIndex_ = context_.getNextIndex();
        assertEq(35, nextIndex_);
    }

    @Test
    public void processEl128Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1_0+2*3",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(16L, (Number)res_);
    }

    @Test
    public void processEl129Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).mod(-8l,3l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(1L, (Number)res_);
    }

    @Test
    public void processEl130Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static($math).quot(-8l,3l)",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(-3L, (Number)res_);
    }

    @Test
    public void processEl131Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [$int(1i,1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertEq(ARR_INT, ((Struct[])res_.getInstance())[0].getClassName(context_.getContext()));
        assertEq(1, ((Struct[])((Struct[])res_.getInstance())[0].getInstance()).length);
        assertEq(0, (Number) ((Struct[])((Struct[])res_.getInstance())[0].getInstance())[0].getInstance());
    }

    @Test
    public void processEl132Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$new [java.lang.Integer(1i,1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertEq(ARR_INTEGER, ((Struct[])res_.getInstance())[0].getClassName(context_.getContext()));
        assertSame(NullStruct.NULL_VALUE, ((Struct[])((Struct[])res_.getInstance())[0].getInstance())[0]);
    }

    @Test
    public void processEl133Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("(1 + 2) * 3.0",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl134Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(" 2.0 + $static($math). quot( -8l, 3l) + 3.0",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(2L, (Number)res_);
    }
    
    @Test
    public void processEl135Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1 + 2 ",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(3L, (Number)res_);
    }

    @Test
    public void processEl136Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1. + 2. ",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(3L, (Number)res_);
    }

    @Test
    public void processEl137Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1.d + 2.d ",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(3L, (Number)res_);
    }

    @Test
    public void processEl138Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-.2_5e0+.5",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.25d, (Number)res_);
    }

    @Test
    public void processEl139Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-.25e0_0+.5",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.25d, (Number)res_);
    }

    @Test
    public void processEl140Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1_0.d + 2.d ",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(12L, (Number)res_);
    }
    @Test
    public void processEl141Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1.05e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(10.5d, (Number)res_);
    }
    @Test
    public void processEl142Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1.00625e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(10.0625d, (Number)res_);
    }
    @Test
    public void processEl143Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("100.625e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(10.0625d, (Number)res_);
    }
    @Test
    public void processEl144Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("100.625",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(100.625d, (Number)res_);
    }
    @Test
    public void processEl145Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456789123456789123456789.0",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1.2345678912345678912e26, (Number)res_);
    }
    @Test
    public void processEl147Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456789123456789123456789.",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1.2345678912345678912e26, (Number)res_);
    }
    @Test
    public void processEl148Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456789123456789123456789.e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1.2345678912345678912e25, (Number)res_);
    }
    @Test
    public void processEl149Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456789123456789123456789.e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1.2345678912345678912e27, (Number)res_);
    }
    @Test
    public void processEl150Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456.e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1234560, (Number)res_);
    }
    @Test
    public void processEl151Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(".078125e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(.078125e-1, (Number)res_);
    }
    @Test
    public void processEl152Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456789123456789123456789.0e-36",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1.2345678912345678912e-10, (Number)res_);
    }
    @Test
    public void processEl153Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("0.0e-36",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.0, (Number)res_);
    }
    @Test
    public void processEl154Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-0.0e-36",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-0.0, (Number)res_);
    }
    @Test
    public void processEl155Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("0.625e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.0625, (Number)res_);
    }
    @Test
    public void processEl156Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(".625e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.0625, (Number)res_);
    }
    @Test
    public void processEl157Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("0.625e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(6.25, (Number)res_);
    }
    @Test
    public void processEl158Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(".625e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(6.25, (Number)res_);
    }
    @Test
    public void processEl159Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("0.625e0",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.625, (Number)res_);
    }
    @Test
    public void processEl160Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(".625e0",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.625, (Number)res_);
    }
    @Test
    public void processEl161Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-.625e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-6.25, (Number)res_);
    }
    @Test
    public void processEl162Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-.6e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-6.0, (Number)res_);
    }
    @Test
    public void processEl163Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-.60e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-6.0, (Number)res_);
    }
    @Test
    public void processEl164Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(".6e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(6.0, (Number)res_);
    }
    @Test
    public void processEl165Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl(".6e2",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(60.0, (Number)res_);
    }
    @Test
    public void processEl166Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("123456789123456789123456789.1e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(1.2345678912345678912e27, (Number)res_);
    }
    @Test
    public void processEl167Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("100.e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(10.0, (Number)res_);
    }
    @Test
    public void processEl168Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-100.e-1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-10.0, (Number)res_);
    }
    @Test
    public void processEl169Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-1.e1",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-10.0, (Number)res_);
    }
    @Test
    public void processEl170Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-1.",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-1.0, (Number)res_);
    }
    @Test
    public void processEl171Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1e-123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(0.0, (Number)res_);
    }
    @Test
    public void processEl172Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-1e-123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(-0.0, (Number)res_);
    }
    @Test
    public void processEl173Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("1e123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(Double.POSITIVE_INFINITY, (Number)res_);
    }
    @Test
    public void processEl174Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("-1e123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Double);
        assertEq(Double.NEGATIVE_INFINITY, (Number)res_);
    }
    @Test
    public void processEl175Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'\\u9FCB'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Character);
        assertEq((char)40907, ((Character)res_).charValue());
    }
    @Test
    public void processEl176Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("\"\\u9FCB\"",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("\u9fcb", (String)res_);
    }
    @Test
    public void processEl177Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("\"\\u9fcb\"",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("\u9fcb", (String)res_);
    }
    @Test
    public void processEl178Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$static(java.lang.Long) .MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(Long.MAX_VALUE, (Number)res_);
    }
    @Test
    public void processEl1FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        ElRenderUtil.processEl("getOverridenOne($null)",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl2FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$(Object)$null",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }








    @Test
    public void processEl4FailTest() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        addBean(context_,new Composite(), COMPOSITE);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("setPrivateInt(arg;.)",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }










    @Test
    public void processEl6FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$new java.lang.Number()",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl7FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$new [$int(-1i)",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl8FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$new [java.lang.Integer(-1i)",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }


    @Test
    public void processEl9FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        CustLgNames cust_ = (CustLgNames) context_.getContext().getStandards();
        addBeanClassName(context_,cust_.getAliasComposite());
        ElRenderUtil.processEl("integer",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }










    @Test
    public void processEl11FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("MAX_VALUE",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }
    
    @Test
    public void processEl12FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.Composite).integer",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl13FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.Composite).int$$eger",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl14FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.StrangeInit).NOT_READ",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl15FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.StrangeInit).fail()",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl16FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$new code.expressionlanguage.classes.StrangeInit()",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl17FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.FailMethods).fail()",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl18FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$new code.expressionlanguage.classes.FailMethods()",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl19FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("get(arg;.)",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl20FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.Composite).getInteger()",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl21FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.FailMethods).fail().arg;.",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl22FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setElement(1l);
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.FailMethods).fail().arg;",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl23FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setElement(1l);
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        ElRenderUtil.processEl("$static(code.expressionlanguage.classes.FailMethods).fail().arg;;",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl24FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "$firstopt(6)*(7+8)";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl25FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format(\"6\",$vararg(6))";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl26FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl27FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "$vararg(java.lang.Object)*(7+8)";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl28FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl29FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($firstopt(6),\"6\")";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }
    
    @Test
    public void processEl30FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "";
        ElRenderUtil.processEl(el_, 0, context_);
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl31FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "get(,)";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl33FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1<2<3";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl34FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(3,4)";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl35FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1< ";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl36FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1<";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl37FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl38FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!=";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl39FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl40FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl42FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$true!$false";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl43FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static(code.expressionlanguage.classes.FailMethods).(fail())";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processEl44FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.[0i,1i]";
        ElRenderUtil.processEl(el_, 0, conf_);
        assertTrue(!conf_.getClasses().getErrorsDet().isEmpty());
    }
    @Test
    public void processEl179Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.+=1i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(4, (Number)lv_.getElement());
    }
    @Test
    public void processEl180Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.&=$false";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(false, (Boolean)lv_.getElement());
    }
    @Test
    public void processEl181Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.|=$true";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(true, (Boolean)lv_.getElement());
    }
    @Test
    public void processEl182Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.&=1/0 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertNotNull(ctx_.getException());
        assertEq(ctx_.getStandards().getAliasDivisionZero(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl183Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.|=1/0 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertNotNull(ctx_.getException());
        assertEq(ctx_.getStandards().getAliasDivisionZero(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl184Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.&=1 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertNotNull(ctx_.getException());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl185Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.|=1 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertNotNull(ctx_.getException());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl186Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.==1i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(false, (Boolean)op_.getArgument().getObject());
    }
    @Test
    public void processEl187Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(4, (Number)lv_.getElement());
        assertEq(3, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl188Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "++v;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(4, (Number)lv_.getElement());
        assertEq(4, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl189Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.[0i]++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(6, (Number) in_[0].getInstance());
        assertEq(5, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl190Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "++v;.[0i]";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(6, (Number) in_[0].getInstance());
        assertEq(6, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl191Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.+=2i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(5, (Number)lv_.getElement());
        assertEq(5, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl192Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.[0i]+=3i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(8, (Number) in_[0].getInstance());
        assertEq(8, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl193Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setElement(12);
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.+++v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(12, (Number)lv2_.getElement());
        assertEq(4, (Number)lv_.getElement());
        assertEq(15, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl194Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setElement(12);
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.---v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(12, (Number)lv2_.getElement());
        assertEq(2, (Number)lv_.getElement());
        assertEq(-9, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl195Test() {
        Configuration context_ = contextEl(true, false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setElement(12);
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.=++v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(13, (Number)lv2_.getElement());
        assertEq(13, (Number)lv_.getElement());
        assertEq(13, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl196Test() {
        Configuration context_ = contextEl(true, true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(3);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setElement(12);
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.= ++v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertEq(13, (Number)lv2_.getElement());
        assertEq(13, (Number)lv_.getElement());
        assertEq(13, (Number)op_.getArgument().getObject());
    }
    @Test
    public void processEl197Test() {
        Configuration context_ = contextEl(true, false);
        addImportingPage(context_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.setRootAffect(true);
        String elr_ = "+1b";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        Object res_ = op_.getArgument().getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(1, (Number)res_);
    }
    @Test
    public void processEl198Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$(java.lang.Number)5",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Long);
        assertEq(5L, (Number)res_);
    }
    @Test
    public void processEl199Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$($byte)5",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Byte);
        assertEq(5L, (Number)res_);
    }
    @Test
    public void processEl200Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$($byte)$null",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }
    @Test
    public void processEl201Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("$(java.lang.Byte)$null",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNull(context_.getContext().getException());
        assertSame(NullStruct.NULL_VALUE, arg_.getStruct());
    }
    @Test
    public void processEl202Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ElRenderUtil.processEl("$(java.lang.Byte)\"not cast\"",0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        Struct exc_ = context_.getContext().getException();
        assertNotNull(exc_);
        assertEq(context_.getStandards().getAliasCast(),exc_.getClassName(context_.getContext()));
    }

    @Test
    public void processEl203Test() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!=2!=3";
        Argument arg_ = ElRenderUtil.processEl(el_, 0, conf_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Boolean);
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl204Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        String el_ = "!v;.";
        ElRenderUtil.processEl(el_, 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getException());
        ContextEl ctx_= context_.getContext();
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl205Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasByte());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        String el_ = "$($byte)v;.";
        ElRenderUtil.processEl(el_,0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ContextEl ctx_= context_.getContext();
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl206Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "++v;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl207Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl208Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(in_, ARR_INTEGER));
        lv_.setClassName(ARR_INTEGER);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "v;.[0i]++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl209Test() {
        Configuration context_ = contextEl(true);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(in_, ARR_INTEGER));
        lv_.setClassName(ARR_INTEGER);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.toContextEl();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        ctx_.getAnalyzing().setLocalVars(localVars_);
        ctx_.setRootAffect(true);
        String elr_ = "++v;.[0i]";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_.substring(0);
        ctx_.setAnalyzingRoot(true);
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElUtil.getSortedDescNodes(op_, static_, ctx_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        ctx_.setAnalyzing(null);
        ElRenderUtil.calculate(all_, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }

    @Test
    public void processEl210Test() {
        Configuration context_ = contextEl(true,false,false);
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'1'+'2'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(99, (Number)res_);
    }

    @Test
    public void processEl211Test() {
        Configuration context_ = contextEl(true,false,true);
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("'1'+'2'",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof String);
        assertEq("12", (String)res_);
    }
    @Test
    public void processEl212Test() {
        Configuration context_ = contextEl(true,false,false);
        addImportingPage(context_);
        Argument arg_ = ElRenderUtil.processEl("('1'+'2')*3i",0, context_);
        Object res_ = arg_.getObject();
        assertTrue(res_ instanceof Integer);
        assertEq(297, (Number)res_);
    }
    @Test
    public void processEl213FailTest() {
        Configuration context_ = contextEl(true,false,true);
        addImportingPage(context_);
        ElRenderUtil.processEl("('1'+'2')*3i",0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }
    @Test
    public void processAffect1Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(0);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.=1i", 0, context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(1, (Number)lv_.getElement());
    }

    @Test
    public void processAffect2Test() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        ElRenderUtil.processEl("v;.integer=12i", 0, context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(12, c_.getInteger());
    }

    @Test
    public void processAffect3Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i]=12i", 0, context_);
        assertEq(12, (Number) in_[0].getInstance());
    }

    @Test
    public void processAffect4Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i][0i]=12i", 0, context_);
        assertEq(12, (Number) ((Struct[])in_[0].getInstance())[0].getInstance());
    }

    @Test
    public void processAffect5Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.+=1i", 0, context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(2, (Number)lv_.getElement());
    }

    @Test
    public void processAffect6Test() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        Argument res_ = ElRenderUtil.processEl("v;.integer-=12i", 0, context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(-12, c_.getInteger());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(-12, (Number)res_.getObject());
    }

    @Test
    public void processAffect7Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = ElRenderUtil.processEl("v;.[0i]-=12i", 0, context_);
        assertEq(-12, (Number) in_[0].getInstance());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(-12, (Number)res_.getObject());
    }

    @Test
    public void processAffect8Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = ElRenderUtil.processEl("v;.[0i][0i]-=12i", 0, context_);
        assertEq(-12, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(-12, (Number)res_.getObject());
    }

    @Test
    public void processAffect9Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = ElRenderUtil.processEl("v;.[0i][0i]++", 0, context_);
        assertEq(1, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(0, (Number)res_.getObject());
    }

    @Test
    public void processAffect10Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = ElRenderUtil.processEl("v;.[0i][0i]--", 0, context_);
        assertEq(-1, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(0, (Number)res_.getObject());
    }


    @Test
    public void processAffect11Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = ElRenderUtil.processEl("++v;.[0i][0i]", 0, context_);
        assertEq(1, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(1, (Number)res_.getObject());
    }

    @Test
    public void processAffect12Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        Struct[] elt_ = new Struct[1];
        elt_[0] = new IntStruct(0);
        in_[0] = new ArrayStruct(elt_, ARR_INT);
        lv_.setStruct(new ArrayStruct(in_,ARR_ARR_INT));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = ElRenderUtil.processEl("--v;.[0i][0i]", 0, context_);
        assertEq(-1, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(-1, (Number)res_.getObject());
    }





































    @Test
    public void processAffect14Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new StdStruct(new ArrayContainer(),"code.expressionlanguage.classes.ArrayContainer");
        lv_.setStruct(new ArrayStruct(c_, "[code.expressionlanguage.classes.ArrayContainer"));
        lv_.setClassName("[code.expressionlanguage.classes.ArrayContainer");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i].array[0i]=1i", 0, context_);
        assertEq(1,((ArrayContainer)c_[0].getInstance()).getArray()[0]);
    }

    @Test
    public void processAffect15Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new StdStruct(new ArrayContainer(),"code.expressionlanguage.classes.ArrayContainer");
        lv_.setStruct(new ArrayStruct(c_, "[code.expressionlanguage.classes.ArrayContainer"));
        lv_.setClassName("[code.expressionlanguage.classes.ArrayContainer");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i].getArray()[0i]=1i", 0, context_);
        assertEq(1,((ArrayContainer)c_[0].getInstance()).getArray()[0]);
    }

    @Test
    public void processAffect16Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new StdStruct(new ArrayContainer(),"code.expressionlanguage.classes.ArrayContainer");
        lv_.setStruct(new ArrayStruct(c_, "[code.expressionlanguage.classes.ArrayContainer"));
        lv_.setClassName("[code.expressionlanguage.classes.ArrayContainer");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i].getCompo()[0i].getArray()[0i]=1i", 0, context_);
        assertEq(1, ((ArrayContainer)c_[0].getInstance()).getCompo()[0].getArray()[0]);
    }









































    @Test
    public void processAffect19Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer c_ = new ArrayContainer();
        lv_.setStruct(new StdStruct(c_,"code.expressionlanguage.classes.ArrayContainer"));
        lv_.setClassName("code.expressionlanguage.classes.ArrayContainer");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.getArray()[0i]=1i", 0, context_);
        assertEq(1, c_.getArray()[0]);
    }

    @Test
    public void processAffect20Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer c_ = new ArrayContainer();
        lv_.setStruct(new StdStruct(c_,"code.expressionlanguage.classes.ArrayContainer"));
        lv_.setClassName("code.expressionlanguage.classes.ArrayContainer");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.getCompo()[0i].getArray()[0i]=1i", 0, context_);
        assertEq(1, c_.getCompo()[0].getArray()[0]);
    }

    @Test
    public void processAffect21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.Integer inst:\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_, true,false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Struct arg_;
        Object res_;
        ElRenderUtil.processEl("$classchoice(pkg.Ex)inst=2i", 0, cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = arg_.getInstance();
        assertTrue(res_ instanceof Integer);
        assertEq(2,(Number) res_);
    }

    @Test
    public void processAffect22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.Integer inst:\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_, true,false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Struct arg_;
        Object res_;
        ElRenderUtil.processEl("$classchoice(pkg.Ex)inst=2i", 0, cont_);
        ElRenderUtil.processEl("$classchoice(pkg.Ex)inst=v;.", 0, cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = arg_.getInstance();
        assertNull(res_);
    }

    @Test
    public void processAffect23Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("add "));
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.+=1i", 0, context_);
        assertEq(context_.getStandards().getAliasString(), lv_.getClassName());
        assertEq("add 1", (String)lv_.getElement());
    }


    @Test
    public void processAffect24Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] arr_ = new Struct[1];
        arr_[0] = new StringStruct("add ");
        String arrayType_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasString());
        lv_.setStruct(new ArrayStruct(arr_, arrayType_));
        lv_.setClassName(arrayType_);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i]+=1i", 0, context_);
        assertEq(arrayType_, lv_.getClassName());
        assertEq("add 1",(String)((Struct[]) lv_.getStruct().getInstance())[0].getInstance());
    }

    @Test
    public void processAffect25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static [$int inst=$new [$int(1i):\n");
        xml_.append(" $public $static [$int exmeth(){\n");
        xml_.append("  $return inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_, true,false);
        addImportingPage(cont_);
        Struct arg_;
        Object res_;
        ElRenderUtil.processEl("$static(pkg.Ex).exmeth()[0i]=2i", 0, cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = arg_.getInstance();
        assertEq(1,((Struct[])res_).length);
        assertEq(2,(Number)((Struct[])res_)[0].getInstance());
    }
    @Test
    public void processAffect26Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.&=$false", 0, context_);
        assertEq(context_.getStandards().getAliasPrimBoolean(), lv_.getClassName());
        assertEq(false, (Boolean)lv_.getElement());
    }
    @Test
    public void processAffect27Test() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.|=$true", 0, context_);
        assertEq(context_.getStandards().getAliasPrimBoolean(), lv_.getClassName());
        assertEq(true, (Boolean)lv_.getElement());
    }

    @Test
    public void processAffect28Test() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        Argument res_ = ElRenderUtil.processEl("v;.integer++", 0, context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(1, c_.getInteger());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(0, (Number)res_.getObject());
    }

    @Test
    public void processAffect29Test() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        Argument res_ = ElRenderUtil.processEl("++v;.integer", 0, context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(1, c_.getInteger());
        assertTrue(res_.getObject() instanceof Integer);
        assertEq(1, (Number)res_.getObject());
    }











    @Test
    public void processAffect2FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().getParameters().putAllMap(localVars_);
        ElRenderUtil.processEl("v;.;=12i", 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }
    @Test
    public void processAffect3FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(c_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i]=\"12i\"", 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processAffect4FailTest() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        ElRenderUtil.processEl("v;.integer=\"12i\"", 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processAffect5FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.=\"12i\"", 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processAffect6FailTest() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        assertEq(0, c_.getInteger());
        ElRenderUtil.processEl("v;.integer=v2;.", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processAffect7FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer[] c_ = new ArrayContainer[1];
        c_[0] = new ArrayContainer();
        lv_.setStruct(new StdStruct(c_, "[code.expressionlanguage.classes.ArrayContainer"));
        lv_.setClassName("[code.expressionlanguage.classes.ArrayContainer");
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i].getCompo()[0i].getArray()[0i]=v2;.", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processAffect8FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.=v2;.", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processAffect9FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        addBean(context_, new BeanOne(), ALIAS_BEAN_ONE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("$this=$null", 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processAffect10FailTest() {
        Configuration context_ = contextEl(true,false);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.=$this", 0, context_);
        assertTrue(!context_.getClasses().getErrorsDet().isEmpty());
    }

    @Test
    public void processAffect11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_, true,false);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("$classchoice(pkg.Ex)inst=v;.", 0, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(cont_.getContext().getException());
    }

    @Test
    public void processAffect12FailTest() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        String stringType_ = custLgNames_.getAliasString();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(exp_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
        lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasObject()));
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.[0i]=v2;.", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processAffect13FailTest() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.objInteger-=12i", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processAffect14FailTest() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("v;.objInteger++", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processAffect15FailTest() {
        Configuration context_ = contextEl(true,false);
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ElRenderUtil.processEl("++v;.objInteger", 0, context_);
        assertTrue(context_.getClasses().getErrorsDet().isEmpty());
        assertNotNull(context_.getContext().getException());
    }
    private static void addImportingPage(Configuration _conf) {
        _conf.addPage(new ImportingPage(false));
        _conf.getContext().setAnalyzing(new AnalyzedPageEl());
    }
    private static void addBean(Configuration _conf, Object _bean, String _beanClass) {
        _conf.getLastPage().setGlobalArgumentStruct(StdStruct.wrapStd(_bean, _conf.toContextEl()),_conf);
        _conf.setGlobalClass(_beanClass);
        _conf.toContextEl().setGlobalClass(_beanClass);
    }

    private static void addBeanClassName(Configuration _conf, String _bean) {
        _conf.setGlobalClass(_bean);
        _conf.toContextEl().setGlobalClass(_bean);
    }

    private Configuration contextEl() {
        return contextEl(false);
    }

    private Configuration contextEl(boolean _multiple) {
        return contextEl(_multiple, true);
    }
    private Configuration contextEl(boolean _multiple, boolean _eqPlus) {
        Configuration conf_ = new Configuration();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setEqPlus(_eqPlus);
        cont_.getOptions().setMultipleAffectations(_multiple);
        InitializationLgNames.initAdvStandards(cont_);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        conf_.setContext(cont_);
        conf_.setStandards((BeanLgNames) cont_.getStandards());
        cont_.initError();
        return conf_;
    }
    private Configuration contextEl(boolean _multiple, boolean _eqPlus, boolean _catChars) {
        Configuration conf_ = new Configuration();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setEqPlus(_eqPlus);
        cont_.getOptions().setMultipleAffectations(_multiple);
        cont_.getOptions().setCatChars(_catChars);
        InitializationLgNames.initAdvStandards(cont_);
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        conf_.setContext(cont_);
        conf_.setStandards((BeanLgNames) cont_.getStandards());
        cont_.initError();
        return conf_;
    }

    private Configuration contextEl(StringMap<String> _files) {
        return contextEl(_files, false);
    }

    private Configuration contextEl(StringMap<String> _files, boolean _multiple) {
        return contextEl(_files, _multiple, true);
    }
    private Configuration contextEl(StringMap<String> _files, boolean _multiple, boolean _eqPlus) {
        Configuration conf_ = new Configuration();
        ContextEl cont_ = new ContextEl();
        cont_.getOptions().setEqPlus(_eqPlus);
        cont_.getOptions().setMultipleAffectations(_multiple);
        InitializationLgNames.initAdvStandards(cont_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        conf_.setContext(cont_);
        conf_.setStandards((BeanLgNames) cont_.getStandards());
        cont_.initError();
        return conf_;
    }
}
