package code.expressionlanguage.opers.util;

import code.util.StringList;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    String getClassName();

    boolean isStatic();

    boolean isVararg();

    int getImported();
    int getAncestor();
    boolean isVarArgWrap();
    void setVarArgWrap(boolean v);
    void format(StringList _params);
    Identifiable getFormatted();
    boolean same(Identifiable _id);
    void setInvocation(InvocationMethod _inv);
    InvocationMethod getInvocation();
}
