package code.formathtml.util;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ElRenderUtil;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendDynOperationNode;
import code.util.*;
import code.util.ints.*;

public abstract class BeanNatLgNames extends BeanLgNames {
    private static final String VALIDATE = "validate";
    private static final String SET_FORMS = "setForms";
    private static final String GET_FORMS = "getForms";
    private static final String SET_LANGUAGE = "setLanguage";
    private static final String GET_LANGUAGE = "getLanguage";
    private static final String SET_SCOPE = "setScope";
    private static final String GET_SCOPE = "getScope";
    private static final String SET_DATA_BASE = "setDataBase";
    private static final String GET_DATA_BASE = "getDataBase";
    private static final String BEFORE_DISPLAYING = "beforeDisplaying";
    private static final String GET_OLD_VALUE = "getOldValue";
    private static final String GET_NEW_VALUE = "getNewValue";
    private static final String GET_VALUE = "getValue";
    private static final String GET_KEY = "getKey";
    private static final String ENTRIES = "entries";

    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private String displayVar;

    private CustList<RendDynOperationNode> expsIterator;
    private CustList<RendDynOperationNode> expsHasNext;
    private CustList<RendDynOperationNode> expsNext;
    private CustList<RendDynOperationNode> expsDisplay;


    private String iteratorTableVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private String beforeDisplayingVar;

    private CustList<RendDynOperationNode> expsIteratorTableCust;
    private CustList<RendDynOperationNode> expsFirstCust;
    private CustList<RendDynOperationNode> expsSecondCust;
    private CustList<RendDynOperationNode> expsBeforeDisplaying;

