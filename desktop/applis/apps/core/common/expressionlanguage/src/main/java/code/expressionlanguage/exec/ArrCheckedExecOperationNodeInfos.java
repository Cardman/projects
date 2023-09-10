package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.structs.Struct;

public final class ArrCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
//    private final int modeField;

    public ArrCheckedExecOperationNodeInfos(ContextEl _context, Struct _i) {
        super(ExecFormattedRootBlock.build(_i.getClassName(_context), _context.getClasses()), _i, null);
//        this.modeField = ArrPoint.BPC_LENGTH;
    }

//    public int getModeField() {
//        return modeField;
//    }

}
