package code.bean.nat;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.util.*;
import code.maths.montecarlo.DefaultGenerator;
import code.util.*;
import code.util.core.StringUtil;

public abstract class BeanNatCommonLgNames extends BeanLgNames {
    public static final String TYPE_LIST = "ls";
    public static final String TYPE_MAP = "lse";
    public static final String TYPE_DISPLAYABLE = "code.util.ints.Displayable";
    public static final String TYPE_VALIDATOR = "code.bean.validator.Validator";
    public static final String TYPE_BEAN = "code.bean.Bean";

    public static final String TYPE_ENTRY = "$custentry";
    public static final String TYPE_ITERATOR = "code.util.SimpleItr";
    public static final String TYPE_COUNTABLE = "code.util.ints.Countable";
    private static final String TYPE_ENTRIES = "$custentries";
    private final StringMap<String> iterables = new StringMap<String>();
    private final StringMap<Validator> validators = new StringMap<Validator>();

    protected BeanNatCommonLgNames() {
        super(new DefaultGenerator());
    }

    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().addEntry(e.getKey(), NullStruct.NULL_VALUE);
        }
    }

    @Override
    public void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().setValue(index_, newSimpleBean(_language, e.getValue(), _ctx, _rendStack.getStackCall()));
            index_++;
        }
    }

    protected abstract Struct newSimpleBean(String _language, BeanInfo _bean, ContextEl _ctx, StackCall _stackCall);

    public Struct getOtherResultLoc(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct[] _args) {
        if (_instance instanceof ArrayStruct) {
            return(BooleanStruct.of(ExecArrayFieldOperation.getArray(_instance, _cont).getLength()==0));
        }
        if (StringUtil.quickEq(_method.getConstraints().getName(), _cont.getStandards().getCharSeq().getAliasIsEmpty())) {
            return(BooleanStruct.of(NumParsers.getString(_args[0]).getInstance().isEmpty()));
        }
        return getOtherResultBean(_cont, _instance, _method, _args).getResult();
    }
    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                                      ClassMethodId _method, Struct... _args);

    protected static Struct getBeanOrNull(Configuration _conf,String _currentBeanName) {
        return getBean(_conf,_currentBeanName);
    }

    private static Struct getBean(Configuration _conf,String _beanName) {
        return _conf.getBuiltBeans().getVal(_beanName);
    }
    @Override
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack) {
        Validator validator_ = validators.getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        StringList v_ = _cont.getValue();
        NodeInformations nInfos_ = _cont.getNodeInformation();
        String className_ = nInfos_.getInputClass();
        ResultErrorStd resError_ = getStructToBeValidated(v_, className_, _conf, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return null;
        }
        Struct obj_ = resError_.getResult();
        return validator_.validate(obj_);
    }

    public abstract ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance);

    public void buildBeans() {
        CustList<StandardField> fields_;
        fields_ = new CustList<StandardField>();
        SpecialNatClass std_;
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new CustList<StandardMethod>();
        StringList params_;
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        std_ = new SpecialNatClass(TYPE_BEAN, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        getStandards().addEntry(TYPE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_LIST, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        params_ = new StringList();
        method_ = new StandardMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        getIterables().put(TYPE_LIST, getAliasObject());
        getStandards().addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<StandardMethod>();
        cl_ = new SpecialNatClass(TYPE_MAP, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_ENTRIES);
        getIterables().put(TYPE_MAP, getAliasObject());
        getStandards().addEntry(TYPE_MAP, cl_);
        methods_ = new CustList<StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<StandardMethod>();
        std_ = new SpecialNatClass(TYPE_ITERATOR, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        getStandards().addEntry(TYPE_ITERATOR, std_);
        methods_ = new CustList<StandardMethod>();
        cl_ = new SpecialNatClass(TYPE_VALIDATOR, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        getStandards().addEntry(TYPE_VALIDATOR, cl_);
    }


    @Override
    public AbstractWrapper newWrapper(LocalVariable _local){
        return new VariableWrapperNat(_local);
    }
    public ResultErrorStd getName(ContextEl _cont, Struct _instance) {
        return getOtherName(_cont, _instance);
    }
    public abstract ResultErrorStd getOtherName(ContextEl _cont, Struct _instance);
//    @Override
//    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
//        ((BeanStruct)_arg).getBean().beforeDisplaying();
//    }


    public ArrayStruct getStringArray(StringList _ls) {
        return getArray(_ls,getAliasString());
    }

    public ArrayStruct getLongsArray(CustList<Longs> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LIST));
        int j_ = 0;
        for (Longs s:_ls) {
            arr_.set(j_,getLongArray(s));
            j_++;
        }
        return arr_;
    }

    public ArrayStruct getLongArray(Longs _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(getAliasPrimLong()));
        int j_ = 0;
        for (Long s:_ls) {
            arr_.set(j_,new LongStruct(s));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getArray(StringList _ls, String _cl) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(_cl));
        int j_ = 0;
        for (String s:_ls) {
            arr_.set(j_,new StringStruct(StringUtil.nullToEmpty(s)));
            j_++;
        }
        return arr_;
    }

    @Override
    public ResultErrorStd getStructToBeValidatedPrim(StringList _values, String _className, ContextEl _ctx, RendStackCall _stack, ResultErrorStd res_) {
        if (StringUtil.quickEq(_className,getAliasPrimBoolean())) {
            res_.setResult(BooleanStruct.of(StringUtil.quickEq(_values.first(),ON)));
            return res_;
        }
        LongInfo val_ = NumParsers.parseLong(_values.first(), 10);
        if (!val_.isValid()) {
            _stack.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, _values.first(), getContent().getCoreNames().getAliasNbFormat(), _stack.getStackCall())));
            return res_;
        }
        res_.setResult(NumParsers.convertToInt((byte) -1,new LongStruct(val_.getValue())));
        return res_;
    }

    public static void initInstancesPattern(Configuration _conf, StringMap<BeanInfo> _beansInfos) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            OperationsSequence seq_ = new OperationsSequence();
            seq_.setValue("",0);
            seq_.setDelimiter(new Delimiters());
            StandardInstancingOperation root_ = new StandardInstancingOperation(0,0,null,seq_);
            root_.setConstId(new ConstructorId(info_.getClassName(), new StringList(), false));
            info_.setResolvedClassName(info_.getClassName());
            _beansInfos.addEntry(e.getKey(),info_);
        }
    }

    public StringMap<String> getIterables() {
        return iterables;
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new NativeContextEl(new CommonExecutionInfos(_opt.getTabWidth(),_opt.getStack(),this, _options.getClasses(), _options.getCoverage(),new DefaultLockingClass(),new DefaultInitializer()));
    }

    public Forwards setupNative(NatAnalyzedCode _page, DualConfigurationContext _context) {
        Options options_ = _context.getOptions();
        Forwards forwards_ = new Forwards(this, null, options_);
        _page.setStandards(getContent());
        //

        //        standards_.addEntry(getCoreNames().getAliasObject(), std_);
        buildBeans();
        buildOther();
        _page.setStandards(getContent());

        RendBlockHelp.setupOverrides(getStandards());
        return forwards_;
    }
