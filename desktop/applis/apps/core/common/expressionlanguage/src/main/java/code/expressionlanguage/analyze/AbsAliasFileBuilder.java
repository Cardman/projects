package code.expressionlanguage.analyze;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.StringMap;

public interface AbsAliasFileBuilder {
    StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content);
}
