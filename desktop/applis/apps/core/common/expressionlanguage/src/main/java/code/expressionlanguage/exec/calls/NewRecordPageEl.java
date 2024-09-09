package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class NewRecordPageEl extends AbstractCallingInstancingPageEl {

    private final CustList<ExecNamedFieldContent> named;
    private final CustList<Struct> args;
    private int indexSupplied = -1;
    public NewRecordPageEl(CustList<ExecNamedFieldContent> _named, CustList<Struct> _args, ExecFormattedRootBlock _global, CustList<ExecFormattedRootBlock> _ls) {
        super(_global, _ls);
        named = _named;
        args = _args;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_stack)) {
            return;
        }
        if (indexSupplied < 0) {
            indexSupplied = 0;
        }
        //set fields for annotation after calculating default one
        int len_ = NumberUtil.min(named.size(),args.size());
        Struct gl_ = getGlobalStruct();
        for (int i = indexSupplied; i <len_; i++) {
            if (_stack.getStopper().isStopAtRef(_context,_stack)) {
                indexSupplied = i;
                return;
            }
            ExecNamedFieldContent info_ = named.get(i);
            String id_ = info_.getIdClass();
            String name_ = info_.getName();
            Struct value_ = args.get(i);
            String t_ = info_.getType();
            ExecFieldTemplates.setInstanceField(gl_, value_, _context, _stack, new ClassField(id_, name_), new ExecTypeReturn(info_.getDeclaring(), t_));
            if (_context.callsOrException(_stack)) {
                return;
            }
        }
        indexSupplied = len_;
        _stack.nullReadWrite();
    }

    public CustList<ExecNamedFieldContent> getNamed() {
        return named;
    }

    public CustList<Struct> getArgs() {
        return args;
    }

    public int getIndexSupplied() {
        return indexSupplied;
    }
}
