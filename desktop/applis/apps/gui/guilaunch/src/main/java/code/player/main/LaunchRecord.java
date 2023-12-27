package code.player.main;

import code.gui.AdvSoftApplicationCore;
import code.gui.ThreadInvoker;
import code.gui.initialize.AbstractProgramInfos;
import code.player.gui.CreateMainWindowRecorder;

public class LaunchRecord extends AdvSoftApplicationCore {
    public LaunchRecord(AbstractProgramInfos _frames) {
        super(_frames);
    }

    @Override
    protected String getApplicationName() {
        return "recorder";
    }

    @Override
    protected void launch(String _language, String[] _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowRecorder(getFrames(), _language), getFrames());

    }
}
