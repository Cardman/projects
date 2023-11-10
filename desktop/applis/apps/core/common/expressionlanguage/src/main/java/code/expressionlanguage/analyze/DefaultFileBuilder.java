package code.expressionlanguage.analyze;

import code.expressionlanguage.stds.LgNamesContent;

public class DefaultFileBuilder implements AbstractFileBuilder {
    private final DefaultAliasGroups defaultAliasGroups;

    public DefaultFileBuilder(DefaultAliasGroups _defaultAliasGroups) {
        defaultAliasGroups = _defaultAliasGroups;
    }

    public static DefaultFileBuilder newInstance(LgNamesContent _content) {
        return new DefaultFileBuilder(new DefaultAliasGroups(_content));
    }

    public DefaultAliasGroups getDefaultAliasGroups() {
        return defaultAliasGroups;
    }

}
