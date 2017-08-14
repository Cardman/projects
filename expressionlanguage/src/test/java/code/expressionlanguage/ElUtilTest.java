package code.expressionlanguage;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Ignore;
import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.classes.AbstractBean;
import code.expressionlanguage.classes.ArrayContainer;
import code.expressionlanguage.classes.BeanOne;
import code.expressionlanguage.classes.BeanSeven;
import code.expressionlanguage.classes.Composite;
import code.expressionlanguage.classes.EnumNumber;
import code.expressionlanguage.classes.FieldClass;
import code.expressionlanguage.classes.FieldFieldClass;
import code.expressionlanguage.classes.FinalFieldClass;
import code.expressionlanguage.classes.IOne;
import code.expressionlanguage.classes.InheritedComposite;
import code.expressionlanguage.classes.InternsClasses;
import code.expressionlanguage.classes.MyImpl;
import code.expressionlanguage.classes.InternsClasses.InternStandard;
import code.expressionlanguage.classes.InternsClasses.InternStaticStandard;
import code.expressionlanguage.classes.InternsClasses.InternStandardTwo.InternStandardOne;
import code.expressionlanguage.classes.InternsClasses.InternStandardTwo.InternStandardThree;
import code.expressionlanguage.classes.InternsClasses.InternStaticStandard.InternStaticStandardFour;
import code.expressionlanguage.classes.InternsClasses.InternStaticStandard.InternStaticStandardThree;
import code.expressionlanguage.exceptions.AbstractClassConstructorException;
import code.expressionlanguage.exceptions.AmbiguousChoiceCallingException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.FinalMemberException;
import code.expressionlanguage.exceptions.IllegalClassConstructorException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.NullGlobalObjectException;
import code.expressionlanguage.exceptions.SettingMemberException;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.RuntimeClassNotFoundException;

@SuppressWarnings("static-method")
public class ElUtilTest {

    private static final String ARR_INT = "[$int";
    private static final String ARR_LONG = "[$long";
    private static final String ARR_ARR_INT = "[[$int";
    private static final String ARR_INTEGER = "[java.lang.Integer";
    private static final String ARR_ARR_INTEGER = "[[java.lang.Integer";
    private static final String STRING_LIST = StringList.class.getName();
    private static final String ENUM = EnumNumber.class.getName();
    private static final String INTERNS = InternsClasses.class.getName();
    private static final String INHERITED_COMPOSITE = InheritedComposite.class.getName();
    private static final String ABSTRACT = AbstractBean.class.getName();
    private static final String COMPOSITE = Composite.class.getName();
    private static final String COMPOSITE_HAT = StringList.replace(COMPOSITE, ".", "^");
    private static final String IONE = IOne.class.getName();
    private static final String MY_IMPL = MyImpl.class.getName();
    private static final String MY_IMPL_HAT = StringList.replace(MY_IMPL, ".", "^");
    private static final String PUBLIC_ACCESS = "PUBLIC";

    @Test(timeout=1000)
    public void processEl1Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        //        ElUtil.processEl("instanceof(\"java.lang.Number\",5)",0, context_);
//        Argument arg_ = ElUtil.processEl("class(\"java.lang.Number\",5)",0, context_);
        Argument arg_ = ElUtil.processEl("^class(\"java.lang.Number\",5)",0, context_);
        assertEq(Number.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(5L, (Number)res_);
    }
    @Test//(timeout=1000)
    public void processEl2Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Long.MAX_VALUE",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(Long.MAX_VALUE, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl3Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("(1+2)*3",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(9L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl4Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1--1",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(2L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl5Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+2*3",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(7L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl6Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("--1",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(1L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl7Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(-8l)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl8Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(8l)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl9Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("composite.integer",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl10Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("40908c",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_CHAR, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq((char)40908, ((Character)res_).charValue());
    }

    @Test(timeout=1000)
    public void processEl11Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("'\\u9fcb'",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_CHAR, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq((char)40907, ((Character)res_).charValue());
    }

    @Test(timeout=1000)
    public void processEl12Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("'\\\\'",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_CHAR, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('\\', ((Character)res_).charValue());
    }

    @Test(timeout=1000)
    public void processEl13Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("'\\''",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_CHAR, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('\'', ((Character)res_).charValue());
    }

    @Test(timeout=1000)
    public void processEl14Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("'\"'",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_CHAR, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('"', ((Character)res_).charValue());
    }

