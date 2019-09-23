package code.converterimages.main;

import code.gui.ConstFiles;
import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.converterimages.gui.CreateMainWindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class LaunchingConverter extends SoftApplicationCore {

    private static final String TEMP_FOLDER = "converter";

    private static int _nbInstances_;

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingConverter(), _args, null));
    }

    @Override
    public Object getObject(String _fileName) {
        try {
            return ImageIO.read(new File(_fileName));
        } catch (Exception e) {
            return StreamTextFile.contentsOfFile(_fileName);
        }
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
        return "converter";
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
