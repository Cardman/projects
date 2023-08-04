package code.expressionlanguage;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.AbsFieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.threads.AbstractAtomicBoolean;
import code.util.CustList;
import code.util.StringList;

public abstract class LaunchableStruct extends AbsFieldableStruct implements Runnable {

    private final CommonExecutionInfos executionInfos;
    private final StringList args;
    private final AbstractAtomicBoolean interrupt;


    protected LaunchableStruct(RunnableContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        super(_className, _fields, _parent, _parentClassName, _ordinal, _name);
        executionInfos = _original.getExecutionInfos();
        args = _original.getArgs();
        interrupt = _original.getInterrupt();
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public StringList getArgs() {
        return args;
    }

    public AbstractAtomicBoolean getInterrupt() {
        return interrupt;
    }
}
