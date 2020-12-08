package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class LambdaStaticCallMethodPageEl extends AbstractRefectLambdaMethodPageEl {

    public LambdaStaticCallMethodPageEl(Argument _instance, ArgumentListCall _array, Argument _right, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _right, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont) {
        return initDefault(_cont);
    }

    @Override
    boolean isAbstract(ContextEl _cont) {
        return false;
    }

    @Override
    boolean isPolymorph(ContextEl _cont) {
        return false;
    }

    @Override
    Argument prepare(ContextEl _context, String _className, Argument _instance, Argument _right, ArgumentListCall _list) {
        MethodMetaInfo method_ = getMetaInfo();
        return prepareStaticCall(getPair(),method_.getCache(), _className, _context,_list);
    }
    private static Argument prepareStaticCall(ExecTypeFunction _pair, Cache _cache, String _className,
                                              ContextEl _conf, ArgumentListCall _list) {
        return checkStaticCall(_pair,_cache, _className, _conf,_list);
    }

    private static Argument checkStaticCall(ExecTypeFunction _pair, Cache _cache,
                                            String _className, ContextEl _conf, ArgumentListCall _list) {
        String paramName_ = _conf.formatVarType(_className);
        return checkStaticCall(_pair, _cache, _conf, paramName_, _list);
    }

    @Override
    public String formatVarType(String _varType) {
        return _varType;
    }
}
