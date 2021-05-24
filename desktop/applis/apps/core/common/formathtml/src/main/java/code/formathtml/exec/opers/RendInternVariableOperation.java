package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendInternVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private final String variableName;

    public RendInternVariableOperation(int _indexChild, ExecClassArgumentMatching _res, int _order, String _varName) {
        super(new ExecOperationContent(_indexChild, _res, _order));
        variableName = _varName;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        LocalVariable locVar_ = ip_.getInternVars().getVal(variableName);
        Argument a_ = new Argument(locVar_.getStruct());
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }
}
