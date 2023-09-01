package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public interface AbsCollection<T> {
    Iterable<T> elts();
    void add(T _b);
    void clear();
    void remove(T _b);
    void setAll(AbsCollection<T> _t);
    AbstractInterceptorStdCaller intercept();
}
