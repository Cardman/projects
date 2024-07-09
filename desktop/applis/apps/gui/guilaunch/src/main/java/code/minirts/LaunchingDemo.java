package code.minirts;

import code.gui.*;

public class LaunchingDemo extends AdvSoftApplicationCore {

//    private static final String TEMP_FOLDER = WindowRts.APPS_RTS;

    public LaunchingDemo(WithAppFactories _frames) {
        super(_frames);
    }

//    protected static void loadLaungage(String[] _args, LaunchingDemo _soft) {
//        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
//    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowRts(_language, getFrames(), _main));
    }


}