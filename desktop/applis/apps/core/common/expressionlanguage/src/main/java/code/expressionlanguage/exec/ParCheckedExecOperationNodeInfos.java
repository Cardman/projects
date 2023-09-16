package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.HiddenCache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class ParCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {

    public ParCheckedExecOperationNodeInfos(ContextEl _context, Struct _i, int _anc) {
        super(ExecFormattedRootBlock.build(_i.getClassName(_context), _context.getClasses()), _i,cacheRightValue(_context,_anc));
    }

    static HiddenCache cacheRightValue(ContextEl _context, int _r) {
        StringMap<AbstractWrapper> rSup_ = new StringMap<AbstractWrapper>();
        rSup_.addEntry(_context.getClasses().getKeyWordValue(), new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(_r), _context)));
        return new HiddenCache(rSup_, new StringMap<LoopVariable>(), null);
    }

}
