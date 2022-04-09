package code.expressionlanguage.functionid;

import code.expressionlanguage.common.DisplayedStrings;
import code.util.core.BoolVal;

public interface Identifiable {

    boolean isVararg();
    boolean isStaticMethod();
    String getName();
    String getParametersType(int _index);
    int getParametersTypesLength();
    BoolVal getParametersRef(int _index);
    String getSignature(DisplayedStrings _ana);
}
