package applications.code.gui;

import code.gui.AbsButton;
import code.gui.InterpretedFile;
import code.gui.LanguageDialogButtons;
import code.gui.LanguagesButtonsPair;

public abstract class AdvSoftApplicationCore extends SoftApplicationCore {
    protected AdvSoftApplicationCore(WithAppFactories _frames) {
        super(_frames);
    }

    public void launch(String _lg, AbsButton _main, LanguagesButtonsPair _pair) {
//        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        launch(_lg, new InterpretedFile(getFrames(),new String[0]), null, _main, _pair);
    }
    public void launchWithoutLanguage(String _language, InterpretedFile _args, AbsButton _main, LanguagesButtonsPair _pair) {
//        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        LanguageDialogButtons.enable(_main,false);
        launch(_language,_args, null, _main, _pair);
    }
}
