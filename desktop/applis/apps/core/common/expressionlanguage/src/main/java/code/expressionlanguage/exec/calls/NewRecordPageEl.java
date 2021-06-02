package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.util.CustList;
import code.util.StringMap;

public final class NewRecordPageEl extends AbstractCallingInstancingPageEl {

    private final StringMap<String> names;
    private final CustList<Argument> args;
    public NewRecordPageEl(StringMap<String> _names, CustList<Argument> _args, ExecFormattedRootBlock _global) {
        super(_global);
        names = _names;
        args = _args;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        //set fields for annotation after calculating default one
        int len_ = Math.min(names.size(),args.size());
        Argument gl_ = getGlobalArgument();
        String className_ = gl_.getStruct().getClassName(_context);
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        for (int i = 0; i <len_; i++) {
            String name_ = names.getKey(i);
            Argument value_ = args.get(i);
            String t_ = names.getValue(i);
            ExecTemplates.setInstanceField(getBlockRootType(), t_, gl_, value_, _context, _stack, new ClassField(id_, name_));
            if (_context.callsOrException(_stack)) {
                return;
            }
        }
        setNullReadWrite();
    }

}
