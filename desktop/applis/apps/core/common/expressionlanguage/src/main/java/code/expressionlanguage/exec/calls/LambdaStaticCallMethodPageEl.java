package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaStaticCallMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaStaticCallMethodPageEl(Argument _instance, ArgumentListCall _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return initDefault(_cont, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont, StackCall _stack) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, Argument _instance, ArgumentListCall _list, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        return prepareStaticCall(getPair(),method_.getCache(), _className, _context,_list, _stack);
    }
    private static Argument prepareStaticCall(ExecTypeFunction _pair, Cache _cache, String _className,
                                              ContextEl _conf, ArgumentListCall _list, StackCall _stackCall) {
        return checkStaticCall(_pair,_cache, _className, _conf,_list, _stackCall);
    }

    private static Argument checkStaticCall(ExecTypeFunction _pair, Cache _cache,
                                            String _className, ContextEl _conf, ArgumentListCall _list, StackCall _stackCall) {
        String paramName_ = _stackCall.formatVarType(_className);
        return checkStaticCall(_pair, _cache, _conf, paramName_, _list, _stackCall);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
