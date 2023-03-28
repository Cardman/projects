package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.LaunchableFunctionalStruct;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.AbstractReflectElement;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class RunnableFunctionalInstance extends LaunchableFunctionalStruct {

    public RunnableFunctionalInstance(String _className, LambdaStruct _functional,
                                      CustList<ClassFieldStruct> _fields, RunnableContextEl _contextEl, ExecNamedFunctionBlock _named) {
        super(_className, _functional, _fields, _contextEl, _named);
    }

    @Override
    public void run() {
        callMethod(new RunnableContextEl(this, getExecutionInfos(), getArgs()), getFunctional(), new CustList<Argument>());
    }

    public static Argument callMethod(RunnableContextEl _localThread, Struct _functional, CustList<Argument> _arguments) {
        RunnableStruct.setupThread(_localThread);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,_localThread);
        ExecInvokingOperation.prepareCallDynReflect(new Argument(_functional), ArrayStruct.instance(StringExpUtil.getPrettyArrayType(_localThread.getStandards().getCoreNames().getAliasObject()),_arguments),false, _localThread, stackCall_);
        if (stackCall_.getCallingState() instanceof AbstractReflectElement) {
            AbstractReflectElement ref_ = (AbstractReflectElement) stackCall_.getCallingState();
            return RunnableStruct.reflect(_localThread, ref_, stackCall_);
        } else {
            _localThread.getCustInit().prExc(_localThread, stackCall_);
            return Argument.createVoid();
        }
    }
}
