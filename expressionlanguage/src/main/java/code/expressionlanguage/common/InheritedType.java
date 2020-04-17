package code.expressionlanguage.common;

import code.expressionlanguage.Analyzable;

public interface InheritedType {
    boolean isSubTypeOf(String _fullName, Analyzable _an);
}
