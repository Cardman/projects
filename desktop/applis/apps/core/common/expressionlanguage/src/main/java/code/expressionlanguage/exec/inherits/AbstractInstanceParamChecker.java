package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class AbstractInstanceParamChecker extends AbstractParamChecker {

    private final ExecTypeFunction pair;
    private final ExecRootBlock type;
    private final ExecNamedFunctionBlock method;
    private final ArgumentListCall arguments;
    private final String fieldName;
    private final int blockIndex;

    protected AbstractInstanceParamChecker(ExecTypeFunction _pair, ArgumentListCall _arguments, String _fieldName,
                                           int _blockIndex) {
        pair = _pair;
        type = _pair.getType();
        method = _pair.getFct();
        arguments = _arguments;
        fieldName = _fieldName;
        blockIndex = _blockIndex;
    }
    @Override
    public ExecFormattedRootBlock checkFormmattedParams(ExecFormattedRootBlock _classNameFound, Struct _previous, ContextEl _conf, StackCall _stackCall) {
        checkNeeded(_conf,_classNameFound,_previous,type,_stackCall);
        return _classNameFound;
    }

    private static void checkNeeded(ContextEl _conf, ExecFormattedRootBlock _className, Struct _previous, ExecRootBlock _g, StackCall _stackCall) {
        if (_g.withoutInstance()) {
            return;
        }
        //From analyze
        LgNames stds_ = _conf.getStandards();
        StringList parts_ = StringExpUtil.getAllPartInnerTypes(_className.getFormatted());
        String param_ = StringUtil.join(parts_.left(parts_.size()-2), "");
        if (_previous == NullStruct.NULL_VALUE) {
            String npe_;
            npe_ = stds_.getContent().getCoreNames().getAliasNullPe();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return;
        }
        String arg_ = _previous.getClassName(_conf);
        if (!ExecInherits.isCorrectExecute(arg_, param_, _conf)) {
            String cast_;
            cast_ = stds_.getContent().getCoreNames().getAliasCastType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, StringUtil.concat(arg_, RETURN_LINE, param_, RETURN_LINE), cast_, _stackCall)));
        }
    }

    @Override
    public void redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Struct _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        Struct needed_;
        if (type.withoutInstance()) {
            needed_ = NullStruct.NULL_VALUE;
        } else {
            needed_ = ArgumentListCall.getNull(_previous);
        }
        _stackCall.setCallingState(new CustomFoundConstructor(_conf,_classNameFound, pair, fieldName, blockIndex, needed_, _classFormat.getParameters()));
    }

    protected ExecTypeFunction getPair() {
        return pair;
    }

    protected ArgumentListCall getArguments() {
        return arguments;
    }

    protected ExecNamedFunctionBlock getMethod() {
        return method;
    }
}
