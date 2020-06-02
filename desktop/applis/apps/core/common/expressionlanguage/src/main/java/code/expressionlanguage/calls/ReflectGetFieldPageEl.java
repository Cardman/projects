package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectGetFieldPageEl extends AbstractReflectPageEl {

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
        String baseClass_ = method_.getDeclaringClass();
        if (stds_.getStandards().contains(baseClass_)) {
            String name_ =method_.getName();
            ClassField id_ = new ClassField(baseClass_, name_);
            Argument arg_ = new Argument(stds_.getSimpleResult(_context, id_).getResult());
            setReturnedArgument(arg_);
            return true;
        }
        Argument instance_ = getArguments().first();
        Argument arg_ = ExecInvokingOperation.getField(method_, instance_, _context);
        if (_context.callsOrException()) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
