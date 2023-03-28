package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ArrayStruct;

public final class ReflectMethodParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;

    private final ArrayStruct argsList;
    private final Argument right;
    private final ArgumentListCall args = new ArgumentListCall();
    private final boolean re;
    public ReflectMethodParamChecker(ExecTypeFunction _pair,
                                     ArrayStruct _args, Argument _right,
                                     MethodAccessKind _kind, boolean _r) {
        super(_kind);
        argsList = _args;
        right = _right;
        this.pair = _pair;
        this.method = _pair.getFct();
        re = _r;
    }


    @Override
    public Argument redirect(ContextEl _conf, ExecFormattedRootBlock _classNameFound, Argument _previous, StackCall _stackCall, FormattedParameters _classFormat) {
        return redir(_conf, _previous, _stackCall, _classFormat, method, args, pair);
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ExecTemplates.wrapAndCallDirect(args,pair,argsList,right,_classFormat,re,0);
        return getParameters(_classFormat, _cache, _conf, _stackCall,method,args);
    }

}
