package code.formathtml.exec;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.formathtml.Configuration;
import code.util.IdMap;

public interface RendCalculableOperation {

    void calculate(ExecutableCode _conf);
    void calculate(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf);
}