//    public void rendRefresh(Navigation _navigation, ContextEl _context, RendStackCall _rendStack) {
//        for (Bean b: beans.values()) {
//            b.setLanguage(_navigation.getLanguage());
//        }
//        _navigation.getSession().setCurrentLanguage(_navigation.getLanguage());
//        _navigation.processRendAnchorRequest(_navigation.getCurrentUrl(), this, _context, _rendStack);
//    }

    public StringMap<Validator> getValidators() {
        return validators;
    }

    public static PairStruct getPairStruct(Struct _arg, ContextEl _ctx) {
        if (_arg instanceof PairStruct) {
            return (PairStruct)_arg;
        }
        String typeInst_ = _ctx.getStandards().getCoreNames().getAliasObject();
        return new PairStruct(StringUtil.concat(TYPE_ENTRY,StringExpUtil.TEMPLATE_BEGIN,typeInst_,",",typeInst_,StringExpUtil.TEMPLATE_END),NullStruct.NULL_VALUE,NullStruct.NULL_VALUE);
    }

    public static SimpleItrStruct getSimpleItrStruct(Struct _arg, ContextEl _ctx) {
        if (_arg instanceof SimpleItrStruct) {
            return (SimpleItrStruct)_arg;
        }
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        String typeInst_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(array_.getClassName(_ctx)));
        return new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,StringExpUtil.TEMPLATE_BEGIN,typeInst_,StringExpUtil.TEMPLATE_END),array_);
    }

}
