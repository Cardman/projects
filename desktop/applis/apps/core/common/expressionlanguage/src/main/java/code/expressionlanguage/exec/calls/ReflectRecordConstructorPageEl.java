package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;

public final class ReflectRecordConstructorPageEl extends AbstractReflectConstructorPageEl {

    private boolean calledMethod;
    private final ExecRootBlock root;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final ExecFormattedRootBlock className;

    private final CustList<Argument> arguments;

    public ReflectRecordConstructorPageEl(CustList<Argument> _arguments, ExecRootBlock _root, CustList<ExecNamedFieldContent> _namedFields, ExecFormattedRootBlock _className) {
        arguments = _arguments;
        root = _root;
        namedFields = _namedFields;
        className = _className;
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(root,_context, _stack)) {
            return false;
        }
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            _stack.setCallingState(new CustomFoundRecordConstructor(className, new ExecTypeFunction(root,null), namedFields, arguments));
            return false;
        }
        return true;
    }

    @Override
    protected ExecFormattedRootBlock getFormatted() {
        return className;
    }
}
