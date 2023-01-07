package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.opers.*;

import code.expressionlanguage.structs.*;
import code.util.*;

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
        ExecInvokingOperation.prepareCallDynReflect(new Argument(_functional), _arguments, _localThread, stackCall_);
        if (stackCall_.getCallingState() instanceof AbstractReflectElement) {
            AbstractReflectElement ref_ = (AbstractReflectElement) stackCall_.getCallingState();
            return RunnableStruct.reflect(_localThread, ref_, stackCall_);
        } else {
            _localThread.getCustInit().prExc(_localThread, stackCall_);
            return Argument.createVoid();
        }
    }
}
