package code.expressionlanguage;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Ignore;
import org.junit.Test;

import code.expressionlanguage.classes.ArrayContainer;
import code.expressionlanguage.classes.BeanOne;
import code.expressionlanguage.classes.Composite;
import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.classes.FailMethods;
import code.expressionlanguage.classes.IOne;
import code.expressionlanguage.classes.ImplFour;
import code.expressionlanguage.classes.MyImpl;
import code.expressionlanguage.classes.StrangeInit;
import code.expressionlanguage.exceptions.AbstractClassConstructorException;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.BadNumberValuesException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.EmptyPartException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.NotStringException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.VarargException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.RuntimeClassNotFoundException;

@SuppressWarnings("static-method")
public class ElUtilTest {

    private static final String ARR_INT = "[$int";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";
    private static final String STRING_LIST = StringList.class.getName();
    private static final String INHERITED_COMPOSITE = "code.expressionlanguage.classes.InheritedComposite";
    private static final String COMPOSITE = "code.expressionlanguage.classes.Composite";
    private static final String COMPOSITE_HAT = StringList.replace(COMPOSITE, ".", "$");
    private static final String STRANGE_INIT = StrangeInit.class.getName();
    private static final String STRANGE_INIT_HAT = StringList.replace(STRANGE_INIT, ".", "$");
    private static final String FAIL_METHODS = FailMethods.class.getName();
    private static final String FAIL_METHODS_HAT = StringList.replace(FAIL_METHODS, ".", "$");
    private static final String IONE = IOne.class.getName();
    private static final String IMPL_FOUR = ImplFour.class.getName();
    private static final String MY_IMPL = MyImpl.class.getName();
    private static final String MY_IMPL_HAT = StringList.replace(MY_IMPL, ".", "$");
    private static final String PUBLIC_ACCESS = "PUBLIC";
    private static final String ALIAS_BEAN_ONE = "code.expressionlanguage.classes.BeanOne";

    @Test
    public void processEl1Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$class(\"java.lang.Number\",5)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(5L, (Number)res_);
    }
    @Test
    public void processEl2Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$java$lang$Long.MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(Long.MAX_VALUE, (Number)res_);
    }

    @Test
    public void processEl3Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(1+2)*3",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl4Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1--1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(2L, (Number)res_);
    }

    @Test
    public void processEl5Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+2*3",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(7L, (Number)res_);
    }

    @Test
    public void processEl6Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("--1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(1L, (Number)res_);
    }

    @Test
    public void processEl7Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(-8l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl8Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(8l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl9Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = ElUtil.processEl("composite.integer",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl10Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("40908c",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq((char)40908, ((Character)res_).charValue());
    }

    @Test
    public void processEl11Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("'\\u9fcb'",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq((char)40907, ((Character)res_).charValue());
    }

    @Test
    public void processEl12Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("'\\\\'",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('\\', ((Character)res_).charValue());
    }

    @Test
    public void processEl13Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("'\\''",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('\'', ((Character)res_).charValue());
    }

    @Test
    public void processEl14Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("'\"'",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('"', ((Character)res_).charValue());
    }

    @Test
    public void processEl15Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("'\\n'",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('\n', ((Character)res_).charValue());
    }

    @Test
    public void processEl16Test() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StdStruct(compos_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;.integer",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl17Test() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setStruct(new StdStruct(compos_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;integer",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0,(Number) res_);
    }

    @Test
    public void processEl18Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$instanceof(\"java.lang.Number\",5)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl19Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$instanceof(\"java.lang.Number\",'5')",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl20Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("!$instanceof(\"java.lang.Number\",'5')",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }


    @Test
    public void processEl21Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1=2",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl22Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1!=2",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl23Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1=2&1+0=8",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl24Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1!=2|1+7=8",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl25Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1=2&(1+0=8|3*3=9)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }
    @Test
    public void processEl26Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1=2|1+6=8&1=1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }

    @Test
    public void processEl27Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        //CustList worked but is generic so it will take args TODO
        Argument arg_ = ElUtil.processEl("$new "+STRING_LIST+"()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(StringList.class, res_.getClass());
        assertEq(new StringList(), (StringList)res_);
    }

    @Test
    public void processEl28Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_, ALIAS_BEAN_ONE);
        Argument arg_ = ElUtil.processEl("composite.integer",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl29Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1+1=2|1/0>8",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, (Boolean)res_);
    }

//    @Ignore
//    @Test
//    public void processEl30Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        BeanSeven b_ = new BeanSeven();
//        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("arrayInt[1i]",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Integer.class, res_.getClass());
//        assertEq(3, (Number)res_);
//    }

    @Test
    public void processEl31Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(-8i)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }

    @Test
    public void processEl32Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(8i)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }


    @Test
    public void processEl33Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(-8I)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }

    @Test
    public void processEl34Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(8I)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }


    @Test
    public void processEl35Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(-8L)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl36Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(8L)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test
    public void processEl37Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenTwo($null)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("one", (String)res_);
    }

    @Test
    public void processEl38Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenTwo($class(\"java.lang.Object\",$null))",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("two", (String)res_);
    }

    @Test
    public void processEl39Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$instanceof(\"java.lang.Object\",$null)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl40Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl41Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("long", (String)res_);
    }

    @Test
    public void processEl42Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Double", (String)res_);
    }

    @Test
    public void processEl43Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0d)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", (String)res_);
    }

    @Test
    public void processEl44Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0F)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", (String)res_);
    }

    @Test
    public void processEl45Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0f)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", (String)res_);
    }

    @Ignore
    @Test
    public void processEl46Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$class(\""+IONE+"\",$new "+MY_IMPL+"()).testOne()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("one", (String)res_);
    }

    @Ignore
    @Test
    public void processEl47Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$"+MY_IMPL_HAT+".ovOne($new "+MY_IMPL+"())",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("three", (String)res_);
    }

