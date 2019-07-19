package code.formathtml;
import static code.formathtml.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import code.expressionlanguage.*;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import org.junit.Test;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.Delimiters;
import code.expressionlanguage.instr.ElResolver;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.expressionlanguage.variables.VariableSuffix;
import code.formathtml.classes.BeanOne;
import code.formathtml.classes.Composite;
import code.formathtml.classes.CustLgNames;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.StdStruct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

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
        Argument arg_ = processEl("5", context_);
        assertEq(5L, arg_.getNumber());
    }
    @Test
    public void processEl2Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static(java.lang.Long).MAX_VALUE", context_);
        assertEq(Long.MAX_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl3Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(1+2)*3", context_);
        assertEq(9L, arg_.getNumber());
    }
    @Test
    public void processEl4Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1- -1", context_);
        assertEq(2L, arg_.getNumber());
    }
    @Test
    public void processEl5Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+2*3", context_);
        assertEq(7L, arg_.getNumber());
    }
    @Test
    public void processEl6Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("- -1", context_);
        assertEq(1L, arg_.getNumber());
    }
    @Test
    public void processEl7Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(-8l)", context_);
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl8Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(8l)", context_);
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl9Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = processEl("composite.integer", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl10Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("40908c", context_);
        assertEq(40908, arg_.getNumber());
    }
    @Test
    public void processEl11Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'\\u9fcb'", context_);
        assertEq(40907, arg_.getNumber());
    }
    @Test
    public void processEl12Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'\\\\'", context_);
        assertEq('\\', arg_.getNumber());
    }
    @Test
    public void processEl13Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'\\''", context_);
        assertEq('\'', arg_.getNumber());
    }
    @Test
    public void processEl14Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'\"'", context_);
        assertEq('"', arg_.getNumber());
    }
    @Test
    public void processEl15Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'\\n'", context_);
        assertEq('\n', arg_.getNumber());
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
        Argument arg_ = processEl("v;.integer", context_);
        assertEq(0, arg_.getNumber());
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
        Argument arg_ = processEl("v;integer", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl18Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("5 $instanceof java.lang.Number", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl19Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'5' $instanceof java.lang.Number", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl20Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("!('5' $instanceof java.lang.Number)", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl21Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1==2", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl22Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1!=2", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl23Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1==2&&1+0==8", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl24Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1!=2||1+7==8", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl25Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1==2&&(1+0==8||3*3==9)", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl26Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1==2||1+6==8&&1==1", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl27Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new code.util.StringList()", context_);
        StdStruct res_ = (StdStruct) arg_.getStruct();
        assertTrue(res_.getInstance() instanceof StringList);
        assertEq(new StringList(), (StringList)res_.getInstance());
    }
    @Test
    public void processEl28Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = processEl("composite.integer", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl29Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1+1==2||1/0>8", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl30Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("1+1==2||(integer>8)", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl31Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(-8i)", context_);
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl32Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(8i)", context_);
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl33Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(-8I)", context_);
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl34Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(8I)", context_);
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl35Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(-8L)", context_);
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl36Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).abs(8L)", context_);
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl37Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenTwo($null)", context_);
        assertEq("one",arg_.getString());
    }
    @Test
    public void processEl38Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenTwo($(java.lang.Object)$null)", context_);
        assertEq("two",arg_.getString());
    }
    @Test
    public void processEl39Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$null $instanceof java.lang.Object", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl40Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenThree(1L)", context_);
        assertEq("Long",arg_.getString());
    }
    @Test
    public void processEl41Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenThree(1l)", context_);
        assertEq("long",arg_.getString());
    }
    @Test
    public void processEl42Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenThree(1.0D)", context_);
        assertEq("Double",arg_.getString());
    }
    @Test
    public void processEl43Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenThree(1.0d)", context_);
        assertEq("double",arg_.getString());
    }
    @Test
    public void processEl44Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenThree(1.0F)", context_);
        assertEq("Double",arg_.getString());
    }
    @Test
    public void processEl45Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenThree(1.0f)", context_);
        assertEq("double",arg_.getString());
    }
    @Test
    public void processEl56Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new code.expressionlanguage.classes.InheritedComposite().getPrivateInt()", context_);
        assertEq(0, arg_.getNumber());
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
        Argument arg_ = processEl("f;.format($vararg(java.lang.String),$firstopt(v;.),2;.,v;.)", context_);
        assertEq("varargs:7 8 7",arg_.getString());
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
        Argument arg_ = processEl("f;.format($vararg(java.lang.String))", context_);
        assertEq("varargs:{0} {1} {2}",arg_.getString());
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
        Argument arg_ = processEl("f;.format(v;.,2;.,v;.)", context_);
        assertEq("varargs:7 8 7",arg_.getString());
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
        Argument arg_ = processEl("f;.format()", context_);
        assertEq("varargs:{0} {1} {2}",arg_.getString());
    }
    @Test
    public void processEl62Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        compos_.setInteger(2);
        addBean(context_, compos_, "code.expressionlanguage.classes.Composite");
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setStruct(new IntStruct(4));
        lv_.setClassName("$int");
        localVars_.put("v", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        Argument arg_ = processEl("v;+integer", context_);
        assertEq(6, arg_.getNumber());
    }
    @Test
    public void processEl62FailTest() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("bonjour"));
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("tout"));
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("$new code.expressionlanguage.classes.Composite($vararg(java.lang.String),$firstopt(v;.),2;.).getStrings()", context_);
        assertNotNull(context_.getContext().getException());
