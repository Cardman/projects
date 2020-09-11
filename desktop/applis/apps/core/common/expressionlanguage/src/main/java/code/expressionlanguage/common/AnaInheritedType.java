package code.expressionlanguage.common;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public interface AnaInheritedType {
    boolean isSubTypeOf(String _fullName, AnalyzedPageEl _an);
}
