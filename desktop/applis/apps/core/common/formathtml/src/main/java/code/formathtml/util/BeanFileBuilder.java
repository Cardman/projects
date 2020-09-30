package code.formathtml.util;

import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.EntryCust;
import code.util.StringMap;

public class BeanFileBuilder extends DefaultFileBuilder {
    private DefaultBeanAliases beanAliases;
    public BeanFileBuilder(LgNamesContent _content, DefaultBeanAliases _beanAliases) {
        super(_content);
        beanAliases = _beanAliases;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        for (EntryCust<String,String> e:beanAliases.buildFiles(_keyWords, getContent(), getPredefinedClasses(), getPredefinedInterfacesInitOrder()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }
}
