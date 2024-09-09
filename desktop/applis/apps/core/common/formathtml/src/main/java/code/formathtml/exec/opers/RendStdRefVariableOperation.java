package code.formathtml.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendStdRefVariableOperation extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult{
    private final ExecVariableContent variableContent;
    private final boolean declare;
    public RendStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent, boolean _declare) {
        super(_l);
        variableContent = _variableContent;
        declare = _declare;
    }
    public RendStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent) {
        super(_l);
        variableContent = _variableContent;
        declare = false;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _rendStack);
        ImportingPage ip_ = _rendStack.getLastPage();
        AbstractWrapper val_ = ExecVariableTemplates.getWrapper(resultCanBeSet(),variableContent,null,ip_.getRefParams());
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        pair_.setWrapper(val_);
        Struct arg_ = processCall(ExecVariableTemplates.getArgValue(val_, _context, _rendStack.getStackCall()),_context,_rendStack).getValue();
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context, _rendStack);
        } else {
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
        }
    }
    public String getVariableName() {
        return variableContent.getVariableName();
    }


    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }

    @Override
    public Struct calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Struct _right, ContextEl _context, RendStackCall _rendStack) {
        return trySetArgument(_nodes,_context, _right, _rendStack);
    }

    private Struct trySetArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _res, RendStackCall _rendStackCall) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        return processCall(ExecVariableTemplates.trySetArgument(_conf, _res, pair_, _rendStackCall.getStackCall()),_conf,_rendStackCall).getValue();
    }

    public boolean isDeclare() {
        return declare;
    }
}
