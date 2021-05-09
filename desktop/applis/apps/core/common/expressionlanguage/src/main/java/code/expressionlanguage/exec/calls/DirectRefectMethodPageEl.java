package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo);
    }

    @Override
    boolean initType(ContextEl _cont, StackCall _stack) {
        return initDefault(_cont, _stack);
    }

    @Override
    boolean isAbstract(ContextEl _cont, StackCall _stack) {
        MethodMetaInfo method_ = getMetaInfo();
        return method_.isAbstract();
    }

    @Override
    boolean isPolymorph(ContextEl _cont, StackCall _stack) {
        return false;
    }

    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        ExecMemberCallingsBlock callee_ = getCallee();
        if (callee_ instanceof ExecAbstractSwitchMethod) {
            ExecRootBlock type_ = getPair().getType();
            AbstractParamChecker sw_ = new SwitchParamChecker(type_,(ExecAbstractSwitchMethod) callee_, _args);
            Argument instance_ = getInstance();
            return sw_.checkParams(getClassName(), instance_, getMetaInfo().getCache(), _context, getAccessKind(), _stack);
        }
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(getPair(),getClassName(),getInstance(),_args,_context, getAccessKind());
        l_.setRight(_right);
        return new DefaultParamChecker(getPair(), l_, CallPrepareState.METHOD, null).checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, getAccessKind(), _stack);
    }

}
