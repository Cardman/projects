package code.expressionlanguage.exec;

import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedMethodInfos {
    private final ExecFormattedRootBlock declaring;
    private final Struct instance;
    private final Parameters parameters;

    public CheckedMethodInfos(ExecFormattedRootBlock _d, Struct _i, Parameters _p) {
        this.declaring = _d;
        this.instance = _i;
        this.parameters = _p;
    }

    public ExecFormattedRootBlock getDeclaring() {
        return declaring;
    }

    public Struct getInstance() {
        return instance;
    }


    public Parameters getParameters() {
        return parameters;
    }
}
