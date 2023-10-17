package code.mock;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.StringViewReplaceAliases;
import code.util.StringMap;

public class MockFileBuilder extends DefaultFileBuilder {
    private final StringViewReplaceAliases stringViewReplaceAliases;
    private final StringMap<String> addon;
    public MockFileBuilder(LgNamesContent _content, DefaultAliasGroups _defaultAliasGroups, StringViewReplaceAliases _s) {
        this(_content, _defaultAliasGroups, _s, new StringMap<String>());
    }
    public MockFileBuilder(LgNamesContent _content, DefaultAliasGroups _defaultAliasGroups, StringViewReplaceAliases _s, StringMap<String> _a) {
        super(_content, _defaultAliasGroups);
        this.stringViewReplaceAliases = _s;
        addon = _a;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        m_.addAllEntries(stringViewReplaceAliases.buildFiles(_keyWords, getContent()));
        m_.addAllEntries(addon);
        return m_;
    }
}
