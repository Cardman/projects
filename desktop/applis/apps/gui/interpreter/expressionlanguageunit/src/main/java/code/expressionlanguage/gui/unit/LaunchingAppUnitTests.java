package code.expressionlanguage.gui.unit;

import code.gui.*;
import code.gui.initialize.*;
import code.stream.*;
import code.util.*;
import code.util.core.StringUtil;

public class LaunchingAppUnitTests extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "UG";

    private final CdmFactory cdmFactory;
    public LaunchingAppUnitTests(AbstractProgramInfos _infos,CdmFactory _cdm) {
        super(_infos);
        cdmFactory = _cdm;
    }

    protected static void loadLaungage(String[] _args, LaunchingAppUnitTests _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowUnit(_language,getFile(_args), cdmFactory, getFrames()), getFrames());
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
        return getMainWindowClass();
    }

    public static String getMainWindowClass() {
        return "ug";
    }

}
