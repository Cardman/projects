package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.DefaultAliasGroups;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.StringMap;

public class CustFileBuilder extends DefaultFileBuilder {
    private final CustAliases custAliases;
    public CustFileBuilder(LgNamesContent _content, CustAliases _custAliases,DefaultAliasGroups _defaultAliasGroups) {
        super(_content,_defaultAliasGroups);
        custAliases = _custAliases;
    }

//    public static CustFileBuilder newInstance(LgNamesContent _content, CustAliases _beanAliases) {
//        return new CustFileBuilder(_content,_beanAliases,new CustAliasGroups(_beanAliases, _content));
//    }
    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        m_.addAllEntries(custAliases.buildFiles(_keyWords, getContent()));
        m_.addAllEntries(custAliases.getStringViewReplaceAliases().buildFiles(_keyWords, getContent()));
        return m_;
    }
}
