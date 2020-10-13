package code.expressionlanguage.gui.unit;

import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.gui.initialize.ProgramInfos;
import code.stream.StreamTextFile;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.awt.image.BufferedImage;
import java.io.File;

public class LaunchingAppUnitTests extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "UG";

    public LaunchingAppUnitTests() {
        this(new ProgramInfos());
    }

    public LaunchingAppUnitTests(AbstractProgramInfos _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args) {
        LoadLanguageUtil.loadLaungage(new LaunchingAppUnitTests(), TEMP_FOLDER, _args);
    }
    @Override
    public Object getObject(String _fileName) {
        return StreamTextFile.contentsOfFile(_fileName);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args, getFrames()));
    }


    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }

    public static String getMainWindowClass() {
        return "ug";
    }
    @Override
    protected BufferedImage getImageIcon() {
        return null;
    }

    public static String getTempFolderSl(String _tmpUserFolderSl) {
        return StringUtil.concat(getTempFolder(_tmpUserFolderSl), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder(String _tmpUserFolderSl) {
        new File(StringUtil.concat(_tmpUserFolderSl,TEMP_FOLDER)).mkdirs();
        return StringUtil.concat(_tmpUserFolderSl,TEMP_FOLDER);
    }

}
