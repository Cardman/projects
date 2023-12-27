package code.network;

import code.gui.AbsButton;
import code.gui.AdvSoftApplicationCore;
import code.gui.AppFactories;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.AbstractBaseExecutorService;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    private final AbsButton button;
    private final AbstractBaseExecutorService serv;
    public LaunchingNetwork(AbstractProgramInfos _frames, AbsButton _b, AbstractBaseExecutorService _service, AppFactories _app) {
        super(_frames,_app);
        button = _b;
        serv = _service;
    }

    @Override
    protected String getApplicationName() {
        return WindowNetWork.APPS_NETWORK;
    }

    @Override
    protected void launch(String _language, String[] _args) {
        serv.execute(new LaunchNetwork(_language,getFrames(), button,getAppFactories().getAikiFactory(),getAppFactories().getCardFactories()));
    }

}
