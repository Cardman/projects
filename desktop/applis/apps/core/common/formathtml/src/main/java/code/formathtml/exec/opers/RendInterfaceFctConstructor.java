package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendInterfaceFctConstructor extends RendInvokingOperation implements RendCalculableOperation {
    private final String className;

    private final ExecInvokingConstructorContent invokingConstructorContent;

    private final ExecTypeFunction pair;
    public RendInterfaceFctConstructor(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecTypeFunction _pair) {
        super(_content, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        className = _className;
        pair = _pair;

    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode main_ = getMainNode(this);
        ArgumentsPair pair_ = getArgumentPair(_nodes, main_);
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(Argument.getNullableValue(pair_.getArgument()).getStruct());
            if (!ExecTemplates.checkObject(_context.getStandards().getContent().getReflect().getAliasFct(), lda_, _context, _stack)) {
                setSimpleArgument(Argument.createVoid(), _nodes, _context, _stack, _rendStack);
                return;
            }
            String form_ = className;
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _context, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _context, _stack)) {
                setSimpleArgument(Argument.createVoid(), _nodes, _context, _stack, _rendStack);
                return;
            }
            pair_.setArgument(ref_);
            prepareArgument(_nodes, ref_, _context, _stack, _rendStack);
            Argument argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
            setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
            return;
        }
        prepareArgument(_nodes, Argument.getNullableValue(pair_.getArgument()), _context, _stack, _rendStack);
        Argument argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }
    private void prepareArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, Argument _arguments, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ invokingConstructorContent.getOffsetOper(), _rendStackCall);
        String superClass_ = invokingConstructorContent.getClassFromName();
        String lastType_ = getLastType();
        lastType_ = ExecInherits.quickFormat(pair.getType(),superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        ExecInvokingOperation.checkParameters(_context, superClass_, pair, _arguments,null, fectchArgs(_all,lastType_,natvararg_, _rendStackCall),CallPrepareState.CTOR, InstancingStep.USING_SUPER,null, MethodAccessKind.INSTANCE, _stackCall);
    }

    public String getLastType() {
        return invokingConstructorContent.getLastType();
    }

    public int getNaturalVararg() {
        return invokingConstructorContent.getNaturalVararg();
    }

}
