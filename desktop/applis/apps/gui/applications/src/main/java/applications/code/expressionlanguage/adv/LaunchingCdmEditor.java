package applications.code.expressionlanguage.adv;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import code.expressionlanguage.adv.CreateWindowCdm;
import code.gui.*;

public class LaunchingCdmEditor extends AdvSoftApplicationCore {
    public LaunchingCdmEditor(WithAppFactories _infos) {
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateWindowCdm(_args.getFileNames(), getAppFactories().getCdmFactory(),getFrames(), _pair));
    }
}
