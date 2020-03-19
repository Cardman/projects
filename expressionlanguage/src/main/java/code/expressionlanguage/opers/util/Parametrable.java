package code.expressionlanguage.opers.util;

import code.expressionlanguage.Analyzable;
import code.util.StringList;

public interface Parametrable {

    ParametersGroup getParameters();

    String getReturnType();

    String getClassName();

    boolean isVararg();

    int getImported();
    int getAncestor();
    boolean isVarArgWrap();
    void setVarArgWrap(boolean _v);

    Identifiable getGeneFormatted();

    void setInvocation(InvocationMethod _inv);
    InvocationMethod getInvocation();
}
