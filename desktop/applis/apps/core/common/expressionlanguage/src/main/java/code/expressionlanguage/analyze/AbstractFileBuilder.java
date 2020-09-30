package code.expressionlanguage.analyze;

import code.expressionlanguage.options.KeyWords;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractFileBuilder {
    DefaultAliasGroups getDefaultAliasGroups();
    StringMap<String> buildFiles(KeyWords _keyWords);

    StringList getPredefinedInterfacesInitOrder();

    StringList getPredefinedClasses();

}
