package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.core.StringUtil;

public abstract class AbstractFormatParamChecker extends AbstractParamChecker {
    private final MethodAccessKind kind;

    protected AbstractFormatParamChecker(MethodAccessKind _kind) {
        this.kind = _kind;
    }
    public ExecFormattedRootBlock checkFormmattedParams(ExecFormattedRootBlock _classNameFound, Argument _previous, ContextEl _conf, StackCall _stackCall) {
        if (kind != MethodAccessKind.INSTANCE) {
            return _classNameFound;
        }
        String className_ = Argument.getNullableValue(_previous).getStruct().getClassName(_conf);
        String sup_ = ExecInherits.getQuickFullTypeByBases(className_, _classNameFound.getFormatted(), _conf);
        if (sup_.isEmpty()) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, getBadCastMessage(_classNameFound.getFormatted(), className_), cast_, _stackCall)));
            return _classNameFound;
        }
        return new ExecFormattedRootBlock(_classNameFound, sup_);
    }
    private static String getBadCastMessage(String _classNameFound, String _className) {
        return StringUtil.concat(_className, RETURN_LINE, _classNameFound, RETURN_LINE);
    }
}
