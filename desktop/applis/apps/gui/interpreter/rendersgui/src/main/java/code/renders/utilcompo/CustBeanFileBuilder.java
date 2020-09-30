package code.renders.utilcompo;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.util.BeanFileBuilder;
import code.formathtml.util.DefaultBeanAliases;
import code.util.EntryCust;
import code.util.StringMap;

public class CustBeanFileBuilder extends BeanFileBuilder {
    private CustRenderAliases custRenderAliases;
    public CustBeanFileBuilder(LgNamesContent _content, DefaultBeanAliases _beanAliases, CustRenderAliases _custRenderAliases) {
        super(_content, _beanAliases,new CustBeanAliasGroups(_custRenderAliases,_beanAliases,_content));
        custRenderAliases = _custRenderAliases;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        for (EntryCust<String,String> e:custRenderAliases.buildFiles(_keyWords, getContent(), getPredefinedClasses(), getPredefinedInterfacesInitOrder()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }
}
