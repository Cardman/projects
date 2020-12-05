package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;

public abstract class AbstractReflectConstructorPageEl extends AbstractReflectPageEl {
    private boolean initClass;
    private String resolved = "";
    protected boolean keep(ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String className_ = getDeclaringClass();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _context.getClassBody(id_);
        if (ExecutingUtil.isAbstractType(type_)) {
            String null_ = stds_.getContent().getCoreNames().getAliasAbstractTypeErr();
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_)));
            return false;
        }
        boolean static_ = type_.isStaticType();
        String res_ = ExecTemplates.correctClassPartsDynamicWildCard(className_,_context);
        if (res_.isEmpty()) {
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _context.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_)));
            return false;
        }
        resolved = res_;
        if (!initClass) {
            initClass = true;
            if (static_ && _context.getExiting().hasToExit(res_)) {
                setWrapException(true);
                return false;
            }
        }
        return true;
    }

    protected String getResolved() {
        return resolved;
    }

    protected abstract String getDeclaringClass();
}
