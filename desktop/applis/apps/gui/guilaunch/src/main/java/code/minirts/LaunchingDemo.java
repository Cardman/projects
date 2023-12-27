package code.minirts;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;

public class LaunchingDemo extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = WindowRts.APPS_RTS;

    public LaunchingDemo(AbstractProgramInfos _frames,AppFactories _app) {
        super(_frames,_app);
    }

    protected static void loadLaungage(String[] _args, LaunchingDemo _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowRts(_language, getFrames()), getFrames());
    }


    @Override
    protected String getApplicationName() {
        return WindowRts.APPS_RTS;
    }

}