package code.gui;

import code.gui.initialize.AbstractProgramInfos;

public abstract class AdvSoftApplicationCore extends SoftApplicationCore {
    protected AdvSoftApplicationCore(AbstractProgramInfos _frames,AppFactories _app) {
        super(_frames,_app);
    }

    public void launch(String _lg) {
        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        launch(_lg, new String[0]);
    }
    public void launchWithoutLanguage(String _language, String[] _args) {
        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        launch(_language,_args);
    }
    protected abstract String getApplicationName();
}
