package code.expressionlanguage.opers.exec;

import code.expressionlanguage.opers.VariableLeafOperation;

public abstract class ExecVariableLeafOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,DirectExecCalculableOperation {

    ExecVariableLeafOperation(VariableLeafOperation _v) {
        super(_v);
    }

}
