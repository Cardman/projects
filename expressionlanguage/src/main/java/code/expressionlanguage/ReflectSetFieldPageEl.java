package code.expressionlanguage;

import code.expressionlanguage.opers.InvokingOperation;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectSetFieldPageEl extends AbstractReflectPageEl {

    private boolean calledMethod;

    @Override
    public boolean checkCondition(ContextEl _context) {
        FieldMetaInfo method_ = (FieldMetaInfo) getGlobalArgument().getStruct();
        if (!calledMethod) {
            Argument instance_ = getArguments().first();
            Argument right_ = getArguments().last();
            setWrapException(false);
            Argument arg_ = InvokingOperation.setField(method_, instance_, right_, _context, false);
            if (_context.getInitClass() != null) {
                setWrapException(true);
                return false;
            }
            calledMethod = true;
            if (_context.callsOrException()) {
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

}