    @Test(timeout=1000)
    public void processEl15Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("'\n'",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_CHAR, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Character.class, res_.getClass());
        assertEq('\n', ((Character)res_).charValue());
    }

    @Test(timeout=1000)
    public void processEl16Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Composite compos_ = new Composite();
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(compos_);
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;.integer",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl17Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Composite compos_ = new Composite();
        StringMap<LoopVariable> localVars_ = new StringMap<LoopVariable>();
        LoopVariable lv_ = new LoopVariable();
        lv_.setElement(compos_);
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;integer",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0,(Number) res_);
    }

    @Test(timeout=1000)
    public void processEl18Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("instanceof(\"java.lang.Number\",5)",0, context_);
        Argument arg_ = ElUtil.processEl("^instanceof(\"java.lang.Number\",5)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }

    @Test(timeout=1000)
    public void processEl19Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("instanceof(\"java.lang.Number\",'5')",0, context_);
        Argument arg_ = ElUtil.processEl("^instanceof(\"java.lang.Number\",'5')",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, res_);
    }

    @Test(timeout=1000)
    public void processEl20Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("!instanceof(\"java.lang.Number\",'5')",0, context_);
        Argument arg_ = ElUtil.processEl("!^instanceof(\"java.lang.Number\",'5')",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }


    @Test(timeout=1000)
    public void processEl21Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+1=2",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }

    @Test(timeout=1000)
    public void processEl22Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+1!=2",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, res_);
    }

    @Test(timeout=1000)
    public void processEl23Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+1=2&1+0=8",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, res_);
    }

    @Test(timeout=1000)
    public void processEl24Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+1!=2|1+7=8",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }

    @Test(timeout=1000)
    public void processEl25Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+1=2&(1+0=8|3*3=9)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }
    @Test(timeout=1000)
    public void processEl26Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("1+1=2|1+6=8&1=1",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }

    @Test(timeout=1000)
    public void processEl27Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        //CustList worked but is generic so it will take args TODO
        Argument arg_ = ElUtil.processEl("^new."+STRING_LIST+"()",0, context_);
        assertEq(StringList.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(StringList.class, res_.getClass());
        assertEq(new StringList(), (StringList)res_);
    }

    @Test(timeout=1000)
    public void processEl28Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("composite.integer",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl29Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        //1+1=2&1/0>8 throws an exception
        Argument arg_ = ElUtil.processEl("1+1=2|1/0>8",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }

    @Test(timeout=1000)
    public void processEl30Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanSeven b_ = new BeanSeven();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("arrayInt[1i]",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(3, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl31Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(-8i)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl32Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(8i)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }


    @Test(timeout=1000)
    public void processEl33Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(-8I)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl34Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(8I)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(8, (Number)res_);
    }


    @Test(timeout=1000)
    public void processEl35Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(-8L)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl36Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(8L)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(8L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl37Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("getOverridenTwo(null)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("one", res_);
    }

    @Test(timeout=1000)
    public void processEl38Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("getOverridenTwo(class(\"java.lang.Object\",null))",0, context_);
        Argument arg_ = ElUtil.processEl("getOverridenTwo(^class(\"java.lang.Object\",null))",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("two", res_);
    }

    @Test(timeout=1000)
    public void processEl39Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("instanceof(\"java.lang.Object\",null)",0, context_);
        Argument arg_ = ElUtil.processEl("^instanceof(\"java.lang.Object\",null)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, res_);
    }

    @Test(timeout=1000)
    public void processEl40Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        b_.getOverridenThree(new Long("1"));
        Argument arg_ = ElUtil.processEl("getOverridenThree(1)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Long", res_);
    }

    @Test(timeout=1000)
    public void processEl41Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1l)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("long", res_);
    }

    @Test(timeout=1000)
    public void processEl42Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("Double", res_);
    }

    @Test(timeout=1000)
    public void processEl43Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0d)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", res_);
    }

    @Test(timeout=1000)
    public void processEl44Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0F)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", res_);
    }

    @Test(timeout=1000)
    public void processEl45Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("getOverridenThree(1.0f)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("double", res_);
    }

    @Test(timeout=1000)
    public void processEl46Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
//        Argument arg_ = ElUtil.processEl("class(\"expressionlanguage.classes.IOne\",new.expressionlanguage.classes.MyImpl()).testOne()",0, context_);
        Argument arg_ = ElUtil.processEl("^class(\""+IONE+"\",^new."+MY_IMPL+"()).testOne()",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("one", res_);
    }

    @Test(timeout=1000)
    public void processEl47Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("static^"+MY_IMPL_HAT+".ovOne(^new."+MY_IMPL+"())",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("three", res_);
    }

    @Test(timeout=1000)
    public void processEl48Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"().^new.InternStandard()",0, context_);
        assertEq(InternStandard.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStandard.class, res_.getClass());
    }

    @Test(timeout=1000)
    public void processEl49Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"$InternStaticStandard()",0, context_);
        assertEq(InternStaticStandard.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStaticStandard.class, res_.getClass());
    }

    @Test(timeout=1000)
    public void processEl50Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"().^new.InternStandardTwo().^new.InternStandardOne()",0, context_);
        assertEq(InternStandardOne.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStandardOne.class, res_.getClass());
    }

    @Test(timeout=1000)
    public void processEl51Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"$InternStaticStandard$InternStaticStandardThree()",0, context_);
        assertEq(InternStaticStandardThree.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStaticStandardThree.class, res_.getClass());
    }

    @Test(timeout=1000)
    public void processEl52Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"$InternStaticStandard().^new.InternStaticStandardFour()",0, context_);
        assertEq(InternStaticStandardFour.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStaticStandardFour.class, res_.getClass());
    }

    @Test(timeout=1000)
    public void processEl53Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"().^new.InternStandardTwo().^new.InternStandardThree(1i)",0, context_);
        assertEq(InternStandardThree.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStandardThree.class, res_.getClass());
    }

    @Test(timeout=1000)
    public void processEl54Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"().^new.InternStandardTwo().^new.InternStandardThree(1i).getPrivateInfo()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl55Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        InternsClasses b_ = new InternsClasses();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new.InternStandardTwo().^new.InternStandardThree(1i).getPrivateInfo()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl56Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        InternsClasses b_ = new InternsClasses();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+INHERITED_COMPOSITE+"().getPrivateInt()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl57Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite composite_ = new Composite();
        composite_.setInteger(6);
        composite_.setPrivateInt(5);
