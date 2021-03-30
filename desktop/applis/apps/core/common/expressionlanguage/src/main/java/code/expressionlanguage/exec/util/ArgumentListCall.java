package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.util.CustList;

public final class ArgumentListCall {
    private final CustList<ArgumentWrapper> argumentWrappers = new CustList<ArgumentWrapper>();

    public CustList<ArgumentWrapper> getArgumentWrappers() {
        return argumentWrappers;
    }


    public void addAllArgs(CustList<Argument> _args) {
        for (Argument a: _args) {
            addArg(a);
        }
    }

    public void addArg(Argument _arg) {
        argumentWrappers.add(new ArgumentWrapper(_arg,null));
    }
    public CustList<Argument> getArguments() {
        CustList<Argument> args_ = new CustList<Argument>();
        for (ArgumentWrapper a: argumentWrappers) {
            Argument value_ = a.getValue();
            if (value_ != null) {
                args_.add(value_);
            }
        }
        return args_;
    }
}
