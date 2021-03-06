package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldMetaInfo;

public final class ReflectGetFieldPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private final FieldMetaInfo metaInfo;

    private final Argument argument;

    public ReflectGetFieldPageEl(Argument _argument, FieldMetaInfo _metaInfo) {
        argument = _argument;
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (metaInfo.isStaticField()) {
                String baseClass_ = metaInfo.getDeclaringClass();
                baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
                if (_context.getExiting().hasToExit(_stack, baseClass_)) {
                    setWrapException(true);
                    return false;
                }
            }
        }
        String baseClass_ = metaInfo.getDeclaringClass();
        if (stds_.getStandards().contains(baseClass_)) {
            String name_ =metaInfo.getName();
            ClassField id_ = new ClassField(baseClass_, name_);
            Argument arg_ = new Argument(stds_.getSimpleResult(id_));
            setReturnedArgument(arg_);
            return true;
        }
        Argument arg_ = ExecTemplates.getField(metaInfo, argument, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