//        InternsClasses b_ = new InternsClasses();
        addBean(context_, composite_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(7);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("summum(v;.)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(13, (Number)res_);
//        Argument arg_ = ElUtil.processEl("static^expressionlanguage^classes^MyImpl.ovOne(null)",0, context_);
//        Argument arg_ = ElUtil.processEl("new.expressionlanguage.classes.MyImpl()",0, context_);
        //
    }

    @Test(timeout=1000)
    public void processEl58Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite composite_ = new Composite();
        composite_.setInteger(6);
        composite_.setPrivateInt(5);
//        InternsClasses b_ = new InternsClasses();
        addBean(context_, composite_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(7);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setElement("varargs:{0} {1} {2}");
        lv_.setClassName(String.class.getName());
        localVars_.put("f", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("varargs:7 8 7", res_);
//        System.out.println(ElUtil.processElObj("varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)", 0, context_));
    }

    @Test(timeout=1000)
    public void processEl59Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite composite_ = new Composite();
        composite_.setInteger(6);
        composite_.setPrivateInt(5);
//        InternsClasses b_ = new InternsClasses();
        addBean(context_, composite_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(7);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setElement("varargs:{0} {1} {2}");
        lv_.setClassName(String.class.getName());
        localVars_.put("f", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("varArgsParam(?java.lang.Object,f;.)",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("varargs:{0} {1} {2}", res_);
//        System.out.println(ElUtil.processElObj("varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)", 0, context_));
    }

    @Test(timeout=1000)
    public void processEl60Test() {
        Composite composite_ = new Composite();
        composite_.setInteger(6);
        composite_.setPrivateInt(5);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(7);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_LONG);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_LONG);
        localVars_.put("2", lv_);
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        addBean(context_, composite_);
        Argument arg_ = ElUtil.processEl("varArgsNoParam(?"+PrimitiveTypeUtil.PRIM_LONG+",v;.?,2;.)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(15L, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl61Test() {
        Composite composite_ = new Composite();
        composite_.setInteger(6);
        composite_.setPrivateInt(5);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        long[] arr_ = new long[7];
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(arr_);
        lv_.setClassName(long[].class.getName());
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        arr_ = new long[8];
        lv_.setElement(arr_);
        lv_.setClassName(long[].class.getName());
        localVars_.put("2", lv_);
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        addBean(context_, composite_);
        Argument arg_ = ElUtil.processEl("varArgsArrays(?"+ARR_LONG+",v;.?,2;.)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(15L, (Number)res_);
    }

    @Test(timeout=1000)
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
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^new."+COMPOSITE+"(?java.lang.String,v;.?,2;.).getStrings()",0, context_);
        assertEq(StringList.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(StringList.class, res_.getClass());
        assertEq(new StringList("bonjour","tout"), (StringList)res_);
    }

    @Test(timeout=1000)
    public void processEl63Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        Argument arg_ = ElUtil.processEl("^new."+ARR_INT+"(1i)",0, context_);
        assertEq(ARR_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(int[].class, res_.getClass());
        assertEq(1, ((int[])res_).length);
        assertEq(0, ((int[])res_)[0]);
    }

    @Test(timeout=1000)
    public void processEl64Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        Argument arg_ = ElUtil.processEl("^new."+ARR_ARR_INT+"(1i)",0, context_);
        assertEq(ARR_ARR_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(int[][].class, res_.getClass());
        assertEq(1, ((int[][])res_).length);
        assertNull( ((int[][])res_)[0]);
    }

    @Test//(timeout=1000)
    public void processEl65Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        Argument arg_ = ElUtil.processEl("^new."+ARR_INTEGER+"(2i)",0, context_);
        assertEq(ARR_INTEGER, arg_.getArgClassName());
        Object res_ = arg_.getObject();
//        assertSame(Integer[].class, res_.getClass());
//        assertEq(2, ((Integer[])res_).length);
//        assertNull( ((Integer[])res_)[0]);
//        assertNull( ((Integer[])res_)[1]);
//        assertSame(Struct[].class, res_.getClass());
        assertSame(Integer[].class, res_.getClass());
        assertEq(2, ((Integer[])res_).length);
        assertNull( ((Integer[])res_)[0]);
        assertNull( ((Integer[])res_)[1]);
        assertTrue(arg_.getStruct().isJavaObject());
    }

    @Test(timeout=1000)
    public void processEl66Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        Argument arg_ = ElUtil.processEl("^new."+ARR_ARR_INTEGER+"(2i)",0, context_);
        assertEq(ARR_ARR_INTEGER, arg_.getArgClassName());
        Object res_ = arg_.getObject();
//        assertSame(Integer[][].class, res_.getClass());
//        assertSame(Struct[][].class, res_.getClass());
        assertSame(Integer[][].class, res_.getClass());
        assertEq(2, ((Integer[][])res_).length);
        assertNull(((Integer[][])res_)[0]);
        assertNull(((Integer[][])res_)[1]);
        assertTrue(arg_.getStruct().isJavaObject());
//        assertEq(2, ((Struct[][])res_).length);
//        assertEq((Object)null, ((Struct[][])res_)[0]);
//        assertEq((Object)null, ((Struct[][])res_)[1]);
    }

    @Test(timeout=1000)
    public void processEl67Test() {
        ContextEl context_ = new ContextEl();
        Composite composite_ = new Composite();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,composite_);
        assertEq(0, composite_.getPrivateInt());
        ElUtil.processEl("setPrivateInt(2i)",0, context_);
        assertEq(2, composite_.getPrivateInt());
    }

    @Test
    public void processEl68Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[] ints_ = new int[2];
        lv_.setStruct(new Struct(ints_));
        lv_.setClassName(ARR_INT);
        localVars_.put("arrays", lv_);
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("arrays;.[0i]",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }


    @Test
    public void processEl69Test() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[][] ints_ = new int[2][2];
        lv_.setStruct(new Struct(ints_));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("arrays", lv_);
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("arrays;.[0i].length",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(2, (Number)res_);
    }

    @Test
    public void processEl70Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new InternsClasses());
        Argument arg_ = ElUtil.processEl("!!false",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(false, res_);
    }

    @Test
    public void processEl71Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBeanClass(context_,Byte.class);
        Argument arg_ = ElUtil.processEl("MAX_VALUE",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BYTE, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Byte.class, res_.getClass());
        assertEq((byte)127, (Number)res_);
    }

    @Test
    public void processEl72Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
//        addBeanClass(context_,Byte.class);
        Argument arg_ = ElUtil.processEl("static^java^lang^Byte.MAX_VALUE",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BYTE, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Byte.class, res_.getClass());
        assertEq((byte)127, (Number)res_);
    }

    @Test
    public void processEl73Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
