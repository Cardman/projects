package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.util.StringList;

public interface Identifiable {

    boolean isVararg();
    boolean isStaticMethod();
    String getName();
    StringList getParametersTypes();
    String getParametersType(int _index);
    int getParametersTypesLength();
    String getSignature(ContextEl _ana);
}
