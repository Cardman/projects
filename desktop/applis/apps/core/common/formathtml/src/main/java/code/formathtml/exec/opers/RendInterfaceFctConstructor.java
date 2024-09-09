package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.opers.ExecAbstractInvokingConstructor;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(invokingConstructorContent.getOffsetOper(), _rendStack);
        RendDynOperationNode main_ = getMainNode(this);
        ArgumentsPair pair_ = getArgumentPair(_nodes, main_);
        if (getIndexChild() == 1) {
            //init and test
            Struct lda_ = ExecInheritsAdv.checkObject(_context.getStandards().getContent().getReflect().getAliasFct(), ArgumentListCall.getNull(pair_.getArgument()), _context, _rendStack.getStackCall());
            if (_context.callsOrException(_rendStack.getStackCall())) {
                setSimpleArgument(NullStruct.NULL_VALUE, _nodes, _context, _rendStack);
                return;
            }
            String form_ = className;
            Struct struct_ = ExecCastOperation.wrapFct(form_,true, _context, lda_);
            Struct ref_ = ExecInheritsAdv.checkObject(form_, struct_, _context, _rendStack.getStackCall());
            if (_context.callsOrException(_rendStack.getStackCall())) {
                setSimpleArgument(NullStruct.NULL_VALUE, _nodes, _context, _rendStack);
                return;
            }
            pair_.setArgument(ref_);
            prepareArgument(_nodes, ref_, _context, _rendStack);
            ArgumentWrapper argres_ = RendDynOperationNode.processCall(NullStruct.NULL_VALUE, _context, _rendStack);
            setSimpleArgument(argres_, _nodes, _context, _rendStack);
            return;
        }
        prepareArgument(_nodes, ArgumentListCall.getNull(pair_.getArgument()), _context, _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(NullStruct.NULL_VALUE, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
    private void prepareArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, Struct _arguments, ContextEl _context, RendStackCall _rendStackCall) {
        ExecFormattedRootBlock superClass_ = StackCall.formatVarType(_rendStackCall,invokingConstructorContent.getFormattedType());
        ExecAbstractInvokingConstructor.prep(_context,_rendStackCall.getStackCall(),_arguments,superClass_,buildInfos(_all),invokingConstructorContent,pair);
    }

}
