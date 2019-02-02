package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectSetFieldPageEl extends AbstractReflectPageEl {

    private boolean initClass;

    @Override
    public boolean checkCondition(ContextEl _context) {
        FieldMetaInfo method_ = (FieldMetaInfo) getGlobalArgument().getStruct();
        if (!initClass) {
            initClass = true;
            if (method_.isStaticField()) {
                String baseClass_ = method_.getDeclaringClass();
                baseClass_ = Templates.getIdFromAllTypes(baseClass_);
                if (ExecInvokingOperation.hasToExit(_context, baseClass_)) {
                    setWrapException(true);
                    return false;
                }
            }
        }
        Argument instance_ = getArguments().first();
        Argument right_ = getArguments().last();
        setWrapException(false);
        Argument arg_ = ExecInvokingOperation.setField(method_, instance_, right_, _context);
        setReturnedArgument(arg_);
        return true;
    }

}