//    @Ignore
//    @Test
//    public void processEl48Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"().$new InternStandard()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStandard.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl49Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"$InternStaticStandard()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStaticStandard.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl50Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"().$new InternStandardTwo().$new InternStandardOne()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStandardOne.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl51Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"$InternStaticStandard$InternStaticStandardThree()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStaticStandardThree.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl52Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"$InternStaticStandard().$new InternStaticStandardFour()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStaticStandardFour.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl53Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"().$new InternStandardTwo().$new InternStandardThree(1i)",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStandardThree.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl54Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"().$new InternStandardTwo().$new InternStandardThree(1i).getPrivateInfo()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Integer.class, res_.getClass());
//        assertEq(1, (Number)res_);
//    }

//    @Ignore
//    @Test
//    public void processEl55Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        InternsClasses b_ = new InternsClasses();
//        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("$new InternStandardTwo().$new InternStandardThree(1i).getPrivateInfo()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Integer.class, res_.getClass());
//        assertEq(1, (Number)res_);
//    }

    @Test
    public void processEl56Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+INHERITED_COMPOSITE+"().getPrivateInt()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

//    @Ignore
//    @Test
//    public void processEl57Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Composite composite_ = new Composite();
//        composite_.setInteger(6);
//        composite_.setPrivateInt(5);
////        InternsClasses b_ = new InternsClasses();
//        addBean(context_, composite_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(7);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("summum(v;.)",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Integer.class, res_.getClass());
//        assertEq(13, (Number)res_);
//    }

    @Test
    public void processEl58Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("f;.format($vararg(\"java.lang.String\"),$firstopt(v;.),2;.,v;.)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("varargs:7 8 7", (String)res_);
    }

    @Test
    public void processEl59Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("f;.format($vararg(\"java.lang.String\"))",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("varargs:{0} {1} {2}", (String)res_);
    }

    @Test
    public void processEl60Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("f;.format(v;.,2;.,v;.)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("varargs:7 8 7", (String)res_);
    }

    @Test
    public void processEl61Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("f;.format()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("varargs:{0} {1} {2}", (String)res_);
    }

    @Ignore
    @Test
    public void processEl62Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement("bonjour");
        lv_.setClassName(String.class.getName());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement("tout");
        lv_.setClassName(String.class.getName());
        localVars_.put("2", lv_);
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$new "+COMPOSITE+"($vararg(\"java.lang.String\"),$firstopt(v;.),2;.).getStrings()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(StringList.class, res_.getClass());
        assertEq(new StringList("bonjour","tout"), (StringList)res_);
    }

    @Test
    public void processEl63Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"(1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertEq(0, (Number) ((Struct[])res_.getInstance())[0].getInstance());
    }

    @Test
    public void processEl64Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_ARR_INT+"(1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[0]);
    }

    @Test
    public void processEl65Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INTEGER+"(2i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_));
        assertEq(2, ((Struct[])res_.getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[1]);
    }

    @Test
    public void processEl66Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_ARR_INTEGER+"(2i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_));
        assertEq(2, ((Struct[])res_.getInstance()).length);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[0]);
        assertSame(NullStruct.NULL_VALUE, ((Struct[])res_.getInstance())[1]);
    }

    @Test
    public void processEl67Test() {
        ContextEl context_ = contextEl();
        Composite composite_ = new Composite();
        addImportingPage(context_);
        addBean(context_,composite_, COMPOSITE);
        assertEq(0, composite_.getPrivateInt());
        ElUtil.processEl("setPrivateInt(2i)",0, context_);
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
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("arrays;.[0i]",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
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
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("arrays;.[0i].length",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(2, (Number)res_);
    }

    @Test
    public void processEl70Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("!!$false",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, (Boolean)res_);
    }

    @Test
    public void processEl71Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        addBeanClassName(context_,context_.getStandards().getAliasByte());
        Argument arg_ = ElUtil.processEl("MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Byte.class, res_.getClass());
        assertEq((byte)127, (Number)res_);
    }

    @Test
    public void processEl72Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$java$lang$Byte.MAX_VALUE",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Byte.class, res_.getClass());
        assertEq((byte)127, (Number)res_);
    }

    @Test
    public void processEl73Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+STRING_LIST+"()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(StringList.class, res_.getClass());
        assertEq(new StringList(), (StringList)res_);
    }

//    @Ignore
//    @Test
//    public void processEl74Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        addBeanClassName(context_,"StringList");
//        Argument arg_ = ElUtil.processEl("isNumber(\"8\")",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Boolean.class, res_.getClass());
//        assertEq(true, (Boolean)res_);
//    }

