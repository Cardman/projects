package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundSwitch;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
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
            AbstractParamChecker sw_ = new SwitchParamChecker((ExecAbstractSwitchMethod) callee_, _args);
            FormattedParameters formatted_ = sw_.checkParams(getPair().getType(), getClassName(), getInstance(), getMetaInfo().getCache(), _context, getAccessKind(), _stack);
            if (_context.callsOrException(_stack)) {
                return Argument.createVoid();
            }
            Parameters parameters_ = formatted_.getParameters();
            _stack.setCallingState(new CustomFoundSwitch(getInstance(),formatted_.getFormattedClass(),getPair().getType(),(ExecAbstractSwitchMethod) callee_,parameters_.getCache(),new Argument(parameters_.getRefParameters().firstValue().getValue(_stack,_context))));
            return Argument.createVoid();
        }
        ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(getPair(),getClassName(),getInstance(),_args,_context, getAccessKind());
        l_.setRight(_right);
        return ExecInvokingOperation.callPrepare(_context, getClassName(), getPair(), getInstance(), getMetaInfo().getCache(), l_, getAccessKind(), _stack);
    }

}
