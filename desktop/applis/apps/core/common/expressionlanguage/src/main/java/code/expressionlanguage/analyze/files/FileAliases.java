package code.expressionlanguage.analyze.files;

import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.stds.PrimitiveTypes;

public final class FileAliases {

    private final PrimitiveTypes primTypes;
    private final String aliasVoid;
    private final String aliasObject;

    public FileAliases(LgNamesContent _content) {
        primTypes = _content.getPrimTypes();
        aliasVoid = _content.getCoreNames().getAliasVoid();
        aliasObject = _content.getCoreNames().getAliasObject();
    }

    public PrimitiveTypes getPrimTypes() {
        return primTypes;
    }

    public String getAliasObject() {
        return aliasObject;
    }

    public String getAliasVoid() {
        return aliasVoid;
    }
}
