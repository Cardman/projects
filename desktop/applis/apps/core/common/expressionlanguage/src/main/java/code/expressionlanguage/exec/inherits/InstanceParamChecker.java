package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.StringList;
import code.util.core.StringUtil;

public final class InstanceParamChecker extends AbstractParamChecker {

    private final ExecTypeFunction pair;
    private final ExecRootBlock type;
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall arguments;
    private final String fieldName;
    private final int blockIndex;

    public InstanceParamChecker(ExecTypeFunction _pair, ArgumentListCall _arguments, String _fieldName,
                                int _blockIndex) {
        pair = _pair;
        type = _pair.getType();
        method = _pair.getFct();
        arguments = _arguments;
        fieldName = _fieldName;
        blockIndex = _blockIndex;
    }
    @Override
    public String checkFormmattedParams(String _classNameFound, Argument _previous, ContextEl _conf, StackCall _stackCall) {
        checkNeeded(_conf,_classNameFound,_previous,type,_stackCall);
        return _classNameFound;
    }

    private static void checkNeeded(ContextEl _conf, String _className, Argument _previous, ExecRootBlock _g, StackCall _stackCall) {
        if (_g.withoutInstance()) {
            return;
        }
        //From analyze
        LgNames stds_ = _conf.getStandards();
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_className);
        String param_ = StringUtil.join(parts_.left(parts_.size()-2), "");
        if (_previous.isNull()) {
            String npe_;
            npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return;
        }
        String arg_ = _previous.getStruct().getClassName(_conf);
        if (!ExecInherits.isCorrectExecute(arg_, param_, _conf)) {
            String cast_;
            cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, StringUtil.concat(arg_, RETURN_LINE, param_, RETURN_LINE), cast_, _stackCall)));
        }
    }

    @Override
    public Argument redirect(ContextEl _conf, String _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        Argument needed_;
        if (type.withoutInstance()) {
            needed_ = new Argument();
        } else {
            needed_ = new Argument(Argument.getNullableValue(_previous).getStruct());
        }
        _stackCall.setCallingState(new CustomFoundConstructor(_classNameFound, pair, fieldName, blockIndex, needed_, _classFormat.getParameters(), InstancingStep.NEWING));
        return Argument.createVoid();
    }

    @Override
    public Parameters check(String _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        return ExecTemplates.okArgsSet(type,method,_classFormat,_cache,arguments,_conf,_stackCall);
    }
}
