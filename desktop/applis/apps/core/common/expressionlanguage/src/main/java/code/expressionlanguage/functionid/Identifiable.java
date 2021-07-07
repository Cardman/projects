package code.expressionlanguage.functionid;

import code.expressionlanguage.common.DisplayedStrings;

public interface Identifiable {

    boolean isVararg();
    boolean isStaticMethod();
    String getName();
    String getParametersType(int _index);
    int getParametersTypesLength();
    boolean getParametersRef(int _index);
    String getSignature(DisplayedStrings _ana);
}