//        addBeanClass(context_,Byte.class);
        Argument arg_ = ElUtil.processEl("^new."+STRING_LIST+"()",0, context_);
        assertEq(StringList.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(StringList.class, res_.getClass());
        assertEq(new StringList(), (StringList)res_);
    }

    @Test
    public void processEl74Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBeanClass(context_,StringList.class);
        Argument arg_ = ElUtil.processEl("isNumber(\"8\")",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_BOOLEAN, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Boolean.class, res_.getClass());
        assertEq(true, res_);
    }

    @Test
    public void processEl75Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBeanClass(context_,StringList.class);
        Argument arg_ = ElUtil.processEl("getMetaCharacters().size()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(3, (Number)res_);
    }

    @Test
    public void processEl76Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("^new."+INTERNS+"$InternStaticStandard()",0, context_);
        assertEq(InternStaticStandard.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStaticStandard.class, res_.getClass());
    }

    @Test
    public void processEl77Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\\\"+\"World\").length()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
    }

    @Test
    public void processEl78Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\"\"+\"World\").length()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
    }

    @Test
    public void processEl79Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\\\"+'\\\\').length()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(7, (Number)res_);
    }
    
    @Test
    public void processEl80Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(\"Hello\\\"\"+'\\'').length()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(7, (Number)res_);
    }

    @Test
    public void processEl81Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite composite_ = new Composite();
        composite_.setInteger(6);
        composite_.setPrivateInt(5);
