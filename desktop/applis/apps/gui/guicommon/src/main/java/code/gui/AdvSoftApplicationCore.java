package code.gui;

import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public abstract class AdvSoftApplicationCore extends SoftApplicationCore {
    public AdvSoftApplicationCore(AbstractProgramInfos _frames) {
        super(_frames);
    }

    public void launch(String _lg) {
        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        launch(_lg, new StringMap<Object>());
    }
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        launch(_language,_args);
    }
    protected abstract String getApplicationName();
}
