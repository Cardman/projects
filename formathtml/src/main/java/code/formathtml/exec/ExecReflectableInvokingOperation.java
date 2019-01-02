package code.formathtml.exec;

import code.expressionlanguage.opers.ReflectableInvokingOperation;

public abstract class ExecReflectableInvokingOperation extends ExecInvokingOperation implements DirectExecCalculableOperation {

    ExecReflectableInvokingOperation(ReflectableInvokingOperation _ref) {
        super(_ref);
    }

}
