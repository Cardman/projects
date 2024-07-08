package code.player.main;

import code.gui.*;
import code.player.gui.CreateMainWindowRecorder;

public class LaunchRecord extends AdvSoftApplicationCore {
    public LaunchRecord(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected String getApplicationName() {
        return "recorder";
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu, AbsButton _main) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowRecorder(getFrames(), _language, _main), getFrames());

    }
}
