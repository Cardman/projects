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
import code.util.CustList;

public abstract class AbstractReflectFormatParamChecker extends AbstractFormatParamChecker {
    private final ExecTypeFunction pair;
    private final ExecNamedFunctionBlock method;

    private final CustList<Argument> argsList;
    private final Argument right;
    private final ArgumentListCall args = new ArgumentListCall();
    protected AbstractReflectFormatParamChecker(ExecTypeFunction _pair,
                                                CustList<Argument> _args, Argument _right,
                                                MethodAccessKind _kind) {
        super(_kind);
        argsList = _args;
        right = _right;
        this.pair = _pair;
        this.method = _pair.getFct();
    }

    @Override
    public Parameters check(ExecFormattedRootBlock _classFormat, Cache _cache, ContextEl _conf, StackCall _stackCall) {
        ExecTemplates.wrapAndCallDirect(args,pair,argsList,right,_classFormat);
        return AbstractMethodParamChecker.getParameters(_classFormat, _cache, _conf, _stackCall,method,args);
    }

    public ArgumentListCall getArgs() {
        return args;
    }

    public ExecNamedFunctionBlock getMethod() {
        return method;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }
}
