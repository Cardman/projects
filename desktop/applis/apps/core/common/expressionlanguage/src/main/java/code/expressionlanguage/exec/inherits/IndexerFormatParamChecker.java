package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class IndexerFormatParamChecker extends AbstractMethodParamChecker {

    public IndexerFormatParamChecker(ExecTypeFunction _pair, ArgumentListCall _args,
                                         MethodAccessKind _kind) {
        super(_pair, _args, _kind);
    }
    @Override
    public Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        return simpleRedir(_previous, _stackCall, _classFormat, getPair());
    }

}
