package code.expressionlanguage.gui.unit;

import code.gui.*;
import code.gui.initialize.*;

public class LaunchingAppUnitTests extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "UG";

    public LaunchingAppUnitTests(WithAppFactories _infos) {
        super(_infos);
    }

    protected static void loadLaungage(String[] _args, LaunchingAppUnitTests _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowUnit(_language,_args.getFileNames(), getAppFactories().getCdmFactory(), getFrames(), _main), getFrames());
    }


    @Override
    protected String getApplicationName() {
        return WindowUnit.APPS_UNIT;
    }

}
