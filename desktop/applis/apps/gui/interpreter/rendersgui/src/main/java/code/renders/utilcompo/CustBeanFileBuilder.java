package code.renders.utilcompo;

import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.formathtml.util.BeanFileBuilder;
import code.formathtml.util.DefaultBeanAliases;

public class CustBeanFileBuilder extends BeanFileBuilder {
    public CustBeanFileBuilder(LgNamesContent _content, DefaultBeanAliases _beanAliases, CustAliases _custAliases) {
        super(new CustBeanAliasGroups(_beanAliases, _custAliases, _content));
    }

}