//        InternsClasses b_ = new InternsClasses();
        addBean(context_, composite_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(7);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("2", lv_);
        lv_ = new LocalVariable();
        lv_.setElement("varargs:{0} {1} {2}");
        lv_.setClassName(String.class.getName());
        localVars_.put("f", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        Argument arg_ = ElUtil.processEl("varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)",0, context_);
//        assertEq(String.class.getName(), arg_.getArgClassName());
//        Object res_ = arg_.getObject();
//        assertSame(String.class, res_.getClass());
//        assertEq("varargs:7 8 7", res_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
        Argument arg_ = ElUtil.processEl("(varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)+'\\'').length()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(14, (Number)res_);
    }

    @Test
    public void processEl82Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[] i_ = new int[1];
        i_[0] = 8;
        lv_.setStruct(new Struct(i_));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("static^java^lang^Math.abs(v;.[0i]+2)*2",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(20L, (Number)res_);
    }

    @Test
    public void processEl83Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[] i_ = new int[1];
        i_[0] = 8;
        lv_.setStruct(new Struct(i_));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("(v;.[0i]+2)*2",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_LONG, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(20L, (Number)res_);
    }
    
    @Test
    public void processEl84Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;.^new.InternStandard()",0, context_);
        assertEq(InternStandard.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(InternStandard.class, res_.getClass());
    }

    @Test
    public void processEl85Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;.news",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("", res_);
    }


    @Test
    public void processEl86Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("v;.news.length()",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl87Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^boolean(1>0,0i,1i)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl88Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^boolean(1<0,0i,1i)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1, (Number)res_);
    }

    @Test
    public void processEl89Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^boolean(1>0,0i,1i/0i)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test
    public void processEl90Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^boolean(1<0,1i/0i,1i)",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(1,(Number) res_);
    }

    @Test
    public void processEl91Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INT+"(0i).getClass().getName()",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("[I", res_);
    }

    @Test
    public void processEl92Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INTEGER+"(0i).getClass().getName()",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("[Ljava.lang.Integer;", res_);
    }

    @Test
    public void processEl93Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_ARR_INT+"(0i).getClass().getName()",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("[[I", res_);
    }

    @Test
    public void processEl94Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_ARR_INTEGER+"(0i).getClass().getName()",0, context_);
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("[[Ljava.lang.Integer;", res_);
    }

    @Test
    public void processEl95Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(new InternsClasses());
        lv_.setClassName(InternsClasses.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INT+"(1i)[0i]",0, context_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(0, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl96Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INT+"[](2i)", 0, context_);
        assertEq(ARR_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(int[].class, res_.getClass());
        int[] o_ = (int[]) res_;
        assertEq(1, o_.length);
        assertEq(2, o_[0]);
//        ovOne((IOne)new MyImpl(), new MyImpl());
    }

    @Test(timeout=1000)
    public void processEl97Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INT+"[](3i,7i)", 0, context_);
        assertEq(ARR_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(int[].class, res_.getClass());
        int[] o_ = (int[]) res_;
        assertEq(2, o_.length);
        assertEq(3, o_[0]);
        assertEq(7, o_[1]);
//        ovOne((IOne)new MyImpl(), new MyImpl());
    }

    @Test(timeout=1000)
    public void processEl98Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INT+"[]()", 0, context_);
        assertEq(ARR_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(int[].class, res_.getClass());
        int[] o_ = (int[]) res_;
        assertEq(0, o_.length);
    }

    @Test(timeout=1000)
    public void processEl99Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl("^new."+ARR_INTEGER+"[](3i,7i)", 0, context_);
        assertEq(ARR_INTEGER, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer[].class, res_.getClass());
        Integer[] o_ = (Integer[]) res_;
        assertEq(2, o_.length);
        assertEq(3, o_[0].intValue());
        assertEq(7, o_[1].intValue());
    }

    @Test(timeout=1000)
    public void processEl100Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl(" {(1+2)*3+\" hello\"+\" world {every body ;)\"} ", context_, 2 ,'{','}');
        assertEq(String.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(String.class, res_.getClass());
        assertEq("9 hello world {every body ;)", res_);
    }

    @Test(timeout=1000)
    public void processEl101Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        Argument arg_ = ElUtil.processEl(" {(\"hello \"+\"world\").length()} ", context_, 2 ,'{','}');
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(11, (Number)res_);
    }

    @Test(timeout=1000)
    public void processEl102Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+^class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        Classes.validateAll(files_, cont_);
        setupAccessValue(cont_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("static^pkg^Ex.exmeth()", 0, cont_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(9, (Number)res_);
    }


    @Test(timeout=1000)
    public void processEl103Test() {
        String xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='static' name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"' class0='"+PrimitiveTypeUtil.PRIM_INT+"' var0='e'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
        xml_ += "<return expression='1i+^class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)+e;.;'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        Classes.validateAll(files_, cont_);
        setupAccessValue(cont_);
        addImportingPage(cont_);
        Argument arg_ = ElUtil.processEl("static^pkg^Ex.exmeth(6i)", 0, cont_);
        assertEq(PrimitiveTypeUtil.PRIM_INT, arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Integer.class, res_.getClass());
        assertEq(15,(Number) res_);
    }

    @Test(timeout=1000, expected=AmbiguousChoiceCallingException.class)
    public void processEl1FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        ElUtil.processEl("getOverridenOne(null)",0, context_);
    }

    @Test(timeout=1000, expected=RuntimeClassNotFoundException.class)
    public void processEl2FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        ElUtil.processEl("^class(\"Object\",null)",0, context_);
    }

    @Test(timeout=1000, expected=AmbiguousChoiceCallingException.class)
    public void processEl3FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        Composite b_ = new Composite();
        addBean(context_, b_);
        ElUtil.processEl("static^"+MY_IMPL_HAT+".ovTwo(^new."+MY_IMPL+"())",0, context_);
    }


    @Test(expected=UnwrappingException.class)
    public void processEl4FailTest() {
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setClassName(Integer.class.getName());
        localVars_.put("arg", lv_);
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new Composite());
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processEl("setPrivateInt(arg;.)",0, context_);
    }

    @Test(expected=IllegalClassConstructorException.class)
    public void processEl5FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new Composite());
        ElUtil.processEl("^new."+ENUM+"()",0, context_);
    }

    @Test(expected=AbstractClassConstructorException.class)
    public void processEl6FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new Composite());
        ElUtil.processEl("^new."+ABSTRACT+"()",0, context_);
    }

    @Test(expected=NegativeSizeException.class)
    public void processEl7FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new Composite());
        ElUtil.processEl("^new."+ARR_INT+"(-1i)",0, context_);
    }

    @Test(expected=NegativeSizeException.class)
    public void processEl8FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBean(context_,new Composite());
        ElUtil.processEl("^new."+ARR_INTEGER+"(-1i)",0, context_);
    }


    @Test(expected=StaticAccessException.class)
    public void processEl9FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBeanClass(context_,Composite.class);
        ElUtil.processEl("integer",0, context_);
    }

    @Test(expected=StaticAccessException.class)
    public void processEl10FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        addBeanClass(context_, InternsClasses.class);
        ElUtil.processEl("^new.InternStandard()",0, context_);
    }

    @Test(expected=NullGlobalObjectException.class)
    public void processEl11FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        ElUtil.processEl("MAX_VALUE",0, context_);
    }

    @Test(expected=StaticAccessException.class)
    public void processEl12FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        ElUtil.processEl("static^"+COMPOSITE_HAT+".integer",0, context_);
    }

    @Test(expected=NoSuchDeclaredFieldException.class)
    public void processEl13FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        ElUtil.processEl("static^"+COMPOSITE_HAT+".int^^eger",0, context_);
    }

    @Test
    public void processAffect1Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(0);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.", "1i", "=",context_, true);
        assertEq(PrimitiveTypeUtil.PRIM_INT, lv_.getClassName());
        assertEq(1, (Number)lv_.getElement());
    }

    @Test
    public void processAffect2Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setElement(c_);
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("v;.integer", "12i", "=",context_, true);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(12, c_.getInteger());
    }

    @Test
    public void processAffect3Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[] c_ = new int[1];
        lv_.setStruct(new Struct(c_));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i]", "12i", "=",context_, true);
        assertEq(12, c_[0]);
    }

    @Test
    public void processAffect4Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[][] c_ = new int[1][1];
        lv_.setStruct(new Struct(c_));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i][0i]", "12i", "=",context_, true);
        assertEq(12, c_[0][0]);
    }

    @Test
    public void processAffect5Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.", "1i", "+=",context_, true);
        assertEq(PrimitiveTypeUtil.PRIM_INT, lv_.getClassName());
        assertEq(2, (Number)lv_.getElement());
    }

    @Test
    public void processAffect6Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setElement(c_);
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("v;.integer", "12i", "-=",context_, true);
        assertEq(COMPOSITE, lv_.getClassName());
        assertEq(-12, c_.getInteger());
    }

    @Test
    public void processAffect7Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[] c_ = new int[1];
        lv_.setStruct(new Struct(c_));
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i]", "12i", "-=",context_, true);
        assertEq(-12, c_[0]);
    }

    @Test
    public void processAffect8Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[][] c_ = new int[1][1];
        lv_.setStruct(new Struct(c_));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i][0i]", "12i", "-=",context_, true);
        assertEq(-12, c_[0][0]);
    }

    @Test
    public void processAffect9Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[][] c_ = new int[1][1];
        lv_.setStruct(new Struct(c_));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i][0i]", "1b", "++",context_, true);
        assertEq(1, c_[0][0]);
    }

    @Test
    public void processAffect10Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[][] c_ = new int[1][1];
        lv_.setStruct(new Struct(c_));
        lv_.setClassName(ARR_ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i][0i]", "1b", "--",context_, true);
        assertEq(-1, c_[0][0]);
    }

    @Test
    public void processAffect11Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        FieldClass c_ = new FieldClass(8);
        lv_.setElement(c_);
        lv_.setClassName(FieldClass.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.field", "12i", "=",context_, true);
        assertEq(12, c_.getField());
    }
    
    
    @Test
    public void processAffect12Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        FieldClass c_ = new FieldClass(8);
        addBean(context_, c_);
        ElUtil.processAffect("field", "12i", "=",context_, false);
        assertEq(12, c_.getField());
    }


    @Test
    public void processAffect13Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        FieldFieldClass c_ = new FieldFieldClass(new FieldClass(8));
        addBean(context_, c_);
        ElUtil.processAffect("field.field", "12i", "=",context_, false);
        assertEq(12, c_.getField().getField());
    }

    @Test
    public void processAffect14Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer[] c_ = new ArrayContainer[1];
        c_[0] = new ArrayContainer();
        lv_.setStruct(new Struct(c_));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i].array[0i]", "1i", "=",context_,true);
        assertEq(1, c_[0].getArray()[0]);
    }

    @Test
    public void processAffect15Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer[] c_ = new ArrayContainer[1];
        c_[0] = new ArrayContainer();
        lv_.setStruct(new Struct(c_));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i].getArray()[0i]", "1i", "=",context_,true);
        assertEq(1, c_[0].getArray()[0]);
    }

    @Test
    public void processAffect16Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        ArrayContainer[] c_ = new ArrayContainer[1];
        c_[0] = new ArrayContainer();
        lv_.setStruct(new Struct(c_));
        lv_.setClassName("["+ArrayContainer.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i].getCompo()[0i].getArray()[0i]", "1i", "=",context_,true);
        assertEq(1, c_[0].getCompo()[0].getArray()[0]);
    }
    @Test(expected=FinalMemberException.class)
    public void processAffect1FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        FinalFieldClass c_ = new FinalFieldClass(8);
        lv_.setElement(c_);
        lv_.setClassName(FinalFieldClass.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.field", "12i", "=",context_, true);
    }

    @Test(expected=SettingMemberException.class)
    public void processAffect2FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(1);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getParameters().putAllMap(localVars_);
        ElUtil.processAffect("v;.;", "12i", "=",context_, true);
    }
    
    @Test(expected=DynamicCastClassException.class)
    public void processAffect3FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        int[] c_ = new int[1];
        lv_.setElement(c_);
        lv_.setClassName(ARR_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.[0i]", "\"12i\"", "=",context_, true);
    }

    @Test(expected=DynamicCastClassException.class)
    public void processAffect4FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        Composite c_ = new Composite();
        lv_.setElement(c_);
        lv_.setClassName(Composite.class.getName());
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        assertEq(0, c_.getInteger());
        ElUtil.processAffect("v;.integer", "\"12i\"", "=",context_, true);
    }

    @Test(expected=DynamicCastClassException.class)
    public void processAffect5FailTest() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
        LocalVariable lv_ = new LocalVariable();
        lv_.setElement(8);
        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
        localVars_.put("v", lv_);
        context_.getLastPage().getLocalVars().putAllMap(localVars_);
        ElUtil.processAffect("v;.", "\"12i\"", "=",context_, true);
    }

    //FinalFieldClass
