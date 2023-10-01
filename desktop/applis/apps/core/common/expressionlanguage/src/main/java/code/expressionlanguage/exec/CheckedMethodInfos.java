package code.expressionlanguage.exec;

import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedMethodInfos extends CoreCheckedExecOperationNodeInfos{
    private final Parameters parameters;

    public CheckedMethodInfos(CoreCheckedExecOperationNodeInfos _core) {
        this(_core.getDeclaring(),_core.getInstance(),params(_core));
    }

    public CheckedMethodInfos(ExecFormattedRootBlock _d, Struct _i, Parameters _p) {
        super(_d,_i);
        this.parameters = _p;
    }

    private static Parameters params(CoreCheckedExecOperationNodeInfos _core) {
        Parameters p_ = new Parameters();
        p_.setCache(_core.getCache());
        return p_;
    }


    public Parameters getParameters() {
        return parameters;
    }
}
