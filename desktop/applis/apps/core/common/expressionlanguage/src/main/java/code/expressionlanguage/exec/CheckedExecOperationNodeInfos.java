package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedExecOperationNodeInfos {
    private final ClassField idClass;
    private final int modeField;
    private final ExecFormattedRootBlock declaring;
    private final Struct instance;
    private final Struct right;

    public CheckedExecOperationNodeInfos(ClassField _id, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        this.idClass = _id;
        this.modeField = _mode;
        this.declaring = _d;
        this.instance = _i;
        this.right = _r;
    }

    public ClassField getIdClass() {
        return idClass;
    }

    public int getModeField() {
        return modeField;
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
