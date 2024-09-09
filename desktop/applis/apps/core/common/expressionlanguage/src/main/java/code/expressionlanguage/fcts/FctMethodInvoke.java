package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomReflectMethod;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class FctMethodInvoke extends FctReflection {
    private final boolean direct;
    private final int ref;

    public FctMethodInvoke(boolean _direct, int _refer) {
        this.direct = _direct;
        ref = _refer;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        MethodMetaInfo meth_ = (MethodMetaInfo) _instance;
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct inst_ = argumentWrappers_.get(0).getValue();
        Struct args_ = argumentWrappers_.get(1).getValue();
        ArrayRefState a_ = ArrayRefState.tryWrap(args_,ref);
        invokeReflect(_cont,_stackCall,meth_,inst_, a_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }

    private void invokeReflect(ContextEl _cont, StackCall _stackCall, MethodMetaInfo _method, Struct _instance, ArrayRefState _a) {
        if (!_method.isInvokable()) {
            _stackCall.setCallingState(new CustomFoundExc(getNonInvokableError(_cont, _method, _stackCall)));
            return;
        }
        if (_method.getStdCallee() != null) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STD_FCT, _method, _instance, _a));
            return;
        }
        if (_method.getPairFct() instanceof ExecAnonymousFunctionBlock || _method.getCallee() instanceof ExecAbstractSwitchMethod) {
            invokeAnon(_stackCall, _method, _instance, _a);
            return;
        }
        ExecRootBlock e_ = _method.getPairType();
        if (e_ instanceof ExecAnnotationBlock) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.ANNOT_FCT, _method, _instance, _a));
            return;
        }
        afterCheckInvoke(_stackCall, _method, _instance, _a);
    }

    private static void invokeAnon(StackCall _stackCall, MethodMetaInfo _method, Struct _instance, ArrayRefState _a) {
        if (_method.isStaticCall()) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, _instance, _a));
            return;
        }
        _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.DIRECT, _method, _instance, _a));
    }

    private void afterCheckInvoke(StackCall _stackCall, MethodMetaInfo _method, Struct _instance, ArrayRefState _a) {
        if (!direct) {
            if (_method.getPairFct() != null) {
                invokeSpec(_stackCall, _method, _instance, ReflectingType.CAST, ReflectingType.METHOD, _a);
                return;
            }
        } else {
            if (_method.getPairFct() != null) {
                invokeSpec(_stackCall, _method, _instance, ReflectingType.CAST_DIRECT, ReflectingType.DIRECT, _a);
                return;
            }
        }
        defInvoke(_stackCall, _method, _instance, _a);
    }

    private static void invokeSpec(StackCall _stackCall, MethodMetaInfo _method, Struct _instance, ReflectingType _cast, ReflectingType _meth, ArrayRefState _a) {
        if (_method.isExpCast()) {
            _stackCall.setCallingState(new CustomReflectMethod(_cast, _method, _instance, _a));
            return;
        }
        if (_method.isStaticCall()) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.STATIC_CALL, _method, _instance, _a));
            return;
        }
        _stackCall.setCallingState(new CustomReflectMethod(_meth, _method, _instance, _a));
    }

    private static void defInvoke(StackCall _stackCall, MethodMetaInfo _method, Struct _instance, ArrayRefState _a) {
        ExecRootBlock e_ = _method.getPairType();
        if (e_ != null) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.ENUM_METHODS, _method, _instance, _a));
            return;
        }
        if (_method.isDirectCast()) {
            _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.CAST_DIRECT, _method, _instance, _a));
            return;
        }
        _stackCall.setCallingState(new CustomReflectMethod(ReflectingType.CLONE_FCT, _method, _instance, _a));
    }

}
