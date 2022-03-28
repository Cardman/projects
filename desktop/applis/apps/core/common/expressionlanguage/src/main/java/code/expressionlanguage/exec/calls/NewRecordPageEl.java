package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;

public final class NewRecordPageEl extends AbstractCallingInstancingPageEl {

    private final CustList<ExecNamedFieldContent> named;
    private final CustList<Argument> args;
    public NewRecordPageEl(CustList<ExecNamedFieldContent> _named, CustList<Argument> _args, ExecFormattedRootBlock _global, CustList<ExecFormattedRootBlock> _ls) {
        super(_global, _ls);
        named = _named;
        args = _args;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_stack)) {
            return;
        }
        //set fields for annotation after calculating default one
        int len_ = Math.min(named.size(),args.size());
        Argument gl_ = getGlobalArgument();
        for (int i = 0; i <len_; i++) {
            ExecNamedFieldContent info_ = named.get(i);
            String id_ = info_.getIdClass();
            String name_ = info_.getName();
            Argument value_ = args.get(i);
            String t_ = info_.getType();
            ExecFieldTemplates.setInstanceField(gl_, value_, _context, _stack, new ClassField(id_, name_), new ExecTypeReturn(info_.getDeclaring(), t_));
            if (_context.callsOrException(_stack)) {
                return;
            }
        }
        setNullReadWrite();
    }

}
