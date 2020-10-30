package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.util.CustList;

public final class ReflectSetFieldPageEl extends AbstractReflectPageEl {

    private boolean initClass;

    public ReflectSetFieldPageEl(CustList<Argument> _arguments) {
        super(_arguments);
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        FieldMetaInfo method_ = NumParsers.getField(getGlobalStruct());
        if (!initClass) {
            initClass = true;
            if (method_.isStaticField()) {
                String baseClass_ = method_.getDeclaringClass();
                baseClass_ = StringExpUtil.getIdFromAllTypes(baseClass_);
                if (_context.getExiting().hasToExit(baseClass_)) {
                    setWrapException(true);
                    return false;
                }
            }
        }
        setWrapException(false);
        String baseClass_ = method_.getDeclaringClass();
        if (stds_.getStandards().contains(baseClass_)) {
            String ill_;
            ill_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            _context.setCallingState(new ErrorStruct(_context,ill_));
            return false;
        }
        Argument instance_ = getArguments().first();
        Argument right_ = getArguments().last();
        Argument arg_ = ExecTemplates.setField(method_, instance_, right_, _context);
        if (_context.callsOrException()) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
