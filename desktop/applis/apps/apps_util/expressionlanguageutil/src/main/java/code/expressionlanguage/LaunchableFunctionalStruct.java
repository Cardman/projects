package code.expressionlanguage;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.structs.AbstractFunctionalInstanceImpl;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.StringList;

public abstract class LaunchableFunctionalStruct extends AbstractFunctionalInstanceImpl implements Runnable {

    private final CommonExecutionInfos executionInfos;
    private final StringList args;
    private final AbstractAtomicBoolean interrupt;

    protected LaunchableFunctionalStruct(String _className, LambdaStruct _functional,
                                         CustList<ClassFieldStruct> _fields, RunnableContextEl _contextEl, ExecNamedFunctionBlock _named) {
        super(_className, _functional,_fields, _named);
        executionInfos = _contextEl.getExecutionInfos();
        args = _contextEl.getArgs();
        interrupt = _contextEl.getInterrupt();
    }

    public AbstractAtomicBoolean getInterrupt() {
        return interrupt;
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public StringList getArgs() {
        return args;
    }
}
