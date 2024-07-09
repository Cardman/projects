package code.expressionlanguage.adv;

import code.gui.*;

public class LaunchingCdmEditor extends AdvSoftApplicationCore {
    public LaunchingCdmEditor(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateWindowCdm(_language, _args.getFileNames(), getAppFactories().getCdmFactory(),getFrames(), _main));
    }
}
