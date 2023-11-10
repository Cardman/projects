package code.mock;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.StringMap;

public final class MockAliasFileBuilder implements AbsAliasFileBuilder {
    private final StringMap<String> addon;

    public MockAliasFileBuilder(StringMap<String> _a) {
        addon = _a;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content) {
        return addon;
    }

}
