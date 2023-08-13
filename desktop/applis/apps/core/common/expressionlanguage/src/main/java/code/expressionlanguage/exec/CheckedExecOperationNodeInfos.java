package code.expressionlanguage.exec;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class CheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final int nbType;
    private final ClassField idClass;
    private final int modeField;

    public CheckedExecOperationNodeInfos(int _nb,ClassField _id, int _mode, ExecFormattedRootBlock _d, Struct _i, Struct _r) {
        super(_d, _i, _r);
        this.nbType = _nb;
        this.idClass = _id;
        this.modeField = _mode;
    }

    public int getNbType() {
        return nbType;
    }

    public ClassField getIdClass() {
        return idClass;
    }

    public int getModeField() {
        return modeField;
    }

}
