package code.expressionlanguage.common;


import code.expressionlanguage.functionid.MethodId;
import code.util.StringList;

public interface GeneCustStaticMethod {

    MethodId getId();
    boolean isStaticMethod();

    StringList getParametersNames();
    String getImportedReturnType();
}
