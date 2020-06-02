package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.formathtml.Configuration;
import code.util.CustList;

public interface RendCallable {
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right);
}
