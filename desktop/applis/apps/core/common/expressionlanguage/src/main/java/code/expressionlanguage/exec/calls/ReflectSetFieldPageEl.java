package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldMetaInfo;
import code.util.CustList;

public final class ReflectSetFieldPageEl extends AbstractReflectPageEl {

    private boolean initClass;
    private FieldMetaInfo metaInfo;

    public ReflectSetFieldPageEl(CustList<Argument> _arguments, FieldMetaInfo _metaInfo) {
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
        setWrapException(false);
        String baseClass_ = metaInfo.getDeclaringClass();
        if (stds_.getStandards().contains(baseClass_)) {
            String ill_;
            ill_ = stds_.getContent().getCoreNames().getAliasIllegalArg();
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ill_)));
            return false;
        }
        Argument instance_ = getArguments().first();
        Argument right_ = getArguments().last();
        Argument arg_ = ExecTemplates.setField(metaInfo, instance_, right_, _context);
        if (_context.callsOrException()) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
