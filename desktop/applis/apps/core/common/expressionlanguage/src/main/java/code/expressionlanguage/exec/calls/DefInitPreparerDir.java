package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DefInitPreparerDir extends DefInitPreparer {

    public DefInitPreparerDir(MethodMetaInfo _metaInfo) {
        super(_metaInfo);
    }

    @Override
    public boolean isAbstract(ContextEl _context, StackCall _stack) {
        return false;
    }
}
