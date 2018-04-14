package code.expressionlanguage.common;

import code.expressionlanguage.Analyzable;
import code.util.StringList;

public interface GeneClass extends GeneType {

    String getGenericSuperClass(Analyzable _classes);

    String getSuperClass(Analyzable _classes);

    StringList getDirectGenericInterfaces(Analyzable _classes);

    StringList getDirectInterfaces(Analyzable _classes);

    StringList getAllSuperClasses(Analyzable _classes);

}
