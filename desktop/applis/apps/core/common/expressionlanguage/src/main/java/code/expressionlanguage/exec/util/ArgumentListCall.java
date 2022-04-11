package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.util.CustList;

public final class ArgumentListCall {
    private final CustList<ArgumentWrapper> argumentWrappers;

    private Argument right;

    public ArgumentListCall() {
        this(new CustList<ArgumentWrapper>());
    }
    public ArgumentListCall(Argument _arg) {
        this(one(_arg));
    }
    public ArgumentListCall(CustList<ArgumentWrapper> _ls) {
        argumentWrappers = _ls;
    }
    private static CustList<ArgumentWrapper> one(Argument _arg) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_arg,null));
        return ls_;
    }
    public static ArgumentListCall wrapCall(CustList<Argument> _args) {
        return new ArgumentListCall(wrapList(_args));
    }
    public static ArgumentListCall wrapCall(CustList<ArgumentWrapper> _ls, Argument _right) {
        ArgumentListCall ls_ = new ArgumentListCall(_ls);
        ls_.setRight(_right);
        return ls_;
    }
    public static CustList<ArgumentWrapper> wrapList(CustList<Argument> _args) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        for (Argument a: _args) {
            ls_.add(new ArgumentWrapper(a,null));
        }
        return ls_;
    }
    public CustList<ArgumentWrapper> getArgumentWrappers() {
        return argumentWrappers;
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
