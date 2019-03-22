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
    void setVarArgWrap(boolean _v);
    void format(StringList _params);
    Identifiable getFormatted();
    void setInvocation(InvocationMethod _inv);
    InvocationMethod getInvocation();
}
