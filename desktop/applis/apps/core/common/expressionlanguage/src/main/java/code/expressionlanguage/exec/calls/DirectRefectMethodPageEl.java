package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.inherits.AbstractParamChecker;
import code.expressionlanguage.exec.inherits.ReflectMethodParamChecker;
import code.expressionlanguage.exec.inherits.SwitchParamChecker;
import code.expressionlanguage.structs.MethodMetaInfo;

public final class DirectRefectMethodPageEl extends AbstractRefectMethodPageEl {

    public DirectRefectMethodPageEl(Argument _instance, MethodMetaInfo _metaInfo, ArrayRefState _a) {
        super(_instance, _metaInfo, new DefInitPreparerAbs(_metaInfo), _a);
    }

    Argument prepare(ContextEl _context, ArrayRefState _args, Argument _right, StackCall _stack) {
        ExecMemberCallingsBlock callee_ = getCallee();
        AbstractParamChecker ab_;
        if (callee_ instanceof ExecAbstractSwitchMethod) {
            ab_ = new SwitchParamChecker((ExecAbstractSwitchMethod) callee_, _args.getArray().listArgs(),getAccessKind());
        } else {
            ab_ = new ReflectMethodParamChecker(getPair(), _args, _right, getAccessKind());
        }
        return ab_.checkParams(getClassName(), getInstance(), getMetaInfo().getCache(), _context, _stack);
    }

    @Override
    protected boolean checkParams(ContextEl _context, StackCall _stack) {
        ExecMemberCallingsBlock callee_ = getCallee();
        if (callee_ instanceof ExecAbstractSwitchMethod) {
            return false;
        }
        if (getCheckedParams() == 0) {
            setCheckedParams(1);
            return _stack.getStopper().isStopAtExcMethod();
        }
        return false;
    }

    @Override
    protected boolean postArg(StackCall _stack) {
        ExecMemberCallingsBlock callee_ = getCallee();
        if (callee_ instanceof ExecAbstractSwitchMethod) {
            return true;
        }
        return postArgBase(_stack);
    }
}
