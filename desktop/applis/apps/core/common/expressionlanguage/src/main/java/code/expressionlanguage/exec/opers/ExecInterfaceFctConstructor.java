package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
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
        ExecOperationNode main_ = ExecTemplates.getMainNode(this);
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, main_);
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
            Argument res_ = getArgument(_nodes,ref_, _conf, _stack);
            setSimpleArgument(res_, _conf, _nodes, _stack);
            return;
        }
        Argument res_ = getArgument(_nodes, mainArgument_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _argument, ContextEl _conf, StackCall _stackCall) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stackCall);
        String superClass_ = _stackCall.formatVarType(getClassFromName());
        checkParameters(_conf, superClass_, getPair(), _argument,null, getArgs(_nodes, superClass_),CallPrepareState.CTOR, InstancingStep.USING_SUPER,null, MethodAccessKind.INSTANCE, _stackCall);
        return Argument.createVoid();
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _superClass) {
        String lastType_ = ExecTemplates.quickFormat(getPair().getType(),_superClass, getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }
}
