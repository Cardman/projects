package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectSetFieldPageEl extends AbstractReflectPageEl {

    private boolean initClass;

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        FieldMetaInfo method_ = ApplyCoreMethodUtil.getField(getGlobalArgument().getStruct());
        if (!initClass) {
            initClass = true;
            if (method_.isStaticField()) {
                String baseClass_ = method_.getDeclaringClass();
                baseClass_ = Templates.getIdFromAllTypes(baseClass_);
                if (_context.hasToExit(baseClass_)) {
                    setWrapException(true);
                    return false;
                }
            }
        }
        setWrapException(false);
        String baseClass_ = method_.getDeclaringClass();
        if (stds_.getStandards().contains(baseClass_)) {
            String ill_;
            ill_ = stds_.getAliasIllegalArg();
            _context.setException(new ErrorStruct(_context,ill_));
            return false;
        }
        Argument instance_ = getArguments().first();
        Argument right_ = getArguments().last();
        Argument arg_ = ExecInvokingOperation.setField(method_, instance_, right_, _context);
        if (_context.callsOrException()) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
