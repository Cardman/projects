package aiki.main;

import aiki.gui.threads.PreparedRenderedPages;
import code.threads.IntCallable;

public final class DataWebInit implements IntCallable<AikiNatLgNamesNavigation> {
    private final PreparedRenderedPages render;

    public DataWebInit(PreparedRenderedPages _r) {
        this.render = _r;
    }

    @Override
    public AikiNatLgNamesNavigation call() {
        render.run();
        return new AikiNatLgNamesNavigation(render.getBeanNatLgNames(),render.getNavigation());
    }
}
