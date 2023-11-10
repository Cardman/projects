package code.formathtml.util;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.stds.LgNamesContent;

public class BeanFileBuilder extends DefaultFileBuilder {
    public BeanFileBuilder(DefaultAliasGroups _defaultAliasGroups) {
        super(_defaultAliasGroups);
    }

    public static BeanFileBuilder newInstance(LgNamesContent _content, DefaultBeanAliases _beanAliases) {
        return new BeanFileBuilder(new BeanAliasGroups(_beanAliases,_content));
    }
}
