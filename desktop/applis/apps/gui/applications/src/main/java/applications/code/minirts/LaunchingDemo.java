package applications.code.minirts;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.gui.*;
import code.minirts.CreateMainWindowRts;

public class LaunchingDemo extends AdvSoftApplicationCore {

//    private static final String TEMP_FOLDER = WindowRts.APPS_RTS;

    public LaunchingDemo(WithAppFactories _frames) {
        super(_frames);
    }

//    protected static void loadLaungage(String[] _args, LaunchingDemo _soft) {
//        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
//    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowRts(getFrames(), _pair));
    }


}