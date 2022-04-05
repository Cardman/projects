package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Ints;
import code.util.core.StringUtil;

public abstract class AbstractParamChecker {
    protected static final String RETURN_LINE = "\n";

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
                    _stackCall.setCallingState(new CustomReflectRecordConstructor(instance_,l_.getRoot(), l_.getNamedFields(), clName_,values_.mid(1), l_.getInts()));
                    return new Argument();
                }
            }
            Argument instance_ = l_.getInstanceCall();
            _stackCall.setCallingState(new CustomReflectRecordConstructor(instance_,l_.getRoot(), l_.getNamedFields(), clName_,values_, l_.getInts()));
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
            _stackCall.setCallingState(new CustomReflectLambdaVarMethod(ReflectingType.VAR_GET, _values));
            return new Argument();
        }
        if (StringUtil.quickEq(_ls.getMethodName(),"=")) {
            _stackCall.setCallingState(new CustomReflectLambdaVarMethod(ReflectingType.VAR_SET, _values));
            return new Argument();
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
                _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_));
                return new Argument();
            }
        }
        Argument instance_ = _ls.getInstanceCall();
        call_.getArgumentWrappers().addAllElts(argumentWrappers_);
        _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_));
        return new Argument();
    }

    public static boolean withInstance(ExecRootBlock _type) {
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
        Struct res_ = ExecArrayTemplates.newCustomArrayOrExc(c_, dims_, _conf, _stackCall);
        if (res_ instanceof ErrorStruct) {
            _stackCall.setCallingState(new CustomFoundExc(res_));
            return new Argument();
        }
        return new Argument(res_);
    }

    private static Argument parent(boolean _static, int _nbAnc, Struct _instanceStruct, ContextEl _conf, StackCall _stackCall) {
        Argument instance_;
        if (!_static) {
            instance_ = new Argument(ExecFieldTemplates.getParent(_nbAnc, _instanceStruct, _conf, _stackCall));
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
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STD_FCT, method_, _instance,_call));
            return new Argument();
        }
        if (method_.getPairFct() instanceof ExecAnonymousFunctionBlock) {
            if (method_.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, method_, _instance,_call));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, method_, _instance,_call));
            return new Argument();
        }
        ExecRootBlock e_ = method_.getPairType();
        if (e_ instanceof ExecAnnotationBlock) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ANNOT_FCT, method_, _instance,_call));
            return new Argument();
        }
        if (method_.getPairFct() != null) {
            if (method_.isExpCast()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST, method_, _instance,_call));
                return new Argument();
            }
            if (method_.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.STATIC_CALL, method_, _instance,_call));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.DIRECT, method_, _instance,_call));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.METHOD, method_, _instance,_call));
            return new Argument();
        }
        if (e_ != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.ENUM_METHODS, method_, _instance,_call));
            return new Argument();
        }
        if (method_.isDirectCast()) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CAST_DIRECT, method_, _instance,_call));
            return new Argument();
        }
        _stackCall.setCallingState(new CustomReflectLambdaMethod(ReflectingType.CLONE_FCT, method_, _instance,_call));
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
                return new Argument(ExecArrayTemplates.setRange(arr_,(RangeStruct) range_, right_.getStruct(),_conf,_stackCall));
            }
            return new Argument(ExecArrayTemplates.getRange(arr_,range_, _conf, _stackCall));
        }
        return defArr(_conf, _call, _stackCall, name_, arguments_, arr_);
    }

    private static Argument defArr(ContextEl _conf, ArgumentListCall _call, StackCall _stackCall, String _name, CustList<Argument> _arguments, Struct _arrOrig) {
        int lastIndex_ = _arguments.size() - 1;
        Struct arr_ = _arrOrig;
        if (StringUtil.quickEq(_name,"[]=")) {
            for (int i = 0; i < lastIndex_; i++) {
                Struct ind_ = _arguments.get(i).getStruct();
                arr_ = ExecArrayTemplates.getElement(arr_, ind_, _conf, _stackCall);
                if (_conf.callsOrException(_stackCall)) {
                    return new Argument();
                }
            }
            Struct ind_ = _arguments.get(lastIndex_).getStruct();
            ExecArrayTemplates.setElement(arr_,ind_, Argument.getNullableValue(_call.getRight()).getStruct(), _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return _call.getRight();
        }
        for (int i = 0; i < lastIndex_; i++) {
            Struct ind_ = _arguments.get(i).getStruct();
            arr_ = ExecArrayTemplates.getElement(arr_,ind_, _conf, _stackCall);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
        }
        Struct ind_ = _arguments.get(lastIndex_).getStruct();
        return new Argument(ExecArrayTemplates.getElement(arr_,ind_, _conf, _stackCall));
    }

    private static Argument rangeInts(ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments, Struct _arr) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            Argument range_ = RangeChecker.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return new Argument(ExecArrayTemplates.getRange(_arr,range_.getStruct(), _conf, _stackCall));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Argument range_ = RangeChecker.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        return new Argument(ExecArrayTemplates.getRange(_arr,range_.getStruct(), _conf, _stackCall));
    }

    private static Argument rangeIntsSet(ContextEl _conf, StackCall _stackCall, CustList<Argument> _arguments, Struct _arr, Struct _right) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            Argument range_ = RangeChecker.rangeUnlimitStep(_conf, _stackCall, lower_, step_);
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
            return new Argument(ExecArrayTemplates.setRange(_arr, (RangeStruct) range_.getStruct(),_right, _conf, _stackCall));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        Argument range_ = RangeChecker.range(_conf, _stackCall, lower_, upper_);
        if (_conf.callsOrException(_stackCall)) {
            return new Argument();
        }
        return new Argument(ExecArrayTemplates.setRange(_arr, (RangeStruct) range_.getStruct(),_right, _conf, _stackCall));
    }

    public Argument checkParams(ExecFormattedRootBlock _classNameFound, Argument _previous, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        ExecFormattedRootBlock classFormat_ = checkFormmattedParams(_classNameFound, _previous, _conf, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        Parameters parameters_ = check(classFormat_, _cache, _conf, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return Argument.createVoid();
        }
        //SwitchParamChecker
        /*Parameters parameters_ = f_.getParameters();
            _stack.setCallingState(new CustomFoundSwitch(_instance,f_.getFormattedClass(),getPair().getType(),(ExecAbstractSwitchMethod) callee_,parameters_.getCache(),new Argument(parameters_.getRefParameters().firstValue().getValue(_stack,_context))));*/
        //
        /*
        if (_state == CallPrepareState.METHOD) {
            return classFormat_;
        }
        Parameters parameters_ = f_.getParameters();
        if (_state == CallPrepareState.CTOR) {
            _stackCall.setCallingState(new CustomFoundConstructor(_classNameFound, _methodId, EMPTY_STRING, -1, _previous, parameters_, _kindCall));
            return f_;
        }
        _stackCall.setCallingState(new CustomFoundMethod(_classNameFound, _methodId, parameters_));
        return f_;*/
        FormattedParameters f_ = new FormattedParameters();
        f_.setParameters(parameters_);
        f_.setFormattedClass(classFormat_);
        return redirect(_conf,_classNameFound,_previous,_stackCall,f_);
    }

    public abstract ExecFormattedRootBlock checkFormmattedParams(ExecFormattedRootBlock _classNameFound, Argument _previous, ContextEl _conf, StackCall _stackCall);
    public abstract Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat);

    public abstract Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall);
}
