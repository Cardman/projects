package code.converterimages.main;

import code.gui.ConstFiles;
import code.gui.LoadLanguage;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.stream.StreamBinaryFile;
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
            if (isPng(StreamBinaryFile.loadFile(_fileName))) {
                return ImageIO.read(new File(_fileName));
            }
            return StreamTextFile.contentsOfFile(_fileName);
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

    public static boolean isPng(byte[] _bytes) {
        return _bytes != null && _bytes.length > 7
                && _bytes[0] == (byte)0x89 && _bytes[1] == (byte)0x50
                && _bytes[2] == (byte)0x4e && _bytes[3] == (byte)0x47
                && _bytes[4] == (byte)0x0d && _bytes[5] == (byte)0x0a
                && _bytes[6] == (byte)0x1a && _bytes[7] == (byte)0x0a;
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
