package code.expressionlanguage.functionid;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.DisplayedStrings;

public interface Identifiable {

    boolean isVararg();
    boolean isStaticMethod();
    String getName();
    String getParametersType(int _index);
    int getParametersTypesLength();
    boolean getParametersRef(int _index);
    String getSignature(ContextEl _ana);
    String getSignature(DisplayedStrings _ana);
}
