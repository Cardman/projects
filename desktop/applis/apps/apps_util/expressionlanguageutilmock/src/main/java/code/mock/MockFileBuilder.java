package code.mock;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.StringViewReplaceAliases;
import code.util.StringMap;

public class MockFileBuilder extends DefaultFileBuilder {
    private final StringViewReplaceAliases stringViewReplaceAliases;
    public MockFileBuilder(LgNamesContent _content, DefaultAliasGroups _defaultAliasGroups, StringViewReplaceAliases _s) {
        super(_content, _defaultAliasGroups);
        this.stringViewReplaceAliases = _s;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        m_.addAllEntries(stringViewReplaceAliases.buildFiles(_keyWords, getContent()));
        return m_;
    }
}
