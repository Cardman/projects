package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.IntParentRetriever;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class ReflectSetFieldPageEl extends AbstractLambdaVariable {

    private final ReflectFieldContent content;

    private final Struct last;

    public ReflectSetFieldPageEl(IntParentRetriever _i, int _anc, Struct _last, FieldMetaInfo _metaInfo, boolean _lambda) {
        this(new ReflectFieldContent(_i, _anc, _metaInfo),_last,_lambda);
        setGlobalArgumentStruct(_metaInfo);
    }

    public ReflectSetFieldPageEl(ReflectFieldContent _cont, Struct _last, boolean _lambda) {
        super(_lambda, _cont);
        last = _last;
        content = _cont;
    }

    @Override
    Struct calculate(ContextEl _context, StackCall _stack) {
        Struct arg_ = ExecFieldTemplates.setField(content.getMetaInfo(), content.getIntParentRetriever().getParent(), last, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return NullStruct.NULL_VALUE;
        }
        return arg_;
    }

    public Struct getFirst() {
        return content.getIntParentRetriever().getParent();
    }

    public Struct getLast() {
        return last;
    }

    public ReflectFieldContent getContent() {
        return content;
    }
}