//    @Test(timeout=1000)
//    public void processEl2000Test() {
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        BeanOne b_ = new BeanOne();
//        addBean(context_, b_);
//        //        ElUtil.processEl("v;.a",0, context_);
//        //        ElUtil.processEl("v;.a[8]",0, context_);
//        //        ElUtil.processEl("'a'<'t'", 0, context_);
//        //        ElUtil.processEl("'a'>'t'", 0, context_);
//        //        ElUtil.processEl("\"a\"<\"t\"", 0, context_);
//        //        ElUtil.processEl("\"a\">\"t\"", 0, context_);
//        //        System.out.println();
//        //        ElUtil.processEl("'a'<='t'", 0, context_);
//        //        ElUtil.processEl("'a'>='t'", 0, context_);
//        //        ElUtil.processEl("\"a\"<=\"t\"", 0, context_);
//        //        ElUtil.processEl("\"a\">=\"t\"", 0, context_);
//        //        System.out.println();
//        //        ElUtil.processEl("'a'<'a'", 0, context_);
//        //        ElUtil.processEl("'a'>'a'", 0, context_);
//        //        ElUtil.processEl("\"a\"<\"a\"", 0, context_);
//        //        ElUtil.processEl("\"a\">\"a\"", 0, context_);
//        //        ElUtil.processEl("'a'<='a'", 0, context_);
//        //        ElUtil.processEl("'a'>='a'", 0, context_);
//        //        ElUtil.processEl("\"a\"<=\"a\"", 0, context_);
//        //        ElUtil.processEl("\"a\">=\"a\"", 0, context_);
//        System.out.println();
//        //        ElUtil.processEl("\"a\">=\"a\"|\"a\"<\"a\"", 0, context_);
//        //        ElUtil.processEl("new.java.lang.Integer(\"8\")", 0, context_);
//        //        System.out.println(ElUtil.processEl("\"8\".length()", 0, context_));
//        //        int[] ip_ = (int[]) ElUtil.processEl("new.[I(8)", 0, context_);
//        //        System.out.println(ip_.length);
//        //        int[][] iip_ = (int[][]) ElUtil.processEl("new.[[I(8)", 0, context_);
//        //        System.out.println(iip_.length);
//        ////        System.out.println(iip_[0].length);
//        //        Integer[] i_ = (Integer[]) ElUtil.processEl("new.[Ljava.lang.Integer;(8)", 0, context_);
//        //        System.out.println(i_.length);
//        //        Integer[][] ii_ = (Integer[][]) ElUtil.processEl("new.[[Ljava.lang.Integer;(8)", 0, context_);
//        //        System.out.println(ii_.length);
//        //        System.out.println(ElUtil.processEl("static^util^StringList.simpleFormat(?java.lang.Object,\"{0} {1}\",4?,6)", 0, context_));
//        //        System.out.println(ElUtil.processEl("static^util^StringList.simpleFormat(?java.lang.Object,\"{0} {1}\",class(\"[Ljava.lang.Object;\",null))", 0, context_));
//        //        System.out.println(StringList.simpleFormat("{0} {1}", (Object[])null));
////        System.out.println(ElUtil.processElObj("new.expressionlanguage.ElUtilTest()", 0, context_));
////        System.out.println(ElUtil.processElObj("new.expressionlanguage.ElUtilTest().new.Inner()", 0, context_));
////        System.out.println(ElUtil.processElObj("new.expressionlanguage.ElUtilTest$InnerTwo().new.Inner()", 0, context_));
//        //        System.out.println(ElUtil.processEl("class(\"[Ljava.lang.Object;\",null)", 0, context_));
//        //        System.out.println(ii_[0].length);
//        //        System.out.println();
//        //        ElUtil.processEl("abs((concat('a',\"hello\")),15)",0, context_);
//        //        ElUtil.processEl("abs(15,(concat('a',\"hello\")))",0, context_);
//    }
//
//    @Test
//    public void extractObject17Test() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(6);
//        composite_.setPrivateInt(5);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        StringMap<LocalVariable> params_ = new StringMap<LocalVariable>();
//        StringMap<LoopVariable> vars_ = new StringMap<LoopVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(7);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
//        localVars_.put("v", lv_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        addBean(context_, composite_);
////        System.out.println(ElUtil.processEl("new.expressionlanguage.classes.Composite().summum(v;.)", 0, context_));;
////        System.out.println(ElUtil.processEl("new.expressionlanguage.classes.Composite().summum(v;.)", 0, context_));;
//        //            int res_ = (int) ExtractObject.extractObject("summum(v;.)", conf_,localVars_, vars_, params_, composite_);
//        //            assertEq(13, res_);
//    }

