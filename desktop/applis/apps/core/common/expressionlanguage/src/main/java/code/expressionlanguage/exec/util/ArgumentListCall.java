package code.expressionlanguage.exec.util;

import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ArgumentListCall {
    private final CustList<ArgumentWrapper> argumentWrappers;

    private Struct right;

    public ArgumentListCall() {
        this(new CustList<ArgumentWrapper>());
    }
    public ArgumentListCall(Struct _arg) {
        this(one(_arg));
    }
    public ArgumentListCall(CustList<ArgumentWrapper> _ls) {
        argumentWrappers = _ls;
    }
    private static CustList<ArgumentWrapper> one(Struct _arg) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_arg,null));
        return ls_;
    }
    public static ArgumentListCall wrapCall(CustList<Struct> _args) {
        return new ArgumentListCall(wrapList(_args));
    }
    public static ArgumentListCall wrapCall(CustList<ArgumentWrapper> _ls, Struct _right) {
        ArgumentListCall ls_ = new ArgumentListCall(_ls);
        ls_.setRight(_right);
        return ls_;
    }
    public static CustList<ArgumentWrapper> wrapList(CustList<Struct> _args) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        for (Struct a: _args) {
            ls_.add(new ArgumentWrapper(a,null));
        }
        return ls_;
    }

    public static Struct getNull(Struct _arg) {
        if (_arg != null) {
            return _arg;
        }
        return NullStruct.NULL_VALUE;
    }

    public static Struct wrapStr(String _arg) {
        if (_arg != null) {
            return new StringStruct(_arg);
        }
        return NullStruct.NULL_VALUE;
    }

    public static Struct[] toArgArray(CustList<Struct> _args) {
        int len_ = _args.size();
        Struct[] args_;
        args_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            args_[i] = _args.get(i);
        }
        return args_;
    }

    public static boolean isNotFalseValue(Struct _arg) {
        if (_arg == null) {
            return true;
        }
        return !BooleanStruct.isFalse(_arg);
    }

    public static boolean isTrueValue(Struct _arg) {
        if (_arg == null) {
            return false;
        }
        return BooleanStruct.isTrue(_arg);
    }

    public CustList<ArgumentWrapper> getArgumentWrappers() {
        return argumentWrappers;
    }

    public CustList<Struct> getArguments() {
        CustList<Struct> args_ = new CustList<Struct>();
        for (ArgumentWrapper a: argumentWrappers) {
            args_.add(a.getValue());
        }
        return args_;
    }

    public Struct getRight() {
        return right;
    }

    public void setRight(Struct _right) {
        this.right = _right;
    }
}
