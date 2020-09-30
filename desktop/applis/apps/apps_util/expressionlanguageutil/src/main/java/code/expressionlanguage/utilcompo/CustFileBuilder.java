package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.util.EntryCust;
import code.util.StringMap;

public class CustFileBuilder extends DefaultFileBuilder {
    private CustAliases custAliases;
    public CustFileBuilder(LgNamesContent _content, CustAliases _custAliases) {
        super(_content);
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