//    @Test
//    public void extractObject18Test() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(6);
//        composite_.setPrivateInt(5);
//        StringMap<LocalVariable> localVars_ = new StringMap<LocalVariable>();
//        StringMap<LocalVariable> params_ = new Map<String,LocalVariable>();
//        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(7);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement(8);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
//        localVars_.put("2", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement("varargs:{0} {1} {2}");
//        lv_.setClassName(String.class.getName());
//        localVars_.put("f", lv_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        addBean(context_, composite_);
//        System.out.println(ElUtil.processElObj("varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)", 0, context_));
//        //            String res_ = (String) ExtractObject.extractObject("varArgsParam(?java.lang.Object,f;.,v;.?,2;.,v;.)", conf_,localVars_, vars_, params_, composite_);
//        //            assertEq("varargs:7 8 7", res_);
//    }

//    @Test
//    public void extractObject19Test() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(6);
//        composite_.setPrivateInt(5);
//        Map<String,LocalVariable> localVars_ = new Map<String,LocalVariable>();
//        Map<String,LocalVariable> params_ = new Map<String,LocalVariable>();
//        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(7);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement(8);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_INT);
//        localVars_.put("2", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement("varargs:{0} {1} {2}");
//        lv_.setClassName(String.class.getName());
//        localVars_.put("f", lv_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        addBean(context_, composite_);
//        System.out.println(ElUtil.processElObj("varArgsParam(?java.lang.Object,f;.)", 0, context_));
//        //            String res_ = (String) ExtractObject.extractObject("varArgsParam(?java.lang.Object,f;.)", conf_,localVars_, vars_, params_, composite_);
//        //            assertEq("varargs:{0} {1} {2}", res_);
//    }

//    @Test
//    public void extractObject20Test() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(6);
//        composite_.setPrivateInt(5);
//        Map<String,LocalVariable> localVars_ = new Map<String,LocalVariable>();
//        Map<String,LocalVariable> params_ = new Map<String,LocalVariable>();
//        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(7);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_LONG);
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement(8);
//        lv_.setClassName(PrimitiveTypeUtil.PRIM_LONG);
//        localVars_.put("2", lv_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        addBean(context_, composite_);
//        System.out.println(ElUtil.processElObj("varArgsNoParam(?long,v;.?,2;.)", 0, context_));;
//        //            long res_ = (long) ExtractObject.extractObject("varArgsNoParam(?long,v;.?,2;.)", conf_,localVars_, vars_, params_, composite_);
//        //            assertEq(15, res_);
//    }

