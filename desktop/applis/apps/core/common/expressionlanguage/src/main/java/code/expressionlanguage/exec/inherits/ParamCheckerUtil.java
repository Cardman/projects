package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.AfterInitExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRecordBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundAnnotation;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstancingAnnotContent;
import code.expressionlanguage.fwd.opers.ExecInstancingCustContent;
import code.expressionlanguage.fwd.opers.ExecInstancingStdContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ParamCheckerUtil {
    private ParamCheckerUtil() {
    }

    public static ArgumentWrapper instancePrepareStd(ContextEl _conf, StandardConstructor _ctor, ConstructorId _constId,
                                                     ArgumentListCall _arguments, StackCall _stackCall) {
        CustList<Struct> args_ = _arguments.getArguments();
        CustomFoundExc ex_ = ExecTemplates.checkParams(_conf, "", _constId, null, args_, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(ex_);
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        StdCaller caller_ = StandardType.caller(_ctor, null);
        AfterInitExiting exit_ = new AfterInitExiting(_conf);
        return _conf.getCaller().invoke(caller_, exit_,_conf,NullStruct.NULL_VALUE,_arguments,_stackCall);
    }

    public static Struct callStd(AbstractExiting _exit, ContextEl _cont, ClassMethodId _classNameFound, Struct _previous, ArgumentListCall _firstArgs, StackCall _stackCall, StandardMethod _stdMeth) {
        CustList<Struct> args_ = _firstArgs.getArguments();
        CustomFoundExc ex_ = ExecTemplates.checkParams(_cont, _classNameFound.getClassName(), _classNameFound.getConstraints(), _previous, args_, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(ex_);
            return NullStruct.NULL_VALUE;
        }
        StdCaller caller_ = _stdMeth.getCaller();
        return _cont.getCaller().invoke(caller_,_exit,_cont,_previous,_firstArgs,_stackCall).getValue();
    }

    public static Struct prep(ContextEl _conf, StackCall _stack, Struct _previous, ExecFormattedRootBlock _className, CustList<ExecOperationInfo> _infos, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        if (_instancingCommonContent.getPair().getType() instanceof ExecRecordBlock) {
            CustList<Struct> arguments_ = getArguments(_infos);
            Struct prev_ = instance(_instancingCommonContent.getPair().getType(),_previous);
            _stack.setCallingState(new CustomFoundRecordConstructor(prev_,_className, _instancingCommonContent.getPair(),_instancingStdContent, arguments_));
        } else {
            new InstanceParamChecker(_instancingCommonContent.getPair(), ExecInvokingOperation.fectchInstFormattedArgs(_className, _instancingCommonContent, _conf, _stack, _infos), _instancingStdContent.getFieldName(), _instancingStdContent.getBlockIndex()).checkParams(_className, _previous, null, _conf, _stack);
        }
        return NullStruct.NULL_VALUE;
    }

    private static CustList<Struct> getArguments(CustList<ExecOperationInfo> _nodes) {
        CustList<Struct> a_ = new CustList<Struct>();
        for (ExecOperationInfo o: _nodes) {
            a_.add(o.getPair().getArgument());
        }
        return a_;
    }

    public static Struct instance(ExecRootBlock _type, Struct _previous) {
        if (!AbstractParamChecker.withInstance(_type)) {
            return NullStruct.NULL_VALUE;
        }
        return _previous;
    }

    public static boolean checkCustomOper(AbstractExiting _exit, ExecTypeFunction _rootBlock, ExecFormattedRootBlock _paramNameOwner, ContextEl _conf, Struct _fwd, StackCall _stackCall, ArgumentListCall _list) {
        if (_exit.hasToExit(_stackCall, _paramNameOwner.getRootBlock(),_fwd)) {
            return true;
        }
        new StaticCallParamChecker(_rootBlock,_list).checkParams(_paramNameOwner,NullStruct.NULL_VALUE,null,_conf,_stackCall);
        return false;
    }

    public static void redirectAnnotation(ContextEl _conf, StackCall _stack, CustList<Struct> _arguments, ExecInstancingAnnotContent _instancingAnnotContent) {
        ExecFormattedRootBlock formattedType_ = _instancingAnnotContent.getFormattedType();
        if (!_conf.getExiting().hasToExit(_stack, formattedType_.getRootBlock())) {
            _stack.setCallingState(new CustomFoundAnnotation(formattedType_, formattedType_.getRootBlock(), _instancingAnnotContent.getFieldNames(), _arguments));
        }
    }

    public static Struct prep(ContextEl _conf, StackCall _stack, Struct _previous, ExecFormattedRootBlock _className, CustList<ExecOperationInfo> _infos, ExecInstancingCustContent _instancingCommonContent) {
        if (!_conf.getExiting().hasToExit(_stack, _className.getRootBlock())) {
            new InstanceParamChecker(_instancingCommonContent.getPair(), ExecInvokingOperation.fectchInstFormattedArgs(_className, _instancingCommonContent,_conf,_stack, _infos), "", -1).checkParams(_className, _previous, null, _conf, _stack);
        }
        return NullStruct.NULL_VALUE;
    }

    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named, ArgumentListCall _firstArgs, ExecFormattedRootBlock _classNameFound, MethodAccessKind _kind, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _classNameFound.getRootBlock())) {
            return;
        }
        new DefaultParamChecker(_named, _firstArgs, _kind, CallPrepareState.OPERATOR).checkParams(_classNameFound, NullStruct.NULL_VALUE, null, _conf, _stackCall);
    }

    public static Struct processEnums(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall, ExecRootBlock _type) {
        //static enum methods
        LgNames stds_ = _cont.getStandards();
        CustList<Struct> args_ = _firstArgs.getArguments();
        if (args_.size() != 1) {
            return IndirectCalledFctUtil.tryGetEnumValues(_exit, _cont, _type,  ClassCategory.ENUM, _stackCall);
        }
        Struct arg_ = args_.first();
        Struct ex_ = ExecInheritsAdv.checkObjectEx(stds_.getContent().getCharSeq().getAliasString(), ArgumentListCall.getNull(arg_).getClassName(_cont), _cont, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
            return NullStruct.NULL_VALUE;
        }
        return IndirectCalledFctUtil.tryGetEnumValue(_exit, _cont, _type, ClassCategory.ENUM, arg_, _stackCall);
    }

    public static Struct tryInit(ContextEl _conf, StackCall _stack, ExecRootBlock _root){
        if (_conf.getExiting().hasToExit(_stack, _root)) {
            return NullStruct.NULL_VALUE;
        }
        return null;
    }

    public static Struct prepare(AbstractExiting _exit, ExecTypeFunction _rootBlock,
                                   ExecFormattedRootBlock _classNameOwner, ContextEl _conf, StackCall _stackCall, ArgumentListCall _list) {
        checkCustomOper(_exit, _rootBlock, StackCall.formatVarType(_stackCall,_classNameOwner), _conf, null, _stackCall, _list);
        return NullStruct.NULL_VALUE;
    }

    public static int processConverter(ContextEl _conf, Struct _right, ImplicitMethods _implicits, int _indexImplicit, StackCall _stackCall) {
        ExecFormattedRootBlock formatted_ = StackCall.formatVarType(_stackCall, _implicits.getOwnerClass());
        ExecTypeFunction c = _implicits.get(_indexImplicit);
        AbstractExiting ex_ = _conf.getExiting();
        ArgumentListCall l_ = new ArgumentListCall(ArgumentListCall.getNull(_right));
        if (checkCustomOper(ex_, c, formatted_, _conf, _right, _stackCall, l_)) {
            return _indexImplicit;
        }
        return _indexImplicit +1;
    }

    public static Struct prep(ContextEl _conf, StackCall _stack, Struct _previous, CustList<ExecOperationInfo> _infos, ExecStdFctContent _stdFctContent) {
        Struct prev_;
        Struct res_ = null;
        if (!_stdFctContent.isStaticMethod()) {
            prev_ = ExecFieldTemplates.getParent(0, _previous, _conf, _stack);
            if (_conf.callsOrException(_stack)) {
                res_ = NullStruct.NULL_VALUE;
            }
        } else {
            prev_ = NullStruct.NULL_VALUE;
        }
        if (res_ == null) {
            res_ = callStd(_conf.getExiting(), _conf, _stdFctContent.getClassMethodId(), prev_, ExecInvokingOperation.fectchArgs(_stdFctContent.getLastType(), _stdFctContent.getNaturalVararg(), _conf, _stack, _infos), _stack, _stdFctContent.getStandardMethod());
        }
        return res_;
    }
}
