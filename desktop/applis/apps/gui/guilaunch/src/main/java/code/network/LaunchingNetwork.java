package code.network;

import code.gui.AbsButton;
import code.gui.AdvSoftApplicationCore;
import code.gui.EnabledMenu;
import code.gui.WithAppFactories;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    public LaunchingNetwork(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected String getApplicationName() {
        return WindowNetWork.APPS_NETWORK;
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new LaunchNetwork(_language,getFrames(), _main,_lgMenu,getAppFactories().getAikiFactory(),getAppFactories().getCardFactories()));
    }

}