//        
//        assertTrue(res_ instanceof StringList);
//        assertEq(new StringList("bonjour","tout"), (StringList)res_);
    }
    @Test
    public void processEl63Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new $int[1i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(0, ((NumberStruct) (((ArrayStruct) res_).getInstance())[0]).intStruct());
    }
    @Test
    public void processEl64Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new $int[1i][]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
    }
    @Test
    public void processEl65Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new java.lang.Integer[2i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(2, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[1]);
    }
    @Test
    public void processEl66Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new java.lang.Integer[2i][]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(2, (((ArrayStruct) res_).getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct) res_).getInstance())[1]);
    }
    @Test
    public void processEl67Test() {
        Configuration context_ = contextEl();
        Composite composite_ = new Composite();
        addImportingPage(context_);
        addBean(context_,composite_, COMPOSITE);
        assertEq(0, composite_.getPrivateInt());
        processEl("setPrivateInt(2i)", context_);
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
        Argument arg_ = processEl("arrays;.[0i]", context_);
        assertEq(0, arg_.getNumber());
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
        Argument arg_ = processEl("arrays;.[0i].length", context_);
        assertEq(2, arg_.getNumber());
    }
    @Test
    public void processEl70Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("!!$false", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl71Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        addBeanClassName(context_,context_.getStandards().getAliasByte());
        Argument arg_ = processEl("MAX_VALUE", context_);
        assertEq((byte)127, arg_.getNumber());
    }
    @Test
    public void processEl72Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static(java.lang.Byte).MAX_VALUE", context_);
        assertEq((byte)127, arg_.getNumber());
    }
    @Test
    public void processEl73Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new code.util.StringList()", context_);
        StdStruct res_ = (StdStruct) arg_.getStruct();
        assertTrue(res_.getInstance() instanceof StringList);
        assertEq(new StringList(), (StringList)res_.getInstance());
    }
    @Test
    public void processEl77Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(\"Hello\\\\\"+\"World\").length()", context_);
        assertEq(11, arg_.getNumber());
    }
    @Test
    public void processEl78Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(\"Hello\\\"\"+\"World\").length()", context_);
        assertEq(11, arg_.getNumber());
    }
    @Test
    public void processEl79Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(\"Hello\\\\\"+'\\\\').length()", context_);
        assertEq(7, arg_.getNumber());
    }
    @Test
    public void processEl80Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(\"Hello\\\"\"+'\\'').length()", context_);
        assertEq(7, arg_.getNumber());
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
        Argument arg_ = processEl("(f;.format($vararg(java.lang.String),$firstopt(v;.),2;.,v;.)+'\\'').length()", context_);
        assertEq(14, arg_.getNumber());
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
        Argument arg_ = processEl("$static($math).abs(v;.[0i]+2)*2", context_);
        assertEq(20L, arg_.getNumber());
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
        Argument arg_ = processEl("(v;.[0i]+2)*2", context_);
        assertEq(20L, arg_.getNumber());
    }
    @Test
    public void processEl87Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("$bool(1>0,0i,1i)", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl88Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("$bool(1<0,0i,1i)", context_);
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl89Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("$bool(1>0,0i,1i/0i)", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl90Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("$bool(1<0,1i/0i,1i)", context_);
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl95Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("($new $int[1i])[0i]", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl96Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new $int[]{2i}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((NumberStruct)o_[0]).intStruct());
    }
    @Test
    public void processEl97Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new $int[]{3i,7i}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct)o_[0]).intStruct());
        assertEq(7, ((NumberStruct)o_[1]).intStruct());
    }
    @Test
    public void processEl98Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new $int[]{}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(0, o_.length);
    }
    @Test
    public void processEl99Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new java.lang.Integer[]{3i,7i}", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_.getContext()));
        Struct[] o_ = ((ArrayStruct) res_).getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((NumberStruct)o_[0]).intStruct());
        assertEq(7, ((NumberStruct)o_[1]).intStruct());
    }
    @Test
    public void processEl100Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"} ", context_, 2);
        assertEq("9 hello world {every body ;)",arg_.getString());
        assertEq(43, context_.getNextIndex());
    }
    @Test
    public void processEl101Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(" {(\"hello \"+\"world\").length()} ", context_, 2);
        assertEq(11, arg_.getNumber());
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
        lv_.setStruct(new LongStruct(1l));
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("getOverridenThree(arg;.)", context_);
        assertEq("Long",arg_.getString());
    }
    @Test
    public void processEl103Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new LongStruct(1l));
        lv_.setClassName(context_.getStandards().getAliasPrimLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument arg_ = processEl("getOverridenThree(arg;.)", context_);
        assertEq("long",arg_.getString());
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
        Argument arg_ = processEl("$static(pkg.Ex).exmeth()", cont_);
        assertEq(9, arg_.getNumber());
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
        Argument arg_ = processEl("$static(pkg.Ex).exmeth(6i)", cont_);
        assertEq(15, arg_.getNumber());
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
        Argument arg_ = processEl("$new pkg.Ex()", cont_);
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
        Argument arg_ = processEl("$classchoice(pkg.Ex)exmeth(6i)", cont_);
        assertEq(15, arg_.getNumber());
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
        Argument arg_ = processEl("$classchoice(pkg.Ex)inst", cont_);
        assertEq(2, arg_.getNumber());
    }
    @Test
    public void processEl111Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenFour($null)", context_);
        assertEq("Long",arg_.getString());
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
        Argument arg_ = processEl("v;..integer", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl113Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenFour(1L)", context_);
        assertEq("Long",arg_.getString());
    }
    @Test
    public void processEl114Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenFour(1l)", context_);
        assertEq("long",arg_.getString());
    }
    @Test
    public void processEl115Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenFive(1L)", context_);
        assertEq("Long",arg_.getString());
    }
    @Test
    public void processEl116Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenFive(1l)", context_);
        assertEq("double",arg_.getString());
    }
    @Test
    public void processEl117Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenSix(1L)", context_);
        assertEq("Long",arg_.getString());
    }
    @Test
    public void processEl118Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("getOverridenSix(1l)", context_);
        assertEq("long",arg_.getString());
    }
    @Test
    public void processEl119Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(1b+2b)*3", context_);
        assertEq(9L, arg_.getNumber());
    }
    @Test
    public void processEl120Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(1s+2b)*3", context_);
        assertEq(9L, arg_.getNumber());
    }
    @Test
    public void processEl121Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("- -1b", context_);
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl122Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-1b", context_);
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl123Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("java.lang.Byte.MAX_VALUE+java.lang.Byte.MAX_VALUE", context_);
        int max_ = Byte.MAX_VALUE+Byte.MAX_VALUE;
        assertEq(max_, arg_.getNumber());
    }
    @Test
    public void processEl123FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("+1b", context_);
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl124Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("+-1b", context_);
        assertEq(-1, arg_.getNumber());
    }
    @Test
    public void processEl125Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-.25e0+.5", context_);
        assertEq(0.25d, arg_.getDouble());
    }
    @Test
    public void processEl126Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, 2);
        assertEq("9 hello world {every body ;)",arg_.getString());
        int nextIndex_ = context_.getNextIndex();
        assertEq(43, nextIndex_);
        arg_ = processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, nextIndex_+1);
        assertEq(40, arg_.getNumber());
        nextIndex_ = context_.getNextIndex();
        assertEq(48, nextIndex_);
        }
    @Test
    public void processEl127Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(" {(\"hello \"+\"world\").length()}{5*8} ", context_, 2);
        assertEq(11, arg_.getNumber());
        int nextIndex_ = context_.getNextIndex();
        assertEq(30, nextIndex_);
        arg_ = processEl(" {(\"hello \"+\"world\").length()}{5*8} ", context_, nextIndex_+1);
        assertEq(40, arg_.getNumber());
        nextIndex_ = context_.getNextIndex();
        assertEq(35, nextIndex_);
    }
    @Test
    public void processEl128Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1_0+2*3", context_);
        assertEq(16L, arg_.getNumber());
    }
    @Test
    public void processEl129Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).mod(-8l,3l)", context_);
        assertEq(1L, arg_.getNumber());
    }
    @Test
    public void processEl130Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($math).quot(-8l,3l)", context_);
        assertEq(-3L, arg_.getNumber());
    }
    @Test
    public void processEl131Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new $int[1i][1i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(ARR_INT, (((ArrayStruct) res_).getInstance())[0].getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance()).length);
        assertEq(0, ((NumberStruct) (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processEl132Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$new java.lang.Integer[1i][1i]", context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_.getContext()));
        assertEq(1, (((ArrayStruct) res_).getInstance()).length);
        assertEq(ARR_INTEGER, (((ArrayStruct) res_).getInstance())[0].getClassName(context_.getContext()));
        assertSame(NullStruct.NULL_VALUE, (((ArrayStruct)(((ArrayStruct) res_).getInstance())[0]).getInstance())[0]);
    }
    @Test
    public void processEl133Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("(1 + 2) * 3.0", context_);
        assertEq(9L, arg_.getNumber());
    }
    @Test
    public void processEl134Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(" 2.0 + $static($math). quot( -8l, 3l) + 3.0", context_);
        assertEq(2L, arg_.getNumber());
    }
    @Test
    public void processEl135Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1 + 2 ", context_);
        assertEq(3L, arg_.getNumber());
    }
    @Test
    public void processEl136Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1. + 2. ", context_);
        assertEq(3L, arg_.getNumber());
    }
    @Test
    public void processEl137Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1.d + 2.d ", context_);
        assertEq(3L, arg_.getNumber());
    }
    @Test
    public void processEl138Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-.2_5e0+.5", context_);
        assertEq(0.25d, arg_.getDouble());
    }
    @Test
    public void processEl139Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-.25e0_0+.5", context_);
        assertEq(0.25d, arg_.getDouble());
    }
    @Test
    public void processEl140Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1_0.d + 2.d ", context_);
        assertEq(12L, arg_.getNumber());
    }
    @Test
    public void processEl141Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1.05e1", context_);
        assertEq(10.5d, arg_.getDouble());
    }
    @Test
    public void processEl142Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1.00625e1", context_);
        assertEq(10.0625d, arg_.getDouble());
    }
    @Test
    public void processEl143Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("100.625e-1", context_);
        assertEq(10.0625d, arg_.getDouble());
    }
    @Test
    public void processEl144Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("100.625", context_);
        assertEq(100.625d, arg_.getDouble());
    }
    @Test
    public void processEl145Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456789123456789123456789.0", context_);
        assertEq(1.2345678912345678912e26, arg_.getDouble());
    }
    @Test
    public void processEl147Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456789123456789123456789.", context_);
        assertEq(1.2345678912345678912e26, arg_.getDouble());
    }
    @Test
    public void processEl148Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456789123456789123456789.e-1", context_);
        assertEq(1.2345678912345678912e25, arg_.getDouble());
    }
    @Test
    public void processEl149Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456789123456789123456789.e1", context_);
        assertEq(1.2345678912345678912e27, arg_.getDouble());
    }
    @Test
    public void processEl150Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456.e1", context_);
        assertEq(1234560, arg_.getDouble());
    }
    @Test
    public void processEl151Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(".078125e-1", context_);
        assertEq(.078125e-1, arg_.getDouble());
    }
    @Test
    public void processEl152Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456789123456789123456789.0e-36", context_);
        assertEq(1.2345678912345678912e-10, arg_.getDouble());
    }
    @Test
    public void processEl153Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("0.0e-36", context_);
        assertEq(0.0, arg_.getDouble());
    }
    @Test
    public void processEl154Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-0.0e-36", context_);
        assertEq(-0.0, arg_.getDouble());
    }
    @Test
    public void processEl155Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("0.625e-1", context_);
        assertEq(0.0625, arg_.getDouble());
    }
    @Test
    public void processEl156Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(".625e-1", context_);
        assertEq(0.0625, arg_.getDouble());
    }
    @Test
    public void processEl157Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("0.625e1", context_);
        assertEq(6.25, arg_.getDouble());
    }
    @Test
    public void processEl158Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(".625e1", context_);
        assertEq(6.25, arg_.getDouble());
    }
    @Test
    public void processEl159Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("0.625e0", context_);
        assertEq(0.625, arg_.getDouble());
    }
    @Test
    public void processEl160Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(".625e0", context_);
        assertEq(0.625, arg_.getDouble());
    }
    @Test
    public void processEl161Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-.625e1", context_);
        assertEq(-6.25, arg_.getDouble());
    }
    @Test
    public void processEl162Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-.6e1", context_);
        assertEq(-6.0, arg_.getDouble());
    }
    @Test
    public void processEl163Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-.60e1", context_);
        assertEq(-6.0, arg_.getDouble());
    }
    @Test
    public void processEl164Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(".6e1", context_);
        assertEq(6.0, arg_.getDouble());
    }
    @Test
    public void processEl165Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(".6e2", context_);
        assertEq(60.0, arg_.getDouble());
    }
    @Test
    public void processEl166Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("123456789123456789123456789.1e1", context_);
        assertEq(1.2345678912345678912e27, arg_.getDouble());
    }
    @Test
    public void processEl167Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("100.e-1", context_);
        assertEq(10.0, arg_.getDouble());
    }
    @Test
    public void processEl168Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-100.e-1", context_);
        assertEq(-10.0, arg_.getDouble());
    }
    @Test
    public void processEl169Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-1.e1", context_);
        assertEq(-10.0, arg_.getDouble());
    }
    @Test
    public void processEl170Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-1.", context_);
        assertEq(-1.0, arg_.getDouble());
    }
    @Test
    public void processEl171Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1e-123456789123456789123", context_);
        assertEq(0.0, arg_.getDouble());
    }
    @Test
    public void processEl172Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-1e-123456789123456789123", context_);
        assertEq(-0.0, arg_.getDouble());
    }
    @Test
    public void processEl173Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("1e123456789123456789123", context_);
        assertEq(Double.POSITIVE_INFINITY, arg_.getDouble());
    }
    @Test
    public void processEl174Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("-1e123456789123456789123", context_);
        assertEq(Double.NEGATIVE_INFINITY, arg_.getDouble());
    }
    @Test
    public void processEl175Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'\\u9FCB'", context_);
        assertEq(40907, arg_.getNumber());
    }
    @Test
    public void processEl176Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("\"\\u9FCB\"", context_);
        assertEq("\u9fcb",arg_.getString());
    }
    @Test
    public void processEl177Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("\"\\u9fcb\"", context_);
        assertEq("\u9fcb",arg_.getString());
    }
    @Test
    public void processEl178Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static(java.lang.Long) .MAX_VALUE", context_);
        assertEq(Long.MAX_VALUE, arg_.getNumber());
    }
    @Test
    public void processEl330Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        context_.setMerged(true);
        context_.setCurrentVarSetting(context_.getStandards().getAliasLong());
        processEl("arg=2,arg2=4", context_);
        StringMap<LocalVariable> localVars_ = context_.getLastPage().getLocalVars();
        assertEq(2,((NumberStruct)localVars_.getVal("arg").getStruct()).intStruct());
        assertEq(4,((NumberStruct)localVars_.getVal("arg2").getStruct()).intStruct());
    }

    @Test
    public void processEl331Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasPrimInteger()));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("arg;.={2}", context_);
        ArrayStruct struct_ = (ArrayStruct)lv_.getStruct();
        assertEq(1,struct_.getInstance().length);
        assertEq(2,((NumberStruct)struct_.getInstance()[0]).intStruct());
    }

    @Test
    public void processEl332Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(2));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.<<2", context_);
        assertEq(8,argument_.getNumber());
    }

    @Test
    public void processEl333Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.>>2", context_);
        assertEq(2,argument_.getNumber());
    }

    @Test
    public void processEl334Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(2));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.<<<2", context_);
        assertEq(8,argument_.getNumber());
    }

    @Test
    public void processEl335Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.>>>2", context_);
        assertEq(2,argument_.getNumber());
    }

    @Test
    public void processEl336Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(2));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.<<<<2", context_);
        assertEq(8,argument_.getNumber());
    }

    @Test
    public void processEl337Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.>>>>2", context_);
        assertEq(2,argument_.getNumber());
    }

    @Test
    public void processEl338Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.>2", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl339Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.&&arg2;.", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl340Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.&&arg2;.", context_);
        assertTrue(argument_.isFalse());
    }
    @Test
    public void processEl341Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.&&arg2;.", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl342Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(10));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.&3", context_);
        assertEq(2,argument_.getNumber());
    }

    @Test
    public void processEl343Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(8));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.|2", context_);
        assertEq(10,argument_.getNumber());
    }

    @Test
    public void processEl344Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.^3", context_);
        assertEq(6,argument_.getNumber());
    }

    @Test
    public void processEl345Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.||arg2;.", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl346Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.||arg2;.", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl347Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.||arg2;.", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl348Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.>\"2\"", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl349Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("~arg;.", context_);
        assertEq(-6,argument_.getNumber());
    }

    @Test
    public void processEl350Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("+arg;.", context_);
        assertEq(5,argument_.getNumber());
    }

    @Test
    public void processEl351Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("!arg;.", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl352Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("!arg;.", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl353Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$bool(arg;.,5,6)", context_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl354Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$bool(arg;.,5,6)", context_);
        assertEq(5,argument_.getNumber());
    }

    @Test
    public void processEl355Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(5));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.%3", context_);
        assertEq(2,argument_.getNumber());
    }
    @Test
    public void processEl356Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = processEl("$this.composite.integer", context_);
        assertEq(0, arg_.getNumber());
    }

    @Test
    public void processEl357Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(false));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$bool(arg;.,5+arg2;.,6+arg2;.)", context_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl358Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$bool(arg;.,5+arg2;.,6+arg2;.)", context_);
        assertEq(5,argument_.getNumber());
    }

    @Test
    public void processEl1FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        processEl("getOverridenOne($null)", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
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
        processEl("setPrivateInt(arg;.)", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl6FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("1&&0", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl7FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$new $int[-1i]", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl8FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$new java.lang.Integer[-1i]", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl9FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$true<$false", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl11FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("MAX_VALUE", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl12FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$static(code.expressionlanguage.classes.Composite).integer", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl13FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$static(code.expressionlanguage.classes.Composite).int$$eger", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }

    @Test
    public void processEl15FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$static(code.expressionlanguage.classes.StrangeInit).fail()", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl16FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$new code.expressionlanguage.classes.StrangeInit()", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl17FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$static(code.expressionlanguage.classes.FailMethods).fail()", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl18FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$new code.expressionlanguage.classes.FailMethods()", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
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
        lv_.setStruct(new LongStruct(1l));
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("get(arg;.)", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl20FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$static(code.expressionlanguage.classes.Composite).getInteger()", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl21FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new LongStruct(1l));
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("$static(code.expressionlanguage.classes.FailMethods).fail().arg;.", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl22FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setStruct(new LongStruct(1l));
        lv_.setClassName(context_.getStandards().getAliasLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        processEl("$static(code.expressionlanguage.classes.FailMethods).fail().arg;", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl23FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setStruct(new LongStruct(1l));
        lv_.setClassName(context_.getStandards().getAliasLong());
        lv_.setIndexClassName(context_.getStandards().getAliasPrimLong());
        localVars_.put("arg", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        processEl("$static(code.expressionlanguage.classes.FailMethods).fail().arg;;", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl24FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "$firstopt(6)*(7+8)";
        processEl(el_, context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl25FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format(\"6\",$vararg(6))";
        processEl(el_, context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl26FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        processEl(el_, context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl27FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "$vararg(java.lang.Object)*(7+8)";
        processEl(el_, context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl28FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        processEl(el_, context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl29FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($firstopt(6),\"6\")";
        processEl(el_, context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl30FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        String el_ = "";
        processEl(el_, context_);
        assertNotNull(context_.getContext().getException());
    }

    @Test
    public void processEl33FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1<2<3";
        processEl(el_, conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }

    @Test
    public void processEl34FailTest() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "f(,)";
        processEl(el_, conf_);
        assertTrue(!conf_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl179Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        context_.setupAnalyzing();
        ContextEl ctx_ = context_.getContext();
        String elr_ = "v;.+=1i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
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
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.&=$false";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertEq(false, ((BooleanStruct)lv_.getStruct()).getInstance());
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
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.|=$true";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertEq(true, ((BooleanStruct)lv_.getStruct()).getInstance());
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
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.&=1/0 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
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
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.|=1/0 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
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
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.&=1 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
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
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.|=1 > 0";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertNotNull(ctx_.getException());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl186Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.==1i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl187Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(3, arg_.getNumber());
    }
    @Test
    public void processEl188Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "++v;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(4, arg_.getNumber());
    }
    @Test
    public void processEl189Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.[0i]++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(6, ((NumberStruct) in_[0]).intStruct());
        assertEq(5, arg_.getNumber());
    }
    @Test
    public void processEl190Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "++v;.[0i]";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(6, ((NumberStruct) in_[0]).intStruct());
        assertEq(6, arg_.getNumber());
    }
    @Test
    public void processEl191Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.+=2i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(5, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(5, arg_.getNumber());
    }
    @Test
    public void processEl192Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(5);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.[0i]+=3i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(8, ((NumberStruct) in_[0]).intStruct());
        assertEq(8, arg_.getNumber());
    }
    @Test
    public void processEl193Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.+++v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(12, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl194Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.---v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(12, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(-9, arg_.getNumber());
    }
    @Test
    public void processEl195Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.=++v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(13, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(13, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(13, arg_.getNumber());
    }
    @Test
    public void processEl196Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.= ++v2;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(13, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(13, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(13, arg_.getNumber());
    }
    @Test
    public void processEl197Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        ContextEl ctx_ = context_.getContext();
        ctx_.setAnalyzing(new AnalyzedPageEl());
        String elr_ = "+1b";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl198Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$(java.lang.Number)5", context_);
        assertEq(5L, arg_.getNumber());
    }
    @Test
    public void processEl199Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$($byte)5", context_);
        assertEq(5L, arg_.getNumber());
    }
    @Test
    public void processEl200Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$($byte)$null", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processEl201Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$(java.lang.Byte)$null", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNull(context_.getContext().getException());
        assertSame(NullStruct.NULL_VALUE, arg_.getStruct());
    }
    @Test
    public void processEl202Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$(java.lang.Byte)\"not cast\"", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        Struct exc_ = context_.getContext().getException();
        assertNotNull(exc_);
        assertEq(context_.getStandards().getAliasCast(),exc_.getClassName(context_.getContext()));
    }
    @Test
    public void processEl203Test() {
        Configuration conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!=2!=3";
        Argument arg_ = processEl(el_, conf_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl204Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        String el_ = "!v;.";
        processEl(el_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
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
        processEl(el_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ContextEl ctx_= context_.getContext();
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl206Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "++v;.";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl207Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl208Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(in_, ARR_INTEGER));
        lv_.setClassName(ARR_INTEGER);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.[0i]++";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl209Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(in_, ARR_INTEGER));
        lv_.setClassName(ARR_INTEGER);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "++v;.[0i]";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        calculate(all_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertEq(ctx_.getStandards().getAliasNullPe(), ctx_.getException().getClassName(ctx_));
    }
    @Test
    public void processEl210Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'1'+'2'", context_);
        assertEq(99, arg_.getNumber());
    }
    @Test
    public void processEl211Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("\"\"+$new $char[]{'1','2'}[0]+$new $char[]{'1','2'}[1]", context_);
        assertEq("12",arg_.getString());
    }
    @Test
    public void processEl212Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("('1'+'2')*3i", context_);
        assertEq(297, arg_.getNumber());
    }
    @Test
    public void processEl213Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'1'>1i", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl214Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'1'<1i", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl215Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'1'<1i", context_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl216Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'1'>1i", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl217Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("'1'==49i", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl218Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("49i=='1'", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl213FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("('1'+'2')*'3'", context_);
        assertEq(5049, arg_.getNumber());
    }
    @Test
    public void processEl219Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("6 + $($int) - $static($math).quot(8,5) - 2", context_);
        assertEq(3, arg_.getNumber());
    }
    @Test
    public void processEl220Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class($int).getName()", context_);
        assertEq("$int",arg_.getString());
    }
    @Test
    public void processEl221Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class(java.lang.Integer).getName()", context_);
        assertEq("java.lang.Integer",arg_.getString());
    }
    @Test
    public void processEl222Test() {
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
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$class(pkg.Ex).getName()", cont_);
        assertEq("pkg.Ex",arg_.getString());
    }
    @Test
    public void processEl223Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$class(pkg.Ex).getName()", cont_);
        assertEq("pkg.Ex",arg_.getString());
    }
    @Test
    public void processEl224Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(#T).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("java.lang.Integer",arg_.getString());
    }
    @Test
    public void processEl225Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T>).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("pkg.Ex<java.lang.Integer>",arg_.getString());
    }
    @Test
    public void processEl226Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($Class).getClass(\"\").getName()", context_);
        assertEq("java.lang.String",arg_.getString());
    }
    @Test
    public void processEl227Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($Class).getClass(1i).getName()", context_);
        assertEq("java.lang.Integer",arg_.getString());
    }
    @Test
    public void processEl228Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($Class).getClass($null)", context_);
        assertTrue(arg_.isNull());
        assertNull(context_.getException());
    }
    @Test
    public void processEl229Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T>).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static($Class).getClass($new pkg.Ex<java.lang.Integer>()).getName()", cont_);
        assertEq("pkg.Ex<java.lang.Integer>",arg_.getString());
    }
    @Test
    public void processEl230Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class($int[]).getName()", context_);
        assertEq("[$int",arg_.getString());
    }
    @Test
    public void processEl231Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class(java.lang.Integer[]).getName()", context_);
        assertEq("[java.lang.Integer",arg_.getString());
    }
    @Test
    public void processEl232Test() {
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
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$class(pkg.Ex[]).getName()", cont_);
        assertEq("[pkg.Ex",arg_.getString());
    }
    @Test
    public void processEl233Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$class(pkg.Ex[]).getName()", cont_);
        assertEq("[pkg.Ex",arg_.getString());
    }
    @Test
    public void processEl234Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$class(pkg.Ex<java.lang.Integer>[]).getName()", cont_);
        assertEq("[pkg.Ex<java.lang.Integer>",arg_.getString());
    }
    @Test
    public void processEl235Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(#T[]).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("[java.lang.Integer",arg_.getString());
    }
    @Test
    public void processEl236Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return $class(pkg.Ex<#T[]>).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.Ex<java.lang.Integer>().exmeth()", cont_);
        assertEq("pkg.Ex<[java.lang.Integer>",arg_.getString());
    }
    @Test
    public void processEl237Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($Class).getClass($new $int[]{1i}).getName()", context_);
        assertEq("[$int",arg_.getString());
    }
    @Test
    public void processEl238Test() {
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
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static($Class).forName(\"pkg.Ex\",$true).getName()", cont_);
        assertEq("pkg.Ex",arg_.getString());
    }
    @Test
    public void processEl239Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"pkg.Ex\",$true).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("pkg.Ex",arg_.getString());
        assertEq(14, ((NumberStruct)cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"))).intStruct());
    }
    @Test
    public void processEl240Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class($void).getName()", context_);
        assertEq("$void",arg_.getString());
    }
    @Test
    public void processEl241Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($Class).forName(\"$void\",$true).getName()", context_);
        assertEq("$void",arg_.getString());
    }
    @Test
    public void processEl242Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke($null)", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl243Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class(java.lang.String).getDeclaredMethods(\"length\",$false,$false)[0i].invoke(1i)", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl244Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i)", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl245Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.ExAbs).getDeclaredMethods(\"exmeth\",$false,$false)[0i]:\n");
        xml_.append("  m;.setPolymorph($false):\n");
        xml_.append("  $return $(java.lang.String) m;.invoke($new pkg.ExConc()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl246Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,\"\")", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl247Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,$null)", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl248Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$true,$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($null,$new $int[]{4i,6i}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl249Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$true,$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($null,$new java.lang.Object[]{$new java.lang.Object[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl250Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(java.lang.String),$class($boolean))[0i]:\n");
        xml_.append("  m;.invoke($null,\"pkg.Ex\",$true):\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(14, arg_.getNumber());
    }
    @Test
    public void processEl251Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class($math).getDeclaredMethods(\"mod\",$true,$false,$class($int),$class($int))[0i].invoke($null,4i,3i)", context_);
        assertEq(1, arg_.getNumber());
    }
    @Test
    public void processEl252Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($null,6i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
        NumberStruct res_ = (NumberStruct) cont_.getContextEl().getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        assertEq(14, res_.intStruct());
    }
    @Test
    public void processEl253Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst = 5i:\n");
        xml_.append(" $public $normal $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$false)[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($new pkg.Ex()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(14, arg_.getNumber());
    }
    @Test
    public void processEl254Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst = 5i:\n");
        xml_.append(" $public $normal $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.ExAbs).getDeclaredMethods(\"exmeth\",$false,$false)[0i]:\n");
        xml_.append("  $return $(java.lang.String) m;.invoke($new pkg.ExConc()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("out",arg_.getString());
    }
    @Test
    public void processEl255Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.ExAbs).getDeclaredMethods(\"exmeth\",$false,$false)[0i]:\n");
        xml_.append("  m;.setPolymorph($false):\n");
        xml_.append("  $return $(java.lang.String) m;.invoke($new pkg.ExConc()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("super",arg_.getString());
    }
    @Test
    public void processEl256Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$true,$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($null,$new java.lang.Object[]{$new $int[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl257Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+$($int)e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$false,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($new pkg.Ex<java.lang.Integer>(),5i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(14, arg_.getNumber());
    }
    @Test
    public void processEl258Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($new pkg.Ex<java.lang.Integer>(),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl259Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($new pkg.Ex<java.lang.Integer>(),$(java.lang.Object)$null):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(9, arg_.getNumber());
    }
    @Test
    public void processEl260Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class($Method).getDeclaredMethods(\"invoke\",$false,$true,$class(java.lang.Object),$class(java.lang.Object))[0i]:\n");
        xml_.append("  $Method mtwo = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(mtwo;.,$new pkg.Ex<java.lang.Integer>(),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl261Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=6i:\n");
        xml_.append(" $public $static $void set($int i){\n");
        xml_.append("  inst;;;+=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Object exmeth(){\n");
        xml_.append("  $Method m = $class($Method).getDeclaredMethods(\"invoke\",$false,$true,$class(java.lang.Object),$class(java.lang.Object))[0i]:\n");
        xml_.append("  $Method mtwo = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"set\",$true,$false,$class($int))[0i]:\n");
        xml_.append("  $return m;.invoke(mtwo;.,$null,$new java.lang.Object[]{4i}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(arg_.isNull());
        assertNull(cont_.getContext().getException());
        assertEq(10, ((NumberStruct)cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"))).intStruct());
    }
    @Test
    public void processEl262Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.ExAbs).getDeclaredConstructors($false)[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(),$new java.lang.Object[]{$new $int[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl263Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public (){\n");
        xml_.append("  inst;;;=0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false)[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl264Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst;;;=p;.;+q;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(1i),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl265Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst;;;=p;.;+q;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(1i,$null),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl266Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst;;;=p;.;+q;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(1i,\"\"),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl267Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric().getDeclaredConstructors($false)[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(),$new java.lang.Object[]{$new $int[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl268Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public ($int p,$int q){\n");
        xml_.append("  inst;;;=p;.;+q;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach($int i:e;.;){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$false,$true,$class($int))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).getDeclaredConstructors($false,$class($int),$class($int))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(1i,2i),$new java.lang.Object[]{$new $int[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(22, arg_.getNumber());
    }
    @Test
    public void processEl269Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public (#T e){\n");
        xml_.append("  inst;;;=$($int)e;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($false,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(9i),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(28, arg_.getNumber());
    }
    @Test
    public void processEl270Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public (#T... e){\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   inst;;;+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance($new java.lang.Object[]{$new java.lang.Integer[]{9i,4i}}),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(32, arg_.getNumber());
    }
    @Test
    public void processEl271Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public (#T... e){\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    inst;;;+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance($(java.lang.Object)$null),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl272Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public (#T... e){\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   inst;;;+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance(9i,4i),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl273Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public (#T... e){\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    inst;;;+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $foreach(#T i:e;.;){\n");
        xml_.append("   t;.+=$($int)i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.+inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex).makeGeneric($class(java.lang.Integer)).getDeclaredConstructors($true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(c;.newInstance($null),$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl274Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke($new pkg.Ex<java.lang.Integer>(),$null):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl275Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method mthree = $class($Constructor).getDeclaredMethods(\"newInstance\",$false,$true,$class(java.lang.Object))[0i]:\n");
        xml_.append("  $Constructor c = $class(pkg.Ex<java.lang.Integer>).getDeclaredConstructors($false)[0i]:\n");
        xml_.append("  pkg.Ex<java.lang.Integer> res = $(pkg.Ex<java.lang.Integer>) mthree;.invoke(c;.,$(java.lang.Object)$new java.lang.Object[]{}):\n");
        xml_.append("  $Method m = $class($Method).getDeclaredMethods(\"invoke\",$false,$true,$class(java.lang.Object),$class(java.lang.Object))[0i]:\n");
        xml_.append("  $Method mtwo = $class(pkg.Ex<java.lang.Integer>).getDeclaredMethods(\"exmeth\",$false,$true,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $return $($int) m;.invoke(mtwo;.,res;.,$new java.lang.Object[]{$new java.lang.Integer[]{4i,6i}}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl276Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $static(pkg.Ex).inst;;;=19i:\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(19, arg_.getNumber());
    }
    @Test
    public void processEl277Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$static($Class).getAllClasses().length > 50", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl278Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $final $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst;;;=15i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl279Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $final $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,16i):\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl280Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $($int)$class(pkg.Ex).getDeclaredFields(\"inst\")[0i].get($null):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl281Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $($int)$class(pkg.Ex).getDeclaredFields(\"inst\")[0i].get(\"$null\"):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl282Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $($int)$class(pkg.Ex).getDeclaredFields(\"inst\")[0i].get($new pkg.Ex()):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl283Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.StringBuilder exmeth(){\n");
        xml_.append("  $return $(java.lang.StringBuilder)$class(java.lang.StringBuilder).getDeclaredConstructors($false,$class(java.lang.String))[0i].newInstance(\"hello\"):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("hello", arg_.getString());
    }
    @Test
    public void processEl284Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,$null):\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl285Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,\"16i\"):\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl286Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static java.lang.Integer inst=15i:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,$null):\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(arg_.isNull());
        assertNull(cont_.getContext().getException());
    }
    @Test
    public void processEl287Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public java.lang.Integer inst=15i:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($null,$null):\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl288Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public java.lang.Integer inst=15i:\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(\"\",$null):\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl289Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($new pkg.Ex(),$null):\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl290Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set($new pkg.Ex(),\"\"):\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl291Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex out = $new pkg.Ex():\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out;.,16i):\n");
        xml_.append("  $return out;.inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ =processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(16, arg_.getNumber());
    }
    @Test
    public void processEl292Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public java.lang.Integer inst=15i:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex out = $new pkg.Ex():\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out;.,$null):\n");
        xml_.append("  $return out;.inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ =processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(arg_.isNull());
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNull(cont_.getContext().getException());
    }
    @Test
    public void processEl293Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T inst:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex<java.lang.Integer> out = $new pkg.Ex<java.lang.Integer>():\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out;.,16i):\n");
        xml_.append("  $return $(java.lang.Integer) out;.inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ =processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(16, arg_.getNumber());
    }
    @Test
    public void processEl294Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public #T inst:\n");
        xml_.append(" $public $normal $int exmeth($int... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach($int i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Integer exmeth(){\n");
        xml_.append("  pkg.Ex<java.lang.Integer> out = $new pkg.Ex<java.lang.Integer>():\n");
        xml_.append("  $class(pkg.Ex).getDeclaredFields(\"inst\")[0i].set(out;.,\"16i\"):\n");
        xml_.append("  $return $(java.lang.Integer) out;.inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"super\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processEl295Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null):\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class($Class).getDeclaredMethods(\"forName\",$true,$false,$class(java.lang.String),$class($boolean))[0i]:\n");
        xml_.append("  m;.invoke($null,\"pkg.Ex\",$true):\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        Struct exc_ = cont_.getContext().getException();
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertTrue(cause_ instanceof CausingErrorStruct);
        cause_ = ((CausingErrorStruct)cause_).getCause();
        assertEq(cont_.getStandards().getAliasNullPe(), cause_.getClassName(cont_));
    }
    @Test
    public void processEl296Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  m;.invoke($null,$(java.lang.Object)$null):\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        Struct exc_ = cont_.getContext().getException();
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertEq(cont_.getStandards().getAliasNullPe(), cause_.getClassName(cont_));
    }
    @Test
    public void processEl297Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null):\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  m;.invoke($null,8i):\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        Struct exc_ = cont_.getContext().getException();
        assertTrue(exc_ instanceof InvokeTargetErrorStruct);
        Struct cause_ = ((InvokeTargetErrorStruct)exc_).getCause();
        assertTrue(cause_ instanceof CausingErrorStruct);
        cause_ = ((CausingErrorStruct)cause_).getCause();
        assertEq(cont_.getStandards().getAliasNullPe(), cause_.getClassName(cont_));
    }
    @Test
    public void processEl298Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null):\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Method m = $class(pkg.Ex).getDeclaredMethods(\"exmeth\",$true,$false,$class(java.lang.Integer))[0i]:\n");
        xml_.append("  $try{\n");
        xml_.append("   m;.invoke($null,8i):\n");
        xml_.append("  } $catch(java.lang.Object e):\n");
        xml_.append("  $return $($int) $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $public $abstract java.lang.String exmeth():\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExConc:pkg.ExAbs {\n");
        xml_.append(" $public $normal java.lang.String exmeth(){\n");
        xml_.append("  $return \"out\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExConc", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        Struct exc_ = cont_.getContext().getException();
        assertTrue(exc_ instanceof CausingErrorStruct);
        Struct cause_ = ((CausingErrorStruct)exc_).getCause();
        assertSame(NullStruct.NULL_VALUE,cause_);
    }
    @Test
    public void processEl299Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst;;;=$static(pkg.ExAbs).sup;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup=15i:\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl300Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst;;;=$static(pkg.ExAbs).sup;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup:\n");
        xml_.append(" $static{\n");
        xml_.append("  sup;;;=15i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl301Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst;;;=sup;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup:\n");
        xml_.append(" $static{\n");
        xml_.append("  sup;;;=15i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl302Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> : pkg.ExAbs {\n");
        xml_.append(" $public $static $final $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst;;;=$static(pkg.ExAbs).sup;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int exmeth(#T... e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $if(e;.;!=$null){\n");
        xml_.append("   $foreach(#T i:e;.;){\n");
        xml_.append("    t;.+=$($int)i;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(pkg.Ex).inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExAbs {\n");
        xml_.append(" $protected $static $final $int sup:\n");
        xml_.append(" $static{\n");
        xml_.append("  sup;;;=15i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExAbs", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq(15, arg_.getNumber());
    }
    @Test
    public void processEl303Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Object exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"Ex\",$true):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        Struct exc_ = cont_.getContext().getException();
        assertEq(cont_.getStandards().getAliasClassNotFoundError(), exc_.getClassName(cont_));
    }
    @Test
    public void processEl304Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth(5i):\n");
        xml_.append(" $public $static $int exmeth($int e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.Object exmeth(){\n");
        xml_.append("  $return $static($Class).forName($null,$true):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        Struct exc_ = cont_.getContext().getException();
        assertEq(cont_.getStandards().getAliasNullPe(), exc_.getClassName(cont_));
    }
    @Test
    public void processEl305Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(3));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        LocalVariable lv2_ = new LocalVariable();
        lv2_.setStruct(new IntStruct(12));
        lv2_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v2", lv2_);
        context_.getLastPage().setLocalVars(localVars_);
        ContextEl ctx_ = context_.getContext();
        context_.setupAnalyzing();
        String elr_ = "v;.=v2;.=4i";
        Delimiters d_ = ElResolver.checkSyntax(elr_, ctx_, 0);
        assertTrue(d_.getBadOffset() < 0);
        String el_ = elr_;
        OperationsSequence opTwo_ = ElResolver.getOperationsSequence(0, el_, ctx_, d_);
        OperationNode op_ = OperationNode.createOperationNode(0, CustList.FIRST_INDEX, null, opTwo_, ctx_);
        assertNotNull(op_);
        Argument argGl_ = context_.getOperationPageEl().getGlobalArgument();
        boolean static_ = argGl_ == null || argGl_.isNull();
        ctx_.setStaticContext(static_);
        CustList<OperationNode> all_ = ElRenderUtil.getSortedDescNodes(op_, static_, context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        ctx_.setAnalyzing(null);
        Argument arg_ = calculate(all_, context_);
        assertEq(4, ((NumberStruct)lv2_.getStruct()).intStruct());
        assertEq(4, ((NumberStruct)lv_.getStruct()).intStruct());
        assertEq(4, arg_.getNumber());
    }
    @Test
    public void processEl306Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke(\"\")", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl307Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        processEl("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.Number[]{})", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processEl308Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{})", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",arg_.getStruct().getClassName(context_));
        assertEq(0,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
    }
    @Test
    public void processEl309Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class(java.lang.String[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{\"sample\"})", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",arg_.getStruct().getClassName(context_));
        assertEq(1,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
        assertEq("java.lang.String",(((ArrayStruct)arg_.getStruct()).getInstance())[0].getClassName(context_));
        assertEq("sample",((StringStruct)(((ArrayStruct)arg_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processEl310Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$class(java.lang.Object[]).getDeclaredMethods(\"clone\",$false,$false)[0i].invoke($new java.lang.String[]{\"sample\"})", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertTrue(arg_.getStruct() instanceof ArrayStruct);
        assertEq("[java.lang.String",arg_.getStruct().getClassName(context_));
        assertEq(1,(((ArrayStruct)arg_.getStruct()).getInstance()).length);
        assertEq("java.lang.String",(((ArrayStruct)arg_.getStruct()).getInstance())[0].getClassName(context_));
        assertEq("sample",((StringStruct)(((ArrayStruct)arg_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processEl311Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl("$math.abs(-8l)", context_);
        assertEq(8L, arg_.getNumber());
    }
    @Test
    public void processEl312Test() {
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
        Argument arg_ = processEl("pkg.Ex.exmeth()", cont_);
        assertEq(9, arg_.getNumber());
    }
    @Test
    public void processEl313Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = processEl("composite.composite.integer", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl314Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = processEl("(composite.composite.integer)", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl315Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("(integer)", context_);
        assertEq(0, arg_.getNumber());
    }
    @Test
    public void processEl316Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = processEl("1+1==2||(integer<8)", context_);
        assertTrue(arg_.isTrue());
    }
    @Test
    public void processEl317Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\\\\\\\"\"+$new $int[]\\{0i,1i\\}.length} ", context_, 2);
        assertEq("9 hello world {every body ;)\\\"2",arg_.getString());
        assertEq(75, context_.getNextIndex());
    }
    @Test
    public void processEl318Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null):\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"[pkg.Ex\",$true).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("[pkg.Ex",arg_.getString());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
    }
    @Test
    public void processEl319Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst = exmeth($null):\n");
        xml_.append(" $public $static $int exmeth(java.lang.Integer e){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.+e;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).forName(\"pkg.Ex[]\",$true).getName():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()", cont_);
        assertEq("[pkg.Ex",arg_.getString());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
    }
    @Test
    public void processEl320Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$static(pkg.ExTwo).exmeth()+pkg.Ex.inst", cont_);
        assertEq(6,arg_.getNumber());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl321Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.Ex().inst", cont_);
        assertEq(1,arg_.getNumber());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl322Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append("  $public $int inst=5:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.Ex().$new Inner().inst", cont_);
        assertEq(5,arg_.getNumber());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl320FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return Ex.inst:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   $return 5:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        processEl("pkg.ExTwo.exmeth()+pkg.Ex.inst", cont_);
        assertNotNull(cont_.getException());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(!cont_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl359Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument arg_ = processEl("$new pkg.Ex().inst", conf_);
        assertEq(1,arg_.getNumber());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }

    @Test
    public void processEl360Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument arg_ = processEl("$new pkg.Ex(5).inst", conf_);
        assertEq(5,arg_.getNumber());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl361Test() {
        Configuration context_ = contextEl();
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setIndexClassName(context_.getStandards().getAliasPrimLong());
        lv_.setStruct(new BooleanStruct(true));
        lv_.setIndex(5);
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setVars(localVars_);
        Argument argument_ = processEl("arg;;", context_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl362Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("$new pkg.Ex(5).inst", conf_);
        assertNotNull(conf_.getException());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(!conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl363Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration2(files_);
        addImportingPage(conf_);
        processEl("$new pkg.Ex(5).inst", conf_);
        assertNotNull(conf_.getException());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(!conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }

    private static Configuration getConfiguration2(StringMap<String> _files) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdTwo(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }

    @Test
    public void processEl364Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int...p){\n");
        xml_.append("  $var sum = q;.;:\n");
        xml_.append("  $foreach($var i:p;.;){\n");
        xml_.append("   sum;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("pkg.ExTwo.exmeth($vararg($int),4,$firstopt(8))", cont_);
        assertEq(12,arg_.getNumber());
    }
    @Test
    public void processEl365Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("($boolean)arg;.", context_);
        assertTrue(argument_.isTrue());
    }
    @Test
    public void processEl366Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new StringStruct("str"));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("(String)arg;.", context_);
        assertEq("str",argument_.getString());
    }
    @Test
    public void processEl367Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("($int)arg;.", context_);
        assertNotNull(context_.getException());
    }
    @Test
    public void processEl368Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        lv_.setStruct(new BooleanStruct(true));
        localVars_.put("arg", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        lv_.setStruct(new IntStruct(0));
        localVars_.put("arg2", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("($boolean)arg2;.", context_);
        assertNotNull(context_.getException());
    }
    @Test
    public void processEl369Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("1 $instanceof pkg.ExTwo", cont_);
        assertTrue(arg_.isFalse());
    }
    @Test
    public void processEl370Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("$new pkg.ExTwo<java.lang.Object>() $instanceof pkg.ExTwo", cont_);
        assertTrue(arg_.isTrue());
    }

    @Test
    public void processEl371Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.!=\"2\"", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl372Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.==\"2\"", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl373Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.!=\"8\"", context_);
        assertTrue(argument_.isFalse());
    }

    @Test
    public void processEl374Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasString());
        lv_.setStruct(new StringStruct("8"));
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("arg;.==\"8\"", context_);
        assertTrue(argument_.isTrue());
    }

    @Test
    public void processEl375Test() {
        Configuration context_ = contextEl();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasString()));
        lv_.setStruct(NullStruct.NULL_VALUE);
        localVars_.put("arg", lv_);
        addImportingPage(context_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("arg;.length", context_);
        assertNotNull(context_.getException());
    }
    @Test
    public void processEl376Test() {
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
        Argument arg_ = processEl("v;.clone()", context_);
        ArrayStruct arr_ = (ArrayStruct) arg_.getStruct();
        assertEq(1, arr_.getInstance().length);
        assertEq(8, ((NumberStruct)arr_.getInstance()[0]).intStruct());
    }
    @Test
    public void processEl377Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int...p){\n");
        xml_.append("  $var sum = q;.;:\n");
        xml_.append("  $foreach($var i:p;.;){\n");
        xml_.append("   sum;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("pkg.ExTwo.exmeth($id(pkg.ExTwo,$static,$int,$int...),4,8)", cont_);
        assertEq(12,arg_.getNumber());
    }
    @Test
    public void processEl378Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth($int q,$int p){\n");
        xml_.append("  $return q;.;+p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = processEl("pkg.ExTwo.exmeth($id(pkg.ExTwo,$static,$int,$int),4,8)", cont_);
        assertEq(12,arg_.getNumber());
    }
    @Test
    public void procesAffect0Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        BeanOne b_ = new BeanOne();
        b_.getComposite().setInteger(5);
        addBean(context_, b_, ALIAS_BEAN_ONE);
        processEl("(v;.)=$this.composite.integer", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(5, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processEl379Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument arg_ = processEl("++pkg.Ex.inst", conf_);
        assertEq(2,arg_.getNumber());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl380Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument arg_ = processEl("pkg.Ex.inst++", conf_);
        assertEq(1,arg_.getNumber());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl381Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument arg_ = processEl("pkg.Ex.inst+=5", conf_);
        assertEq(6,arg_.getNumber());
        assertTrue(conf_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(conf_.getClasses().isSuccessfulInitialized("pkg.Ex"));
    }
    @Test
    public void processEl382Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("$new pkg.Ex(5).inst/=0", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl383Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.inst/=0", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl384Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$new pkg.Ex(5).inst=10", conf_);
        assertEq(10,argument_.getNumber());
    }
    @Test
    public void processEl385Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt:\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.elt.inst=10", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl386Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt:\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.elt.inst", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl387Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("pkg.Ex.inst=10", conf_);
        assertEq(10,argument_.getNumber());
    }
    @Test
    public void processEl388Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.inst=10", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl389Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$new pkg.Ex(52).$that.res(8)", conf_);
        assertEq(60,argument_.getNumber());
    }
    @Test
    public void processEl390Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("pkg.Ex.res(8)", conf_);
        assertEq(9,argument_.getNumber());
    }
    @Test
    public void processEl391Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl392Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt:\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.elt.res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl393Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$new pkg.Ex(52).$classchoice(pkg.Ex)res(8)", conf_);
        assertEq(60,argument_.getNumber());
    }
    @Test
    public void processEl394Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("pkg.Ex.$classchoice(pkg.Ex)res(8)", conf_);
        assertEq(9,argument_.getNumber());
    }
    @Test
    public void processEl395Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public $static Ex<String> inst=$new Ex<>():\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(#T v){\n");
        xml_.append("  $return ($int)inst+($int)v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("(pkg.Ex.inst).$classchoice(pkg.Ex<java.lang.Integer>)res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl396Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt:\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.elt.$classchoice(pkg.Ex)res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl397Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.$classchoice(pkg.Ex)res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl398Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$new pkg.Ex(52).$superaccess(pkg.Ex)res(8)", conf_);
        assertEq(60,argument_.getNumber());
    }
    @Test
    public void processEl399Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("pkg.Ex.$superaccess(pkg.Ex)res(8)", conf_);
        assertEq(9,argument_.getNumber());
    }
    @Test
    public void processEl400Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex elt:\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.elt.$superaccess(pkg.Ex)res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl401Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst/=0:\n");
        xml_.append("  inst++:\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int val){\n");
        xml_.append("  inst=val;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int res($int v){\n");
        xml_.append("  $return inst+v;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 5:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("pkg.Ex.$superaccess(pkg.Ex)res(8)", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl402Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO:\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$values(pkg.ExTwo).length", conf_);
        assertEq(2,argument_.getNumber());
    }
    @Test
    public void processEl403Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5:}},TWO{(){myval=7:}}:\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v++:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$valueOf(pkg.ExTwo,\"ONE\").myval", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl404Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5:}},TWO{(){myval=7:}}:\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v++:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$valueOf(pkg.ExTwo,\"TWO\").myval", conf_);
        assertEq(7,argument_.getNumber());
    }
    @Test
    public void processEl405Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO:\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v/=0:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("$values(pkg.ExTwo).length", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl406Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5:}},TWO{(){myval=7:}}:\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v/=0:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("$valueOf(pkg.ExTwo,\"ONE\").myval", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl407Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5:}},TWO{(){myval=7:}}:\n");
        xml_.append(" $static{\n");
        xml_.append("  Other.v/=0:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int myval:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Other{\n");
        xml_.append(" $public $static $int v:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        processEl("$valueOf(pkg.ExTwo,\"TWO\").myval", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl408Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE,TWO\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$values(pkg.ExTwo).length", conf_);
        assertEq(2,argument_.getNumber());
    }
    @Test
    public void processEl409Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5:}},TWO{(){myval=7:}}:\n");
        xml_.append(" $public $int myval:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$valueOf(pkg.ExTwo,\"ONE\").myval", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl410Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo {\n");
        xml_.append(" ONE{(){myval=5:}},TWO{(){myval=7:}}:\n");
        xml_.append(" $public $int myval:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$valueOf(pkg.ExTwo,\"TWO\").myval", conf_);
        assertEq(7,argument_.getNumber());
    }
    @Test
    public void processEl411Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$lambda(pkg.Ex,,inst,$int).call(4)", conf_);
        assertEq(4,argument_.getNumber());
    }
    @Test
    public void processEl412Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$lambda(java.lang.String,length).call(\"mystr\")", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl413Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("((java.lang.String)$lambda(java.lang.String,$new,$char...).call($new $char[]{'m','y','s','t','r'})).length()", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl414Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("\"mystr\".$lambda(java.lang.String,length).call()", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl415Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst++:\n");
        xml_.append("  inst=\"mystr\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("pkg.Ex.inst.$lambda(java.lang.String,length).call()", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl416Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String inst:\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst++:\n");
        xml_.append("  inst=\"\":\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("$lambda(pkg.Ex,,inst,java.lang.String).call(\"mystr\")", conf_);
        assertEq("mystr",argument_.getString());
    }
    @Test
    public void processEl417Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("($new pkg.Ex(6)+$new pkg.Ex(8)).inst", conf_);
        assertEq(14,argument_.getNumber());
    }
    @Test
    public void processEl418Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex res = $new Ex(6):\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res+=$new pkg.Ex(8)).inst", conf_);
        assertEq(14,argument_.getNumber());
    }
    @Test
    public void processEl419Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex res = $new Ex(6):\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res++).inst", conf_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl420Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex res = $new Ex(6):\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(++pkg.Ex.res).inst", conf_);
        assertEq(7,argument_.getNumber());
    }
    @Test
    public void processEl421Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] res = {$new Ex(6)}:\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res[0]+=$new pkg.Ex(8)).inst", conf_);
        assertEq(14,argument_.getNumber());
    }
    @Test
    public void processEl422Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] res = {$new Ex(6)}:\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res[0]++).inst", conf_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl423Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex[] res = {$new Ex(6)}:\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(++pkg.Ex.res[0]).inst", conf_);
        assertEq(7,argument_.getNumber());
    }
    @Test
    public void processEl424Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex[] res = {$new Ex(6)}:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res.res[0]+=$new pkg.Ex(8)).inst", conf_);
        assertEq(14,argument_.getNumber());
    }
    @Test
    public void processEl425Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex[] res = {$new Ex(6)}:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res.res[0]++).inst", conf_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl426Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex[] res = {$new Ex(6)}:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(++pkg.Ex.res.res[0]).inst", conf_);
        assertEq(7,argument_.getNumber());
    }
    @Test
    public void processEl427Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6):\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res.res+=$new pkg.Ex(8)).inst", conf_);
        assertEq(14,argument_.getNumber());
    }
    @Test
    public void processEl428Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6):\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(pkg.Ex.res.res++).inst", conf_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl429Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6):\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration(files_);
        Argument argument_ = processEl("(++pkg.Ex.res.res).inst", conf_);
        assertEq(7,argument_.getNumber());
    }
    @Test
    public void processEl430Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex a,pkg.Ex b){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+b;.;inst:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6):\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("(v;.+=$new pkg.Ex(8)).inst", conf_);
        assertEq(14,argument_.getNumber());
        assertEq(14,((NumberStruct)((FieldableStruct)lv_.getStruct()).getStruct(new ClassField("pkg.Ex","inst"))).intStruct());
    }

    private static Configuration getConfiguration3(StringMap<String> _files) {
        Configuration conf_ = EquallableExUtil.newConfiguration();
        Options opt_ = new Options();
        opt_.setInitializeStaticClassFirst(false);
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdTwo(opt_);
        Classes.validateAll(_files, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }

    @Test
    public void processEl431Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6):\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("(v;.++).inst", conf_);
        assertEq(6,argument_.getNumber());
        assertEq(7,((NumberStruct)((FieldableStruct)lv_.getStruct()).getStruct(new ClassField("pkg.Ex","inst"))).intStruct());
    }
    @Test
    public void processEl432Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex a){\n");
        xml_.append(" $var o = $new pkg.Ex():\n");
        xml_.append(" o;.inst = a;.;inst+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public Ex res = $new Ex(6):\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static ExTwo res = $new ExTwo():\n");
        xml_.append(" $public $int inst:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("(++v;.).inst", conf_);
        assertEq(7,argument_.getNumber());
        assertEq(7,((NumberStruct)((FieldableStruct)lv_.getStruct()).getStruct(new ClassField("pkg.Ex","inst"))).intStruct());
    }
    @Test
    public void processEl434Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1]:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$new pkg.Ex(5)[0]", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl435Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1]:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$new pkg.Ex(5)[0]=15", conf_);
        assertEq(15,argument_.getNumber());
    }
    @Test
    public void processEl436Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1]:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$new pkg.Ex(5)[0]+=15", conf_);
        assertEq(20,argument_.getNumber());
    }
    @Test
    public void processEl437Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1]:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("++$new pkg.Ex(5)[0]", conf_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl438Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1]:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$new pkg.Ex(5)[0]++", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl439Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$new pkg.Ex(5)[0].myval", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl440Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("($new pkg.Ex(5)[0]=$new pkg.ExTwo(15)).myval", conf_);
        assertEq(15,argument_.getNumber());
    }
    @Test
    public void processEl441Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.ExTwo(pkg.ExTwo a, pkg.ExTwo b) {\n");
        xml_.append(" $var o =$new pkg.ExTwo():\n");
        xml_.append(" o;.myval=a;.;myval+b;.;myval:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("($new pkg.Ex(5)[0]+=$new pkg.ExTwo(15)).myval", conf_);
        assertEq(20,argument_.getNumber());
    }
    @Test
    public void processEl442Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.ExTwo(pkg.ExTwo a) {\n");
        xml_.append(" $var o =$new pkg.ExTwo():\n");
        xml_.append(" o;.myval=a;.;myval+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("(++$new pkg.Ex(5)[0]).myval", conf_);
        assertEq(6,argument_.getNumber());
    }
    @Test
    public void processEl443Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.ExTwo(pkg.ExTwo a) {\n");
        xml_.append(" $var o =$new pkg.ExTwo():\n");
        xml_.append(" o;.myval=a;.;myval+1:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("($new pkg.Ex(5)[0]++).myval", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl444Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.ExTwo(pkg.ExTwo a, pkg.ExTwo b) {\n");
        xml_.append(" $var o =$new pkg.ExTwo():\n");
        xml_.append(" o;.myval=a;.;myval+b;.;myval:\n");
        xml_.append(" $return o;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<#T> {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this(#T p){\n");
        xml_.append("  $return inst[$($int)p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this(#T p){\n");
        xml_.append("  inst[$($int)p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        processEl("$new pkg.Ex<java.lang.Integer>(5).$classchoice(pkg.Ex<java.lang.String>)[\"\"]", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl445Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub(){}\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        Argument argument_ = processEl("$new pkg.ExSub(5).$super[0].myval", conf_);
        assertEq(5,argument_.getNumber());
    }
    @Test
    public void processEl446Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[1]:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=p;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $int $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        processEl("$new pkg.Ex(5)[0]/=0", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void processEl447Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int myval:\n");
        xml_.append(" $public ExTwo(){}\n");
        xml_.append(" $public ExTwo($int p){\n");
        xml_.append("  myval=p;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub:Ex {\n");
        xml_.append(" $public ExSub(){}\n");
        xml_.append(" $public ExSub($int p){\n");
        xml_.append("  $super(p;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Ex st:\n");
        xml_.append(" $public ExTwo[] inst={$new ExTwo()}:\n");
        xml_.append(" $public Ex(){}\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst[0]=$new ExTwo(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public ExTwo $this($int p){\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p){\n");
        xml_.append("  inst[p;.;]=$value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration conf_ = getConfiguration3(files_);
        ContextEl cont_ = conf_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct value_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex", "", -1);
        ((FieldableStruct)value_).setStruct(new ClassField("pkg.Ex","inst"),new IntStruct(6));
        lv_.setStruct(value_);
        lv_.setClassName("pkg.Ex");
        localVars_.put("v", lv_);
        addImportingPage(conf_);
        conf_.getLastPage().setLocalVars(localVars_);
        processEl("pkg.Ex.st[0].myval", conf_);
        assertNotNull(conf_.getException());
    }
    @Test
    public void procesAffect00Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("(v;.)=1i", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(1, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processAffect1Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(0));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.=1i", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(1, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processAffect2Test() {
        Configuration context_ = contextEl();
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
        processEl("v;.integer=12i", context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(12, c_.getInteger());
    }
    @Test
    public void processAffect3Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.[0i]=12i", context_);
        assertEq(12, ((NumberStruct) in_[0]).intStruct());
    }
    @Test
    public void processAffect4Test() {
        Configuration context_ = contextEl();
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
        processEl("v;.[0i][0i]=12i", context_);
        assertEq(12, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processAffect5Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.+=1i", context_);
        assertEq(context_.getStandards().getAliasPrimInteger(), lv_.getClassName());
        assertEq(2, ((NumberStruct)lv_.getStruct()).intStruct());
    }
    @Test
    public void processAffect6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processEl("v;.integer-=12i", cont_);
        assertEq(-12, ((NumberStruct)((FieldableStruct)var_).getStruct(new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(-12, res_.getNumber());
    }
    @Test
    public void processAffect7Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processEl("v;.[0i]-=12i", context_);
        assertEq(-12, ((NumberStruct) in_[0]).intStruct());
        assertEq(-12, res_.getNumber());
    }
    @Test
    public void processAffect8Test() {
        Configuration context_ = contextEl();
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
        Argument res_ = processEl("v;.[0i][0i]-=12i", context_);
        assertEq(-12, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(-12, res_.getNumber());
    }
    @Test
    public void processAffect9Test() {
        Configuration context_ = contextEl();
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
        Argument res_ = processEl("v;.[0i][0i]++", context_);
        assertEq(1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(0, res_.getNumber());
    }
    @Test
    public void processAffect10Test() {
        Configuration context_ = contextEl();
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
        Argument res_ = processEl("v;.[0i][0i]--", context_);
        assertEq(-1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(0, res_.getNumber());
    }
    @Test
    public void processAffect11Test() {
        Configuration context_ = contextEl();
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
        Argument res_ = processEl("++v;.[0i][0i]", context_);
        assertEq(1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(1, res_.getNumber());
    }
    @Test
    public void processAffect12Test() {
        Configuration context_ = contextEl();
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
        Argument res_ = processEl("--v;.[0i][0i]", context_);
        assertEq(-1, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
        assertEq(-1, res_.getNumber());
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
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        NumberStruct arg_;
        processEl("$classchoice(pkg.Ex)inst=2i", cont_);
        arg_ = (NumberStruct) cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        assertEq(2, arg_.intStruct());
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
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Struct arg_;
        processEl("$classchoice(pkg.Ex)inst=2i", cont_);
        processEl("$classchoice(pkg.Ex)inst=v;.", cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        assertSame(NullStruct.NULL_VALUE,arg_);
    }
    @Test
    public void processAffect23Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("add "));
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.+=1i", context_);
        assertEq(context_.getStandards().getAliasString(), lv_.getClassName());
        assertEq("add 1", ((StringStruct)lv_.getStruct()).getInstance());
    }
    @Test
    public void processAffect24Test() {
        Configuration context_ = contextEl();
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
        processEl("v;.[0i]+=1i", context_);
        assertEq(arrayType_, lv_.getClassName());
        assertEq("add 1",((StringStruct)(((ArrayStruct) lv_.getStruct()).getInstance())[0]).getInstance());
    }
    @Test
    public void processAffect25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int[] inst=$new $int[1i]:\n");
        xml_.append(" $public $static $int[] exmeth(){\n");
        xml_.append("  $return inst;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        Struct arg_;
        Struct[] res_;
        processEl("$static(pkg.Ex).exmeth()[0i]=2i", cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = ((ArrayStruct) arg_).getInstance();
        assertEq(1,res_.length);
        assertEq(2,((NumberStruct)res_[0]).intStruct());
    }
    @Test
    public void processAffect26Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(true));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.&=$false", context_);
        assertEq(context_.getStandards().getAliasPrimBoolean(), lv_.getClassName());
        assertEq(false, ((BooleanStruct)lv_.getStruct()).getInstance());
    }
    @Test
    public void processAffect27Test() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new BooleanStruct(false));
        lv_.setClassName(context_.getStandards().getAliasPrimBoolean());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.|=$true", context_);
        assertEq(context_.getStandards().getAliasPrimBoolean(), lv_.getClassName());
        assertEq(true, ((BooleanStruct)lv_.getStruct()).getInstance());
    }
    @Test
    public void processAffect28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processEl("v;.integer++", cont_);
        assertEq("pkg.Composite", lv_.getClassName());
        assertEq(1, ((NumberStruct)((FieldableStruct)var_).getStruct(new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(0, res_.getNumber());
    }
    @Test
    public void processAffect29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processEl("++v;.integer", cont_);
        assertEq("pkg.Composite", lv_.getClassName());
        assertEq(1, ((NumberStruct)((FieldableStruct)var_).getStruct(new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(1, res_.getNumber());
    }

    @Test
    public void processAffect30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Composite {\n");
        xml_.append(" $public $int integer:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        ContextEl context_ = cont_.getContext();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct var_ = context_.getInit().processInit(context_, NullStruct.NULL_VALUE, "pkg.Composite", "", -1);
        lv_.setStruct(var_);
        lv_.setClassName("pkg.Composite");
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        Argument res_ = processEl("(v;.integer-=12i)", cont_);
        assertEq(-12, ((NumberStruct)((FieldableStruct)var_).getStruct(new ClassField("pkg.Composite","integer"))).intStruct());
        assertEq(-12, res_.getNumber());
    }
    @Test
    public void processAffect31Test() {
        Configuration context_ = contextEl();
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
        processEl("(v;.[0i][0i])=12i", context_);
        assertEq(12, ((NumberStruct)(((ArrayStruct)in_[0]).getInstance())[0]).intStruct());
    }
    @Test
    public void processAffect2FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().getParameters().putAllMap(localVars_);
        processEl("v;.;=12i", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processAffect3FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(c_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.[0i]=\"12i\"", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processAffect4FailTest() {
        Configuration context_ = contextEl();
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
        processEl("v;.integer=\"12i\"", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processAffect5FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(8));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.=\"12i\"", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processAffect6FailTest() {
        Configuration context_ = contextEl();
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
        processEl("v;.integer=v2;.", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect8FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.=v2;.", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect9FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        addBean(context_, new BeanOne(), ALIAS_BEAN_ONE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("$this=$null", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
    }
    @Test
    public void processAffect10FailTest() {
        Configuration context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasPrimInteger());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.=$this", context_);
        assertTrue(!context_.getClasses().isEmptyErrors());
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
        Configuration cont_ = contextEl(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(NullStruct.NULL_VALUE);
        lv_.setClassName(cont_.getStandards().getAliasInteger());
        localVars_.put("v", lv_);
        cont_.getLastPage().setLocalVars(localVars_);
        processEl("$classchoice(pkg.Ex)inst=v;.", cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertNotNull(cont_.getContext().getException());
    }
    @Test
    public void processAffect12FailTest() {
        Configuration context_ = contextEl();
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
        lv_.setStruct(new IntStruct(1));
        lv_.setClassName(context_.getStandards().getAliasInteger());
        localVars_.put("v2", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.[0i]=v2;.", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect13FailTest() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.objInteger-=12i", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect14FailTest() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.objInteger++", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect15FailTest() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName("code.expressionlanguage.classes.Composite");
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("++v;.objInteger", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect16FailTest() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        String primIntType_ = custLgNames_.getAliasPrimInteger();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(exp_, PrimitiveTypeUtil.getPrettyArrayType(primIntType_)));
        lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("v;.[0i]/=0", context_);
        assertTrue(context_.getClasses().isEmptyErrors());
        assertNotNull(context_.getContext().getException());
    }
    @Test
    public void processAffect17FailTest() {
        Configuration context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getContext().getStandards();
        String primIntType_ = custLgNames_.getAliasPrimInteger();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(exp_, PrimitiveTypeUtil.getPrettyArrayType(primIntType_)));
        lv_.setClassName(PrimitiveTypeUtil.getPrettyArrayType(primIntType_));
        localVars_.put("v", lv_);
        context_.getLastPage().setLocalVars(localVars_);
        processEl("$this()", context_);
        assertNotNull(context_.getContext().getException());
    }
    private static Argument processEl(String _el, Configuration _cont) {
        return ElRenderUtil.processEl(_el, 0, _cont);
    }
    private static Argument processEl(String _el, Configuration _conf, int _minIndex){
        return ElRenderUtil.processEl(_el,_conf,_minIndex, '{','}');
    }

    private static Configuration getConfiguration(StringMap<String> _files) {
        Configuration conf_ = getConfiguration3(_files);
        addImportingPage(conf_);
        return conf_;
    }

    private static Argument calculate(CustList<OperationNode> _ops, Configuration _an) {
        CustList<RendDynOperationNode> out_ = ElRenderUtil.getExecutableNodes(_ops);
        ElRenderUtil.calculate(out_, _an);
        return out_.last().getArgument();
    }
    private static void addImportingPage(Configuration _conf) {
        _conf.addPage(new ImportingPage(false));
        _conf.getContext().setAnalyzing(new AnalyzedPageEl());
    }
    private static void addBean(Configuration _conf, Object _bean, String _beanClass) {
        _conf.getLastPage().setGlobalArgumentStruct(StdStruct.newInstance(_bean, _beanClass),_conf);
        _conf.setGlobalClass(_beanClass);
        _conf.getContext().setGlobalClass(_beanClass);
    }
    private static void addBeanClassName(Configuration _conf, String _bean) {
        _conf.setGlobalClass(_bean);
        _conf.getContext().setGlobalClass(_bean);
    }
    private Configuration contextEl() {
        Configuration conf_ =  EquallableExUtil.newConfiguration();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        Options opt_ = new Options();
        opt_.setEndLineSemiColumn(false);
        opt_.setSuffixVar(VariableSuffix.DISTINCT);
        ContextEl cont_ = InitializationLgNames.buildStdTwo(opt_);
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        conf_.setContext(cont_);
        BeanLgNames standards_ = (BeanLgNames) cont_.getStandards();
        conf_.setStandards(standards_);
        standards_.buildIterables(conf_);
        return conf_;
    }
    private Configuration contextEl(StringMap<String> _files) {
        Configuration conf_ = getConfiguration2(_files);
        return conf_;
    }
}
