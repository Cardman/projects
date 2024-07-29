package applications.code.player.main;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.gui.*;
import code.player.gui.CreateMainWindowRecorder;

public class LaunchRecord extends AdvSoftApplicationCore {
    public LaunchRecord(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowRecorder(getFrames(), _language, _pair));

    }
}
