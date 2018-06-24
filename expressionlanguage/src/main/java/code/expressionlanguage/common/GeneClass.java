package code.expressionlanguage.common;

import code.expressionlanguage.Analyzable;
import code.util.StringList;

public interface GeneClass extends GeneType {

    String getSuperClass(Analyzable _classes);

    StringList getDirectInterfaces(Analyzable _classes);

    StringList getAllSuperClasses(Analyzable _classes);

}
