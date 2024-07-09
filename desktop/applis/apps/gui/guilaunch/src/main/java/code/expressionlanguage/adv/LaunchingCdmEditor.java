package code.expressionlanguage.adv;

import code.gui.*;

public class LaunchingCdmEditor extends AdvSoftApplicationCore {
    public LaunchingCdmEditor(WithAppFactories _infos) {
        super(_infos);
    }
//    protected static void loadLaungage(String[] _args, LaunchingCdmEditor _soft) {
//        LoadLanguageUtil.loadLaungage(_soft, WindowCdmEditor.TEMP_FOLDER, _args);
//    }
    @Override
    protected String getApplicationName() {
        return WindowCdmEditor.CDM_EDITOR;
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateWindowCdm(_language, _args.getFileNames(), getAppFactories().getCdmFactory(),getFrames(), _main), getFrames());
    }
}
