package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
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
            Argument arg_ = ExecInvokingOperation.setField(method_, instance_, right_, _context);
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
