package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.util.CustList;

public final class ReflectGetFieldPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private FieldMetaInfo metaInfo;

    public ReflectGetFieldPageEl(CustList<Argument> _arguments, FieldMetaInfo _metaInfo) {
        super(_arguments);
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (!initClass) {
            initClass = true;
            if (metaInfo.isStaticField()) {
                String baseClass_ = metaInfo.getDeclaringClass();
                baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
                if (_context.getExiting().hasToExit(baseClass_)) {
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
        Argument instance_ = ExecTemplates.getFirstArgument(getArguments());
        Argument arg_ = ExecTemplates.getField(metaInfo, instance_, _context);
        if (_context.callsOrException()) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