//    @Ignore
//    @Test
//    public void processEl75Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        addBeanClassName(context_,"StringList");
//        Argument arg_ = ElUtil.processEl("getMetaCharacters().size()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Integer.class, res_.getClass());
//        assertEq(3, (Number)res_);
//    }

//    @Ignore
//    @Test
//    public void processEl76Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        Argument arg_ = ElUtil.processEl("$new "+INTERNS+"$InternStaticStandard()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStaticStandard.class, res_.getClass());
//    }

    @Test
    public void processEl77Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\\\"+\"World\").length()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
    }

    @Test
    public void processEl78Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\"\"+\"World\").length()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
    }

    @Test
    public void processEl79Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\\\"+'\\\\').length()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(7, (Number)res_);
    }
    @Test
    public void processEl80Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\"\"+'\\'').length()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(7, (Number)res_);
    }

    @Test
    public void processEl81Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("(f;.format($vararg(\"java.lang.String\"),$firstopt(v;.),2;.,v;.)+'\\'').length()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(14, (Number)res_);
    }

    @Test
    public void processEl82Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$static$$math.abs(v;.[0i]+2)*2",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(20L, (Number)res_);
    }

    @Test
    public void processEl83Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] i_ = new Struct[1];
        i_[0] = new IntStruct(8);
        lv_.setStruct(new ArrayStruct(i_,ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("(v;.[0i]+2)*2",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(20L, (Number)res_);
    }
//    @Ignore
//    @Test
//    public void processEl84Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("v;.$new InternStandard()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStandard.class, res_.getClass());
//    }

//    @Ignore
//    @Test
//    public void processEl85Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("v;.news",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(String.class, res_.getClass());
//        assertEq("", (String)res_);
//    }
//
//
//    @Ignore
//    @Test
//    public void processEl86Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("v;.news.length()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(Integer.class, res_.getClass());
//        assertEq(0, (Number)res_);
//    }

    @Test
    public void processEl87Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$bool(1>0,0i,1i)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl88Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$bool(1<0,0i,1i)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1, (Number)res_);
    }

    @Test
    public void processEl89Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$bool(1>0,0i,1i/0i)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl90Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$bool(1<0,1i/0i,1i)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1,(Number) res_);
    }

