package code.renders.utilcompo;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.formathtml.util.BeanFileBuilder;
import code.formathtml.util.DefaultBeanAliases;
import code.util.StringMap;

public class CustBeanFileBuilder extends BeanFileBuilder {
    private final CustAliases custAliases;
    public CustBeanFileBuilder(LgNamesContent _content, DefaultBeanAliases _beanAliases, CustAliases _custAliases) {
        super(_content, _beanAliases,new CustBeanAliasGroups(_beanAliases, _custAliases, _content));
        custAliases = _custAliases;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        m_.addAllEntries(custAliases.buildFiles(_keyWords, getContent()));
        m_.addAllEntries(custAliases.getStringViewReplaceAliases().buildFiles(_keyWords, getContent()));
        return m_;
    }
}
