package code.expressionlanguage.guicompos;

import code.gui.*;
import code.gui.initialize.*;

public class LaunchingFull extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = WindowFull.APPS_LAUNCHER;

    public LaunchingFull(WithAppFactories _infos) {
        super(_infos);
    }
    protected static void loadLaungage(String[] _args, LaunchingFull _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowFull(_language,getFile(_args), getAppFactories().getCdmFactory(),getFrames()), getFrames());
    }

    @Override
    protected String getApplicationName() {
        return WindowFull.APPS_LAUNCHER;
    }

}
