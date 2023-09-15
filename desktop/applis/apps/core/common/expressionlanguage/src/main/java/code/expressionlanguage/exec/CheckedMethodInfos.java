package code.expressionlanguage.exec;

import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedMethodInfos extends CoreCheckedExecOperationNodeInfos{
    private final Parameters parameters;

    public CheckedMethodInfos(ExecFormattedRootBlock _d, Struct _i, Parameters _p) {
        super(_d,_i);
        this.parameters = _p;
    }


    public Parameters getParameters() {
        return parameters;
    }
}
