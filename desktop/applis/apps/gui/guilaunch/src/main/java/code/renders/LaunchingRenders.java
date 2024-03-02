package code.renders;

import code.gui.*;
import code.gui.initialize.LoadLanguageUtil;
import code.util.StringList;
import code.util.core.StringUtil;

public class LaunchingRenders extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = WindowRenders.APPS_RENDERS_SITES;

    public LaunchingRenders(WithAppFactories _infos) {
        super(_infos);
    }

    protected static void loadLaungage(String[] _args, LaunchingRenders _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowRenders(_language,getFile(_args), getAppFactories().getCdmFactory(), getFrames()), getFrames());
    }

    protected StringList getFile(String[] _args) {
        StringList files_ = new StringList();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.add(fileName_);
        }
        return files_;
    }

    @Override
    protected String getApplicationName() {
        return WindowRenders.APPS_RENDERS_SITES;
    }

}
