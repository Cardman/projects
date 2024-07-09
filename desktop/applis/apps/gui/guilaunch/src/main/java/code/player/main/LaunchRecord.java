package code.player.main;

import code.gui.*;
import code.player.gui.CreateMainWindowRecorder;

public class LaunchRecord extends AdvSoftApplicationCore {
    public LaunchRecord(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowRecorder(getFrames(), _language, _main));

    }
}
