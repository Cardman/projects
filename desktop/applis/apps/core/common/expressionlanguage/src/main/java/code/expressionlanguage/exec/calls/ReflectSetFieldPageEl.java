package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectSetFieldPageEl extends AbstractBasicReflectPageEl {

    private boolean initClass;
    private final FieldMetaInfo metaInfo;

    private final Argument first;
    private final Argument last;

    public ReflectSetFieldPageEl(Argument _first, Argument _last, FieldMetaInfo _metaInfo) {
        first = _first;
        last = _last;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!initClass) {
            initClass = true;
            if (metaInfo.isStaticField() && _context.getExiting().hasToExit(_stack, metaInfo.getFormatted().getRootBlock())) {
                setWrapException(true);
                return false;
            }
        }
        setWrapException(false);
        Argument arg_ = ExecFieldTemplates.setField(metaInfo, first, last, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
