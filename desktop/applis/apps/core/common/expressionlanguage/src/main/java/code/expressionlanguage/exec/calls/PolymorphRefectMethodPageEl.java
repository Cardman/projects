package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.inherits.ReflectMethodParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class PolymorphRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public PolymorphRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
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
        MethodMetaInfo method_ = getMetaInfo();
        return method_.isInstanceMethod();
    }

    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        return new ReflectMethodParamChecker(getPair(), _args,_right, getAccessKind()).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

}
