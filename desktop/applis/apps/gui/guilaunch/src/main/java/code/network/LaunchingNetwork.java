package code.network;

import code.gui.AbsButton;
import code.gui.AdvSoftApplicationCore;
import code.gui.EnabledMenu;
import code.gui.WithAppFactories;
import code.threads.AbstractBaseExecutorService;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    private final AbsButton button;
    private final AbstractBaseExecutorService serv;
    public LaunchingNetwork(WithAppFactories _frames, AbsButton _b, AbstractBaseExecutorService _service) {
        super(_frames);
        button = _b;
        serv = _service;
    }

    @Override
    protected String getApplicationName() {
        return WindowNetWork.APPS_NETWORK;
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        serv.execute(new LaunchNetwork(_language,getFrames(), button,_lgMenu,getAppFactories().getAikiFactory(),getAppFactories().getCardFactories()));
    }

}