//    @Ignore
//    @Test
//    public void processEl91Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"(0i).getClass().getName()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(String.class, res_.getClass());
//        assertEq("[$int", (String)res_);
//    }
//
//    @Ignore
//    @Test
//    public void processEl92Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("$new "+ARR_INTEGER+"(0i).getClass().getName()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(String.class, res_.getClass());
//        assertEq("[java.lang.Integer", (String)res_);
//    }
//
//    @Ignore
//    @Test
//    public void processEl93Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("$new "+ARR_ARR_INT+"(0i).getClass().getName()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(String.class, res_.getClass());
//        assertEq("[[$int", (String)res_);
//    }
//
//    @Ignore
//    @Test
//    public void processEl94Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(new InternsClasses());
//        lv_.setClassName(InternsClasses.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("$new "+ARR_ARR_INTEGER+"(0i).getClass().getName()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(String.class, res_.getClass());
//        assertEq("[[java.lang.Integer", (String)res_);
//    }

    @Test
    public void processEl95Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"(1i)[0i]",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl96Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"[](2i)", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(1, o_.length);
        assertEq(2, ((Number)o_[0].getInstance()).intValue());
    }

    @Test
    public void processEl97Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"[](3i,7i)", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((Number)o_[0].getInstance()).intValue());
        assertEq(7, ((Number)o_[1].getInstance()).intValue());
    }

    @Test
    public void processEl98Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"[]()", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INT, res_.getClassName(context_));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(0, o_.length);
    }

    @Test
    public void processEl99Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INTEGER+"[](3i,7i)", 0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_INTEGER, res_.getClassName(context_));
        Struct[] o_ = (Struct[]) res_.getInstance();
        assertEq(2, o_.length);
        assertEq(3, ((Number)o_[0].getInstance()).intValue());
        assertEq(7, ((Number)o_[1].getInstance()).intValue());
    }

    @Test
    public void processEl100Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("9 hello world {every body ;)", (String)res_);
        assertEq(43, context_.getNextIndex());
    }

    @Test
    public void processEl101Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(" {(\"hello \"+\"world\").length()} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
        assertEq(30, context_.getNextIndex());
    }

    @Test
    public void processEl102Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(Long.class.getName());
        localVars_.put("arg", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(arg;.)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl103Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName("$long");
        localVars_.put("arg", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(arg;.)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("long", (String)res_);
    }

//    @Ignore
//    @Test
//    public void processEl104Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        addBeanClass(context_, InternsClasses.class);
//        Argument arg_ = ElUtil.processEl("$new InternStaticStandard()",0, context_);
//        Object res_ = arg_.getObject();
//        assertSame(InternStaticStandard.class, res_.getClass());
//    }

    @Test
    public void processEl105Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("$static$pkg$Ex.exmeth()", 0, cont_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(9, (Number)res_);
    }


    @Test
    public void processEl106Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("$static$pkg$Ex.exmeth(6i)", 0, cont_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(15,(Number) res_);
    }

    @Test
    public void processEl107Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("$new pkg.Ex()", 0, cont_);
        Struct res_ = arg_.getStruct();
        assertEq("pkg.Ex",res_.getClassName(cont_));
    }

    @Ignore
    @Test
    public void processEl108Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+IMPL_FOUR+"().method()",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1,(Number) res_);
    }

    @Test
    public void processEl109Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("$classchoice$pkg$Ex$$exmeth(6i)", 0, cont_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(15,(Number) res_);
    }

    @Test
    public void processEl110Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field static='' access='"+PUBLIC_ACCESS+"' name='inst' class='"+PrimitiveTypeUtil.PRIM_INT+"' value='2i'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("$classchoice$pkg$Ex$$inst;;;", 0, cont_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(2,(Number) res_);
    }

    @Test
    public void processEl111Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenFour($null)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl112Test() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        Composite compos_ = new Composite();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StdStruct(compos_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getCatchVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;..integer",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl113Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenFour(1L)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl114Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenFour(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("long", (String)res_);
    }

    @Test
    public void processEl115Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenFive(1L)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl116Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenFive(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", (String)res_);
    }

    @Test
    public void processEl117Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenSix(1L)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", (String)res_);
    }

    @Test
    public void processEl118Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        Argument arg_ = ElUtil.processEl("getOverridenSix(1l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("long",(String) res_);
    }

    @Test
    public void processEl119Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(1b+2b)*3",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl120Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(1s+2b)*3",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl121Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("--1b",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1, (Number)res_);
    }

    @Test
    public void processEl122Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-1b",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Byte.class, res_.getClass());
        assertEq(-1, (Number)res_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl123FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("+1b",0, context_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl124Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("+-1b",0, context_);
    }

    @Test
    public void processEl125Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-.25e0+.5",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.25d, (Number)res_);
    }

    @Test
    public void processEl126Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("9 hello world {every body ;)", (String)res_);
        int nextIndex_ = context_.getNextIndex();
        assertEq(43, nextIndex_);
        arg_ = ElUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"}{5*8} ", context_, nextIndex_+1 ,'{','}');
        res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(40, (Number) res_);
        nextIndex_ = context_.getNextIndex();
        assertEq(48, nextIndex_);
        
    }

    @Test
    public void processEl127Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(" {(\"hello \"+\"world\").length()}{5*8} ", context_, 2 ,'{','}');
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
        int nextIndex_ = context_.getNextIndex();
        assertEq(30, nextIndex_);
        arg_ = ElUtil.processEl(" {(\"hello \"+\"world\").length()}{5*8} ", context_, nextIndex_+1 ,'{','}');
        res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(40, (Number) res_);
        nextIndex_ = context_.getNextIndex();
        assertEq(35, nextIndex_);
    }

    @Test
    public void processEl128Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1_0+2*3",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(16L, (Number)res_);
    }

    @Test
    public void processEl129Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.mod(-8l,3l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(1L, (Number)res_);
    }

    @Test
    public void processEl130Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$static$$math.quot(-8l,3l)",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(-3L, (Number)res_);
    }

    @Test
    public void processEl131Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INT+"(1i,1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INT, res_.getClassName(context_));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertEq(ARR_INT, ((Struct[])res_.getInstance())[0].getClassName(context_));
        assertEq(1, ((Struct[])((Struct[])res_.getInstance())[0].getInstance()).length);
        assertEq(0, (Number) ((Struct[])((Struct[])res_.getInstance())[0].getInstance())[0].getInstance());
    }

    @Test
    public void processEl132Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("$new "+ARR_INTEGER+"(1i,1i)",0, context_);
        Struct res_ = arg_.getStruct();
        assertEq(ARR_ARR_INTEGER, res_.getClassName(context_));
        assertEq(1, ((Struct[])res_.getInstance()).length);
        assertEq(ARR_INTEGER, ((Struct[])res_.getInstance())[0].getClassName(context_));
        assertSame(NullStruct.NULL_VALUE, ((Struct[])((Struct[])res_.getInstance())[0].getInstance())[0]);
    }

    @Test
    public void processEl133Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(1 + 2) * 3.0",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(9L, (Number)res_);
    }

    @Test
    public void processEl134Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(" 2.0 + $static$$math. quot( -8l, 3l) + 3.0",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(2L, (Number)res_);
    }
    
    @Test
    public void processEl135Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1 + 2 ",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(3L, (Number)res_);
    }

    @Test
    public void processEl136Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1. + 2. ",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(3L, (Number)res_);
    }

    @Test
    public void processEl137Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1.d + 2.d ",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(3L, (Number)res_);
    }

    @Test
    public void processEl138Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-.2_5e0+.5",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.25d, (Number)res_);
    }

    @Test
    public void processEl139Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-.25e0_0+.5",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.25d, (Number)res_);
    }

    @Test
    public void processEl140Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1_0.d + 2.d ",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(12L, (Number)res_);
    }
    @Test
    public void processEl141Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1.05e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(10.5d, (Number)res_);
    }
    @Test
    public void processEl142Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1.00625e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(10.0625d, (Number)res_);
    }
    @Test
    public void processEl143Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("100.625e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(10.0625d, (Number)res_);
    }
    @Test
    public void processEl144Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("100.625",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(100.625d, (Number)res_);
    }
    @Test
    public void processEl145Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456789123456789123456789.0",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1.2345678912345678912e26, (Number)res_);
    }
    @Test
    public void processEl147Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456789123456789123456789.",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1.2345678912345678912e26, (Number)res_);
    }
    @Test
    public void processEl148Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456789123456789123456789.e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1.2345678912345678912e25, (Number)res_);
    }
    @Test
    public void processEl149Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456789123456789123456789.e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1.2345678912345678912e27, (Number)res_);
    }
    @Test
    public void processEl150Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456.e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1234560, (Number)res_);
    }
    @Test
    public void processEl151Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(".078125e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(.078125e-1, (Number)res_);
    }
    @Test
    public void processEl152Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456789123456789123456789.0e-36",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1.2345678912345678912e-10, (Number)res_);
    }
    @Test
    public void processEl153Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("0.0e-36",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.0, (Number)res_);
    }
    @Test
    public void processEl154Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-0.0e-36",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-0.0, (Number)res_);
    }
    @Test
    public void processEl155Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("0.625e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.0625, (Number)res_);
    }
    @Test
    public void processEl156Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(".625e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.0625, (Number)res_);
    }
    @Test
    public void processEl157Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("0.625e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(6.25, (Number)res_);
    }
    @Test
    public void processEl158Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(".625e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(6.25, (Number)res_);
    }
    @Test
    public void processEl159Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("0.625e0",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.625, (Number)res_);
    }
    @Test
    public void processEl160Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(".625e0",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.625, (Number)res_);
    }
    @Test
    public void processEl161Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-.625e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-6.25, (Number)res_);
    }
    @Test
    public void processEl162Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-.6e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-6.0, (Number)res_);
    }
    @Test
    public void processEl163Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-.60e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-6.0, (Number)res_);
    }
    @Test
    public void processEl164Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(".6e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(6.0, (Number)res_);
    }
    @Test
    public void processEl165Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl(".6e2",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(60.0, (Number)res_);
    }
    @Test
    public void processEl166Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("123456789123456789123456789.1e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(1.2345678912345678912e27, (Number)res_);
    }
    @Test
    public void processEl167Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("100.e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(10.0, (Number)res_);
    }
    @Test
    public void processEl168Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-100.e-1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-10.0, (Number)res_);
    }
    @Test
    public void processEl169Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-1.e1",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-10.0, (Number)res_);
    }
    @Test
    public void processEl170Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-1.",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-1.0, (Number)res_);
    }
    @Test
    public void processEl171Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1e-123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(0.0, (Number)res_);
    }
    @Test
    public void processEl172Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-1e-123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(-0.0, (Number)res_);
    }
    @Test
    public void processEl173Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("1e123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(Double.POSITIVE_INFINITY, (Number)res_);
    }
    @Test
    public void processEl174Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("-1e123456789123456789123",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Double.class, res_.getClass());
        assertEq(Double.NEGATIVE_INFINITY, (Number)res_);
    }
    @Test
    public void processEl75Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("'\\u9FCB'",0, context_);
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq((char)40907, ((Character)res_).charValue());
    }
    @Test
    public void processEl76Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("\"\\u9FCB\"",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("\u9fcb", (String)res_);
    }
    @Test
    public void processEl177Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("\"\\u9fcb\"",0, context_);
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("\u9fcb", (String)res_);
    }
    @Test(expected=NoSuchDeclaredMethodException.class)
    public void processEl1FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        ElUtil.processEl("getOverridenOne($null)",0, context_);
    }

    @Test(expected=RuntimeClassNotFoundException.class)
    public void processEl2FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$class(\"Object\",$null)",0, context_);
    }

    @Ignore
    @Test(expected=NoSuchDeclaredMethodException.class)
    public void processEl3FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+MY_IMPL_HAT+".ovTwo($new "+MY_IMPL+"())",0, context_);
    }


    @Test(expected=InvokeException.class)
    public void processEl4FailTest() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("arg", lv_);
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        addBean(context_,new Composite(), COMPOSITE);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processEl("setPrivateInt(arg;.)",0, context_);
    }

