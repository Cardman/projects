package code.expressionlanguage.guicompos;

import code.gui.ConstFiles;
import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;

import java.awt.image.BufferedImage;
import java.io.File;

public class LaunchingFull extends SoftApplicationCore {

    private static final String TEMP_FOLDER = "launcher";

    private static int _nbInstances_;

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingFull(), _args, null));
    }

    @Override
    public Object getObject(String _fileName) {
        return null;
    }

    @Override
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        launch(_language, _args);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args));
    }


    public static void increment() {
        _nbInstances_++;
    }

    public static void decrement() {
        _nbInstances_--;
    }

    public static boolean alreadyLaunched() {
        return _nbInstances_ > 0;
    }

    public static String getMainWindowClass() {
        return "launcher";
    }
    @Override
    protected BufferedImage getImageIcon() {
        return null;
    }

    public static String getTempFolderSl() {
        return StringList.concat(getTempFolder(), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }

}
