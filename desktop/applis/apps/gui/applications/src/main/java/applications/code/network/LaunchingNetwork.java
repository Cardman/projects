package applications.code.network;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.gui.*;
import code.network.LaunchNetwork;

public final class LaunchingNetwork extends AdvSoftApplicationCore {

    public LaunchingNetwork(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new LaunchNetwork(_language,getFrames(), getAppFactories().getAikiFactory(),getAppFactories().getCardFactories(), _pair));
    }

}
