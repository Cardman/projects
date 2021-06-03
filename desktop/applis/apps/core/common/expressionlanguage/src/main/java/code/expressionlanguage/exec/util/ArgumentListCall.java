package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.util.CustList;

public final class ArgumentListCall {
    private final CustList<ArgumentWrapper> argumentWrappers = new CustList<ArgumentWrapper>();

    private Argument right;

    public ArgumentListCall() {
    }
    public ArgumentListCall(Argument _arg) {
        addArg(_arg);
    }
    public ArgumentListCall(CustList<Argument> _args) {
        for (Argument a: _args) {
            addArg(a);
        }
    }
    public CustList<ArgumentWrapper> getArgumentWrappers() {
        return argumentWrappers;
    }


    private void addArg(Argument _arg) {
        argumentWrappers.add(new ArgumentWrapper(_arg,null));
    }
    public CustList<Argument> getArguments() {
        CustList<Argument> args_ = new CustList<Argument>();
        for (ArgumentWrapper a: argumentWrappers) {
            args_.add(a.getValue());
        }
        return args_;
    }

    public Argument getRight() {
        return right;
    }

    public void setRight(Argument _right) {
        this.right = _right;
    }
}