    static Object[] adaptedArgs(StringList _params, BeanNatLgNames _stds, Struct... _args) {
        int len_ = _params.size();
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_ == NullStruct.NULL_VALUE) {
                continue;
            }
            if (argStruct_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) argStruct_;
                Struct[] str_ = arr_.getInstance();
                String compo_ = PrimitiveTypeUtil.getQuickComponentType(arr_.getClassName());
                if (StringList.quickEq(compo_, _stds.getAliasPrimByte())) {
                    byte[] adapt_ = new byte[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).byteStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                    short[] adapt_ = new short[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).shortStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                    int[] adapt_ = new int[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).intStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimChar())) {
                    char[] adapt_ = new char[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((CharStruct) s).getChar();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimLong())) {
                    long[] adapt_ = new long[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).longStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                    float[] adapt_ = new float[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).floatStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                    double[] adapt_ = new double[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).doubleStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasString())) {
                    String[] adapt_ = new String[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((CharSequenceStruct)s).toStringInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasReplacement())) {
                    Replacement[] adapt_ = new Replacement[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((ReplacementStruct) s).getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimBoolean())) {
                    boolean[] adapt_ = new boolean[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((BooleanStruct) s).getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                args_[i] = _stds.getOtherArguments(str_, compo_);
                continue;
            }
            String p_ = _params.get(i);
            String pType_ = PrimitiveTypeUtil.toPrimitive(p_, _stds);
            if (argStruct_ instanceof NumberStruct) {
                if (argStruct_ instanceof CharStruct) {
                    if (StringList.quickEq(pType_, _stds.getAliasPrimChar())) {
                        args_[i] = ((CharStruct) argStruct_).getChar();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).longStruct();
                    }
                } else if (argStruct_ instanceof IntStruct) {
                    args_[i] = ((NumberStruct) argStruct_).intStruct();
                } else if (argStruct_ instanceof DoubleStruct) {
                    args_[i] = ((DoubleStruct) argStruct_).doubleStruct();
                } else {
                    if (StringList.quickEq(pType_, _stds.getAliasPrimChar())) {
                        args_[i] = (char) ((NumberStruct) argStruct_).intStruct();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).longStruct();
                    }
                }
            } else if (argStruct_ instanceof StringStruct) {
                args_[i] = ((StringStruct)argStruct_).getInstance();
            } else if (argStruct_ instanceof StringBuilderStruct) {
                args_[i] = ((StringBuilderStruct)argStruct_).getInstance();
            } else if (argStruct_ instanceof BooleanStruct) {
                args_[i] = ((BooleanStruct)argStruct_).getInstance();
            } else {
                args_[i] = ((RealInstanceStruct)argStruct_).getInstance();
            }
        }
        return args_;
    }

    static Object adaptedArg(BeanNatLgNames _stds, Struct _args) {
        if (_args == NullStruct.NULL_VALUE) {
            return null;
        }
        if (_args instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) _args;
            Struct[] str_ = arr_.getInstance();
            String compo_ = PrimitiveTypeUtil.getQuickComponentType(arr_.getClassName());
            if (StringList.quickEq(compo_, _stds.getAliasPrimByte())) {
                byte[] adapt_ = new byte[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).byteStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                short[] adapt_ = new short[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).shortStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                int[] adapt_ = new int[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).intStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimChar())) {
                char[] adapt_ = new char[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((CharStruct) s).getChar();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimLong())) {
                long[] adapt_ = new long[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).longStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                float[] adapt_ = new float[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).floatStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                double[] adapt_ = new double[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).doubleStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasString())) {
                String[] adapt_ = new String[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((CharSequenceStruct)s).toStringInstance();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasReplacement())) {
                Replacement[] adapt_ = new Replacement[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((ReplacementStruct) s).getInstance();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimBoolean())) {
                boolean[] adapt_ = new boolean[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((BooleanStruct) s).getInstance();
                    i_++;
                }
                return adapt_;
            }
            return _stds.getOtherArguments(str_, compo_);
        }
        if (_args instanceof NumberStruct) {
            if (_args instanceof CharStruct) {
                return ((CharStruct) _args).getChar();
            }
            if (_args instanceof IntStruct) {
                return ((IntStruct) _args).intStruct();
            }
            return ((NumberStruct) _args).longStruct();
        }
        if (_args instanceof StringStruct) {
            return ((StringStruct)_args).getInstance();
        }
        if (_args instanceof StringBuilderStruct) {
            return ((StringBuilderStruct)_args).getInstance();
        }
        if (_args instanceof BooleanStruct) {
            return ((BooleanStruct) _args).getInstance();
        }
        return ((RealInstanceStruct) _args).getInstance();
    }

    public Object getOtherArguments(Struct[] _str, String _base) {
        return null;
    }
    public static ResultErrorStd getField(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames lgNames_ = (BeanNatLgNames) _cont.getStandards();
        String type_ = _classField.getClassName();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            return lgNames_.getSimpleResult(_cont, _classField);
        }
        return lgNames_.getOtherResult(_cont, _classField, _instance);
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        return new ResultErrorStd();
    }

    public void buildBeans() {
        StringMap<StandardField> fields_;
        fields_ = new StringMap<StandardField>();
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(BEAN, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod(BEFORE_DISPLAYING, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_DATA_BASE, params_, getAliasObject(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(SET_DATA_BASE, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_SCOPE, params_, getAliasString(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(SET_SCOPE, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_LANGUAGE, params_, getAliasString(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(SET_LANGUAGE, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_FORMS, params_, ALIAS_STRING_MAP_OBJECT, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(ALIAS_STRING_MAP_OBJECT);
        method_ = new StandardMethod(SET_FORMS, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        getStandards().put(BEAN, std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(ALIAS_STRING_MAP_OBJECT, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(ALIAS_STRING_MAP_OBJECT, std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        StandardClass cl_;
        cl_ = new StandardClass(getCustList(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        getIterables().put(getCustList(),getAliasObject());
        getStandards().put(getCustList(), cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(getCustMap(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(ENTRIES, params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getCustEntries());
        getIterables().put(getCustMap(),getAliasObject());
        getStandards().put(getCustMap(), cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(getCustEntries(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        getIterables().put(getCustEntries(),getCustEntry());
        getStandards().put(getCustEntries(), cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(getCustEntry(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEY, params_, getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_VALUE, params_, getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(getCustEntry(), cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(getValueChangedEvent(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NEW_VALUE, params_, getAliasObject(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_OLD_VALUE, params_, getAliasObject(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(getValueChangedEvent(), cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(getErrorEl(), fields_, constructors_, methods_, getAliasError(), MethodModifier.ABSTRACT);
        getStandards().put(getErrorEl(), cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        StandardInterface stdi_ = new StandardInterface(getAliasSimpleIterableType(), methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasSimpleIterator(), params_, getAliasSimpleIterableType(), false, MethodModifier.ABSTRACT, stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(getAliasSimpleIterableType(), stdi_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdi_ = new StandardInterface(getAliasCountable(), methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasSize(), params_, getAliasPrimInteger(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(getAliasGet(), params_, getAliasObject(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(getAliasCountable(), stdi_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass stdcl_ = new StandardClass(getAliasSimpleIteratorType(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(getAliasSimpleIteratorType(), std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdi_ = new StandardInterface(getAliasDisplayable(), methods_, new StringList());
        params_ = new StringList();
        method_ = new StandardMethod(getAliasDisplay(), params_, getAliasString(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(getAliasDisplayable(), stdi_);
        cl_ = new StandardClass(getValidator(), fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList(getAliasObject(),getAliasObject(),getAliasObject());
        method_ = new StandardMethod(VALIDATE, params_, getAliasObject(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(getValidator(), cl_);
    }

    public void buildIterables(Configuration _context) {
        ContextEl context_ = _context.getContext();
        _context.getImporting().add(new ImportingPage(false));
        context_.setAnalyzing(new AnalyzedPageEl());
        context_.getAnalyzing().setEnabledInternVars(true);
        String locName_ = context_.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasSimpleIterableType());
        _context.getInternVars().put(locName_, locVar_);
        iteratorVar = locName_;
        String simpleIterator_ = getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(simpleIterator_,PARS));
        expsIterator = ElRenderUtil.getAnalyzedOperations(exp_,0, _context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        hasNextVar = locName_;
        String hasNext_ = getAliasHasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        expsHasNext = ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        nextVar = locName_;
        String next_ = getAliasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        expsNext = ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasDisplayable());
        _context.getInternVars().put(locName_, locVar_);
        displayVar = locName_;
        String display_ = getAliasDisplay();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(display_,PARS));
        expsDisplay = ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getCustMap()));
        _context.getInternVars().put(locName_, locVar_);
        iteratorTableVarCust= locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(ENTRIES,PARS));
        expsIteratorTableCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getCustEntry()));
        _context.getInternVars().put(locName_, locVar_);
        firstVarCust= locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(GET_KEY,PARS));
        expsFirstCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));
        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getCustEntry()));
        _context.getInternVars().put(locName_, locVar_);
        secondVarCust= locName_;
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(GET_VALUE,PARS));
        expsSecondCust= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        locName_ = context_.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasBean()));
        _context.getInternVars().put(locName_, locVar_);
        beforeDisplayingVar = locName_;
        String beforeDisplaying_ = getAliasBeforeDisplaying();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(beforeDisplaying_,PARS));
        expsBeforeDisplaying= ElRenderUtil.getAnalyzedOperations(exp_, 0,_context, Calculation.staticCalculation(true));

        _context.clearPages();
    }
    @Override
    public IterableAnalysisResult getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        Boolean nativeCmp_ = null;
        for (String f: _names) {
            String type_ = getIterableFullTypeByStds(f, _context);
            String iterable_ = getAliasIterable();
            if (type_ == null) {
                type_ = Templates.getFullTypeByBases(f, iterable_, _context);
                nativeCmp_ = false;
            } else {
                nativeCmp_ = true;
            }
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return new NativeIterableAnalysisResult(out_, nativeCmp_);
    }
    private static String getIterableFullTypeByStds(String _subType, ContextEl _context) {
        BeanLgNames lgNames_ = (BeanLgNames) _context.getStandards();
        String it_ = lgNames_.getIterables().getVal(_subType);
        if (it_ == null) {
            return null;
        }
        return StringList.concat(lgNames_.getAliasIterable(),"<",it_,">");
    }
    public String getIteratorVar() {
        return iteratorVar;
    }
    public String getHasNextVar() {
        return hasNextVar;
    }
    public String getNextVar() {
        return nextVar;
    }
    public CustList<RendDynOperationNode> getExpsIterator() {
        return expsIterator;
    }
    public CustList<RendDynOperationNode> getExpsHasNext() {
        return expsHasNext;
    }
    public CustList<RendDynOperationNode> getExpsNext() {
        return expsNext;
    }

    private CustList<RendDynOperationNode> getExpsDisplay() {
        return expsDisplay;
    }

    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }

    public String getHasNextPairVarCust() {
        return getHasNextVar();
    }

    public String getNextPairVarCust() {
        return getNextVar();
    }

    public String getFirstVarCust() {
        return firstVarCust;
    }

    public String getSecondVarCust() {
        return secondVarCust;
    }

    public String getBeforeDisplayingVar() {
        return beforeDisplayingVar;
    }

    public CustList<RendDynOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }

    public CustList<RendDynOperationNode> getExpsHasNextPairCust() {
        return getExpsHasNext();
    }

    public CustList<RendDynOperationNode> getExpsNextPairCust() {
        return getExpsNext();
    }

    public CustList<RendDynOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }

    public CustList<RendDynOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }

    public CustList<RendDynOperationNode> getExpsBeforeDisplaying() {
        return expsBeforeDisplaying;
    }

    public String processString(Argument _arg, Configuration _cont) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_cont).getInstance();
        }
        String param_ = _cont.getAdvStandards().getAliasDisplayable();
        ContextEl context_ = _cont.getContext();
        String arg_ = getStructClassName(struct_, context_);
        if (Templates.isCorrectExecute(arg_, param_, context_)) {
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(arg_);
            locVar_.setStruct(struct_);
            _cont.getLastPage().getInternVars().put(displayVar, locVar_);
            struct_ = ElRenderUtil.calculateReuse(getExpsDisplay(), _cont).getStruct();
            return ((DisplayableStruct)struct_).getDisplayedString(_cont).getInstance();
        }
        return _arg.getObjectClassName(context_);
    }

    public static ResultErrorStd setField(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        BeanNatLgNames lgNames_ = (BeanNatLgNames) _cont.getStandards();
        Object value_ = adaptedArg(lgNames_, _value);
        return lgNames_.setOtherResult(_cont, _classField, _instance, value_);
    }

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        if (StringList.quickEq(_method.getName(),getAliasObject())) {
            return super.getOtherResult(_cont,_method,_args);
        }
        StringList list_ = _method.getParametersTypes();
        Object[] argsObj_ = adaptedArgs(list_, this, _args);
        return getOtherResultBean(_cont, _method, argsObj_);
    }

    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args) {
        return new ResultErrorStd();
    }

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, getAliasEnums())) {
            return super.getOtherResult(_cont,_instance,_method,_args);
        }
        Object[] argsObj_ = adaptedArgs(list_, this, _args);
        Object instance_ = null;
        if (!_method.getConstraints().isStaticMethod()) {
            instance_ = ((RealInstanceStruct)_instance).getInstance();
        }
        if (instance_ instanceof Displayable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasDisplay()) || StringList.quickEq(name_, getAliasToString())) {
                res_.setResult(new StringStruct(((Displayable)instance_).display()));
                return res_;
            }
        }
        if (instance_ instanceof Bean) {
            if (StringList.quickEq(_method.getConstraints().getName(), BEFORE_DISPLAYING)) {
                ((Bean)instance_).beforeDisplaying();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_DATA_BASE)) {
                ((Bean)instance_).setDataBase(argsObj_[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_DATA_BASE)) {
                Object db_ = ((Bean)instance_).getDataBase();
                if (getAliasDataBase() != null) {
                    res_.setResult(StdStruct.wrapStd(db_, _cont, getAliasDataBase()));
                    return res_;
                }
                res_.setResult(StdStruct.wrapStd(db_, _cont));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_FORMS)) {
                StringMapObject resMap_ = ((Bean)instance_).getForms();
                res_.setResult(new StringMapObjectStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_FORMS)) {
                ((Bean)instance_).setForms((StringMapObject)((StringMapObjectStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_LANGUAGE)) {
                String resMap_ = ((Bean)instance_).getLanguage();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_LANGUAGE)) {
                ((Bean)instance_).setLanguage(((StringStruct) _args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCOPE)) {
                String resMap_ = ((Bean)instance_).getScope();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_SCOPE)) {
                ((Bean)instance_).setScope(((StringStruct) _args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (instance_ instanceof Countable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasIsEmpty())) {
                res_.setResult(new BooleanStruct(((Countable) instance_).isEmpty()));
                return res_;
            }
            if (StringList.quickEq(name_, getAliasSize())) {
                res_.setResult(new IntStruct(((Countable) instance_).size()));
                return res_;
            }
            if (StringList.quickEq(name_, getAliasGet())) {
                res_.setResult(StdStruct.wrapStd(((Countable) instance_).getObj(((NumberStruct) _args[0]).intStruct()), _cont));
                return res_;
            }
        }
        if (instance_ instanceof SimpleIterable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasSimpleIterator())) {
                String typeInst_ = getStructClassName(_instance, _cont);
                String it_ = getIterables().getVal(typeInst_);
                res_.setResult(StdStruct.newInstance(((SimpleIterable) instance_).simpleIterator(), StringList.concat(getAliasSimpleIteratorType(),Templates.TEMPLATE_BEGIN,it_,Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (instance_ instanceof SimpleItr) {
            String name_ = _method.getConstraints().getName();
            return prIterator(_cont, name_, _instance);
        }
        if (instance_ instanceof SimpleEntries) {
            SimpleIterable db_ = ((SimpleEntries)instance_).entries();
            res_.setResult(new StdStruct(db_, getCustEntries()));
            return res_;
        }
        if (instance_ instanceof SimpleEntry) {
            SimpleEntry db_ = (SimpleEntry)instance_;
            if (StringList.quickEq(_method.getConstraints().getName(), GET_KEY)) {
                Object key_ = db_.getSimpleKey();
                res_.setResult(StdStruct.wrapStd(key_, _cont));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_VALUE)) {
                Object value_ = db_.getSimpleValue();
                res_.setResult(StdStruct.wrapStd(value_, _cont));
                return res_;
            }
        }
        if (instance_ instanceof ValueChangeEvent) {
            ValueChangeEvent db_ = (ValueChangeEvent)instance_;
            if (StringList.quickEq(_method.getConstraints().getName(), GET_NEW_VALUE)) {
                res_.setResult(db_.getNewValue());
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_OLD_VALUE)) {
                res_.setResult(db_.getOldValue());
                return res_;
            }
        }
        if (instance_ instanceof Validator) {
            Validator validator_ = (Validator) instance_;
            if (StringList.quickEq(_method.getConstraints().getName(), VALIDATE)) {
                Message message_ = validator_.validate(argsObj_[0], argsObj_[1], argsObj_[2]);
                if (message_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(StdStruct.wrapStd(message_, _cont));
                return res_;
            }
        }
        return getOtherResultBean(_cont, _instance, _method, argsObj_);
    }
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                             ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }
    private ResultErrorStd prIterator(ContextEl _cont, String _name, Struct _struct) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = ((StdStruct) _struct).getInstance();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(_name, lgNames_.getAliasNext())) {
            Object resObj_ = ((SimpleItr)instance_).next();
            result_.setResult(StdStruct.wrapStd(resObj_, _cont));
            return result_;
        }
        result_.setResult(new BooleanStruct(((SimpleItr)instance_).hasNext()));
        return result_;
    }

    public String getStdBeanStructClassName(Object _struct, ContextEl _context) {
        String cl_ = getOtherBeanStructClassName(_struct, _context);
        if (!StringList.quickEq(cl_, getAliasObject())) {
            return cl_;
        }
        if (_struct instanceof Bean) {
            return ((Bean)_struct).getClassName();
        }
        if (_struct instanceof Validator) {
            return ((Validator)_struct).getClassName();
        }
        if (_struct instanceof Translator) {
            return ((Translator)_struct).getClassName();
        }
        if (_struct instanceof SimpleList) {
            return getCustList();
        }
        if (_struct instanceof SimpleEntries) {
            return getCustMap();
        }
        if (_struct instanceof SimpleEntry) {
            return getCustEntry();
        }
        if (_struct instanceof ValueChangeEvent) {
            return getValueChangedEvent();
        }
        if (_struct instanceof SimpleIterable) {
            return getAliasSimpleIterableType();
        }
        if (_struct instanceof SimpleItr) {
            return getAliasSimpleIteratorType();
        }
        return cl_;
    }

}
