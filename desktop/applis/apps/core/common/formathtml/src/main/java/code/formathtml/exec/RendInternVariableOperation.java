package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.util.IdMap;

public final class RendInternVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private String variableName;

    public RendInternVariableOperation(int _indexChild, ClassArgumentMatching _res, int _order, String _varName) {
        super(_indexChild,_res,_order);
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
