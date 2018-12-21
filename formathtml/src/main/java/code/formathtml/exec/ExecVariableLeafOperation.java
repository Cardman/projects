package code.formathtml.exec;

import code.expressionlanguage.opers.VariableLeafOperation;

public abstract class ExecVariableLeafOperation extends ExecLeafOperation implements DirectExecCalculableOperation {

    ExecVariableLeafOperation(VariableLeafOperation _v) {
        super(_v);
    }

}
