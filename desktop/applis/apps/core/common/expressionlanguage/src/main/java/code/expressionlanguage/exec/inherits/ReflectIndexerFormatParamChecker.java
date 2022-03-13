package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.util.CustList;

public final class ReflectIndexerFormatParamChecker extends AbstractReflectFormatParamChecker {

    public ReflectIndexerFormatParamChecker(ExecTypeFunction _pair,
                                     CustList<Argument> _args, Argument _right,
                                     MethodAccessKind _kind) {
        super(_pair, _args, _right, _kind);
    }


    @Override
    public Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        return AbstractMethodParamChecker.simpleRedir(_previous, _stackCall, _classFormat, getPair());
    }

}
