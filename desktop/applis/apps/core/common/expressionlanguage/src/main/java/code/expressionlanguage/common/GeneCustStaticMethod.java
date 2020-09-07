package code.expressionlanguage.common;


import code.util.StringList;

public interface GeneCustStaticMethod extends GeneMethod {

    boolean isStaticMethod();

    StringList getParametersNames();
    String getImportedReturnType();
}
