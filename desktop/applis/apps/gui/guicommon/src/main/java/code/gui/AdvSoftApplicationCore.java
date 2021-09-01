package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public abstract class AdvSoftApplicationCore extends SoftApplicationCore {
    public AdvSoftApplicationCore(AbstractProgramInfos _frames) {
        super(_frames);
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
