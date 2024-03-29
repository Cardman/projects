package code.network;

import code.gui.AbsButton;
import code.gui.AdvSoftApplicationCore;
import code.gui.EnabledMenu;
import code.gui.WithAppFactories;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    private final AbsButton button;

    public LaunchingNetwork(WithAppFactories _frames, AbsButton _b) {
        super(_frames);
        button = _b;
    }

    @Override
    protected String getApplicationName() {
        return WindowNetWork.APPS_NETWORK;
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        getFrames().getCompoFactory().invokeNow(new LaunchNetwork(_language,getFrames(), button,_lgMenu,getAppFactories().getAikiFactory(),getAppFactories().getCardFactories()));
    }

}
