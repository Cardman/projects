package applications.code.renders;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.gui.*;
import code.renders.CreateMainWindowRenders;
import code.renders.MessagesRenders;

public class LaunchingRenders extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = MessagesRenders.APPS_RENDERS_SITES;

    public LaunchingRenders(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowRenders(_language,_args.getFileNames(), getAppFactories().getCdmFactory(), getFrames(), _pair));
    }


}
