package code.expressionlanguage.common;

import code.util.StringList;

public interface AnaGeneType extends AnaInheritedType {
    StringList getAllSuperTypes();

    StringList getAllGenericSuperTypes();
    boolean isStaticType();
    String getPackageName();

    String getFullName();

    String getGenericString();
}
