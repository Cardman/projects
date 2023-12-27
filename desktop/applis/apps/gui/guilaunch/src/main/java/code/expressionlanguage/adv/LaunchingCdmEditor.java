package code.expressionlanguage.adv;

import code.gui.AdvSoftApplicationCore;
import code.gui.CdmFactory;
import code.gui.ThreadInvoker;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.stream.StreamFolderFile;

public class LaunchingCdmEditor extends AdvSoftApplicationCore {
    private final CdmFactory cdmFactory;
    public LaunchingCdmEditor(AbstractProgramInfos _infos, CdmFactory _cdm) {
        super(_infos);
        cdmFactory = _cdm;
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
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateWindowCdm(_language, StreamFolderFile.getFilesNames(getFrames().getFileCoreStream(), _args), cdmFactory,getFrames()), getFrames());
    }
}
