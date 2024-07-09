package code.expressionlanguage.guicompos;

import code.gui.*;

public class LaunchingFull extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = WindowFull.APPS_LAUNCHER;

    public LaunchingFull(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowFull(_language,_args.getFileNames(), getAppFactories().getCdmFactory(),getFrames(), _main));
    }

}
