package aiki.main;

import aiki.gui.threads.PreparedRenderedPages;
import code.gui.EnabledMenu;
import code.gui.LanguageDialogButtons;
import code.threads.IntCallable;

public final class DataWebInit implements IntCallable<AikiNatLgNamesNavigation> {
    private final PreparedRenderedPages render;
    private final EnabledMenu generalHelp;

    public DataWebInit(PreparedRenderedPages _r, EnabledMenu _geneHelp) {
        this.render = _r;
        this.generalHelp = _geneHelp;
    }

    @Override
    public AikiNatLgNamesNavigation call() {
        render.run();
        LanguageDialogButtons.enable(generalHelp,true);
        return new AikiNatLgNamesNavigation(render.getBeanNatLgNames(),render.getNavigation());
    }
}