//    @Ignore
//    @Test(expected=IllegalClassConstructorException.class)
//    public void processEl5FailTest() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        addBean(context_,new Composite());
//        ElUtil.processEl("$new "+ENUM+"()",0, context_);
//    }

    @Test(expected=AbstractClassConstructorException.class)
    public void processEl6FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$new java.lang.Number()",0, context_);
    }

    @Test(expected=InvokeException.class)
    public void processEl7FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$new "+ARR_INT+"(-1i)",0, context_);
    }

    @Test(expected=InvokeException.class)
    public void processEl8FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$new "+ARR_INTEGER+"(-1i)",0, context_);
    }


    @Test(expected=StaticAccessException.class)
    public void processEl9FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        CustLgNames cust_ = (CustLgNames) context_.getStandards();
        addBeanClassName(context_,cust_.getAliasComposite());
        ElUtil.processEl("integer",0, context_);
    }

//    @Ignore
//    @Test(expected=StaticAccessException.class)
//    public void processEl10FailTest() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        addBeanClass(context_, InternsClasses.class);
//        ElUtil.processEl("$new InternStandard()",0, context_);
//    }

    @Test(expected=NullGlobalObjectException.class)
    public void processEl11FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("MAX_VALUE",0, context_);
    }
    
    @Test(expected=StaticAccessException.class)
    public void processEl12FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+COMPOSITE_HAT+".integer",0, context_);
    }

    @Test(expected=NoSuchDeclaredFieldException.class)
    public void processEl13FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+COMPOSITE_HAT+".int$$eger",0, context_);
    }

    @Test(expected=InvokeException.class)
    public void processEl14FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+STRANGE_INIT_HAT+".NOT_READ",0, context_);
    }

    @Test(expected=InvokeException.class)
    public void processEl15FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+STRANGE_INIT_HAT+".fail()",0, context_);
    }

    @Ignore
    @Test(expected=ErrorCausingException.class)
    public void processEl16FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$new "+STRANGE_INIT+"()",0, context_);
    }

    @Test(expected=InvokeException.class)
    public void processEl17FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+FAIL_METHODS_HAT+".fail()",0, context_);
    }

    @Test(expected=InvokeException.class)
    public void processEl18FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$new "+FAIL_METHODS+"()",0, context_);
    }

    @Test(expected=NoSuchDeclaredMethodException.class)
    public void processEl19FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_, COMPOSITE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(Long.class.getName());
        localVars_.put("arg", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processEl("get(arg;.)",0, context_);
    }

    @Test(expected=StaticAccessException.class)
    public void processEl20FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        ElUtil.processEl("$static$"+COMPOSITE_HAT+".getInteger()",0, context_);
    }

    @Ignore
    @Test(expected=SettingMemberException.class)
    public void processEl21FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1l);
        lv_.setClassName(Long.class.getName());
        localVars_.put("arg", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processEl("$static$"+FAIL_METHODS_HAT+".fail().arg;.",0, context_);
    }

    @Ignore
    @Test(expected=SettingMemberException.class)
    public void processEl22FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setElement(1l);
        lv_.setClassName(Long.class.getName());
        localVars_.put("arg", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        ElUtil.processEl("$static$"+FAIL_METHODS_HAT+".fail().arg;",0, context_);
    }

    @Test(expected=SettingMemberException.class)
    public void processEl23FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setElement(1l);
        lv_.setClassName(Long.class.getName());
        localVars_.put("arg", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        ElUtil.processEl("$static$"+FAIL_METHODS_HAT+".fail().arg;;",0, context_);
    }

    @Test(expected=VarargException.class)
    public void processEl24FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "$firstopt(6)*(7+8)";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=VarargException.class)
    public void processEl25FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format(\"6\",$vararg(\"6\"))";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=RuntimeClassNotFoundException.class)
    public void processEl26FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(\"6\"),\"6\")";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=VarargException.class)
    public void processEl27FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "$vararg(\"java.lang.Object\")*(7+8)";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=NotStringException.class)
    public void processEl28FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($vararg(6),\"6\")";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=VarargException.class)
    public void processEl29FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "\"\".format($firstopt(6),\"6\")";
        ElUtil.processEl(el_, 0, context_);
    }
    
    @Test(expected=BadExpressionLanguageException.class)
    public void processEl30FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl31FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        String el_ = "get(,)";
        ElUtil.processEl(el_, 0, context_);
    }

    @Test(expected=BadNumberValuesException.class)
    public void processEl32FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!=2!=3";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadNumberValuesException.class)
    public void processEl33FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1<2<3";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadNumberValuesException.class)
    public void processEl34FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "(3,4)";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl35FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1< ";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl36FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1<";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void processEl37FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl38FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "1!=";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void processEl39FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "!";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=EmptyPartException.class)
    public void processEl40FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "-";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void processEl42FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$true!$false";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadNumberValuesException.class)
    public void processEl43FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "$static$"+FAIL_METHODS_HAT+".(fail())";
        ElUtil.processEl(el_, 0, conf_);
    }

    @Test(expected=BadExpressionLanguageException.class)
    public void processEl44FailTest() {
        ContextEl conf_ = contextEl();
        addImportingPage(conf_);
        String el_ = "var;.[0i,1i]";
        ElUtil.processEl(el_, 0, conf_);
    }
    @Test
    public void processAffect1Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(0);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.", "1i", "=",context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, lv_.getClassName());
        assertEq(1, (Number)lv_.getElement());
    }

    @Test
    public void processAffect2Test() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("","","","v;.integer", "12i", "=",context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(12, c_.getInteger());
    }

    @Test
    public void processAffect3Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i]", "12i", "=",context_);
        assertEq(12, (Number) in_[0].getInstance());
    }

    @Test
    public void processAffect4Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i][0i]", "12i", "=",context_);
        assertEq(12, (Number) ((Struct[])in_[0].getInstance())[0].getInstance());
    }

    @Test
    public void processAffect5Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.", "1i", "+=",context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, lv_.getClassName());
        assertEq(2, (Number)lv_.getElement());
    }

    @Test
    public void processAffect6Test() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("","","","v;.integer", "12i", "-=",context_);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(-12, c_.getInteger());
    }

    @Test
    public void processAffect7Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] in_ = new Struct[1];
        in_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(in_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i]", "12i", "-=",context_);
        assertEq(-12, (Number) in_[0].getInstance());
    }

    @Test
    public void processAffect8Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i][0i]", "12i", "-=",context_);
        assertEq(-12, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
    }

    @Test
    public void processAffect9Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i][0i]", "1b", "++",context_);
        assertEq(1, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
    }

    @Test
    public void processAffect10Test() {
        ContextEl context_ = contextEl();
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
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i][0i]", "1b", "--",context_);
        assertEq(-1, (Number)((Struct[])in_[0].getInstance())[0].getInstance());
    }

