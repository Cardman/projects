package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundRecordConstructor;
import code.expressionlanguage.exec.opers.ExecStandardInstancingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.util.CustList;

public final class ReflectRecordConstructorPageEl extends AbstractReflectConstructorPageEl {

    private final CustList<ExecFormattedRootBlock> listSup;
    private boolean calledMethod;
    private final ExecRootBlock root;
    private final Argument instance;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final ExecFormattedRootBlock className;

    private final CustList<Argument> arguments;

    public ReflectRecordConstructorPageEl(CustList<Argument> _arguments, Argument _instance, ExecRootBlock _root, CustList<ExecNamedFieldContent> _namedFields, ExecFormattedRootBlock _className, CustList<ExecFormattedRootBlock> _list) {
        arguments = _arguments;
        root = _root;
        instance = _instance;
        namedFields = _namedFields;
        className = _className;
        listSup = _list;
        setLambda(true);
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(root,_context, _stack)) {
            return false;
        }
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            _stack.setCallingState(new CustomFoundRecordConstructor(ExecStandardInstancingOperation.instance(root,instance),className, className.getRootBlock().getEmptyCtorPair(), namedFields, arguments, listSup));
            return false;
        }
        return true;
    }

    @Override
    protected ExecFormattedRootBlock getFormatted() {
        return className;
    }
}
