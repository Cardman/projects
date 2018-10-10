package code.expressionlanguage.opers.util;

import code.util.StringList;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    Identifiable getId();

    String getClassName();

    boolean isStatic();

    boolean isVararg();

    int getImported();
    int getAncestor();
    boolean isVarArgWrap();
    void setVarArgWrap(boolean v);
    void format(StringList _params);
    Identifiable getFormatted();
}
