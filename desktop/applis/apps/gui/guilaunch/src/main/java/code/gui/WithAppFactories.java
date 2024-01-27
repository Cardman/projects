package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public final class WithAppFactories {
    private final AbstractProgramInfos programInfos;
    private final AppFactories appFactories;

    public WithAppFactories(AbstractProgramInfos _p, AppFactories _a) {
        this.programInfos = _p;
        this.appFactories = _a;
    }

    public AbstractProgramInfos getProgramInfos() {
        return programInfos;
    }

    public AppFactories getAppFactories() {
        return appFactories;
    }
}
