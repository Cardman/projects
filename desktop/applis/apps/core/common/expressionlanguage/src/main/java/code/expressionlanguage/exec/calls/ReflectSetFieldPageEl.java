package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.IntParentRetriever;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectSetFieldPageEl extends AbstractLambdaVariable {

    private final ReflectFieldContent content;

    private final Argument last;

    public ReflectSetFieldPageEl(IntParentRetriever _i, int _anc, Argument _last, FieldMetaInfo _metaInfo, boolean _lambda) {
        this(new ReflectFieldContent(_i, _anc, _metaInfo),_last,_lambda);
        setGlobalArgumentStruct(_metaInfo);
    }

    public ReflectSetFieldPageEl(ReflectFieldContent _cont, Argument _last, boolean _lambda) {
        super(_lambda, _cont);
        last = _last;
        content = _cont;
    }

    @Override
    Argument calculate(ContextEl _context, StackCall _stack) {
        Argument arg_ = ExecFieldTemplates.setField(content.getMetaInfo(), ArgumentListCall.toStr(content.getIntParentRetriever().getParent()), last, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return Argument.createVoid();
        }
        return arg_;
    }

    public Argument getFirst() {
        return ArgumentListCall.toStr(content.getIntParentRetriever().getParent());
    }

    public Argument getLast() {
        return last;
    }

    public ReflectFieldContent getContent() {
        return content;
    }
}
