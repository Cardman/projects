package code.formathtml.exec;

import code.expressionlanguage.methods.util.ArgumentsPair;
import code.formathtml.Configuration;
import code.util.IdMap;

public interface RendCalculableOperation {

    void calculate(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf);
}
