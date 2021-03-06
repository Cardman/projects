package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecInterfaceFctConstructor extends ExecAbstractInvokingConstructor {
    private final String className;
    public ExecInterfaceFctConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation, _invokingConstructorContent, _pair);
        className = _className;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        ExecOperationNode main_ = ExecHelper.getMainNode(this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, main_);
        Argument mainArgument_ = Argument.getNullableValue(pair_.getArgument());
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(mainArgument_.getStruct());
            if (!ExecTemplates.checkObject(_conf.getStandards().getContent().getReflect().getAliasFct(), lda_, _conf, _stack)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes, _stack);
                return;
            }
            String form_ = _stack.formatVarType(className);
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _conf, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _conf, _stack)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes, _stack);
                return;
            }
            pair_.setArgument(ref_);
            int off_ = getOffsetOper();
            setRelOffsetPossibleLastPage(off_, _stack);
            String superClass_ = _stack.formatVarType(getClassFromName());
            String lastType_ = ExecInherits.quickFormat(getPair().getType(), superClass_, getLastType());
            checkParameters(_conf, superClass_, getPair(), ref_,null, fectchArgs(_nodes, lastType_, getNaturalVararg()),CallPrepareState.CTOR, InstancingStep.USING_SUPER,null, MethodAccessKind.INSTANCE, _stack);
            Argument res_ = Argument.createVoid();
            setSimpleArgument(res_, _conf, _nodes, _stack);
            return;
        }
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);
        String superClass_ = _stack.formatVarType(getClassFromName());
        String lastType_ = ExecInherits.quickFormat(getPair().getType(), superClass_, getLastType());
        checkParameters(_conf, superClass_, getPair(), mainArgument_,null, fectchArgs(_nodes, lastType_, getNaturalVararg()),CallPrepareState.CTOR, InstancingStep.USING_SUPER,null, MethodAccessKind.INSTANCE, _stack);
        Argument res_ = Argument.createVoid();
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
