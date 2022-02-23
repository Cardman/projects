package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.inherits.AbstractParamChecker;
import code.expressionlanguage.exec.inherits.ReflectMethodParamChecker;
import code.expressionlanguage.exec.inherits.SwitchParamChecker;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.CustList;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectRefectMethodPageEl(Argument _instance, Argument _array, MethodMetaInfo _metaInfo) {
        super(_instance,_array, _metaInfo, new DefInitPreparerAbs(_metaInfo));
    }

    Argument prepare(ContextEl _context, CustList<Argument> _args, Argument _right, StackCall _stack) {
        ExecMemberCallingsBlock callee_ = getCallee();
        AbstractParamChecker ab_;
        if (callee_ instanceof ExecAbstractSwitchMethod) {
            ab_ = new SwitchParamChecker((ExecAbstractSwitchMethod) callee_, _args,getAccessKind());
        } else {
            ab_ = new ReflectMethodParamChecker(getPair(), _args,_right,getAccessKind());
        }
        return ab_.checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

}
