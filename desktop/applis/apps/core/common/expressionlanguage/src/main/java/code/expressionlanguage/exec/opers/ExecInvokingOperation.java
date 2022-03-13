package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.NoExiting;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Sizes;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.ReflectVariableWrapper;
import code.expressionlanguage.fcts.FctRangeUnlimitedStep;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecInstancingCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class ExecInvokingOperation extends ExecMethodOperation implements ExecPossibleIntermediateDotted, AtomicExecCalculableOperation {
    private final boolean intermediate;

    protected ExecInvokingOperation(
            ExecOperationContent _opCont, boolean _intermediateDottedOperation) {
        super(_opCont);
        intermediate = _intermediateDottedOperation;
    }

    protected ExecInvokingOperation(int _indexChild, ExecClassArgumentMatching _res, int _order,
                                 boolean _intermediate) {
        super(_indexChild,_res,_order);
        intermediate = _intermediate;
    }

    public static ArgumentListCall fectchInstFormattedArgs(ExecFormattedRootBlock _formatted, ExecInstancingCommonContent _lastType, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        String lastType_ = ExecInherits.quickFormat(_formatted, _lastType.getLastType());
        return fectchArgs(lastType_, _lastType.getNaturalVararg(), null,_conf,_stack, _infos);
    }

    protected static ArgumentListCall fetchFormattedArgs(ContextEl _conf, StackCall _stack, Struct _pr, ExecRootBlock _rootBlock, ExecInstFctContent _lastType, Argument _right, CustList<ExecOperationInfo> _infos) {
        String cl_ = _pr.getClassName(_conf);
        String lastType_ = ExecTemplates.formatType(_conf, _rootBlock, _lastType.getLastType(), cl_);
        return fectchArgs(lastType_, _lastType.getNaturalVararg(),_right,_conf,_stack, _infos);
    }

    public static ArgumentListCall fectchArgs(String _lastType, int _naturalVararg, Argument _right, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        ArgumentList argumentList_ = listNamedArguments(_naturalVararg, _infos);
        CustList<ArgumentWrapper> first_ = argumentList_.getArguments().getArgumentWrappers();
        listArguments(argumentList_.getNaturalVararg(), _lastType, first_,_conf,_stack);
        argumentList_.getArguments().setRight(_right);
        return argumentList_.getArguments();
    }
    public static ArgumentList listNamedArguments(int _naturalVararg, CustList<ExecOperationInfo> _infos) {
        ArgumentList out_ = listNamedArguments(_infos);
        out_.setNaturalVararg(_naturalVararg);
        return out_;
    }

    public static void listArguments(int _natVararg, String _lastType, CustList<ArgumentWrapper> _nodes, ContextEl _conf, StackCall _stack) {
        if (_natVararg <= -1) {
            return;
        }
        CustList<Argument> optArgs_ = new CustList<Argument>();
        CustList<ArgumentWrapper> reord_ = new CustList<ArgumentWrapper>();
        int lenCh_ = _nodes.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
            ArgumentWrapper aw_ = _nodes.get(i);
            if (aw_.getWrapper() != null) {
                reord_.add(aw_);
                continue;
            }
            Argument a_ = aw_.getValue();
            if (i >= _natVararg) {
                optArgs_.add(a_);
            } else {
                reord_.add(aw_);
            }
        }
        String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
        ArrayStruct str_ = new ArrayStruct(optArgs_.size(),clArr_);
        ExecTemplates.setCheckedElements(optArgs_,str_,_conf,_stack);
        reord_.add(new ArgumentWrapper(new Argument(str_),null));
        _nodes.clear();
        _nodes.addAllElts(reord_);
    }
    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

    public static ArgumentWrapper instancePrepareStd(ContextEl _conf, StandardConstructor _ctor, ConstructorId _constId,
                                              ArgumentListCall _arguments, StackCall _stackCall) {
        CustList<Argument> args_ = _arguments.getArguments();
        if (ExecTemplates.okArgsSet(_constId, args_, _conf, _stackCall) != null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        StdCaller caller_ = StandardType.caller(_ctor, null);
        NoExiting exit_ = new NoExiting();
        return _conf.getCaller().invoke(caller_, exit_,_conf,NullStruct.NULL_VALUE,_arguments,_stackCall);
    }

    public static ExecOverrideInfo polymorphOrSuper(boolean _super,ContextEl _conf, Struct _previous, ExecFormattedRootBlock _className, ExecTypeFunction _named) {
        if (_super) {
            return new ExecOverrideInfo(_className,_named);
        }
        return polymorph(_conf, _previous, _named);
    }
    public static ExecOverrideInfo polymorph(ContextEl _conf, Struct _previous, ExecTypeFunction _named) {
        String argClassName_ = Argument.getNull(_previous).getClassName(_conf);
        String base_ = StringExpUtil.getIdFromAllTypes(argClassName_);
        ExecRootBlock type_ = _named.getType();
        ExecNamedFunctionBlock fct_ = _named.getFct();
        ExecOverrideInfo res_ = type_.getRedirections().getVal(fct_,base_);
        if (res_ != null) {
            return res_;
        }
        return new ExecOverrideInfo(new ExecFormattedRootBlock(type_),_named);
    }
    public static Argument callStd(AbstractExiting _exit, ContextEl _cont, ClassMethodId _classNameFound, Argument _previous, ArgumentListCall _firstArgs, StackCall _stackCall, StandardMethod _stdMeth) {
        CustList<Argument> args_ = _firstArgs.getArguments();
        ExecTemplates.checkParams(_cont, _classNameFound.getClassName(), _classNameFound.getConstraints(), _previous, args_, _stackCall);
        if (_cont.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        StdCaller caller_ = _stdMeth.getCaller();
        return _cont.getCaller().invoke(caller_,_exit,_cont,_previous.getStruct(),_firstArgs,_stackCall).getValue();
    }

    public static Argument tryGetEnumValues(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        return getEnumValues(_exit, _r, _cont, _stackCall);
    }

    public static Argument tryGetEnumValue(AbstractExiting _exit, ContextEl _cont, ExecRootBlock _r, ClassCategory _category, Argument _clArg, StackCall _stackCall) {
        if (isNotEnumType(_r, _category)) {
            return new Argument();
        }
        return getEnumValue(_exit, _r, _clArg, _cont, _stackCall);
    }


    public static Argument processEnums(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall, ExecRootBlock _type) {
        //static enum methods
        LgNames stds_ = _cont.getStandards();
        CustList<Argument> args_ = _firstArgs.getArguments();
        if (args_.size() != 1) {
            return tryGetEnumValues(_exit, _cont, _type,  ClassCategory.ENUM, _stackCall);
        }
        Argument arg_ = args_.first();
        Struct ex_ = ExecTemplates.checkObjectEx(stds_.getContent().getCharSeq().getAliasString(), Argument.getNullableValue(arg_).getStruct().getClassName(_cont), _cont, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
            return Argument.createVoid();
        }
        return tryGetEnumValue(_exit, _cont, _type, ClassCategory.ENUM, arg_, _stackCall);
    }

    private static boolean isNotEnumType(ExecRootBlock _r, ClassCategory _category) {
        return _r == null || _category != ClassCategory.ENUM;
    }

    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named, ArgumentListCall _firstArgs, ExecFormattedRootBlock _classNameFound, MethodAccessKind _kind, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _classNameFound.getRootBlock())) {
            return;
        }
        new DefaultParamChecker(_named, _firstArgs, _kind, CallPrepareState.OPERATOR, null).checkParams(_classNameFound, Argument.createVoid(), null, _conf, _stackCall);
    }

    public static Argument getInstanceCall(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return ((LambdaStruct) ls_).getInstanceCall();
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }
    public static Argument getMetaInfo(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = _previous.getStruct();
        if (ls_ instanceof LambdaStruct) {
            return new Argument(((LambdaStruct) ls_).getMetaInfo());
        }
        LgNames lgNames_ = _conf.getStandards();
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }

    public static Argument prepareCallDynReflect(Argument _previous, CustList<Argument> _values, ContextEl _conf, StackCall _stackCall) {
        CustList<Argument> values_ = new CustList<Argument>();
        for (Argument v: _values) {
            values_.add(Argument.getNullableValue(v));
        }

        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        String typeFct_ = ls_.getClassName(_conf);
        StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
        CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
        int valuesSize_ = values_.size();
        if (valuesSize_ != paramsFct_.size()) {
            LgNames lgNames_ = _conf.getStandards();
            String null_ = lgNames_.getContent().getCoreNames().getAliasBadArgNumber();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, ExecTemplates.countDiff(valuesSize_, paramsFct_.size()).toString(), null_, _stackCall)));
            return new Argument();
        }
        for (int i = 0; i < valuesSize_; i++) {
            Argument arg_ = values_.get(i);
            String param_ = paramsFct_.get(i);
            if (param_.startsWith("~")) {
                param_ = param_.substring(1);
            }
            if (!ExecTemplates.checkQuick(param_, arg_.getStruct().getClassName(_conf), _conf, _stackCall)) {
                return new Argument();
            }
        }
        ArgumentListCall argumentListCall_ = new ArgumentListCall();
        int i_ = 0;
        for (String c: paramsFct_) {
            if (c.startsWith("~")) {
                Struct struct_ = values_.get(i_).getStruct();
                LocalVariable local_ = LocalVariable.newLocalVariable(struct_, c.substring(1));
                ReflectVariableWrapper v_ = new ReflectVariableWrapper(local_);
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(values_.get(i_),v_));
            } else {
                argumentListCall_.getArgumentWrappers().add(new ArgumentWrapper(values_.get(i_),null));
            }
            i_++;
        }
        return prepareCallDyn(_previous,argumentListCall_,_conf, _stackCall);
    }
    public static Argument prepareCallDynNormal(Argument _previous, ArgumentListCall _values, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        ArgumentListCall call_ = new ArgumentListCall();
        if (!(ls_ instanceof LambdaStruct)) {
            return prepareCallDyn(_previous,call_,_conf, _stackCall);
        }
        return prepareCallDynNormalDefault(_previous, _values.getArgumentWrappers(), _conf, _stackCall);
    }

    private static Argument prepareCallDynNormalDefault(Argument _previous, CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
        ArgumentListCall call_ = new ArgumentListCall();
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        String typeFct_ = ls_.getClassName(_conf);
        StringList parts_ = StringExpUtil.getAllTypes(typeFct_);
        CustList<String> paramsFct_ = parts_.leftMinusOne(parts_.size() - 2);
        StringList typesRef_ = new StringList();
        StringList types_ = new StringList();
        StringList typesAll_ = new StringList();
        feedTypes(paramsFct_, typesRef_, types_, typesAll_);
        int wrappers_ = countWrappers(_values);
        int values_ = countValues(_values);
        int parNb_ = paramsFct_.size();
        if (_values.size() != parNb_) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
            StringBuilder mess_ = ExecTemplates.countDiff(_values.size(), parNb_);
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall)));
            return new Argument();
        }
        for (Sizes s: new CustList<Sizes>(
                new Sizes(values_, types_.size()),
                new Sizes(wrappers_, typesRef_.size())
        )) {
            if (s.getArg() != s.getParam()) {
                LgNames stds_ = _conf.getStandards();
                String cast_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                StringBuilder mess_ = ExecTemplates.countDiff(s.getArg(), s.getParam());
                _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf,mess_.toString(),cast_, _stackCall)));
                return new Argument();
            }

        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (ArgumentWrapper a: _values) {
            String param_ = paramsFct_.get(i_);
            if (param_.startsWith("~")) {
                if (a.getWrapper() == null) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getValue().getStruct().getClassName(_conf)),cast_, _stackCall)));
                    return new Argument();
                }
            } else {
                if (a.getWrapper() != null) {
                    LgNames stds_ = _conf.getStandards();
                    String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
                    _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(param_, a.getWrapper().getClassName(_stackCall, _conf)),cast_, _stackCall)));
                    return new Argument();
                }
            }
            i_++;
        }
        if (koArgs(typesAll_, _values, _conf, _stackCall)) {
            return new Argument();
        }
        call_.getArgumentWrappers().addAllElts(_values);
        return prepareCallDyn(_previous, call_, _conf, _stackCall);
    }

    private static boolean koArgs(StringList _typesAll,CustList<ArgumentWrapper> _values, ContextEl _conf, StackCall _stackCall) {
        int i_ = IndexConstants.FIRST_INDEX;
        for (ArgumentWrapper a:_values) {
            String param_ = _typesAll.get(i_);
            String arg_ = getClName(_conf, _stackCall, a);
            if (!ExecTemplates.checkQuick(param_, arg_, _conf, _stackCall)) {
                return true;
            }
            i_++;
        }
        return false;
    }
    private static String getClName(ContextEl _conf, StackCall _stackCall, ArgumentWrapper _a) {
        AbstractWrapper wr_ = _a.getWrapper();
        String arg_;
        if (wr_ == null) {
            arg_ = _a.getValue().getStruct().getClassName(_conf);
        } else {
            arg_ = wr_.getClassName(_stackCall, _conf);
        }
        return arg_;
    }

    private static int countWrappers(CustList<ArgumentWrapper> _values) {
        int wrappers_ = 0;
        for (ArgumentWrapper a: _values) {
            AbstractWrapper wrapper_ = a.getWrapper();
            if (wrapper_ != null) {
                wrappers_++;
            }
        }
        return wrappers_;
    }

    private static int countValues(CustList<ArgumentWrapper> _values) {
        int values_ = 0;
        for (ArgumentWrapper a: _values) {
            AbstractWrapper wrapper_ = a.getWrapper();
            if (wrapper_ == null) {
                values_++;
            }
        }
        return values_;
    }

    private static void feedTypes(CustList<String> _paramsFct, StringList _typesRef, StringList _types, StringList _typesAll) {
        int parNb_ = _paramsFct.size();
        for (int i = 0; i < parNb_; i++) {
            String param_ = _paramsFct.get(i);
            if (param_.startsWith("~")) {
                _typesRef.add(param_.substring(1));
                _typesAll.add(param_.substring(1));
            } else {
                _types.add(param_);
                _typesAll.add(param_);
            }
        }
    }

    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }
    public static Argument prepareCallDyn(Argument _previous, ArgumentListCall _values, ContextEl _conf, StackCall _stackCall) {
        Struct ls_ = Argument.getNullableValue(_previous).getStruct();
        LgNames lgNames_ = _conf.getStandards();
        if (ls_ instanceof LambdaRecordConstructorStruct) {
            LambdaRecordConstructorStruct l_ = (LambdaRecordConstructorStruct) ls_;
            if (l_.isSafeInstance()) {
                return defaultValueLambda(_conf, l_);
            }
            ExecFormattedRootBlock clName_ = l_.getFormClassName();
            CustList<Argument> values_ = _values.getArguments();
            if (!l_.isShiftInstance()) {
                ExecRootBlock type_ = l_.getRoot();
                if (withInstance(type_)) {
                    Argument instance_ = ExecHelper.getFirstArgument(values_);
                    _stackCall.setCallingState(new CustomReflectRecordConstructor(instance_,l_.getRoot(), l_.getNamedFields(), clName_,values_.mid(1),true));
                    return new Argument();
                }
            }
            Argument instance_ = l_.getInstanceCall();
            _stackCall.setCallingState(new CustomReflectRecordConstructor(instance_,l_.getRoot(), l_.getNamedFields(), clName_,values_,true));
            return new Argument();
        }
        if (ls_ instanceof LambdaConstructorStruct) {
            return lambdaConstructor(_values, _conf, _stackCall, (LambdaConstructorStruct) ls_);
        }
        if (ls_ instanceof LambdaFieldStruct) {
            return lambdaField(_values, _conf, _stackCall, (LambdaFieldStruct) ls_);
        }
        if (ls_ instanceof LambdaMethodStruct) {
            return lambdaMethod(_values, _conf, _stackCall, (LambdaMethodStruct) ls_);
        }
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }

    private static Argument lambdaMethod(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaMethodStruct _ls) {
        int nbAncestors_ = _ls.getAncestor();
        boolean static_ = _ls.getKind() != MethodAccessKind.INSTANCE;
        if (_ls.isSafeInstance()) {
            return defaultValueLambda(_conf, _ls);
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
        if (StringUtil.quickEq(_ls.getMethodName(),":")) {
            ArgumentWrapper formal_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
            return new Argument(ExecTemplates.getWrap(formal_.getWrapper()).getValue(_stackCall,_conf));
        }
        if (StringUtil.quickEq(_ls.getMethodName(),"=")) {
            ArgumentWrapper formal_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
            Argument right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
            ExecTemplates.getWrap(formal_.getWrapper()).setValue(_stackCall,_conf,right_);
            return right_;
        }
        CustList<ArgumentWrapper> formal_;
        Argument right_;
        if (StringUtil.quickEq(_ls.getMethodName(),"[]=")||StringUtil.quickEq(_ls.getMethodName(),"[:]=")) {
            formal_ = argumentWrappers_.left(argumentWrappers_.size()-1);
            right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
        } else {
            formal_ = argumentWrappers_;
            right_ = null;
        }
        Struct instanceStruct_ = _ls.getInstanceCall().getStruct();
        ArgumentListCall call_ = new ArgumentListCall();
        if (!_ls.isShiftInstance()) {
            Argument par_ = parent(static_, nbAncestors_, instanceStruct_, _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            call_.getArgumentWrappers().addAllElts(formal_);
            call_.setRight(right_);
            return redirect(_conf, _ls,par_, call_, _stackCall);
        }
        if (StringExpUtil.isOper(_ls.getMethodName())) {
            formal_.add(0,new ArgumentWrapper(new Argument(instanceStruct_),null));
            call_.getArgumentWrappers().addAllElts(formal_);
            call_.setRight(right_);
            return redirect(_conf, _ls, Argument.createVoid(), call_, _stackCall);
        }
        CustList<ArgumentWrapper> arr_ = formal_.mid(1);
        Struct value_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(formal_)).getStruct();
        Argument par_ = parent(static_, nbAncestors_, value_, _conf, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        call_.getArgumentWrappers().addAllElts(arr_);
        call_.setRight(right_);
        return redirect(_conf, _ls, par_, call_, _stackCall);
    }

    private static Argument lambdaField(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaFieldStruct _ls) {
        Struct metaInfo_ = _ls.getMetaInfo();
        if (!(metaInfo_ instanceof FieldMetaInfo)) {
            return virtualField(_values, _conf, _stackCall, _ls);
        }
        FieldMetaInfo method_ = (FieldMetaInfo)metaInfo_;
        boolean static_ = _ls.isStaticField();
        int nbAncestors_ = _ls.getAncestor();
        if (_ls.isSafeInstance()) {
            return defaultValueLambda(_conf, _ls);
        }
        Struct value_ = retrInstance(_values, _ls);
        Argument par_ = parent(static_, nbAncestors_, value_, _conf, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        ReflectingType type_;
        boolean aff_ = _ls.isAffect();
        if (aff_) {
            type_ = ReflectingType.SET_FIELD;
            CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
            _stackCall.setCallingState(new CustomReflectSetField(type_, method_, par_, ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)), true));
            return new Argument();
        }
        type_ = ReflectingType.GET_FIELD;
        _stackCall.setCallingState(new CustomReflectGetField(type_, method_, par_, true));
        return new Argument();
    }

    private static Argument lambdaConstructor(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaConstructorStruct _ls) {
        Struct metaInfo_ = _ls.getMetaInfo();
        if (!(metaInfo_ instanceof ConstructorMetaInfo)) {
            CustList<Argument> values_ = _values.getArguments();
            return initArray(_conf, _stackCall, values_, _ls);
        }
        if (_ls.isSafeInstance()) {
            return defaultValueLambda(_conf, _ls);
        }
        ConstructorMetaInfo meta_ = (ConstructorMetaInfo)metaInfo_;
        ArgumentListCall call_ = new ArgumentListCall();
        CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
        if (!_ls.isShiftInstance()) {
            ExecRootBlock type_ = meta_.getPairType();
            if (withInstance(type_)) {
                Argument instance_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(argumentWrappers_));
                call_.getArgumentWrappers().addAllElts(argumentWrappers_.mid(1));
                _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, true));
                return new Argument();
            }
        }
        Argument instance_ = _ls.getInstanceCall();
        call_.getArgumentWrappers().addAllElts(argumentWrappers_);
        _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, true));
        return new Argument();
    }

    protected static boolean withInstance(ExecRootBlock _type) {
        return _type != null && !_type.withoutInstance();
    }

    private static Argument virtualField(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaFieldStruct _l) {
        Struct realInstance_ = retrInstance(_values, _l);
        if (_l.isToStrField()) {
            _stackCall.setCallingState(new CustomReflectLambdaToStr(new Argument(realInstance_)));
            return Argument.createVoid();
        }
        if (_l.isRdCodField()) {
            _stackCall.setCallingState(new CustomReflectLambdaRdCod(new Argument(realInstance_)));
            return Argument.createVoid();
        }
        if (_l.isInstanceField()) {
            String ownerType_ = StringUtil.nullToEmpty(_l.getOwnerType());
            boolean res_ = ExecInherits.safeObject(ownerType_, realInstance_.getClassName(_conf), _conf) == ErrorType.NOTHING;
            return new Argument(BooleanStruct.of(res_));
        }
        return new Argument(realInstance_.getParent());
    }

    private static Argument initArray(ContextEl _conf, StackCall _stackCall, CustList<Argument> _values, LambdaConstructorStruct _l) {
        Ints dims_ = new Ints();
        for (Argument a: _values) {
            int dim_ = NumParsers.convertToNumber(a.getStruct()).intStruct();
            dims_.add(dim_);
        }
        String c_ = StringUtil.nullToEmpty(_l.getFormClassName());
        Struct res_ = ExecTemplates.newCustomArrayOrExc(c_, dims_, _conf, _stackCall);
        if (res_ instanceof ErrorStruct) {
            _stackCall.setCallingState(new CustomFoundExc(res_));
            return new Argument();
        }
        return new Argument(res_);
    }

    private static Argument parent(boolean _static, int _nbAnc, Struct _instanceStruct,ContextEl _conf, StackCall _stackCall) {
        Argument instance_;
        if (!_static) {
            instance_ = new Argument(ExecTemplates.getParent(_nbAnc, _instanceStruct, _conf, _stackCall));
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
        } else {
            instance_ = new Argument();
        }
        return instance_;
    }
    private static Struct retrInstance(ArgumentListCall _values, LambdaFieldStruct _ldaField) {
        Argument instance_ = _ldaField.getInstanceCall();
        Struct realInstance_;
        if (!_ldaField.isShiftInstance()) {
            realInstance_ = instance_.getStruct();
        } else {
            CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
            realInstance_ = ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(argumentWrappers_)).getStruct();
        }
        return realInstance_;
    }

    private static Argument defaultValueLambda(ContextEl _conf, LambdaStruct _l) {
        String last_ = StringExpUtil.getAllTypes(_l.getClassName(_conf)).last();
        return new Argument(ExecClassArgumentMatching.defaultValue(last_, _conf));
    }

    private static Argument redirect(ContextEl _conf, LambdaMethodStruct _l, Argument _instance, ArgumentListCall _call, StackCall _stackCall) {
        Struct metaInfo_ = _l.getMetaInfo();
        if (!(metaInfo_ instanceof MethodMetaInfo)) {
            return arrMethods(_conf, _l, _instance, _call, _stackCall);
        }
        MethodMetaInfo method_ = (MethodMetaInfo)metaInfo_;
        if (method_.getStdCallee() != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STD_FCT, method_, _instance,_call, true));
            return new Argument();
        }
        if (method_.getPairFct() instanceof ExecAnonymousFunctionBlock) {
            if (method_.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, method_, _instance,_call, true));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, method_, _instance,_call, true));
            return new Argument();
        }
        ExecRootBlock e_ = method_.getPairType();
        if (e_ instanceof ExecAnnotationBlock) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ANNOT_FCT, method_, _instance,_call, true));
            return new Argument();
        }
        if (method_.getPairFct() != null) {
            if (method_.isExpCast()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST, method_, _instance,_call, true));
                return new Argument();
            }
            if (method_.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, method_, _instance,_call, true));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, method_, _instance,_call, true));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.METHOD, method_, _instance,_call, true));
            return new Argument();
        }
        if (e_ != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ENUM_METHODS, method_, _instance,_call, true));
            return new Argument();
        }
        if (method_.isDirectCast()) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST_DIRECT, method_, _instance,_call, true));
            return new Argument();
        }
        _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CLONE_FCT, method_, _instance,_call, true));
        return new Argument();
    }

    private static Argument arrMethods(ContextEl _conf, LambdaMethodStruct _l, Argument _instance, ArgumentListCall _call, StackCall _stackCall) {
        String name_ = _l.getMethodName();
        CustList<Argument> arguments_ = _call.getArguments();
        Struct arr_ = _instance.getStruct();
        if (arguments_.isEmpty()) {
            return new Argument(new IntStruct(ExecArrayFieldOperation.getLength(arr_, _conf)));
        }
        if (StringUtil.quickEq(name_,"[:]")) {
            return rangeInts(_conf, _stackCall, arguments_, arr_);
        }
        if (StringUtil.quickEq(name_,"[:]=")) {
            return rangeIntsSet(_conf, _stackCall, arguments_, arr_,Argument.getNullableValue(_call.getRight()).getStruct());
        }
        Struct range_ = arguments_.last().getStruct();
        if (range_ instanceof RangeStruct) {
            Argument right_ = _call.getRight();
            if (right_ != null) {
                return new Argument(ExecTemplates.setRange(arr_,(RangeStruct) range_, right_.getStruct(),_conf,_stackCall));
            }
            return new Argument(ExecTemplates.getRange(arr_,range_, _conf, _stackCall));
        }
        return defArr(_conf, _call, _stackCall, name_, arguments_, arr_);
    }

    private static Argument defArr(ContextEl _conf, ArgumentListCall _call, StackCall _stackCall, String _name, CustList<Argument> _arguments, Struct _arrOrig) {
        int lastIndex_ = _arguments.size() - 1;
        Struct arr_ = _arrOrig;
        if (StringUtil.quickEq(_name,"[]=")) {
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = _arguments.get(i).getStruct();
                arr_ = ExecTemplates.getElement(arr_, ind_, _conf, _stackCall);
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
            }
            Struct ind_ = _arguments.get(lastIndex_).getStruct();
            ExecTemplates.setElement(arr_,ind_, Argument.getNullableValue(_call.getRight()).getStruct(), _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return _call.getRight();
        }
        for (int i = 0; i < lastIndex_; i++) {
            Struct ind_ = _arguments.get(i).getStruct();
            arr_ = ExecTemplates.getElement(arr_,ind_, _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
        }
        Struct ind_ = _arguments.get(lastIndex_).getStruct();
        return new Argument(ExecTemplates.getElement(arr_,ind_, _conf, _stackCall));
    }

    private static Argument rangeInts(ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments, Struct _arr) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            Argument range_ = FctRangeUnlimitedStep.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return new Argument(ExecTemplates.getRange(_arr,range_.getStruct(), _conf, _stackCall));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Argument range_ = FctRangeUnlimitedStep.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        return new Argument(ExecTemplates.getRange(_arr,range_.getStruct(), _conf, _stackCall));
    }
    private static Argument rangeIntsSet(ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments, Struct _arr, Struct _right) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            Argument range_ = FctRangeUnlimitedStep.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return new Argument(ExecTemplates.setRange(_arr, (RangeStruct) range_.getStruct(),_right, _conf, _stackCall));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Argument range_ = FctRangeUnlimitedStep.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        return new Argument(ExecTemplates.setRange(_arr, (RangeStruct) range_.getStruct(),_right, _conf, _stackCall));
    }

    private static Argument getEnumValues(AbstractExiting _exit, ExecRootBlock _root, ContextEl _conf, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _root)) {
            return Argument.createVoid();
        }
        String wc_ = _root.getWildCardElement();
        String id_ = StringExpUtil.getIdFromAllTypes(wc_);
        Classes classes_ = _conf.getClasses();
        CustList<Struct> enums_ = new CustList<Struct>();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            Struct str_ = classes_.getStaticField(new ClassField(id_, fieldName_),b.getImportedClassName(),_conf);
            _stackCall.getInitializingTypeInfos().addSensibleField(id_, str_, _stackCall);
            enums_.add(str_);
        }
        String clArr_ = wc_;
        clArr_ = StringExpUtil.getPrettyArrayType(clArr_);
        ArrayStruct array_ = new ArrayStruct(enums_.size(), clArr_);
        int i_ = IndexConstants.FIRST_INDEX;
        for (Struct o: enums_) {
            array_.set(i_,o);
            i_++;
        }
        return new Argument(array_);
    }
    private static Argument getEnumValue(AbstractExiting _exit, ExecRootBlock _root, Argument _name, ContextEl _conf, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _root)) {
            return Argument.createVoid();
        }
        Struct name_ = _name.getStruct();
        if (!(name_ instanceof StringStruct)) {
            return new Argument();
        }
        Classes classes_ = _conf.getClasses();
        String enumName_ = _root.getFullName();
        for (ExecInnerTypeOrElement b: _root.getEnumElements()) {
            String fieldName_ = b.getUniqueFieldName();
            if (StringUtil.quickEq(fieldName_, ((StringStruct) name_).getInstance())) {
                Struct str_ = classes_.getStaticField(new ClassField(enumName_, fieldName_),b.getImportedClassName(),_conf);
                _stackCall.getInitializingTypeInfos().addSensibleField(enumName_, str_, _stackCall);
                return new Argument(str_);
            }
        }
        return new Argument();
    }
}
