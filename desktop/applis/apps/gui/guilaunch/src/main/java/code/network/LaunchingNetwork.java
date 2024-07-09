package code.network;

import code.gui.*;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    public LaunchingNetwork(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new LaunchNetwork(_language,getFrames(), _main,_lgMenu,getAppFactories().getAikiFactory(),getAppFactories().getCardFactories()));
    }

}