//    @Ignore
//    @Test
//    public void processAffect11Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        FieldClass c_ = new FieldClass(8);
//        lv_.setElement(c_);
//        lv_.setClassName(FieldClass.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        ElUtil.processAffect("","","","v;.field", "12i", "=",context_);
//        assertEq(12, c_.getField());
//    }
//    @Ignore
//    @Test
//    public void processAffect12Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        FieldClass c_ = new FieldClass(8);
//        addBean(context_, c_);
//        ElUtil.processAffect("","","","field", "12i", "=",context_);
//        assertEq(12, c_.getField());
//    }


//    @Ignore
//    @Test
//    public void processAffect13Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        FieldFieldClass c_ = new FieldFieldClass(new FieldClass(8));
//        addBean(context_, c_);
//        ElUtil.processAffect("","","","field.field", "12i", "=",context_);
//        assertEq(12, c_.getField().getField());
//    }

    @Test
    public void processAffect14Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new StdStruct(new ArrayContainer(),ArrayContainer.class.getName());
        lv_.setStruct(new ArrayStruct(c_, "["+ArrayContainer.class.getName()));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i].array[0i]", "1i", "=",context_);
        assertEq(1,((ArrayContainer)c_[0].getInstance()).getArray()[0]);
    }

    @Test
    public void processAffect15Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new StdStruct(new ArrayContainer(),ArrayContainer.class.getName());
        lv_.setStruct(new ArrayStruct(c_, "["+ArrayContainer.class.getName()));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i].getArray()[0i]", "1i", "=",context_);
        assertEq(1,((ArrayContainer)c_[0].getInstance()).getArray()[0]);
    }

    @Test
    public void processAffect16Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new StdStruct(new ArrayContainer(),ArrayContainer.class.getName());
        lv_.setStruct(new ArrayStruct(c_, "["+ArrayContainer.class.getName()));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i].getCompo()[0i].getArray()[0i]", "1i", "=",context_);
        assertEq(1, ((ArrayContainer)c_[0].getInstance()).getCompo()[0].getArray()[0]);
    }

