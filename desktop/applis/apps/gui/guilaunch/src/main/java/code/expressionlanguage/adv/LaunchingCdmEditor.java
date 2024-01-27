package code.expressionlanguage.adv;

import code.gui.*;
import code.gui.initialize.LoadLanguageUtil;
import code.stream.StreamFolderFile;

public class LaunchingCdmEditor extends AdvSoftApplicationCore {
    public LaunchingCdmEditor(WithAppFactories _infos) {
        super(_infos);
    }
    protected static void loadLaungage(String[] _args, LaunchingCdmEditor _soft) {
        LoadLanguageUtil.loadLaungage(_soft, WindowCdmEditor.TEMP_FOLDER, _args);
    }
    @Override
    protected String getApplicationName() {
        return WindowCdmEditor.CDM_EDITOR;
    }

    @Override
    protected void launch(String _language, String[] _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateWindowCdm(_language, StreamFolderFile.getFilesNames(getFrames().getFileCoreStream(), _args), getAppFactories().getCdmFactory(),getFrames()), getFrames());
    }
}
