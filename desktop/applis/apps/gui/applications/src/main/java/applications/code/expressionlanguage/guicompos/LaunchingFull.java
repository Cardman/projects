package applications.code.expressionlanguage.guicompos;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.expressionlanguage.guicompos.CreateMainWindowFull;
import code.gui.*;

public class LaunchingFull extends AdvSoftApplicationCore {

//    public static final String TEMP_FOLDER = MessagesCdmFullGui.APPS_LAUNCHER;

    public LaunchingFull(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowFull(_args.getFileNames(), getAppFactories().getCdmFactory(),getFrames(), _pair));
    }

}
