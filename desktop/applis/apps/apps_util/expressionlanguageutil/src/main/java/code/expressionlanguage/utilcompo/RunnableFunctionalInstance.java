package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.AbstractReflectElement;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;

import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class RunnableFunctionalInstance extends WithoutParentIdStruct implements AbstractFunctionalInstance, Runnable,
        FieldableStruct {

    private final String className;

    private final LambdaStruct functional;

    private final CustList<ClassFieldStruct> fields;
    private final CommonExecutionInfos executionInfos;

    private final ExecNamedFunctionBlock named;
    public RunnableFunctionalInstance(String _className, LambdaStruct _functional,
                                      CustList<ClassFieldStruct> _fields, ContextEl _contextEl, ExecNamedFunctionBlock _named) {
        className = _className;
        functional = _functional;
        executionInfos = _contextEl.getExecutionInfos();
        fields = _fields;
        named = _named;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    @Override
    public LambdaStruct getFunctional() {
        return functional;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }

    @Override
    public void run() {
        callMethod(new RunnableContextEl(InitPhase.NOTHING, executionInfos), functional, new CustList<Argument>());
    }

    public static Argument callMethod(RunnableContextEl _localThread, Struct _functional, CustList<Argument> _arguments) {
        RunnableStruct.setupThread(_localThread);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_localThread);
        ExecInvokingOperation.prepareCallDynReflect(new Argument(_functional), _arguments, _localThread, stackCall_);
        if (stackCall_.getCallingState() instanceof AbstractReflectElement) {
            AbstractReflectElement ref_ = (AbstractReflectElement) stackCall_.getCallingState();
            return RunnableStruct.reflect(_localThread, ref_, stackCall_);
        } else {
            _localThread.getCustInit().prExc(_localThread, stackCall_);
            return Argument.createVoid();
        }
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }
}
