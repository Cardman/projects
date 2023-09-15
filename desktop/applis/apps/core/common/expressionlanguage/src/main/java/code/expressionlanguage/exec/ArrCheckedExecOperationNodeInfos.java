package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.dbg.ArrPoint;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.HiddenCache;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class ArrCheckedExecOperationNodeInfos extends CoreCheckedExecOperationNodeInfos {
    private final int modeField;

    public ArrCheckedExecOperationNodeInfos(ContextEl _context, Struct _i) {
        super(ExecFormattedRootBlock.build(_i.getClassName(_context), _context.getClasses()), _i);
        this.modeField = ArrPoint.BPC_LENGTH;
    }

    public ArrCheckedExecOperationNodeInfos(ContextEl _context, String _i, Struct _values) {
        super(ExecFormattedRootBlock.build(_i, _context.getClasses()), NullStruct.NULL_VALUE, merged(_context, _values, null));
        this.modeField = ArrPoint.BPC_INIT;
    }

    public ArrCheckedExecOperationNodeInfos(ContextEl _context, Struct _i, int _m, Struct _values, Struct _r) {
        super(ExecFormattedRootBlock.build(_i.getClassName(_context), _context.getClasses()), _i, merged(_context, _values, _r));
        this.modeField = _m;
    }

    static HiddenCache merged(ContextEl _context, Struct _values, Struct _r) {
        HiddenCache sup_ = cacheRightValue(_context, _r);
        StringMap<AbstractWrapper> r_ = new StringMap<AbstractWrapper>();
        r_.addEntry(_context.getClasses().getKeyWordValue(), new VariableWrapper(LocalVariable.newLocalVariable(_values, _context)));
        return new HiddenCache(r_, new StringMap<LoopVariable>(), sup_);
    }

    static HiddenCache cacheRightValue(ContextEl _context, Struct _r) {
        StringMap<AbstractWrapper> rSup_ = new StringMap<AbstractWrapper>();
        if (_r != null) {
            rSup_.addEntry(_context.getClasses().getKeyWordValue(), new VariableWrapper(LocalVariable.newLocalVariable(_r, _context)));
        }
        return new HiddenCache(rSup_, new StringMap<LoopVariable>(), null);
    }

    static HiddenCache cacheMin() {
        return new HiddenCache(new StringMap<AbstractWrapper>(), new StringMap<LoopVariable>(), null);
    }

    public int getModeField() {
        return modeField;
    }

}
