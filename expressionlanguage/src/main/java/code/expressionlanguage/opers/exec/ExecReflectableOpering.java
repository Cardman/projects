package code.expressionlanguage.opers.exec;

import code.expressionlanguage.opers.ReflectableOpering;

public abstract class ExecReflectableOpering extends ExecMethodOperation implements AtomicExecCalculableOperation, DirectExecCalculableOperation {

    public ExecReflectableOpering(ReflectableOpering _r) {
        super(_r);
    }

}
