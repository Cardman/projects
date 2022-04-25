package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.NoExiting;
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
        CustList<Argument> args_ = _arguments.getArguments();
        if (ExecTemplates.okArgsSet(_constId, args_, _conf, _stackCall) != null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        StdCaller caller_ = StandardType.caller(_ctor, null);
        NoExiting exit_ = new NoExiting();
        return _conf.getCaller().invoke(caller_, exit_,_conf,NullStruct.NULL_VALUE,_arguments,_stackCall);
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

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, ExecFormattedRootBlock _className, CustList<ExecOperationInfo> _infos, ExecInstancingCustContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        Argument res_;
        if (_instancingCommonContent.getPair().getType() instanceof ExecRecordBlock) {
            CustList<Argument> arguments_ = getArguments(_infos);
            Argument prev_ = instance(_instancingCommonContent.getPair().getType(),_previous);
            _stack.setCallingState(new CustomFoundRecordConstructor(prev_,_className, _instancingCommonContent.getPair(),_instancingStdContent, arguments_));
            res_ = Argument.createVoid();
        } else {
            res_ = new InstanceParamChecker(_instancingCommonContent.getPair(), ExecInvokingOperation.fectchInstFormattedArgs(_className, _instancingCommonContent, _conf, _stack, _infos), _instancingStdContent.getFieldName(), _instancingStdContent.getBlockIndex()).checkParams(_className, _previous, null, _conf, _stack);
        }
        return res_;
    }

    private static CustList<Argument> getArguments(CustList<ExecOperationInfo> _nodes) {
        CustList<Argument> a_ = new CustList<Argument>();
        for (ExecOperationInfo o: _nodes) {
            a_.add(o.getPair().getArgument());
        }
        return a_;
    }

    public static Argument instance(ExecRootBlock _type, Argument _previous) {
        if (!AbstractParamChecker.withInstance(_type)) {
            return Argument.createVoid();
        }
        return _previous;
    }

    public static boolean checkCustomOper(AbstractExiting _exit, ExecTypeFunction _rootBlock, ExecFormattedRootBlock _paramNameOwner, ContextEl _conf, Argument _fwd, StackCall _stackCall, ArgumentListCall _list) {
        if (_exit.hasToExit(_stackCall, _paramNameOwner.getRootBlock(),_fwd)) {
            return true;
        }
        new StaticCallParamChecker(_rootBlock,_list).checkParams(_paramNameOwner,Argument.createVoid(),null,_conf,_stackCall);
        return false;
    }

    public static void redirectAnnotation(ContextEl _conf, StackCall _stack, CustList<Argument> _arguments, ExecInstancingAnnotContent _instancingAnnotContent) {
        ExecFormattedRootBlock formattedType_ = _instancingAnnotContent.getFormattedType();
        if (!_conf.getExiting().hasToExit(_stack, formattedType_.getRootBlock())) {
            _stack.setCallingState(new CustomFoundAnnotation(formattedType_, formattedType_.getRootBlock(), _instancingAnnotContent.getFieldNames(), _arguments));
        }
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, ExecFormattedRootBlock _className, CustList<ExecOperationInfo> _infos, ExecInstancingCustContent _instancingCommonContent) {
        Argument res_;
        if (_conf.getExiting().hasToExit(_stack, _className.getRootBlock())) {
            res_ = Argument.createVoid();
        } else {
            res_ = new InstanceParamChecker(_instancingCommonContent.getPair(), ExecInvokingOperation.fectchInstFormattedArgs(_className, _instancingCommonContent,_conf,_stack, _infos), "", -1).checkParams(_className, _previous, null, _conf, _stack);
        }
        return res_;
    }

    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named, ArgumentListCall _firstArgs, ExecFormattedRootBlock _classNameFound, MethodAccessKind _kind, StackCall _stackCall) {
        if (_exit.hasToExit(_stackCall, _classNameFound.getRootBlock())) {
            return;
        }
        new DefaultParamChecker(_named, _firstArgs, _kind, CallPrepareState.OPERATOR, null).checkParams(_classNameFound, Argument.createVoid(), null, _conf, _stackCall);
    }

    public static Argument processEnums(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall, ExecRootBlock _type) {
        //static enum methods
        LgNames stds_ = _cont.getStandards();
        CustList<Argument> args_ = _firstArgs.getArguments();
        if (args_.size() != 1) {
            return IndirectCalledFctUtil.tryGetEnumValues(_exit, _cont, _type,  ClassCategory.ENUM, _stackCall);
        }
        Argument arg_ = args_.first();
        Struct ex_ = ExecInheritsAdv.checkObjectEx(stds_.getContent().getCharSeq().getAliasString(), Argument.getNullableValue(arg_).getStruct().getClassName(_cont), _cont, _stackCall);
        if (ex_ != null) {
            _stackCall.setCallingState(new CustomFoundExc(ex_));
            return Argument.createVoid();
        }
        return IndirectCalledFctUtil.tryGetEnumValue(_exit, _cont, _type, ClassCategory.ENUM, arg_, _stackCall);
    }

    public static Argument tryInit(ContextEl _conf, StackCall _stack, ExecRootBlock _root){
        if (_conf.getExiting().hasToExit(_stack, _root)) {
            return Argument.createVoid();
        }
        return null;
    }

    public static Argument prepare(AbstractExiting _exit, ExecTypeFunction _rootBlock,
                                   ExecFormattedRootBlock _classNameOwner, ContextEl _conf, StackCall _stackCall, ArgumentListCall _list) {
        checkCustomOper(_exit, _rootBlock, StackCall.formatVarType(_stackCall,_classNameOwner), _conf, null, _stackCall, _list);
        return Argument.createVoid();
    }

    public static int processConverter(ContextEl _conf, Argument _right, ImplicitMethods _implicits, int _indexImplicit, StackCall _stackCall) {
        ExecFormattedRootBlock formatted_ = StackCall.formatVarType(_stackCall, _implicits.getOwnerClass());
        ExecTypeFunction c = _implicits.get(_indexImplicit);
        AbstractExiting ex_ = _conf.getExiting();
        ArgumentListCall l_ = new ArgumentListCall(Argument.getNullableValue(_right));
        if (checkCustomOper(ex_, c, formatted_, _conf, _right, _stackCall, l_)) {
            return _indexImplicit;
        }
        return _indexImplicit +1;
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, CustList<ExecOperationInfo> _infos, ExecStdFctContent _stdFctContent) {
        Argument prev_;
        Argument res_ = null;
        if (!_stdFctContent.isStaticMethod()) {
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecFieldTemplates.getParent(0, argPrev_, _conf, _stack));
            if (_conf.callsOrException(_stack)) {
                res_ = new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        if (res_ == null) {
            res_ = callStd(_conf.getExiting(), _conf, _stdFctContent.getClassMethodId(), prev_, ExecInvokingOperation.fectchArgs(_stdFctContent.getLastType(), _stdFctContent.getNaturalVararg(), _conf, _stack, _infos), _stack, _stdFctContent.getStandardMethod());
        }
        return res_;
    }
}
