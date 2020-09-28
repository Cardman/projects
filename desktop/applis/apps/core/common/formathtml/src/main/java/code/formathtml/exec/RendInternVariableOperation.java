package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.util.IdMap;

public final class RendInternVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private String variableName;

    public RendInternVariableOperation(int _indexChild, ExecClassArgumentMatching _res, int _order, String _varName) {
        super(new ExecOperationContent(_indexChild, _res, _order));
        variableName = _varName;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        LocalVariable locVar_ = ip_.getInternVars().getVal(variableName);
        Argument a_ = new Argument(locVar_.getStruct());
        setSimpleArgument(a_, _conf, _nodes);
    }
}
