package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.InternVariableOperation;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.util.IdMap;

public final class RendInternVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private String variableName;
    RendInternVariableOperation(InternVariableOperation _i) {
        super(_i);
        variableName = _i.getVariableName();
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        ImportingPage ip_ = ((Configuration)_conf).getLastPage();
        LocalVariable locVar_ = ip_.getInternVars().getVal(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        LocalVariable locVar_ = ip_.getInternVars().getVal(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        setSimpleArgument(a_, _conf, _nodes);
    }
}
