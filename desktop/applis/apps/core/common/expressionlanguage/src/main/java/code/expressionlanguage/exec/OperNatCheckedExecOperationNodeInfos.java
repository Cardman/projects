package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class OperNatCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final int modeField;
    private final String key;

    public OperNatCheckedExecOperationNodeInfos(ContextEl _context, String _k, int _m, Struct _first, Struct _second) {
        super(ExecFormattedRootBlock.defValue(), NullStruct.NULL_VALUE, ArrCheckedExecOperationNodeInfos.merged(_context, _first, _second));
        this.modeField = _m;
        this.key = _k;
    }

    public String getKey() {
        return key;
    }

    public int getModeField() {
        return modeField;
    }

}
