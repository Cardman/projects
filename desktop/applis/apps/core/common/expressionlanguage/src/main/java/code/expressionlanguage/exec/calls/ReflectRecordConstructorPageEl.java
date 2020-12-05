package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;
import code.util.StringMap;

public final class ReflectRecordConstructorPageEl extends AbstractReflectConstructorPageEl {

    private boolean calledMethod;
    private ExecRootBlock root;
    private StringMap<String> id;
    private final String className;

    public ReflectRecordConstructorPageEl(CustList<Argument> _arguments,ExecRootBlock _root,StringMap<String> _id,String _className) {
        super(_arguments);
        root = _root;
        id = _id;
        className = _className;
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        if (!keep(_context)) {
            return false;
        }
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            _context.setCallingState(new CustomFoundRecordConstructor(className, new ExecTypeFunction(root,null),id, "", -1, getArguments()));
            return false;
        }
        return true;
    }

    @Override
    protected String getDeclaringClass() {
        return className;
    }
}
