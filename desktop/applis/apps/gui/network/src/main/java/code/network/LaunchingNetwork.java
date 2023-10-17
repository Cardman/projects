package code.network;

import code.gui.AbsButton;
import code.gui.AdvSoftApplicationCore;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorService;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    private final AbsButton button;
    private final AbstractBaseExecutorService serv;
    public LaunchingNetwork(AbstractProgramInfos _frames, AbsButton _b, AbstractBaseExecutorService _service) {
        super(_frames);
        button = _b;
        serv = _service;
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }

    @Override
    protected void launch(String _language, String[] _args) {
        serv.execute(new LaunchNetwork(_language,getFrames(), button));
    }

    public static String getMainWindowClass() {
        return "network";
    }
}
