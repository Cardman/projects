package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.util.IdMap;

public final class RendFinalVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private ExecVariableContent variableContent;

    public RendFinalVariableOperation(ExecOperationContent _content, ExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _conf);
        ImportingPage ip_ = _conf.getLastPage();
        return ExecTemplates.getIndexLoop(_conf.getContext(), variableContent.getVariableName(), ip_.getPageEl(), variableContent.getDeep());
    }

}
