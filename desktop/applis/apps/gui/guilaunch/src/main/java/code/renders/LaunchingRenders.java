package code.renders;

import code.gui.*;

public class LaunchingRenders extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = WindowRenders.APPS_RENDERS_SITES;

    public LaunchingRenders(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowRenders(_language,_args.getFileNames(), getAppFactories().getCdmFactory(), getFrames(), _main));
    }


}
