package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecAnnotationMethodOperation;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectAnnotationRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectAnnotationRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo, boolean _refer) {
        super(_instance,_array, _metaInfo, new DefPreparer(), _refer);
    }

    @Override
    Argument prepare(ContextEl _context, ArrayStruct _args, Argument _right, StackCall _stack) {
        MethodId mid_ = getMetaInfo().getRealId();
        if (ExecTemplates.checkParams(_context,getClassName().getFormatted(),mid_,getInstance(),_args.listArgs(), _stack).isEmpty()) {
            return Argument.createVoid();
        }
        return ExecAnnotationMethodOperation.getAnnotation(getInstance(),mid_.getName(),_context, _stack);
    }
}
