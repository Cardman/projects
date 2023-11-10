package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.utilcompo.AbsFileBuilderFactory;

public final class GuiFileBuilderFactory implements AbsFileBuilderFactory {
    private final LgNamesGui stds;
    public GuiFileBuilderFactory(LgNamesGui _definedLgNames) {
        stds = _definedLgNames;
    }
    @Override
    public AbstractFileBuilder build() {
        return new GuiFileBuilder(stds.getContent(),stds.getGuiAliases(),stds.getExecContent().getCustAliases());
    }
}
