package code.expressionlanguage.functionid;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.StringList;

public interface Identifiable {

    boolean isVararg();
    boolean isStaticMethod();
    String getName();
    StringList getParametersTypes();
    String getParametersType(int _index);
    int getParametersTypesLength();
    String getSignature(ContextEl _ana);
    String getSignature(DisplayedStrings _ana);
}
