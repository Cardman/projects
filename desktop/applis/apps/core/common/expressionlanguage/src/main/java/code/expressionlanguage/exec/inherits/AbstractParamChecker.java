package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.StringUtil;

public abstract class AbstractParamChecker {
    protected static final String RETURN_LINE = "\n";

    public static Argument prepareCallDyn(Argument _previous, ArgumentListCall _values, int _ref, ContextEl _conf, StackCall _stackCall) {
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
                    _stackCall.setCallingState(new CustomReflectRecordConstructor(instance_,l_.getRoot(), l_.getNamedFields(), clName_,values_.mid(1), l_.getInts(),_ref));
                    return new Argument();
                }
            }
            Argument instance_ = l_.getInstanceCall();
            _stackCall.setCallingState(new CustomReflectRecordConstructor(instance_,l_.getRoot(), l_.getNamedFields(), clName_,values_, l_.getInts(),_ref));
            return new Argument();
        }
        if (ls_ instanceof LambdaConstructorStruct) {
            return lambdaConstructor(_values, _conf, _stackCall, (LambdaConstructorStruct) ls_, _ref);
        }
        if (ls_ instanceof LambdaFieldStruct) {
            return lambdaField(_values, _conf, _stackCall, (LambdaFieldStruct) ls_);
        }
        if (ls_ instanceof LambdaMethodStruct) {
            return lambdaMethod(_values, _conf, _stackCall, (LambdaMethodStruct) ls_,_ref);
        }
        String null_;
        null_ = lgNames_.getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_, _stackCall)));
        return Argument.createVoid();
    }

    private static Argument lambdaMethod(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaMethodStruct _ls, int _ref) {
        if (_ls.isSafeInstance()) {
            return defaultValueLambda(_conf, _ls);
        }
        if (StringUtil.quickEq(_ls.getMethodName(),":")) {
            _stackCall.setCallingState(new CustomReflectLambdaVarMethod(ReflectingType.VAR_GET, _values));
            return new Argument();
        }
        if (StringUtil.quickEq(_ls.getMethodName(),"=")) {
            _stackCall.setCallingState(new CustomReflectLambdaVarMethod(ReflectingType.VAR_SET, _values));
            return new Argument();
        }
        return redirect(_ls, _values, _stackCall, _ref);
    }

    private static Argument lambdaField(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaFieldStruct _ls) {
        Struct metaInfo_ = _ls.getMetaInfo();
        if (!(metaInfo_ instanceof FieldMetaInfo)) {
            return virtualField(_values, _stackCall, _ls);
        }
        FieldMetaInfo method_ = (FieldMetaInfo)metaInfo_;
        if (_ls.isSafeInstance()) {
            return defaultValueLambda(_conf, _ls);
        }
        boolean aff_ = _ls.isAffect();
        if (aff_) {
            CustList<ArgumentWrapper> argumentWrappers_ = _values.getArgumentWrappers();
            _stackCall.setCallingState(new CustomReflectSetField(new FieldLambdaParentRetriever(_values,_ls), method_, ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)), true, _ls.getAncestor()));
            return new Argument();
        }
        _stackCall.setCallingState(new CustomReflectGetField(new FieldLambdaParentRetriever(_values,_ls), method_, true, _ls.getAncestor()));
        return new Argument();
    }

    private static Argument lambdaConstructor(ArgumentListCall _values, ContextEl _conf, StackCall _stackCall, LambdaConstructorStruct _ls, int _ref) {
        Struct metaInfo_ = _ls.getMetaInfo();
        if (!(metaInfo_ instanceof ConstructorMetaInfo)) {
            _stackCall.setCallingState(new CustomReflectLambdaConstructorWithoutInfo(_ls,_values));
            return new Argument();
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
                _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, _ref));
                return new Argument();
            }
        }
        Argument instance_ = _ls.getInstanceCall();
        call_.getArgumentWrappers().addAllElts(argumentWrappers_);
        _stackCall.setCallingState(new CustomReflectLambdaConstructor(meta_, instance_.getStruct(),call_, _ref));
        return new Argument();
    }

    public static boolean withInstance(ExecRootBlock _type) {
        return _type != null && !_type.withoutInstance();
    }

    private static Argument virtualField(ArgumentListCall _values, StackCall _stackCall, LambdaFieldStruct _l) {
        Struct realInstance_ = FieldLambdaParentRetriever.retrInstance(_values, _l);
        if (_l.isToStrField()) {
            _stackCall.setCallingState(new CustomReflectLambdaToStr(new Argument(realInstance_)));
            return Argument.createVoid();
        }
        if (_l.isRdCodField()) {
            _stackCall.setCallingState(new CustomReflectLambdaRdCod(new Argument(realInstance_)));
            return Argument.createVoid();
        }
        _stackCall.setCallingState(new CustomReflectLambdaFieldWithoutInfo(_l,_values));
        return Argument.createVoid();
    }

    private static Argument defaultValueLambda(ContextEl _conf, LambdaStruct _l) {
        String last_ = StringExpUtil.getAllTypes(_l.getClassName(_conf)).last();
        return new Argument(ExecClassArgumentMatching.defaultValue(last_, _conf));
    }

    private static Argument redirect(LambdaMethodStruct _l, ArgumentListCall _call, StackCall _stackCall, int _ref) {
        Struct metaInfo_ = _l.getMetaInfo();
        if (!(metaInfo_ instanceof MethodMetaInfo)) {
            _stackCall.setCallingState(new CustomReflectLambdaMethodWithoutInfo(_l, _call));
            return new Argument();
        }
        MethodMetaInfo method_ = (MethodMetaInfo)metaInfo_;
        if (method_.getStdCallee() != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.STD_FCT, method_, _call, _ref));
            return new Argument();
        }
        if (method_.getPairFct() instanceof ExecAnonymousFunctionBlock) {
            if (method_.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.STATIC_CALL, method_, _call, _ref));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.DIRECT, method_, _call, _ref));
            return new Argument();
        }
        ExecRootBlock e_ = method_.getPairType();
        if (e_ instanceof ExecAnnotationBlock) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.ANNOT_FCT, method_, _call, _ref));
            return new Argument();
        }
        if (method_.getPairFct() != null) {
            if (method_.isExpCast()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.CAST, method_, _call, _ref));
                return new Argument();
            }
            if (method_.isStaticCall()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.STATIC_CALL, method_, _call, _ref));
                return new Argument();
            }
            if (!_l.isPolymorph()) {
                _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.DIRECT, method_, _call, _ref));
                return new Argument();
            }
            _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.METHOD, method_, _call, _ref));
            return new Argument();
        }
        if (e_ != null) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.ENUM_METHODS, method_, _call, _ref));
            return new Argument();
        }
        if (method_.isDirectCast()) {
            _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.CAST_DIRECT, method_, _call, _ref));
            return new Argument();
        }
        _stackCall.setCallingState(new CustomReflectLambdaMethod(_l,ReflectingType.CLONE_FCT, method_, _call, _ref));
        return new Argument();
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
