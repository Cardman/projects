package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.EqList;

public abstract class ProcessMethodCommon {

    protected static final String ARR_NUMBER = "[java.lang.Number";
    protected static final String ARR_INTEGER = "[java.lang.Integer";
    protected static final String ARR_OBJECT = "[java.lang.Object";
    protected static final String ARR_ARR_OBJECT = "[[java.lang.Object";
    protected static final String ARR_CUST = "[pkg.ExThree";
    protected static final String ARR_ARR_CUST = "[[pkg.ExThree";
    protected static final String NUMBERS = "code.expressionlanguage.classes.Ints";
    protected static final String CUST = NUMBERS;
    protected static final String INTEGER = "java.lang.Integer";
    protected static final String STRING = "java.lang.String";

    protected static Argument calculateArgument(String _class, MethodId _method, CustList<Argument> _args, ContextEl _cont) {
        EqList<ClassName> constraints_ = new EqList<ClassName>();
        for (ClassName c: _method.getClassNames()) {
            constraints_.add(new ClassName(c.getName(),false));
        }
        MethodId fct_ = new MethodId(_method.isStaticMethod(), _method.getName(),constraints_);
        Classes classes_ = _cont.getClasses();
        MethodBlock method_ = classes_.getMethodBodiesById(_class, fct_).first();
        Block firstChild_ = method_.getFirstChild();
        if (firstChild_ == null) {
            Argument a_ = new Argument();
            return a_;
        }
        Argument argGlLoc_ = new Argument();
        return ProcessMethod.calculateArgument(argGlLoc_, _class, fct_, _args, _cont);
    }

    protected static MethodId getMethodId(String _name, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            cl_.add(new ClassName(c, false));
        }
        return new MethodId(true, _name, cl_);
    }

    protected static MethodId getMethodId(String _name, boolean _vararg, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            boolean var_ = _vararg && _classNames.length == cl_.size() + 1;
            cl_.add(new ClassName(c, var_));
        }
        return new MethodId(true, _name, cl_);
    }

    protected static Argument instanceArgument(String _class, Argument _global, ConstructorId _id, CustList<Argument> _args, ContextEl _cont) {
        int len_ = _id.getClassNames().size();
        EqList<ClassName> constraints_ = new EqList<ClassName>();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            String n_ = _id.getClassNames().get(i).getName();
            constraints_.add(new ClassName(n_,false));
        }
        ConstructorId id_ = new ConstructorId(_id.getName(),constraints_);
        return ProcessMethod.instanceArgument(_class, _global, id_, _args, _cont);
    }

    protected static ConstructorId getConstructorId(String _name, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            cl_.add(new ClassName(c, false));
        }
        return new ConstructorId(_name, cl_);
    }

    protected static ConstructorId getConstructorId(String _name, boolean _vararg, String..._classNames) {
        EqList<ClassName> cl_ = new EqList<ClassName>();
        for (String c: _classNames) {
            boolean var_ = _vararg && _classNames.length == cl_.size() + 1;
            cl_.add(new ClassName(c, var_));
        }
        return new ConstructorId(_name, cl_);
    }
    protected static ContextEl contextEl(int... _m) {
        ContextEl ct_;
        if (_m.length == 0) {
            ct_ = new ContextEl();
        } else {
            ct_ = new ContextEl(_m[0]);
        }
        InitializationLgNames.initAdvStandards(ct_);
        ct_.initError();
        return ct_;
    }
}
