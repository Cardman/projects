package code.expressionlanguage.exec;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public class CoreCheckedExecOperationNodeInfos {
    private final ExecFormattedRootBlock declaring;
    private final Struct instance;
    private final Struct right;

    public CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        this.declaring = _d;
        this.instance = _i;
        this.right = _r;
    }

    public ExecFormattedRootBlock getDeclaring() {
        return declaring;
    }

    public Struct getInstance() {
        return instance;
    }

    public Struct getRight() {
        return right;
    }
}
