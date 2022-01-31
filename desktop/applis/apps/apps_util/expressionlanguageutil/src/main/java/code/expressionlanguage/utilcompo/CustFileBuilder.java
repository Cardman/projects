package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.EntryCust;
import code.util.StringMap;

public class CustFileBuilder extends DefaultFileBuilder {
    private CustAliases custAliases;
    public CustFileBuilder(LgNamesContent _content, CustAliases _custAliases,DefaultAliasGroups _defaultAliasGroups) {
        super(_content,_defaultAliasGroups);
        custAliases = _custAliases;
    }

    public static CustFileBuilder newInstance(LgNamesContent _content, CustAliases _beanAliases) {
        return new CustFileBuilder(_content,_beanAliases,new CustAliasGroups(_beanAliases, _content));
    }
    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        for (EntryCust<String,String> e:custAliases.buildFiles(_keyWords, getContent()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }
}
