package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public abstract class AbstractFormatParamChecker extends AbstractParamChecker {
    private final MethodAccessKind kind;

    protected AbstractFormatParamChecker(MethodAccessKind _kind) {
        this.kind = _kind;
    }

    protected static void redir(ContextEl _conf, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat, ExecNamedFunctionBlock _method, ArgumentListCall _args, ExecTypeFunction _pair) {
        Struct prev_ = _previous.getStruct();
        LambdaStruct lda_ = matchAbstract(prev_, _method);
        if (lda_ != null) {
            Argument fctInst_ = new Argument(lda_);
            AbstractParamChecker.prepareCallDyn(fctInst_, _args,0, _conf, _stackCall);
            return;
        }
        _stackCall.setCallingState(new CustomFoundMethod(_previous, _classFormat.getFormattedClass(), _pair, _classFormat.getParameters()));
    }

    protected static Parameters getParameters(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall, ExecNamedFunctionBlock _method, ArgumentListCall _args) {
        return ExecTemplates.okArgsSet(_method, _classFormat, _cache, _args, _conf, _stackCall);
    }

    public static LambdaStruct matchAbstract(Struct _pr, ExecOverrideInfo _poly) {
        return matchAbstract(_pr, _poly.getPair().getFct());
    }
    public static LambdaStruct matchAbstract(Struct _pr, ExecNamedFunctionBlock _method) {
        if (_pr instanceof AbstractFunctionalInstance && ((AbstractFunctionalInstance) _pr).getNamed() == _method) {
            return ((AbstractFunctionalInstance) _pr).getFunctional();
        }
        return null;
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
