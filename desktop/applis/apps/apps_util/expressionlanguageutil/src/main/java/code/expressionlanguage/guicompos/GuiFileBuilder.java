package code.expressionlanguage.guicompos;

import code.expressionlanguage.stds.LgNamesContent;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.CustFileBuilder;

public class GuiFileBuilder extends CustFileBuilder {
    public GuiFileBuilder(LgNamesContent _content, GuiAliases _guiAliases, CustAliases _custAliases) {
        super(new GuiAliasGroups(_guiAliases,_custAliases,_content));
    }

}
