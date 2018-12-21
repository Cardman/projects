package code.formathtml.exec;

import code.expressionlanguage.opers.ReflectableOpering;

public abstract class ExecReflectableOpering extends ExecMethodOperation implements DirectExecCalculableOperation {

    public ExecReflectableOpering(ReflectableOpering _r) {
        super(_r);
    }

}