//    @Ignore
//    @Test
//    public void processAffect17Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        Composite c_ = new Composite("cont");
//        lv_.setElement(c_);
//        lv_.setClassName(Composite.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        assertNotNull(c_.getStrings());
//        ElUtil.processAffect("","","","v;.strings", "$null", "=",context_);
//        assertEq(COMPOSITE, lv_.getClassName());
//        assertNull(c_.getStrings());
//    }
//
//    @Ignore
//    @Test
//    public void processAffect18Test() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        Composite c_ = new Composite();
//        lv_.setElement(c_);
//        lv_.setClassName(Composite.class.getName());
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement(new StringList("cont"));
//        lv_.setClassName(StringList.class.getName());
//        localVars_.put("v2", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        assertNull(c_.getStrings());
//        ElUtil.processAffect("","","","v;.strings", "v2;.", "=",context_);
//        assertEq(1, c_.getStrings().size());
//        assertEq("cont", c_.getStrings().first());
//    }

    @Test
    public void processAffect19Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer c_ = new ArrayContainer();
        lv_.setStruct(new StdStruct(c_,ArrayContainer.class.getName()));
        lv_.setClassName(ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.getArray()[0i]", "1i", "=",context_);
        assertEq(1, c_.getArray()[0]);
    }

    @Test
    public void processAffect20Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer c_ = new ArrayContainer();
        lv_.setStruct(new StdStruct(c_,ArrayContainer.class.getName()));
        lv_.setClassName(ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.getCompo()[0i].getArray()[0i]", "1i", "=",context_);
        assertEq(1, c_.getCompo()[0].getArray()[0]);
    }

    @Test
    public void processAffect21Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field static='' access='"+PUBLIC_ACCESS+"' name='inst' class='"+Integer.class.getName()+"'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v", lv_);
        cont_.getLastPage().getLocalVars().putAllMap(localVars_);
        Struct arg_;
        Object res_;
        ElUtil.processAffect("","","","$classchoice$pkg$Ex$$inst;;;", "2i", "=",cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = arg_.getInstance();
        assertSame(Integer.class, res_.getClass());
        assertEq(2,(Number) res_);
    }

    @Test
    public void processAffect22Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field static='' access='"+PUBLIC_ACCESS+"' name='inst' class='"+Integer.class.getName()+"'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v", lv_);
        cont_.getLastPage().getLocalVars().putAllMap(localVars_);
        Struct arg_;
        Object res_;
        ElUtil.processAffect("","","","$classchoice$pkg$Ex$$inst;;;", "2i", "=",cont_);
        ElUtil.processAffect("","","","$classchoice$pkg$Ex$$inst;;;", "v;.", "=",cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = arg_.getInstance();
        assertNull(res_);
    }

    @Test
    public void processAffect23Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(new StringStruct("add "));
        lv_.setClassName(context_.getStandards().getAliasString());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.", "1i", "+=",context_);
        assertEq(context_.getStandards().getAliasString(), lv_.getClassName());
        assertEq("add 1", (String)lv_.getElement());
    }


    @Test
    public void processAffect24Test() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] arr_ = new Struct[1];
        arr_[0] = new StringStruct("add ");
        String arrayType_ = PrimitiveTypeUtil.getPrettyArrayType(context_.getStandards().getAliasString());
        lv_.setStruct(new ArrayStruct(arr_, arrayType_));
        lv_.setClassName(arrayType_);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i]", "1i", "+=",context_);
        assertEq(arrayType_, lv_.getClassName());
        assertEq("add 1",(String)((Struct[]) lv_.getStruct().getInstance())[0].getInstance());
    }

    @Test
    public void processAffect25Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field static='' access='"+PUBLIC_ACCESS+"' name='inst' class='[$int' value='$new [$int(1i)'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='[$int'>\n";
        xml_ += "<return expression='inst;;;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        Struct arg_;
        Object res_;
        ElUtil.processAffect("","","","$static$pkg$Ex.exmeth()[0i]", "2i", "=",cont_);
        arg_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "inst"));
        res_ = arg_.getInstance();
        assertEq(1,((Struct[])res_).length);
        assertEq(2,(Number)((Struct[])res_)[0].getInstance());
    }
