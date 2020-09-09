package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public interface RendCallable {
    Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right);
}
