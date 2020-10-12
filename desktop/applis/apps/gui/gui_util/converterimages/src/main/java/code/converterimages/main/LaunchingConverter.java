package code.converterimages.main;

import code.gui.*;
import code.stream.StreamBinaryFile;
import code.stream.StreamImageFile;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringMap;
import code.converterimages.gui.CreateMainWindow;
import code.util.core.StringUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class LaunchingConverter extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "converter";

    private static final AtomicInteger COUNT = new AtomicInteger();

    public LaunchingConverter() {
        this(new CustList<GroupFrame>());
    }
    public LaunchingConverter(CustList<GroupFrame> _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(new LaunchingConverter(), getTempFolder(), _args, null));
    }

    @Override
    public Object getObject(String _fileName) {
        if (isBinary(StreamBinaryFile.loadFile(_fileName))) {
            BufferedImage img_ = StreamImageFile.read(_fileName);
            if (img_ != null) {
                return img_;
            }
        }
        return StreamTextFile.contentsOfFile(_fileName);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args, getFrames()));
    }

    public static boolean isBinary(byte[] _bytes) {
        if (_bytes == null) {
            return false;
        }
        for (byte b: _bytes) {
            if (b < ' ') {
                if (b == '\n') {
                    continue;
                }
                if (b == '\t') {
                    continue;
                }
                if (b == '\r') {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
    public static void increment() {
        COUNT.incrementAndGet();
    }

    public static void decrement() {
        COUNT.decrementAndGet();
    }

    public static boolean alreadyLaunched() {
        return COUNT.get() > 0;
    }

    public static String getMainWindowClass() {
        return "converter";
    }

    @Override
    protected BufferedImage getImageIcon() {
        return null;
    }

    public static String getTempFolderSl() {
        return StringUtil.concat(getTempFolder(), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder() {
        new File(StringUtil.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringUtil.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }
}
