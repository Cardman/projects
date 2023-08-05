package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final ClassField idClass;
    private final int modeField;
    private final boolean nul;

    public CheckedExecOperationNodeInfos(ClassField _id, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r, boolean _n) {
        super(_d, _i, _r);
        this.idClass = _id;
        this.modeField = _mode;
        this.nul = _n;
    }

    public ClassField getIdClass() {
        return idClass;
    }

    public int getModeField() {
        return modeField;
    }

    public boolean isNul() {
        return nul;
    }
}
