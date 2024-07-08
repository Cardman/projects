package code.renders;

import code.gui.*;
import code.gui.initialize.LoadLanguageUtil;

public class LaunchingRenders extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = WindowRenders.APPS_RENDERS_SITES;

    public LaunchingRenders(WithAppFactories _infos) {
        super(_infos);
    }

    protected static void loadLaungage(String[] _args, LaunchingRenders _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowRenders(_language,_args.getFileNames(), getAppFactories().getCdmFactory(), getFrames(), _main), getFrames());
    }


    @Override
    protected String getApplicationName() {
        return WindowRenders.APPS_RENDERS_SITES;
    }

}
