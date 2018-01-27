package code.expressionlanguage.common;

import code.expressionlanguage.ContextEl;
import code.util.StringList;

public interface GeneClass extends GeneType {

    String getGenericSuperClass(ContextEl _classes);

    String getSuperClass(ContextEl _classes);

    StringList getDirectGenericInterfaces(ContextEl _classes);

    StringList getDirectInterfaces(ContextEl _classes);

    StringList getAllSuperClasses(ContextEl _classes);

}