//    @Ignore
//    @Test(expected=FinalMemberException.class)
//    public void processAffect1FailTest() {
//        ContextEl context_ = contextEl();
//        addImportingPage(context_);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        FinalFieldClass c_ = new FinalFieldClass(8);
//        lv_.setElement(c_);
//        lv_.setClassName(FinalFieldClass.class.getName());
//        localVars_.put("v", lv_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        ElUtil.processAffect("","","","v;.field", "12i", "=",context_);
//    }

    @Test(expected=SettingMemberException.class)
    public void processAffect2FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getParameters().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.;", "12i", "=",context_);
    }
    @Test(expected=DynamicCastClassException.class)
    public void processAffect3FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] c_ = new Struct[1];
        c_[0] = new IntStruct(0);
        lv_.setStruct(new ArrayStruct(c_, ARR_INT));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i]", "\"12i\"", "=",context_);
    }

    @Test(expected=DynamicCastClassException.class)
    public void processAffect4FailTest() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("","","","v;.integer", "\"12i\"", "=",context_);
    }

    @Test(expected=DynamicCastClassException.class)
    public void processAffect5FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.", "\"12i\"", "=",context_);
    }

    @Test(expected=InvokeException.class)
    public void processAffect6FailTest() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setStruct(new StdStruct(c_, custLgNames_.getAliasComposite()));
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v2", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("","","","v;.integer", "v2;.", "=",context_);
    }

    @Test(expected=InvokeException.class)
    public void processAffect7FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer[] c_ = new ArrayContainer[1];
        c_[0] = new ArrayContainer();
        lv_.setStruct(new StdStruct(c_, "["+ArrayContainer.class.getName()));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v2", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i].getCompo()[0i].getArray()[0i]", "v2;.", "=",context_);
    }

    @Test(expected=InvokeException.class)
    public void processAffect8FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v2", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.", "v2;.", "=",context_);
    }

    @Test(expected=SettingMemberException.class)
    public void processAffect9FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        addBean(context_, new BeanOne(), ALIAS_BEAN_ONE);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v2", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","$this", "$null", "=",context_);
    }

    @Test(expected=StaticAccessException.class)
    public void processAffect10FailTest() {
        ContextEl context_ = contextEl();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v2", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.", "$this", "=",context_);
    }

    @Test(expected=InvokeException.class)
    public void processAffect11FailTest() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<field static='' access='"+PUBLIC_ACCESS+"' name='inst' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+$class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = contextEl(files_);
        addImportingPage(cont_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setStruct(null);
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v", lv_);
        cont_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","$classchoice$pkg$Ex$$inst;;;", "v;.", "=",cont_);
    }

    @Test(expected=InvokeException.class)
    public void processAffect12FailTest() {
        ContextEl context_ = contextEl();
        CustLgNames custLgNames_ = (CustLgNames) context_.getStandards();
        String stringType_ = custLgNames_.getAliasString();
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Struct[] exp_ = new Struct[1];
        exp_[0] = NullStruct.NULL_VALUE;
        lv_.setStruct(new ArrayStruct(exp_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
        lv_.setClassName("["+Object.class.getName());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(Integer.class.getName());
        localVars_.put("v2", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("","","","v;.[0i]", "v2;.", "=",context_);
    }

    private static void addImportingPage(ContextEl _conf) {
        _conf.addPage(new PageEl());
    }
    private static void addBean(ContextEl _conf, Object _bean, String _beanClass) {
        _conf.getLastPage().setGlobalArgumentStruct(StdStruct.wrapStd(_bean, _beanClass));
        _conf.getLastPage().setGlobalClass(_beanClass);
    }

    private static void addBeanClassName(ContextEl _conf, String _bean) {
        _conf.getLastPage().setGlobalClass(_bean);
    }

    private ContextEl contextEl() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        Classes.validateAll(files_, cont_);
        return cont_;
    }

    private ContextEl contextEl(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes();
        cont_.setClasses(classes_);
        InitializationLgNames.initAdvStandards(cont_);
        Classes.validateAll(_files, cont_);
        return cont_;
    }
}
