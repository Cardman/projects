package applications.code.expressionlanguage.gui.unit;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.expressionlanguage.gui.unit.CreateMainWindowUnit;
import code.gui.*;

public class LaunchingAppUnitTests extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = "UG";

    public LaunchingAppUnitTests(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowUnit(_args.getFileNames(), getAppFactories().getCdmFactory(), getFrames(), _pair));
    }


}
