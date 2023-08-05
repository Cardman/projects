package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final ClassField idClass;
    private final int modeField;

    public CheckedExecOperationNodeInfos(ClassField _id, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, _r);
        this.idClass = _id;
        this.modeField = _mode;
    }

    public ClassField getIdClass() {
        return idClass;
    }

    public int getModeField() {
        return modeField;
    }

}
