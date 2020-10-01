package code.expressionlanguage.guicompos;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.CustFileBuilder;
import code.util.EntryCust;
import code.util.StringMap;

public class GuiFileBuilder extends CustFileBuilder {
    private GuiAliases guiAliases;
    public GuiFileBuilder(LgNamesContent _content, GuiAliases _guiAliases, CustAliases _custAliases) {
        super(_content, _custAliases, new GuiAliasGroups(_guiAliases,_custAliases,_content));
        guiAliases = _guiAliases;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        for (EntryCust<String,String> e:guiAliases.buildFiles(_keyWords, getContent(), getPredefinedClasses(), getPredefinedInterfacesInitOrder()).entryList()) {
            m_.addEntry(e.getKey(),e.getValue());
        }
        return m_;
    }
}
