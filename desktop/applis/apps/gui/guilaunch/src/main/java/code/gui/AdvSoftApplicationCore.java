package code.gui;

public abstract class AdvSoftApplicationCore extends SoftApplicationCore {
    protected AdvSoftApplicationCore(WithAppFactories _frames) {
        super(_frames);
    }

    public void launch(String _lg, AbsButton _main) {
//        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        launch(_lg, new InterpretedFile(getFrames(),new String[0]), null, _main);
    }
    public void launchWithoutLanguage(String _language, InterpretedFile _args, AbsButton _main) {
//        getFrames().getCounts().getVal(getApplicationName()).incrementAndGet();
        LanguageDialogButtons.enable(_main,false);
        launch(_language,_args, null, _main);
    }
}
