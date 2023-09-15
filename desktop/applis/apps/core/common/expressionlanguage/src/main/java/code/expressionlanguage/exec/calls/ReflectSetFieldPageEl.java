package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.IntParentRetriever;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectSetFieldPageEl extends AbstractLambdaVariable {

    private boolean initClass;
    private final IntParentRetriever intParentRetriever;
    private final FieldMetaInfo metaInfo;

    private final Argument last;

    public ReflectSetFieldPageEl(IntParentRetriever _i, Argument _last, FieldMetaInfo _metaInfo, boolean _lambda) {
        super(_lambda);
        intParentRetriever = _i;
        last = _last;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    boolean hasToExit(ContextEl _context, StackCall _stack) {
        if (!initClass) {
            initClass = true;
            return metaInfo.isStaticField() && _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock());
        }
        return false;
    }

    @Override
    Argument calculate(ContextEl _context, StackCall _stack) {
        Argument arg_ = ExecFieldTemplates.setField(metaInfo, ArgumentListCall.toStr(intParentRetriever.getParent()), last, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return Argument.createVoid();
        }
        return arg_;
    }

    @Override
    protected boolean koParent(ContextEl _context, StackCall _stack) {
        return !intParentRetriever.retrieve(_context, _stack);
    }

    public Argument getFirst() {
        return ArgumentListCall.toStr(intParentRetriever.getParent());
    }

    public Argument getLast() {
        return last;
    }

    public FieldMetaInfo getMetaInfo() {
        return metaInfo;
    }
}
