package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendInterfaceFctConstructor extends RendInvokingOperation implements RendCalculableOperation {
    private final String className;

    private final ExecInvokingConstructorContent invokingConstructorContent;

    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public RendInterfaceFctConstructor(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecTypeFunction _pair) {
        super(_content, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        className = _className;
        pair = _pair;
        formattedType = _invokingConstructorContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode main_ = getMainNode(this);
        ArgumentsPair pair_ = getArgumentPair(_nodes, main_);
        if (getIndexChild() == 1) {
            //init and test
            Argument lda_ = new Argument(Argument.getNullableValue(pair_.getArgument()).getStruct());
            if (!ExecTemplates.checkObject(_context.getStandards().getContent().getReflect().getAliasFct(), lda_, _context, _rendStack.getStackCall())) {
                setSimpleArgument(Argument.createVoid(), _nodes, _context, _rendStack);
                return;
            }
            String form_ = className;
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, _context, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, _context, _rendStack.getStackCall())) {
                setSimpleArgument(Argument.createVoid(), _nodes, _context, _rendStack);
                return;
            }
            pair_.setArgument(ref_);
            prepareArgument(_nodes, ref_, _context, _rendStack);
            ArgumentWrapper argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack);
            setSimpleArgument(argres_, _nodes, _context, _rendStack);
            return;
        }
        prepareArgument(_nodes, Argument.getNullableValue(pair_.getArgument()), _context, _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
    private void prepareArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, Argument _arguments, ContextEl _context, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ invokingConstructorContent.getOffsetOper(), _rendStackCall);
        ExecFormattedRootBlock superClass_ = invokingConstructorContent.getFormattedType();
        String lastType_ = getLastType();
        lastType_ = ExecInherits.quickFormat(formattedType, lastType_);
        int natvararg_ = getNaturalVararg();
        new DefaultParamChecker(pair, ExecInvokingOperation.fectchArgs(lastType_, natvararg_, null, _context, _rendStackCall.getStackCall(), buildInfos(_all)), MethodAccessKind.INSTANCE, CallPrepareState.CTOR, InstancingStep.USING_SUPER).checkParams(superClass_, _arguments, null, _context, _rendStackCall.getStackCall());
    }

    public String getLastType() {
        return invokingConstructorContent.getLastType();
    }

    public int getNaturalVararg() {
        return invokingConstructorContent.getNaturalVararg();
    }

}
