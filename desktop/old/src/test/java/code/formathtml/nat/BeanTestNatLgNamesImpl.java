package code.formathtml.nat;

import code.bean.Bean;
import code.bean.nat.*;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.BeanInfo;
import code.util.CustList;
import code.util.StringList;

public final class BeanTestNatLgNamesImpl extends BeanTestNatLgNames {

    private static final String STRINGS = "strings";
    private static final String TREE = "tree";
    private static final String TYPED_STRING = "typedString";
    private static final String TYPED_STRINGS = "typedStrings";
    private static final String TYPE_BEAN_ONE = "simple.BeanOne";
    private static final String TYPE_INPUT = "simple.Input";

    @Override
    public ResultErrorStd getOtherResultBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        return null;
    }

    @Override
    public ResultErrorStd getOtherName(Struct _instance) {
        String str_ = processString(new Argument(_instance), null, null);
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(new StringStruct(str_));
        return res_;
    }

    @Override
    public BeanStruct getOtherResultBean(ContextEl _cont, ConstructorId _method) {
        SimpleOne bean_ = newBeanOne();
        return new BeanStruct(bean_);
    }

    @Override
    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        SimpleOne bean_ = newBeanOne();
        return new BeanStruct(bean_);
    }

    static SimpleOne newBeanOne() {
        SimpleOne bean_ = new SimpleOne();
        bean_.setClassName(TYPE_BEAN_ONE);
        return bean_;
    }

//    @Override
//    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
//        ResultErrorStd res_ = new ResultErrorStd();
//        String name_ = _method.getConstraints().getName();
//        if (_instance instanceof BeanStruct) {
//            BeanStruct instance_ = (BeanStruct) _instance;
//            Bean bean_ = instance_.getBean();
//            if (bean_ instanceof SimpleOne) {
//                if (StringUtil.quickEq(name_, STRINGS)) {
//                    res_.setResult(((SimpleOne) bean_).getList(_cont));
//                    return res_;
//                }
//                if (StringUtil.quickEq(name_, TYPED_STRINGS)) {
//                    res_.setResult(((SimpleOne) bean_).getTypedStrings(_cont));
//                    return res_;
//                }
//                if (StringUtil.quickEq(name_,"length")) {
//                    res_.setResult(new IntStruct(NumParsers.getString(_args[0]).getInstance().length()));
//                    return res_;
//                }
//                if (StringUtil.quickEq(name_, TREE)) {
//                    res_.setResult(((SimpleOne) bean_).getTree(_cont));
//                    return res_;
//                }
//                if (StringUtil.quickEq(name_, TYPED_STRING)) {
//                    if (_method.getConstraints().getParametersTypesLength() == 1) {
//                        ((SimpleOne) bean_).setTypedString(NumParsers.getString(_args[0]).toStringInstance());
//                        res_.setResult(NullStruct.NULL_VALUE);
//                        return res_;
//                    } else {
//                        res_.setResult(new StringStruct(((SimpleOne) bean_).getTypedString()));
//                        return res_;
//                    }
//                }
//            }
//        }
//        if (_instance instanceof SampleInputStruct) {
//            if (StringUtil.quickEq(name_, TYPED_STRING)) {
//                if (_method.getConstraints().getParametersTypesLength() == 1) {
//                    ((SampleInputStruct) _instance).setTypedString(NumParsers.getString(_args[0]).toStringInstance());
//                    res_.setResult(NullStruct.NULL_VALUE);
//                    return res_;
//                } else {
//                    res_.setResult(new StringStruct(((SampleInputStruct) _instance).getTypedString()));
//                    return res_;
//                }
//            }
//        }
//        res_.setResult(BooleanStruct.of(ExecArrayFieldOperation.getArray(_instance,_cont).getLength()==0));
//        return res_;
//    }
    @Override
    public void beforeDisplaying(Struct _arg) {
//        ((BeanStruct)_arg).getBean().beforeDisplaying();
    }

    @Override
    public ResultErrorStd getOtherResult(ClassField _classField, Struct _instance) {
        return null;
    }

    @Override
    public void setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {

    }

    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_ONE, fields_, methods_, TYPE_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(STRINGS,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(TYPED_STRINGS,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(TREE,params_, TYPE_MAP, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasObject());
        method_ = new SpecNatMethod("length",params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(TYPED_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(TYPED_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_ONE, cl_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_INPUT, fields_, methods_, getAliasObject());
        params_ = new StringList();
        method_ = new SpecNatMethod(TYPED_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(TYPED_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_INPUT, cl_);
    }
    protected BeanStruct newSimpleBean(String _language, BeanInfo _bean, ContextEl _ctx) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        BeanStruct res_ = getOtherResultBean(_ctx, id_);
//        Bean bean_ = res_.getBean();
        new StringMapObjectSample();
//        bean_.setLanguage(_language);
//        bean_.setScope(_bean.getScope());
        return res_;
    }

}
