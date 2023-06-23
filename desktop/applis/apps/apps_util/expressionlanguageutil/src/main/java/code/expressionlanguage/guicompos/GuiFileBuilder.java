package code.expressionlanguage.guicompos;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.CustFileBuilder;
import code.util.StringMap;

public class GuiFileBuilder extends CustFileBuilder {
    private final GuiAliases guiAliases;
    public GuiFileBuilder(LgNamesContent _content, GuiAliases _guiAliases, CustAliases _custAliases) {
        super(_content, _custAliases, new GuiAliasGroups(_guiAliases,_custAliases,_content));
        guiAliases = _guiAliases;
    }

    @Override
    public StringMap<String> buildFiles(KeyWords _keyWords) {
        StringMap<String> m_ = super.buildFiles(_keyWords);
        m_.addAllEntries(guiAliases.buildFiles(_keyWords, getContent()));
        return m_;
    }
}
