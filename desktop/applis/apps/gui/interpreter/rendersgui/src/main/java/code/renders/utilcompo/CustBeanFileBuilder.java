package code.renders.utilcompo;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.formathtml.util.BeanFileBuilder;
import code.formathtml.util.DefaultBeanAliases;
import code.util.EntryCust;
import code.util.StringMap;

public class CustBeanFileBuilder extends BeanFileBuilder {
    private CustAliases custAliases;
    public CustBeanFileBuilder(LgNamesContent _content, DefaultBeanAliases _beanAliases, CustAliases _custAliases) {
        super(_content, _beanAliases,new CustBeanAliasGroups(_beanAliases, _custAliases, _content));
        custAliases = _custAliases;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        for (EntryCust<String,String> e:custAliases.buildFiles(_keyWords, getContent(), getPredefinedClasses(), getPredefinedInterfacesInitOrder()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }
}
