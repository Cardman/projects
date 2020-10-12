package code.renders;

import code.gui.*;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class LaunchingRenders extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "renders_sites";

    private static final AtomicInteger COUNT = new AtomicInteger();
    protected static void loadLaungage(String[] _args) {
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingRenders(), _args, null));
    }
    @Override
    public Object getObject(String _fileName) {
        return StreamTextFile.contentsOfFile(_fileName);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        ThreadInvoker.invokeNow(new CreateMainWindow(_language,_args, getFrames()));
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
        return "renders_sites";
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
