package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;
import code.util.StringMap;

public final class ReflectRecordConstructorPageEl extends AbstractReflectConstructorPageEl {

    private boolean calledMethod;
    private final ExecRootBlock root;
    private final StringMap<String> id;
    private final String className;

    private final CustList<Argument> arguments;

    public ReflectRecordConstructorPageEl(CustList<Argument> _arguments,ExecRootBlock _root,StringMap<String> _id,String _className) {
        arguments = _arguments;
        root = _root;
        id = _id;
        className = _className;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(_context, _stack)) {
            return false;
        }
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            _stack.setCallingState(new CustomFoundRecordConstructor(className, new ExecTypeFunction(root,null),id, "", -1, arguments));
            return false;
        }
        return true;
    }

    @Override
    protected String getDeclaringClass() {
        return className;
    }
}