//    @Test
//    public void extractObject21Test() {
//        Composite composite_ = new Composite();
//        composite_.setInteger(6);
//        composite_.setPrivateInt(5);
//        Map<String,LocalVariable> localVars_ = new Map<String,LocalVariable>();
//        Map<String,LocalVariable> params_ = new Map<String,LocalVariable>();
//        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
//        long[] arr_ = new long[7];
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement(arr_);
//        lv_.setClassName(long[].class.getName());
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        arr_ = new long[8];
//        lv_.setElement(arr_);
//        lv_.setClassName(long[].class.getName());
//        localVars_.put("2", lv_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        addBean(context_, composite_);
//        System.out.println(ElUtil.processElObj("varArgsArrays(?[J,v;.?,2;.)", 0, context_));
//        //            long res_ = (long) ExtractObject.extractObject("varArgsArrays(?[J,v;.?,2;.)", conf_,localVars_, vars_, params_, composite_);
//        //            assertEq(15, res_);
//    }
//    @Test
//    public void extractObject22Test() {
//        Composite composite_ = new Composite();
//        Map<String,LocalVariable> localVars_ = new Map<String,LocalVariable>();
//        Map<String,LocalVariable> params_ = new Map<String,LocalVariable>();
//        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
//        LocalVariable lv_ = new LocalVariable();
//        lv_.setElement("bonjour");
//        lv_.setClassName(String.class.getName());
//        localVars_.put("v", lv_);
//        lv_ = new LocalVariable();
//        lv_.setElement("tout");
//        lv_.setClassName(String.class.getName());
//        localVars_.put("2", lv_);
//        ContextEl context_ = new ContextEl();
//        setupAccessValue(context_);
//        addImportingPage(context_);
//        context_.getLastPage().getLocalVars().putAllMap(localVars_);
//        addBean(context_, composite_);
//        System.out.println(ElUtil.processElObj("new.expressionlanguage.classes.Composite(?java.lang.String,v;.?,2;.).getStrings()", 0, context_));
//    }
    @Ignore
    @Test(timeout=1000)
    public void processElTmp1Test() {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        //        ElUtil.processEl("instanceof(\"java.lang.Number\",5)",0, context_);
        Argument arg_ = ElUtil.processEl("^class(\"java.lang.Number\",5)",0, context_);
        assertEq(Number.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(5L, (Number)res_);
    }

//    @Ignore
//    @Test(timeout=1000)
//    public void templates() throws Exception {
//        java.util.List<? extends java.lang.Object> l_;
//        java.util.List<? extends java.lang.String> l2_;
//        l2_ = new java.util.ArrayList<java.lang.String>();
//        l_ = l2_;
////        String arg_ = "java.util.ArrayList<java.lang.Object>";
////        String param_ = "java.util.List<java.lang.Object>";
//        String arg_ = "java.util.List<? ~ java.lang.String>";
//        String param_ = "java.util.List<? ~ java.lang.Object>";
//        Mapping m_ = new Mapping();
//        m_.setArg(arg_);
//        m_.setParam(param_);
////        Templates.getMapping(m_, null);
////        System.out.println(Templates.isCorrect(m_, null));
//        arg_ = "java.util.List<? ~ java.lang.Object>";
//        param_ = "java.util.List<? ~ java.lang.String>";
//        m_.setArg(arg_);
//        m_.setParam(param_);
////        System.out.println(Templates.isCorrect(m_, null));
//        System.out.println();
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<expressionlanguage.classes.EnumNumber>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<java.lang.String>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<? ~ java.lang.Object>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<? ~ java.lang.String>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<? ~ java.lang.String>", new StringMap<StringList>(),null));
//        System.out.println();
//        StringMap<StringList> t_ = new StringMap<StringList>();
//        t_.put("E", new StringList("java.lang.Object"));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<E>", t_,null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<E>", t_,null));
//        System.out.println();
//        t_.put("E", new StringList("java.lang.Enum<E>"));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<E>", t_,null));
//        t_.put("E", new StringList("java.lang.Enum<E>","util.ints.Cmp<E>"));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<E>", t_,null));
//        t_.put("E", new StringList("util.ints.Cmp<E>","java.lang.Enum<E>"));
//        System.out.println("OK");
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<E>", t_,null));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<java.lang.Object>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<java.lang.String>", new StringMap<StringList>(),null));
//        System.out.println();
//        System.out.println(Templates.isCorrectTemplate("java.util.List<[Ljava.lang.Object;>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<[Ljava.lang.String;>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<[I>", new StringMap<StringList>(),null));
//        System.out.println();
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<[Ljava.lang.Object;>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<[Ljava.lang.String;>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<[I>", new StringMap<StringList>(),null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<[Lexpressionlanguage.classes.EnumNumber;>", new StringMap<StringList>(),null));
//        System.out.println();
//        t_.put("E", new StringList("java.lang.Enum<E>"));
//        System.out.println(Templates.isCorrectTemplate("java.util.List<[LE;>", t_,null));
//        System.out.println(Templates.isCorrectTemplate("util.EnumList<[LE;>", t_,null));
//    }

    @Ignore
    @Test(timeout=1000)
    public void processElTmp2Test() throws Exception {
        ContextEl context_ = new ContextEl();
        setupAccessValue(context_);
        addImportingPage(context_);
        BeanOne b_ = new BeanOne();
        addBean(context_, b_);
        //        ElUtil.processEl("instanceof(\"java.lang.Number\",5)",0, context_);
        Argument arg_ = ElUtil.processEl("^class(\"java.lang.Number\",5)",0, context_);
        assertEq(Number.class.getName(), arg_.getArgClassName());
        Object res_ = arg_.getObject();
        assertSame(Long.class, res_.getClass());
        assertEq(5L, (Number)res_);
        InternsClasses out_ = new InternsClasses();
        Object i_ = out_.new InternStandardTwo().new InternStandardThree(5).new InternStandardFour();
        System.out.println("#{} "+new InternsClasses.InternStandardTwo[0].getClass().getEnclosingClass());
        System.out.println(new Struct(i_).getParent().getClassName());
        System.out.println(new Struct(i_).getParent().getParent().getClassName());
        System.out.println(new Struct(i_).getParent().getParent().getParent().getClassName());
        System.out.println(new Struct(i_).getParent().getParent().getParent().getInstance() == out_);
        System.out.println();
        Field o_ = i_.getClass().getDeclaredField("this$2");
        o_.setAccessible(true);
        Object r_ = o_.get(i_);
        System.out.println(r_);
        for (Field f: i_.getClass().getDeclaredFields()) {
            System.out.println(f.getName());
        }
        i_ = new InternsClasses.InternStaticStandard();
        for (Field f: i_.getClass().getDeclaredFields()) {
            System.out.println(f.getName());
        }
        o_ = i_.getClass().getDeclaredField("this$0");
        o_.setAccessible(true);
        r_ = o_.get(i_);
        System.out.println(r_);
        //InternStaticStandard
    }
    private static void setupAccessValue(ContextEl _conf) {
        _conf.setAccessValue(new AccessValueEx());
    }

    private static void addImportingPage(ContextEl _conf) {
        _conf.addPage(new PageEl());
    }
    
    private static void addBean(ContextEl _conf, Object _bean) {
//        LoopVariable lv_ = new LoopVariable();
//        lv_.setElement(_bean);
//        lv_.setExtendedExpression("");
//        _conf.getLastPage().getVars().put("", lv_);
        _conf.getLastPage().setGlobalArgumentObj(_bean);
        _conf.getLastPage().setGlobalClass(_bean.getClass().getName());
    }

    private static void addBeanClass(ContextEl _conf, Class<?> _bean) {
//        LoopVariable lv_ = new LoopVariable();
//        lv_.setElement(_bean);
//        lv_.setExtendedExpression("");
//        _conf.getLastPage().getVars().put("", lv_);
        _conf.getLastPage().setGlobalArgument(_bean);
        _conf.getLastPage().setGlobalClass(_bean.getName());
    }


}
